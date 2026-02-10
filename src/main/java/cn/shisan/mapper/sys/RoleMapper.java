package cn.shisan.mapper.sys;

import java.util.List;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import cn.shisan.domain.entity.sys.Role;

public interface RoleMapper extends BaseMapper<Role> {

    List<Role> findByUserId(Long userId);

}

