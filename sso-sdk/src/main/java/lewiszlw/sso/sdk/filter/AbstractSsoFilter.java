package lewiszlw.sso.sdk.filter;

import lewiszlw.sso.sdk.config.SsoConfiguration;
import lewiszlw.sso.sdk.constant.HandleResult;
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
        // 分发SsoUriHandler来处理
        SsoUriHandler ssoUriHandler = dispatch(request, response);
        HandleResult handleResult = null;
        try {
            handleResult = ssoUriHandler.handle(request, response);
        } catch (Exception e) {
            // TODO
            e.printStackTrace();
        }
        switch (handleResult) {
            case AUTHORIZED:
                // 已验证通过，转给业务处理
                filterChain.doFilter(servletRequest, servletResponse);
                break;
            case UNAUTHORIZED:
                break;
        }
        // 在 servlet 请求结束前,清空 UserUtils 中的登录用户缓存
        // TODO
    }

    protected abstract SsoUriHandler dispatch(HttpServletRequest request, HttpServletResponse response);

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
