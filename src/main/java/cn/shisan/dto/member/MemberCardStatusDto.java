package com.shisan.note.dto.member;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class MemberCardStatusDto {

    @ApiModelProperty("会员名称")
    private Long id;

    @ApiModelProperty("状态：1-正常，2-冻结，3-注销")
    private Integer status;

    @ApiModelProperty("操作人")
    private String operator;

}
