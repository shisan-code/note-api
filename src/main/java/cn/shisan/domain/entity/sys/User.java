package cn.shisan.domain.entity.sys;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName("tbl_user")
public class User {

    @TableId(value = "id", type = IdType.AUTO)
    @Schema(title = "主键ID")
    private Long id;

    @Schema(title = "用户名称")
    private String name;

    @Schema(title = "用户名")
    private String userName;

    @Schema(title = "密码")
    private String password;

    @Schema(title = "手机号码")
    private String phone;

    @Schema(title = "邮箱")
    private String email;

    @Schema(title = "用户状态 1=激活，2=禁用")
    private Integer status;

    @Schema(title = "个性签名")
    private String signature;

    @Schema(title = "创建时间")
    private LocalDateTime created;

    @Schema(title = "修改时间")
    private LocalDateTime modified;

    @Schema(title = "是否删除 0否 1是")
    private Integer deleted;
}
