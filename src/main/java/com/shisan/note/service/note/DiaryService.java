package com.shisan.note.service.note;

import com.baomidou.mybatisplus.extension.service.IService;
import com.shisan.note.dto.note.DiaryDto;
import com.shisan.note.domain.entity.note.Diary;

/**
 * <p>
 * 日记，每日一记 服务类
 * </p>
 *
 * @author lijing
 * @since 2025-06-19
 */
public interface DiaryService extends IService<Diary> {


    void add(DiaryDto diaryDto);


    void update(DiaryDto diaryDto);

    /**
     * 删除
     */
    void delete(Long id);

    /**
     * 查询笔
     */
    Diary findById(Long id);
}
