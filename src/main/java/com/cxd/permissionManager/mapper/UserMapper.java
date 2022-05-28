package com.cxd.permissionManager.mapper;

import com.cxd.permissionManager.entity.User;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author cxd
 * @since 2018-01-13
 */
public interface UserMapper extends BaseMapper<User> {
    @Select("selectUserByUserNo")
    User selectUserByUserNo(String userNo);
}
