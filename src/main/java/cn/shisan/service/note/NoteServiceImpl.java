package com.shisan.note.service.note;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shisan.note.domain.common.enums.DelEnum;
import com.shisan.note.convert.NoteConvert;
import com.shisan.note.dto.note.NoteDto;
import com.shisan.note.domain.entity.note.Note;
import com.shisan.note.domain.entity.note.Notebook;
import com.shisan.note.mapper.note.NoteMapper;
import com.shisan.note.utils.AssertUtils;
import com.shisan.note.utils.AuthUtil;
import com.shisan.note.utils.RequestContextUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;


@Service
@RequiredArgsConstructor
public class NoteServiceImpl extends ServiceImpl<NoteMapper, Note> implements NoteService {

    private final NotebookService notebookService;

    @Override
    public void add(NoteDto noteDto) {
        checkData(noteDto);

        Note note = NoteConvert.convert(noteDto);
        note.setUserId(RequestContextUtils.getUserId());
        note.setCreated(LocalDateTime.now());
        int insert = baseMapper.insert(note);
        AssertUtils.isTrue(insert <= 0, "笔记添加失败，请重试!");
    }

    private void checkData(NoteDto noteDto) {
        Notebook notebook = notebookService.findById(noteDto.getId());
        AssertUtils.isNull(notebook, "笔记本不存在!");
    }

    @Override
    public void update(NoteDto noteDto) {
        Note one = baseMapper.selectById(noteDto.getId());
        AssertUtils.isNull(one, "笔记不存在");
        AuthUtil.check(one.getUserId());
        checkData(noteDto);

        Note note = NoteConvert.convert(noteDto);
        note.setModified(LocalDateTime.now());
        int i = baseMapper.updateById(note);
        AssertUtils.isTrue(i <= 0, "笔记修改失败，请重试!");
    }

    @Override
    public void delete(Long id) {
        Note notebook = baseMapper.selectById(id);
        AssertUtils.isNull(notebook, "笔记不存在");
        AuthUtil.check(notebook.getUserId());

        Note del = new Note();
        del.setId(id);
        del.setDeleted(DelEnum.DEL.getCode());
        del.setModified(LocalDateTime.now());
        int updated = baseMapper.updateById(del);
        AssertUtils.isTrue(updated <= 0, "笔记删除失败，请重试!");
    }

    @Override
    public List<Note> findNoteList(Long notebookId) {
        return baseMapper.selectList(Wrappers.<Note>lambdaQuery()
                .eq(Note::getDeleted, DelEnum.NO_DEL.getCode())
                .eq(Note::getNotebookId, notebookId)
                .eq(Note::getUserId, RequestContextUtils.getUserId()));
    }

}
