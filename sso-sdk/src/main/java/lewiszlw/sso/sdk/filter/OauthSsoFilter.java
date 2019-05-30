package lewiszlw.sso.sdk.filter;

import lewiszlw.sso.sdk.handler.SsoUriHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Desc:
 *
 * @author zhanglinwei02
 * @date 2019-05-29
 */
public class OauthSsoFilter extends AbstractSsoFilter {

    @Override
    public SsoUriHandler dispatch(HttpServletRequest request, HttpServletResponse response) {
        return null;
    }
}
