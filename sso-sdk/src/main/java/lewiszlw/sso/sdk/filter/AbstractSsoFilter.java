package lewiszlw.sso.sdk.filter;

import lewiszlw.sso.sdk.config.SsoConfiguration;
import lewiszlw.sso.sdk.handler.SsoUriHandler;
import lewiszlw.sso.sdk.listener.SsoListener;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Desc:
 * sso filter 抽象父类
 *
 * @author zhanglinwei02
 * @date 2019-05-28
 */
public abstract class AbstractSsoFilter implements Filter {

    private SsoConfiguration ssoConfiguration = SsoConfiguration.getInstance();

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        SsoUriHandler ssoUriHandler = dispatch(request, response);
        if (ssoUriHandler.handle(request, response)) {
            filterChain.doFilter(servletRequest, servletResponse);
        }
        // TODO 解绑user

    }

    public abstract SsoUriHandler dispatch(HttpServletRequest request, HttpServletResponse response);

    public void init(FilterConfig filterConfig) throws ServletException {}

    public void destroy() {}

    /** ==============setter===============*/
    public void setClientId(String clientId) {
        ssoConfiguration.setClientId(clientId);
    }

    public void setClientSecret(String clientSecret) {
        ssoConfiguration.setClientSecret(clientSecret);
    }

    public void setIncludeUriList(String includeUriList) {
        ssoConfiguration.setIncludeUriList(includeUriList);
    }

    public void setExcludeUriList(String excludeUriList) {
        ssoConfiguration.setExcludeUriList(excludeUriList);
    }

    public void setSsoListener(SsoListener ssoListener) {
        ssoConfiguration.setSsoListener(ssoListener);
    }
}
