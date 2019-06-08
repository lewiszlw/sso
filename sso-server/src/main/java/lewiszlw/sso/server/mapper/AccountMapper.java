package lewiszlw.sso.server.mapper;

import lewiszlw.sso.server.entity.AccountEntity;
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
public interface AccountMapper {

    AccountEntity selectByUsernameAndPassword(@Param("username") String username,
                                              @Param("password") String password);
}
