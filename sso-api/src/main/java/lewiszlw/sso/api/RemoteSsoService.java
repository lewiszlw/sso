package lewiszlw.sso.api;

import lewiszlw.sso.api.dto.AccessTokenDTO;
import lewiszlw.sso.api.dto.UserDTO;

/**
 * Desc:
 *
 * @author zhanglinwei02
 * @date 2019-05-31
 */
public interface RemoteSsoService {

    AccessTokenDTO getAccessToken(String code);

    AccessTokenDTO refreshAccessToken(String refreshToken);

    UserDTO getUser(String accessToken);
}
