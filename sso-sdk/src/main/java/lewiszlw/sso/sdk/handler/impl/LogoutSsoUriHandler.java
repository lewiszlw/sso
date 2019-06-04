package lewiszlw.sso.sdk.handler.impl;

import lewiszlw.sso.sdk.constant.HandleResult;
import lewiszlw.sso.sdk.handler.SsoUriHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Desc:
 *
 * @author zhanglinwei02
 * @date 2019-05-28
 */
public class LogoutSsoUriHandler implements SsoUriHandler {

    public HandleResult handle(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        // 清除sso cookie
        // 303转到sso登录页面
        // TODO
        return HandleResult.UNAUTHORIZED;
    }
}
