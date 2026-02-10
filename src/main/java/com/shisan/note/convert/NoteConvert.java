package com.shisan.note.convert;

import com.shisan.note.dto.note.NoteDto;
import com.shisan.note.domain.entity.note.Note;

public class NoteConvert {


    /**
     * 构建-》Note
     *
     */
    public static Note convert(NoteDto noteDto) {
        Note note = new Note();
        note.setId(noteDto.getId());
        note.setNotebookId(noteDto.getNotebookId());
        note.setTitle(noteDto.getTitle());
        note.setContent(noteDto.getContent());
        return note;
    }
}
