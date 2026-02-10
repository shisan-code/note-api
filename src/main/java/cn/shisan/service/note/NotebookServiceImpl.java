package cn.shisan.service.note;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.shisan.domain.common.enums.DelEnum;
import cn.shisan.dto.note.NotebookDto;
import cn.shisan.domain.entity.note.Notebook;
import cn.shisan.mapper.note.NotebookMapper;
import cn.shisan.utils.AssertUtils;
import cn.shisan.utils.AuthUtil;
import cn.shisan.utils.RequestContextUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;


@Service
@RequiredArgsConstructor
public class NotebookServiceImpl extends ServiceImpl<NotebookMapper, Notebook> implements NotebookService {


    @Override
    public void add(NotebookDto notebookDto) {
        Notebook notebook = new Notebook();
        notebook.setName(notebookDto.getName());
        notebook.setUserId(RequestContextUtils.getUserId());
        notebook.setCreated(LocalDateTime.now());
        int insert = baseMapper.insert(notebook);
        AssertUtils.isTrue(insert <= 0, "笔记本添加失败，请重试!");
    }

    @Override
    public void update(NotebookDto notebookDto) {
        Notebook one = baseMapper.selectById(notebookDto.getId());
        AssertUtils.isNull(one, "笔记本不存在");
        AuthUtil.check(one.getUserId());

        Notebook notebook = new Notebook();
        notebook.setId(notebook.getId());
        notebook.setName(notebookDto.getName());
        notebook.setModified(LocalDateTime.now());
        int i = baseMapper.updateById(notebook);
        AssertUtils.isTrue(i <= 0, "笔记本修改失败，请重试!");
    }

    @Override
    public void delete(Long id) {
        Notebook notebook = baseMapper.selectById(id);
        AssertUtils.isNull(notebook, "笔记本不存在");
        AuthUtil.check(notebook.getUserId());

        Notebook del = new Notebook();
        del.setId(id);
        del.setDeleted(DelEnum.DEL.getCode());
        del.setModified(LocalDateTime.now());
        int updated = baseMapper.updateById(del);
        AssertUtils.isTrue(updated <= 0, "笔记本删除失败，请重试!");
    }

    @Override
    public List<Notebook> getUserNotebook() {
        return baseMapper.selectList(Wrappers.<Notebook>lambdaQuery()
                .eq(Notebook::getDeleted, DelEnum.NO_DEL.getCode())
                .eq(Notebook::getUserId, RequestContextUtils.getUserId()));
    }

    @Override
    public Notebook findById(Long id) {
        Notebook notebook = baseMapper.selectOne(Wrappers.<Notebook>lambdaQuery()
                .eq(Notebook::getDeleted, DelEnum.NO_DEL.getCode())
                .eq(Notebook::getId, id));
        AuthUtil.check(notebook.getUserId());
        return notebook;
    }
}
