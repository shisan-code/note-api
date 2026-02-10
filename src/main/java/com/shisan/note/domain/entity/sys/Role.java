package com.shisan.note.domain.entity.sys;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 角色表
 */
@Data
@TableName("tbl_role")
public class Role {

	@TableId(value = "id", type = IdType.AUTO)
	@ApiModelProperty("角色ID")
	private Long id;

	@ApiModelProperty("角色名称")
	private String name;

	@ApiModelProperty("角色类型：0-超级管理员，1-普通角色")
	private Integer type;

	@ApiModelProperty("角色描述")
	private String remark;

	@ApiModelProperty("创建时间")
	private LocalDateTime created;

	@ApiModelProperty("修改时间")
	private LocalDateTime modified;

	@ApiModelProperty("是否删除 0否 1是")
	private Integer deleted;
	
}