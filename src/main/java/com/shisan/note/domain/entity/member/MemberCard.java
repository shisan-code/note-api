package com.shisan.note.domain.entity.member;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@TableName("tbl_member_card")
@Data
public class MemberCard {

    @TableId(value = "id", type = IdType.AUTO)
    @ApiModelProperty("权限ID")
    private Long id;

    @ApiModelProperty("账户")
    private String account;

    @ApiModelProperty("会员名称")
    private String name;

    @ApiModelProperty("会员手机号")
    private String phone;

    @ApiModelProperty("性别：0-女，1-男，2-未知")
    private Integer gender;

    @ApiModelProperty("会员生日")
    private LocalDate birthday;

    @ApiModelProperty("余额")
    private BigDecimal remainingAmount;

    @ApiModelProperty("会员等级")
    private Integer level;

    @ApiModelProperty("状态：1-正常，2-冻结，3-注销")
    private Integer status;

    @ApiModelProperty("版本号")
    private Integer version;

    @ApiModelProperty("操作人")
    private String operator;

    @ApiModelProperty("创建时间")
    private LocalDateTime created;

    @ApiModelProperty("修改时间")
    private LocalDateTime modified;

    @ApiModelProperty("是否删除 0否 1是")
    private Integer deleted;
}
