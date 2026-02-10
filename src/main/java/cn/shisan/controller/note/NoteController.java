package com.shisan.note.controller.note;

import cn.shisan.common.domain.common.JResult;
import com.shisan.note.controller.BaseController;
import com.shisan.note.dto.note.NoteDto;
import com.shisan.note.domain.entity.note.Note;
import com.shisan.note.service.note.NoteService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Api(tags = "笔记管理")
@RequestMapping("/api/note")
@RestController
@RequiredArgsConstructor
public class NoteController extends BaseController {

    private final NoteService noteService;

    @ApiOperation("笔记添加")
    @PostMapping("/add")
    public JResult<Void> add(@RequestBody NoteDto noteDto) {
        noteService.add(noteDto);
        return success();
    }

    @ApiOperation("笔记修改")
    @PostMapping("/update")
    public JResult<Void> update(@RequestBody NoteDto noteDto) {
        noteService.update(noteDto);
        return success();
    }

    @ApiOperation("笔记删除")
    @PostMapping("/delete/{id}")
    public JResult<Void> delete(@PathVariable Long id) {
        noteService.delete(id);
        return success();
    }

    @ApiOperation("笔记本下所有笔记")
    @PostMapping("/findNoteList/{notebookId}")
    public JResult<List<Note>> findNoteList(@PathVariable Long notebookId) {
        List<Note> noteList = noteService.findNoteList(notebookId);
        return success(noteList);
    }
}
