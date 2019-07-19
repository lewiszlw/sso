package lewiszlw.sso.sdk.filter;

import lewiszlw.sso.sdk.config.SsoConfiguration;
import lewiszlw.sso.sdk.constant.HandleResult;
import lewiszlw.sso.sdk.handler.SsoUriHandler;
import lewiszlw.sso.sdk.listener.SsoListener;
import lewiszlw.sso.sdk.util.UserUtils;
import lewiszlw.sso.sdk.util.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;

/**
 * Desc:
 * sso filter 抽象父类
 *
 * @author zhanglinwei02
 * @date 2019-05-28
 */
public abstract class AbstractSsoFilter implements Filter {

    private static Logger log = LoggerFactory.getLogger(AbstractSsoFilter.class);

    private SsoConfiguration ssoConfiguration = SsoConfiguration.getInstance();

    {
        // 暂时只支持http
        ssoConfiguration.setServiceImpl("http");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        // 分发SsoUriHandler来处理
        SsoUriHandler ssoUriHandler = dispatch(request, response);
        HandleResult handleResult = null;
        try {
            handleResult = ssoUriHandler.handle(request, response);
            switch (handleResult) {
                case FREE_ACCESS:
                case AUTHORIZED:
                    // 已验证通过/无需验证，转给业务处理
                    filterChain.doFilter(servletRequest, servletResponse);
                    break;
                case REDIRECT_TO_LOGIN:
                    response.sendRedirect(WebUtils.genLoginUrL(request));
                    break;
                case REDIRECT_TO_ORIGIN:
                    // 转到用户访问界面
                    response.sendRedirect(request.getParameter("origin_url"));
                    break;
            }
        } catch (Exception e) {
            log.error("sso doFilter处理异常", e);
        }
        // 在 servlet 请求结束前,清空 UserUtils 中的登录用户缓存
        UserUtils.unbindUser();
    }

    protected abstract SsoUriHandler dispatch(HttpServletRequest request, HttpServletResponse response);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {}

    @Override
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

    public void setAppSimpleName(String appSimpleName) {
        ssoConfiguration.setAppSimpleName(appSimpleName);
    }
}
