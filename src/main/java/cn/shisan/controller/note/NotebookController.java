package cn.shisan.controller.note;

import cn.shisan.common.domain.common.JResult;
import cn.shisan.controller.BaseController;
import cn.shisan.dto.note.NotebookDto;
import cn.shisan.domain.entity.note.Notebook;
import cn.shisan.service.note.NotebookService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Api(tags = "笔记本管理")
@RequestMapping("/api/notebook")
@RestController
@RequiredArgsConstructor
public class NotebookController extends BaseController {

    private final NotebookService notebookService;

    @ApiOperation("笔记本添加")
    @PostMapping("/add")
    public JResult<Void> add(@RequestBody NotebookDto notebookDto) {
        notebookService.add(notebookDto);
        return success();
    }

    @ApiOperation("笔记本修改")
    @PostMapping("/update")
    public JResult<Void> update(@RequestBody NotebookDto updateDto) {
        notebookService.update(updateDto);
        return success();
    }

    @ApiOperation("笔记本删除")
    @PostMapping("/delete/{id}")
    public JResult<Void> delete(@PathVariable Long id) {
        notebookService.delete(id);
        return success();
    }

    @ApiOperation("获取用户笔记本")
    @PostMapping("/getUserNotebook")
    public JResult<List<Notebook>> getUserNotebook() {
        List<Notebook> notebook = notebookService.getUserNotebook();
        return success(notebook);
    }
}
