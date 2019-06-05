package lewiszlw.sso.demo.app1.config;

import com.google.common.collect.Lists;
import lewiszlw.sso.sdk.filter.OAuthSsoFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.DelegatingFilterProxy;

import javax.servlet.DispatcherType;
import java.util.EnumSet;

/**
 * Desc:
 *
 * @author zhanglinwei02
 * @date 2019-06-04
 */
@Configuration
public class SsoFilterConfiguration {

    @Bean
    public FilterRegistrationBean ssoFilter() {
        DelegatingFilterProxy filterProxy = new DelegatingFilterProxy();
        filterProxy.setTargetBeanName("oAuthSsoFilterBean");
        filterProxy.setTargetFilterLifecycle(true);

        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(filterProxy);
        filterRegistrationBean.setUrlPatterns(Lists.newArrayList("/*"));
        filterRegistrationBean.setDispatcherTypes(EnumSet.of(DispatcherType.REQUEST));
        filterRegistrationBean.setName("ssoFilter");
        filterRegistrationBean.setOrder(1);
        filterRegistrationBean.setEnabled(false);
        return filterRegistrationBean;
    }

    @Bean(name = "oAuthSsoFilterBean")
    public OAuthSsoFilter oAuthSsoFilter() {
        OAuthSsoFilter oAuthSsoFilter = new OAuthSsoFilter();
        oAuthSsoFilter.setClientId("39932b59-c9f1-461d-af98-b287bd8d4798");
        oAuthSsoFilter.setClientSecret("3c1d49c0-11ea-4579-9e38-d462d42af4d5");
        oAuthSsoFilter.setAppSimpleName("app1");
        return oAuthSsoFilter;
    }

}
