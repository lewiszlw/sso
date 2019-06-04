package lewiszlw.sso.server.mapper;

import lewiszlw.sso.server.entity.OAuthAppEntity;
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
public interface OAuthAppMapper {

    Integer insertOne(@Param("oauthAppEntity") OAuthAppEntity oauthAppEntity);

    OAuthAppEntity selectByClientId(@Param("clientId") String clientId);
}
