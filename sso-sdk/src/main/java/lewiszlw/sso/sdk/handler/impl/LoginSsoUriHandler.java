package lewiszlw.sso.sdk.handler.impl;

import lewiszlw.sso.sdk.constant.HandleResult;
import lewiszlw.sso.sdk.handler.SsoUriHandler;
import lewiszlw.sso.sdk.util.WebUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Desc:
 *
 * @author zhanglinwei02
 * @date 2019-06-04
 */
public class LoginSsoUriHandler implements SsoUriHandler {

    public HandleResult handle(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        // 跳转到sso 登录页面
        resp.sendRedirect(WebUtils.genLoginUrL(req));
        return HandleResult.UNAUTHORIZED;
    }
}
