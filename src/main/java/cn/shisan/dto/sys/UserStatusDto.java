package cn.shisan.dto.sys;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class UserStatusDto {

    @ApiModelProperty("主键ID")
    private Long id;

    @ApiModelProperty("用户状态 1=激活，2=禁用")
    private Integer status;

}
