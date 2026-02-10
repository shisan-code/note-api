package cn.shisan.controller.sys;

import cn.shisan.common.domain.common.JResult;
import cn.shisan.common.domain.common.PageQuery;
import com.github.pagehelper.PageInfo;
import cn.shisan.controller.BaseController;
import cn.shisan.dto.member.MemberCardRegisterDto;
import cn.shisan.dto.member.MemberCardStatusDto;
import cn.shisan.dto.member.MemberCardTransactionDto;
import cn.shisan.dto.member.MemberCardUpdateDto;
import cn.shisan.dto.query.MemberCardQueryDto;
import cn.shisan.domain.entity.member.MemberCard;
import cn.shisan.service.note.MemberCardService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


@Api(tags = "会员卡管理")
@RequestMapping("/api/member")
@RestController
@RequiredArgsConstructor
public class MemberCardController extends BaseController {

    private final MemberCardService memberCardService;

    @ApiOperation("会员注册")
    @PostMapping("/register")
    public JResult<Void> register(@RequestBody MemberCardRegisterDto registerDto) {
        memberCardService.register(registerDto);
        return success();
    }

    @ApiOperation("会员修改")
    @PostMapping("/update")
    public JResult<Void> update(@RequestBody MemberCardUpdateDto updateDto) {
        memberCardService.update(updateDto);
        return success();
    }

    @ApiOperation("会员充值")
    @PostMapping("/recharge")
    public JResult<Void> recharge(@RequestBody MemberCardTransactionDto transactionDto) {
        memberCardService.recharge(transactionDto);
        return success();
    }

    @ApiOperation("会员消费")
    @PostMapping("/consume")
    public JResult<Void> consume(@RequestBody MemberCardTransactionDto transactionDto) {
        memberCardService.consume(transactionDto);
        return success();
    }

    @ApiOperation("会员状态变更")
    @PostMapping("/updateStatus")
    public JResult<Void> updateStatus(@RequestBody MemberCardStatusDto statusDto) {
        memberCardService.updateStatus(statusDto);
        return success();
    }

    @ApiOperation("会员列表")
    @PostMapping("/page")
    public JResult<PageInfo<MemberCard>> pageList(@RequestBody PageQuery<MemberCardQueryDto> query) {
        PageInfo<MemberCard> pageInfo = memberCardService.pageList(query);
        return success(pageInfo);
    }


}
