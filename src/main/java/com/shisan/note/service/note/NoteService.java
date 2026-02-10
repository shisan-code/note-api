package com.shisan.note.service.note;

import com.baomidou.mybatisplus.extension.service.IService;
import com.shisan.note.dto.note.NoteDto;
import com.shisan.note.domain.entity.note.Note;

import java.util.List;


/**
 * 笔记本
 * date 2025/6/12
 *
 * @author tuota
 */
public interface NoteService extends IService<Note> {


    /**
     * 笔记本添加
     */
    void add(NoteDto noteDto);

    /**
     * 笔记本修改
     */
    void update(NoteDto noteDto);

    /**
     * 笔记本删除
     */
    void delete(Long id);

    /**
     * 获取笔记本下所有笔记
     */
    List<Note> findNoteList(Long notebookId);


}
