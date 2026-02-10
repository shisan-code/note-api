package com.shisan.note.mapper.sys;

import cn.shisan.common.domain.common.PageQuery;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.shisan.note.dto.sys.UserDto;
import com.shisan.note.dto.query.UserQueryDto;
import com.shisan.note.domain.entity.sys.User;

import java.util.List;


public interface UserMapper extends BaseMapper<User> {


    List<UserDto> list(PageQuery<UserQueryDto> params);
}
