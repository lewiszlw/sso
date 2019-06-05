package lewiszlw.sso.sdk.handler.impl;

import lewiszlw.sso.sdk.constant.HandleResult;
import lewiszlw.sso.sdk.handler.SsoUriHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Desc: 无需验证，直接放行
 *
 * @author zhanglinwei02
 * @date 2019-05-28
 */
public class FreeAccessSsoUriHandler implements SsoUriHandler {

    @Override
    public HandleResult handle(HttpServletRequest req, HttpServletResponse resp) {
        return HandleResult.FREE_ACCESS;
    }
}
