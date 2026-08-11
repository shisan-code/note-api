package cn.shisan.common;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;

@Data
public class PageQuery<T> implements Serializable {

    @Schema(title = "page=当前页数,可空,默认1")
    private int page = 1;

    @Schema(title = "size=每个条数,可空,默认10")
    private int size = 10;

    @Schema(title = "查询参数")
    private T params;
}
