package com.shisan.note.dto.query;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
@Data
public class MemberCardQueryDto {

    @ApiModelProperty("会员名称")
    private String name;

    @ApiModelProperty("会员手机号")
    private String phone;

    @ApiModelProperty("状态：1-正常，2-冻结，3-注销")
    private Integer status;

    @ApiModelProperty("操作人")
    private String operator;


}
