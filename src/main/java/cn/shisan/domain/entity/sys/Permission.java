package cn.shisan.domain.entity.sys;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 权限表
 */
@TableName("tbl_permission")
@Data
public class Permission {

	@TableId(value = "id", type = IdType.AUTO)
	@Schema(title = "权限ID")
	private Long id;

	@Schema(title = "父ID")
	private Long parentId;

	@Schema(title = "权限名称")
	private String name;

	@Schema(title = "url 地址")
	private String url;

	@Schema(title = "类型 1菜单 2api")
	private Integer type;

	@Schema(title = "状态 1启用 2停用")
	private Integer status;

	@Schema(title = "备注")
	private String remark;

	@Schema(title = "排序")
	private Integer sort;

	@Schema(title = "创建时间")
	private LocalDateTime created;

	@Schema(title = "修改时间")
	private LocalDateTime modified;

	@Schema(title = "是否删除 0否 1是")
	private Integer deleted;
	
}