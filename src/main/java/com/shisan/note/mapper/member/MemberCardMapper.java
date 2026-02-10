package com.shisan.note.mapper.member;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.shisan.note.domain.entity.member.MemberCard;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;

public interface MemberCardMapper extends BaseMapper<MemberCard> {

    int updateRemainingAmount(@Param("id") Long id,
                              @Param("remainingAmount") BigDecimal remainingAmount,
                              @Param("version") Integer version);
}
