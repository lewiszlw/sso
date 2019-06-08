package lewiszlw.sso.server.mapper;

import lewiszlw.sso.server.entity.UserEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * Desc:
 *
 * @author zhanglinwei02
 * @date 2019-06-06
 */
@Mapper
@Repository
public interface UserMapper {

    UserEntity selectById(@Param("id") Integer id);
}
