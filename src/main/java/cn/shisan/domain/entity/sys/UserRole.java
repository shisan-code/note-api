package cn.shisan.domain.entity.sys;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 用户角色关联表
 */
@Data
@TableName("tbl_user_role")
public class UserRole {

	@TableId(value = "id", type = IdType.AUTO)
	@Schema(title = "ID")
	private Long id;
	
	@Schema(title = "用户ID")
	private Long userId;
	
	@Schema(title = "角色ID")
	private Long roleId;
	
	@Schema(title = "创建时间")
	private LocalDateTime created;
	
	@Schema(title = "修改时间")
	private LocalDateTime modified;
	
	@Schema(title = "是否删除 0否 1是")
	private Integer deleted;
	
}