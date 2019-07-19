package lewiszlw.sso.sdk.util;

import lewiszlw.sso.api.RemoteSsoService;
import lewiszlw.sso.api.dto.UserDTO;
import lewiszlw.sso.sdk.constant.Constants;
import lewiszlw.sso.sdk.service.RemoteSsoServiceProxy;
import org.springframework.util.StringUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * Desc:
 *
 * @author zhanglinwei02
 * @date 2019-06-04
 */
public class UserUtils {

    private static RemoteSsoService remoteSsoService = new RemoteSsoServiceProxy();

    /**
     * 给客户端用于获取登录用户基本信息
     */
    public static UserDTO getUser(HttpServletRequest request) {
        Cookie cookie = WebUtils.getCookie(request, SsoUtils.genCookieName());
        if (cookie != null) {
            String accessToken = cookie.getValue();
            return remoteSsoService.getUser(accessToken);
        }
        // cookie为空则验证header中是否携带access token
        String accessTokenFromHeader = request.getHeader(Constants.ACCESS_TOKEN_HEADER_NAME);
        if (!StringUtils.isEmpty(accessTokenFromHeader)) {
            return remoteSsoService.getUser(accessTokenFromHeader);
        }
        return null;
    }
}
