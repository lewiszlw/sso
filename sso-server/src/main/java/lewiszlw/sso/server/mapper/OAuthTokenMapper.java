package lewiszlw.sso.server.mapper;

import lewiszlw.sso.server.constant.OAuthTokenType;
import lewiszlw.sso.server.entity.OAuthTokenEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * Desc:
 *
 * @author zhanglinwei02
 * @date 2019-06-03
 */
@Mapper
@Repository
public interface OAuthTokenMapper {

    Integer insertOne(@Param("oauthTokenEntity")OAuthTokenEntity oauthTokenEntity);

    OAuthTokenEntity selectByTokenAndType(@Param("token") String token,
                                          @Param("type")OAuthTokenType type);
    Integer updateStatusInvalid(@Param("token") String token);
}
