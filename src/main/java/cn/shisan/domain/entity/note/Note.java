package com.shisan.note.domain.entity.note;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

@TableName("tbl_note")
@Data
public class Note {

    @TableId(value = "id", type = IdType.AUTO)
    @ApiModelProperty("id")
    private Long id;

    @ApiModelProperty("笔记本id")
    private Long notebookId;

    @ApiModelProperty("用户id")
    private Long userId;

    @ApiModelProperty("标题名称")
    private String title;

    @ApiModelProperty("笔记内容")
    private String content;

    @ApiModelProperty("创建时间")
    private LocalDateTime created;

    @ApiModelProperty("修改时间")
    private LocalDateTime modified;

    @ApiModelProperty("是否删除 0否 1是")
    private Integer deleted;
}
