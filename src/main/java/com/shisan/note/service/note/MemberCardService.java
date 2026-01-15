package com.shisan.note.service.note;

import cn.shisan.common.domain.common.PageQuery;
import com.baomidou.mybatisplus.extension.service.IService;
import com.github.pagehelper.PageInfo;
import com.shisan.note.dto.member.MemberCardStatusDto;
import com.shisan.note.dto.member.MemberCardTransactionDto;
import com.shisan.note.dto.member.MemberCardRegisterDto;
import com.shisan.note.dto.member.MemberCardUpdateDto;
import com.shisan.note.dto.query.MemberCardQueryDto;
import com.shisan.note.entity.member.MemberCard;

public interface MemberCardService extends IService<MemberCard> {

    /**
     * 会员卡注册
     */
    void register(MemberCardRegisterDto registerDto);

    /**
     * 会员卡修改
     */
    void update(MemberCardUpdateDto updateDto);

    /**
     * 会员卡充值
     */
    void recharge(MemberCardTransactionDto rechargeDto);

    /**
     * 会员卡消费
     */
    void consume(MemberCardTransactionDto rechargeDto);

    /**
     * 会员卡状态变更
     */
    void updateStatus(MemberCardStatusDto statusDto);

    /**
     * 会员卡列表
     */
    PageInfo<MemberCard> pageList(PageQuery<MemberCardQueryDto> query);
}
