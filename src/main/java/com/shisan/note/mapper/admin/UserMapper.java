package com.shisan.note.mapper.admin;

import cn.shisan.common.domain.common.PageQuery;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.shisan.note.dto.admin.UserDto;
import com.shisan.note.dto.query.UserQueryDto;
import com.shisan.note.entity.admin.User;

import java.util.List;


public interface UserMapper extends BaseMapper<User> {


    List<UserDto> list(PageQuery<UserQueryDto> params);
}
