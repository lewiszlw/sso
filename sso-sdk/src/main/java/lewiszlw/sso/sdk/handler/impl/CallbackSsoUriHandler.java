package lewiszlw.sso.sdk.handler.impl;

import lewiszlw.sso.sdk.constant.HandleResult;
import lewiszlw.sso.sdk.handler.SsoUriHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Desc:
 *
 * @author zhanglinwei02
 * @date 2019-05-29
 */
public class CallbackSsoUriHandler implements SsoUriHandler {

    public HandleResult handle(HttpServletRequest req, HttpServletResponse resp) {
        // 根据code申请access token
        String code = req.getParameter("code");

        req.getQueryString();
        // 设置sso cookie
        // TODO
        return HandleResult.UNAUTHORIZED;
    }
}
