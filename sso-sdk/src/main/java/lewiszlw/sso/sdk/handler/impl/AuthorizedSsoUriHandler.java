package lewiszlw.sso.sdk.handler.impl;

import lewiszlw.sso.sdk.constant.HandleResult;
import lewiszlw.sso.sdk.handler.SsoUriHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Desc: 已验证
 *
 * @author zhanglinwei02
 * @date 2019-06-05
 */
public class AuthorizedSsoUriHandler implements SsoUriHandler {

    @Override
    public HandleResult handle(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        return HandleResult.AUTHORIZED;
    }
}
