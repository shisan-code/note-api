package cn.shisan.domain.entity.sys;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 角色权限关联表
 */
@Data
@TableName("tbl_role_permission")
public class RolePermission {

	@TableId(value = "id", type = IdType.AUTO)
	@ApiModelProperty("关联ID")
	private Long id;
	
	@ApiModelProperty("角色ID")
	private Long roleId;
	
	@ApiModelProperty("权限ID")
	private Long permissionId;
	
	@ApiModelProperty("创建时间")
	private LocalDateTime created;
	
	@ApiModelProperty("修改时间")
	private LocalDateTime modified;
	
	@ApiModelProperty("是否删除 0否 1是")
	private Integer deleted;
	
}