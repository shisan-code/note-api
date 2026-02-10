package com.shisan.note.domain.entity.note;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

@TableName("tbl_notebook")
@Data
public class Notebook {

    @TableId(value = "id", type = IdType.AUTO)
    @ApiModelProperty("id")
    private Long id;

    @ApiModelProperty("笔记本名称")
    private String name;

    @ApiModelProperty("用户id")
    private Long userId;

    @ApiModelProperty("创建时间")
    private LocalDateTime created;

    @ApiModelProperty("修改时间")
    private LocalDateTime modified;

    @ApiModelProperty("是否删除 0否 1是")
    private Integer deleted;
}
