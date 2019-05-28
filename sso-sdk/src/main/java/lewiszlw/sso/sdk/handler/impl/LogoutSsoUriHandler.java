package lewiszlw.sso.sdk.handler.impl;

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

    public boolean handle(String uri, HttpServletRequest req, HttpServletResponse resp) {
        return false;
    }
}
