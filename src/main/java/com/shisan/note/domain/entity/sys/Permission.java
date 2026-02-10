package com.shisan.note.domain.entity.sys;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 权限表
 */
@TableName("tbl_permission")
@Data
public class Permission {

	@TableId(value = "id", type = IdType.AUTO)
	@ApiModelProperty("权限ID")
	private Long id;

	@ApiModelProperty("父ID")
	private Long parentId;

	@ApiModelProperty("权限名称")
	private String name;

	@ApiModelProperty("url 地址")
	private String url;

	@ApiModelProperty("类型 1菜单 2api")
	private Integer type;

	@ApiModelProperty("状态 1启用 2停用")
	private Integer status;

	@ApiModelProperty("备注")
	private String remark;

	@ApiModelProperty("排序")
	private Integer sort;

	@ApiModelProperty("创建时间")
	private LocalDateTime created;

	@ApiModelProperty("修改时间")
	private LocalDateTime modified;

	@ApiModelProperty("是否删除 0否 1是")
	private Integer deleted;
	
}