package com.shisan.note.service.note;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shisan.note.domain.common.enums.DelEnum;
import com.shisan.note.dto.note.DiaryDto;
import com.shisan.note.domain.entity.note.Diary;
import com.shisan.note.mapper.note.DiaryMapper;
import com.shisan.note.utils.AssertUtils;
import com.shisan.note.utils.AuthUtil;
import com.shisan.note.utils.RequestContextUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;


/**
 * <p>
 * 日记，每日一记 服务实现类
 * </p>
 *
 * @author lijing
 * @since 2025-06-19
 */
@Service
public class DiaryServiceImpl extends ServiceImpl<DiaryMapper, Diary> implements DiaryService {

    @Override
    public void add(DiaryDto diaryDto) {
        Diary diary = new Diary();
        diary.setUserId(RequestContextUtils.getUserId());
        diary.setName(diaryDto.getName());
        diary.setContent(diaryDto.getContent());
        diary.setDate(LocalDate.now());
        diary.setCreated(LocalDateTime.now());
        int insert = baseMapper.insert(diary);
        AssertUtils.isTrue(insert <= 0, "添加失败，请重试!");
    }

    @Override
    public void update(DiaryDto diaryDto) {
        Diary one = baseMapper.selectById(diaryDto.getId());
        AssertUtils.isNull(one, "记录不存在");
        AuthUtil.check(one.getUserId());

        Diary diary = new Diary();
        diary.setId(diary.getId());
        diary.setName(diaryDto.getName());
        diary.setContent(diaryDto.getContent());
        diary.setModified(LocalDateTime.now());
        int update = baseMapper.updateById(diary);
        AssertUtils.isTrue(update <= 0, "修改失败，请重试!");
    }

    @Override
    public void delete(Long id) {
        Diary one = baseMapper.selectById(id);
        AssertUtils.isNull(one, "记录不存在");
        AuthUtil.check(one.getUserId());

        Diary del = new Diary();
        del.setId(id);
        del.setDeleted(DelEnum.DEL.getCode());
        del.setModified(LocalDateTime.now());
        int updated = baseMapper.updateById(del);
        AssertUtils.isTrue(updated <= 0, "笔记本删除失败，请重试!");
    }

    @Override
    public Diary findById(Long id) {
        Diary diary = baseMapper.selectOne(Wrappers.<Diary>lambdaQuery()
                .eq(Diary::getDeleted, DelEnum.NO_DEL.getCode())
                .eq(Diary::getId, id));
        AuthUtil.check(diary.getUserId());
        return diary;
    }
}
