package lewiszlw.sso.sdk.handler.impl;

import lewiszlw.sso.sdk.constant.HandleResult;
import lewiszlw.sso.sdk.handler.SsoUriHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Desc: 未验证，需登录
 *
 * @author zhanglinwei02
 * @date 2019-06-04
 */
public class LoginSsoUriHandler implements SsoUriHandler {

    @Override
    public HandleResult handle(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        // 跳转到sso 登录页面
        return HandleResult.REDIRECT_TO_LOGIN;
    }
}
