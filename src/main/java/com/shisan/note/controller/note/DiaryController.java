package com.shisan.note.controller.note;

import cn.shisan.common.domain.common.JResult;
import com.shisan.note.controller.BaseController;
import com.shisan.note.dto.note.DiaryDto;
import com.shisan.note.domain.entity.note.Diary;
import com.shisan.note.service.note.DiaryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;



@Api(tags = "日记管理")
@RequestMapping("/api/diary")
@RestController
@RequiredArgsConstructor
public class DiaryController extends BaseController {

    private final DiaryService diaryService;

    @ApiOperation("添加")
    @PostMapping("/add")
    public JResult<Void> add(@RequestBody DiaryDto diaryDto) {
        diaryService.add(diaryDto);
        return success();
    }

    @ApiOperation("修改")
    @PostMapping("/update")
    public JResult<Void> update(@RequestBody DiaryDto diaryDto) {
        diaryService.update(diaryDto);
        return success();
    }

    @ApiOperation("删除")
    @PostMapping("/delete/{id}")
    public JResult<Void> delete(@PathVariable Long id) {
        diaryService.delete(id);
        return success();
    }

    @ApiOperation("查询")
    @PostMapping("/find/{id}")
    public JResult<Diary> findNoteList(@PathVariable Long id) {
        Diary diary = diaryService.findById(id);
        return success(diary);
    }
}
