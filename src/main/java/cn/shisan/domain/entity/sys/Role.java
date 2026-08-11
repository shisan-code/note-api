package cn.shisan.domain.entity.sys;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 角色表
 */
@Data
@TableName("tbl_role")
public class Role {

	@TableId(value = "id", type = IdType.AUTO)
	@Schema(title = "角色ID")
	private Long id;

	@Schema(title = "角色名称")
	private String name;

	@Schema(title = "角色类型：0-超级管理员，1-普通角色")
	private Integer type;

	@Schema(title = "角色描述")
	private String remark;

	@Schema(title = "创建时间")
	private LocalDateTime created;

	@Schema(title = "修改时间")
	private LocalDateTime modified;

	@Schema(title = "是否删除 0否 1是")
	private Integer deleted;
	
}