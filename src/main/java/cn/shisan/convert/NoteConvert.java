package cn.shisan.convert;

import cn.shisan.dto.note.NoteDto;
import cn.shisan.domain.entity.note.Note;

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
