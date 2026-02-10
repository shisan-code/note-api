package com.shisan.note.dto.member;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDate;

@Data
public class MemberCardUpdateDto {

    @ApiModelProperty("会员名称")
    private Long id;

    @ApiModelProperty("会员名称")
    private String name;

    @ApiModelProperty("会员手机号")
    private String phone;

    @ApiModelProperty("性别：0-女，1-男，2-未知")
    private Integer gender;

    @ApiModelProperty("会员生日")
    private LocalDate birthday;

    @ApiModelProperty("操作人")
    private String operator;

}
