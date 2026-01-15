package com.shisan.note.service.note;

import cn.shisan.common.domain.common.PageQuery;
import cn.shisan.common.exception.BusinessException;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.shisan.note.common.enums.MemberCardEnums;
import com.shisan.note.dto.member.MemberCardRegisterDto;
import com.shisan.note.dto.member.MemberCardStatusDto;
import com.shisan.note.dto.member.MemberCardTransactionDto;
import com.shisan.note.dto.member.MemberCardUpdateDto;
import com.shisan.note.dto.query.MemberCardQueryDto;
import com.shisan.note.entity.member.MemberCard;
import com.shisan.note.entity.member.MemberTransactionRecord;
import com.shisan.note.mapper.member.MemberCardMapper;
import com.shisan.note.mapper.member.MemberTransactionRecordMapper;
import com.shisan.note.service.note.MemberCardService;
import com.shisan.note.utils.AssertUtils;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class MemberCardServiceImpl extends ServiceImpl<MemberCardMapper, MemberCard> implements MemberCardService {

    private final MemberTransactionRecordMapper memberTransactionRecordMapper;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void register(MemberCardRegisterDto registerDto) {
        AssertUtils.isBlank(registerDto.getName(), "会员名称不能为空");
        AssertUtils.isBlank(registerDto.getPhone(), "会员手机号不能为空");
        AssertUtils.isNull(registerDto.getRemainingAmount(), "会员注册开卡金额不能为空");

        MemberCard memberCard = new MemberCard();
        memberCard.setAccount(registerDto.getPhone());
        memberCard.setName(registerDto.getName());
        memberCard.setPhone(registerDto.getPhone());
        memberCard.setGender(registerDto.getGender());
        memberCard.setBirthday(registerDto.getBirthday());
        memberCard.setRemainingAmount(registerDto.getRemainingAmount());
        memberCard.setStatus(MemberCardEnums.Status.ACTIVATE.getCode());
        memberCard.setOperator(registerDto.getOperator());
        memberCard.setCreated(LocalDateTime.now());
        int insert = baseMapper.insert(memberCard);
        if (insert <= 0) {
            throw new BusinessException("会员卡添加失败，请重试!");
        }

        MemberTransactionRecord transactionRecord = new MemberTransactionRecord();
        transactionRecord.setMemberCardId(memberCard.getId());
        transactionRecord.setTransactionType(MemberCardEnums.TransactionType.RECHARGE.getCode());
        transactionRecord.setAmount(registerDto.getRemainingAmount());
        transactionRecord.setRemainingAmount(registerDto.getRemainingAmount());
        transactionRecord.setRemark("注册开卡");
        transactionRecord.setOperator(registerDto.getOperator());
        transactionRecord.setCreated(LocalDateTime.now());
        int tr = memberTransactionRecordMapper.insert(transactionRecord);
        if (tr <= 0) {
            throw new BusinessException("会员卡添加失败，请重试!");
        }
    }

    @Override
    public void update(MemberCardUpdateDto updateDto) {
        MemberCard card = baseMapper.selectById(updateDto.getId());
        AssertUtils.isNull(card, "会员不存在");

        MemberCard memberCard = new MemberCard();
        memberCard.setId(updateDto.getId());
        memberCard.setAccount(updateDto.getPhone());
        memberCard.setName(updateDto.getName());
        memberCard.setPhone(updateDto.getPhone());
        memberCard.setGender(updateDto.getGender());
        memberCard.setBirthday(updateDto.getBirthday());
        memberCard.setOperator(updateDto.getOperator());
        memberCard.setModified(LocalDateTime.now());
        int insert = baseMapper.updateById(memberCard);
        if (insert <= 0) {
            throw new BusinessException("会员卡修改失败，请重试!");
        }
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void recharge(MemberCardTransactionDto rechargeDto) {
        AssertUtils.isNull(rechargeDto.getAmount(), "金额不能为空");
        MemberCard memberCard = baseMapper.selectById(rechargeDto.getId());
        AssertUtils.isNull(memberCard, "会员不存在");
        if (!Objects.equals(memberCard.getStatus(), MemberCardEnums.Status.ACTIVATE.getCode())) {
            throw new BusinessException("会员卡已冻结或注销不可充值!");
        }
        BigDecimal remainingAmount = memberCard.getRemainingAmount();
        BigDecimal sum = remainingAmount.add(rechargeDto.getAmount());
        int updated = baseMapper.updateRemainingAmount(memberCard.getId(), sum, memberCard.getVersion());
        if (updated <= 0) {
            throw new BusinessException("会员卡充值失败，请重试!");
        }

        // 交易记录
        transaction(rechargeDto, MemberCardEnums.TransactionType.RECHARGE, sum);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void consume(MemberCardTransactionDto transactionDto) {
        AssertUtils.isNull(transactionDto.getAmount(), "金额不能为空");
        MemberCard memberCard = baseMapper.selectById(transactionDto.getId());
        AssertUtils.isNull(memberCard, "会员不存在");
        if (!Objects.equals(memberCard.getStatus(), MemberCardEnums.Status.ACTIVATE.getCode())) {
            throw new BusinessException("会员卡已冻结或注销不可消费!");
        }
        BigDecimal remainingAmount = memberCard.getRemainingAmount();
        BigDecimal subtract = remainingAmount.subtract(transactionDto.getAmount());
        int updated = baseMapper.updateRemainingAmount(memberCard.getId(), subtract, memberCard.getVersion());
        if (updated <= 0) {
            throw new BusinessException("会员卡消费失败，请重试!");
        }

        // 交易记录
        transaction(transactionDto, MemberCardEnums.TransactionType.CONSUME, subtract);
    }

    @Override
    public void updateStatus(MemberCardStatusDto statusDto) {
        AssertUtils.isNull(statusDto.getStatus(), "状态不能为空");
        MemberCard memberCard = baseMapper.selectById(statusDto.getId());
        AssertUtils.isNull(memberCard, "会员不存在");

        MemberCard card = new MemberCard();
        card.setStatus(statusDto.getStatus());
        card.setModified(LocalDateTime.now());
        card.setOperator(statusDto.getOperator());
        int updated = baseMapper.updateById(card);
        if (updated <= 0) {
            throw new BusinessException("会员卡状态变更失败，请重试!");
        }
    }

    @Override
    public PageInfo<MemberCard> pageList(PageQuery<MemberCardQueryDto> query) {
        MemberCardQueryDto params = query.getParams();
        QueryWrapper<MemberCard> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(MemberCard::getDeleted, 0);
        if (StringUtils.isNotBlank(params.getPhone())) {
            wrapper.lambda().eq(MemberCard::getPhone, params.getPhone());
        }
        if (StringUtils.isNotBlank(params.getName())) {
            wrapper.lambda().like(MemberCard::getName, params.getName());
        }
        if (StringUtils.isNotBlank(params.getOperator())) {
            wrapper.lambda().eq(MemberCard::getOperator, params.getOperator());
        }
        if (null != params.getStatus()) {
            wrapper.lambda().eq(MemberCard::getStatus, params.getStatus());
        }
        wrapper.lambda().orderByDesc(MemberCard::getId);
        PageHelper.startPage(query.getPage(), query.getSize());
        List<MemberCard> cards = baseMapper.selectList(wrapper);
        return new PageInfo<>(cards);
    }

    /**
     * 交易记录
     */
    private void transaction(MemberCardTransactionDto rechargeDto,
                             MemberCardEnums.TransactionType transactionType,
                             BigDecimal remainingAmount) {
        MemberTransactionRecord transactionRecord = new MemberTransactionRecord();
        transactionRecord.setMemberCardId(rechargeDto.getId());
        transactionRecord.setTransactionType(transactionType.getCode());
        transactionRecord.setAmount(rechargeDto.getAmount());
        transactionRecord.setRemainingAmount(remainingAmount);
        transactionRecord.setRemark(rechargeDto.getRemark());
        transactionRecord.setOperator(rechargeDto.getOperator());
        transactionRecord.setCreated(LocalDateTime.now());
        int tr = memberTransactionRecordMapper.insert(transactionRecord);
        if (tr <= 0) {
            throw new BusinessException("会员交易失败，请重试!");
        }
    }
}
