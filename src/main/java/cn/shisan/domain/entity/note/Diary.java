package cn.shisan.domain.entity.note;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * <p>
 * 日记，每日一记
 * </p>
 *
 * @author lijing
 * @since 2025-06-19
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("tbl_diary")
@ApiModel(value="Diary对象", description="日记，每日一记")
public class Diary implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键（日记）id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "用户id")
    private Long userId;

    @ApiModelProperty(value = "名称")
    private String name;

    @ApiModelProperty(value = "小记内容")
    private String content;

    @ApiModelProperty(value = "日期")
    private LocalDate date;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime created;

    @ApiModelProperty(value = "修改时间")
    private LocalDateTime modified;

    @ApiModelProperty(value = "是否删除 0否 1是")
    private Integer deleted;


}
