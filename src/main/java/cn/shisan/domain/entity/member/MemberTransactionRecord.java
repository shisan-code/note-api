package com.shisan.note.domain.entity.member;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@TableName("tbl_member_transaction_record")
@Data
public class MemberTransactionRecord {

    @TableId(value = "id", type = IdType.AUTO)
    @ApiModelProperty("主键")
    private Long id;

    @ApiModelProperty("会员卡id")
    private Long memberCardId;

    @ApiModelProperty("交易类型：1-充值，2-消费")
    private Integer transactionType;

    @ApiModelProperty("交易金额")
    private BigDecimal amount;

    @ApiModelProperty("余额（交易后余额）")
    private BigDecimal remainingAmount;

    @ApiModelProperty("备注")
    private String remark;

    @ApiModelProperty("操作人")
    private String operator;

    @ApiModelProperty("创建时间")
    private LocalDateTime created;

    @ApiModelProperty("修改时间")
    private LocalDateTime modified;

    @ApiModelProperty("是否删除 0否 1是")
    private Integer deleted;
}
