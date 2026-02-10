package com.shisan.note.dto.member;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class MemberCardTransactionDto {

    @ApiModelProperty("主键id")
    private Long id;

    @ApiModelProperty("金额")
    private BigDecimal amount;

    @ApiModelProperty("备注")
    private String remark;

    @ApiModelProperty("操作人")
    private String operator;


}
