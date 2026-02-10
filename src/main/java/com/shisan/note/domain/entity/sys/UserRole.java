package com.shisan.note.domain.entity.sys;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 用户角色关联表
 */
@Data
@TableName("tbl_user_role")
public class UserRole {

	@TableId(value = "id", type = IdType.AUTO)
	@ApiModelProperty("ID")
	private Long id;
	
	@ApiModelProperty("用户ID")
	private Long userId;
	
	@ApiModelProperty("角色ID")
	private Long roleId;
	
	@ApiModelProperty("创建时间")
	private LocalDateTime created;
	
	@ApiModelProperty("修改时间")
	private LocalDateTime modified;
	
	@ApiModelProperty("是否删除 0否 1是")
	private Integer deleted;
	
}