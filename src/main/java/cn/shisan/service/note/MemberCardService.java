package cn.shisan.service.note;

import cn.shisan.common.domain.common.PageQuery;
import com.baomidou.mybatisplus.extension.service.IService;
import com.github.pagehelper.PageInfo;
import cn.shisan.dto.member.MemberCardStatusDto;
import cn.shisan.dto.member.MemberCardTransactionDto;
import cn.shisan.dto.member.MemberCardRegisterDto;
import cn.shisan.dto.member.MemberCardUpdateDto;
import cn.shisan.dto.query.MemberCardQueryDto;
import cn.shisan.domain.entity.member.MemberCard;

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
