package cn.shisan.mapper.sys;

import cn.shisan.common.domain.common.PageQuery;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import cn.shisan.dto.sys.UserDto;
import cn.shisan.dto.query.UserQueryDto;
import cn.shisan.domain.entity.sys.User;

import java.util.List;


public interface UserMapper extends BaseMapper<User> {


    List<UserDto> list(PageQuery<UserQueryDto> params);
}
