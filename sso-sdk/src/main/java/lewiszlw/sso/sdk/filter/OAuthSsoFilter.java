package lewiszlw.sso.sdk.filter;

import lewiszlw.sso.sdk.constant.Constants;
import lewiszlw.sso.sdk.handler.SsoUriHandler;
import lewiszlw.sso.sdk.handler.impl.AllowSsoUriHandler;
import lewiszlw.sso.sdk.handler.impl.CallbackSsoUriHandler;
import lewiszlw.sso.sdk.handler.impl.LoginSsoUriHandler;
import lewiszlw.sso.sdk.handler.impl.LogoutSsoUriHandler;
import lewiszlw.sso.sdk.util.SsoUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Desc:
 *
 * @author zhanglinwei02
 * @date 2019-05-29
 */
public class OAuthSsoFilter extends AbstractSsoFilter {

    @Override
    protected SsoUriHandler dispatch(HttpServletRequest request, HttpServletResponse response) {
        String uri = request.getRequestURI();
        if ("options".equalsIgnoreCase(request.getMethod())) {
            return new AllowSsoUriHandler();
        }
        if (uri.contains(Constants.RESERVE_CALLBACK_URI)) {
            return new CallbackSsoUriHandler();
        }
        if (uri.contains(Constants.RESERVE_LOGOUT_URI)) {
            return new LogoutSsoUriHandler();
        }
        // 判断url是否免登陆
        if (SsoUtils.isUrlFreeAccess(uri, request.getContextPath())) {
            return new AllowSsoUriHandler();
        }
        // 验证登录状态
        if (validateLogin()) {
            return new AllowSsoUriHandler();
        } else {
            return new LoginSsoUriHandler();
        }
    }

    /**
     * 验证登录状态
     */
    private boolean validateLogin() {
        // TODO
        return false;
    }

}
