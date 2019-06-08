package lewiszlw.sso.server.service;

import lewiszlw.sso.server.constant.Constants;
import lewiszlw.sso.server.constant.OAuthTokenType;
import lewiszlw.sso.server.model.ValidationResult;
import lewiszlw.sso.server.util.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * Desc:
 *
 * @author zhanglinwei02
 * @date 2019-06-06
 */
@Service
public class SsoService {

    @Autowired
    private OAuthSerivce oAuthSerivce;

    /**
     * 判断是否登录
     * true：已登录，false：未登录
     */
    public ValidationResult validateLogin(HttpServletRequest request) {
        Cookie cookie = WebUtils.getCookie(request, Constants.SSO_LOGIN_ACCESS_TOKEN_COOKIE_NAME);
        // 验证cookie
        if (cookie != null) {
            String accessToken = cookie.getValue();
            return oAuthSerivce.validateToken(accessToken, OAuthTokenType.ACCESS_TOKEN);
        }
        // cookie为空则验证header中是否携带access token
        String accessToken = request.getHeader(Constants.SSO_LOGIN_ACCESS_TOKEN_COOKIE_NAME);
        if (!StringUtils.isEmpty(accessToken)) {
            return oAuthSerivce.validateToken(accessToken, OAuthTokenType.ACCESS_TOKEN);
        }
        return ValidationResult.createFailValidationResult("未登录");
    }
}
