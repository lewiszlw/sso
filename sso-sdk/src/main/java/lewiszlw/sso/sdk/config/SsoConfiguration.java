package lewiszlw.sso.sdk.config;

import lewiszlw.sso.sdk.listener.SsoListener;

/**
 * Desc:
 * sso 配置，单例
 *
 * @author zhanglinwei02
 * @date 2019-05-29
 */
public class SsoConfiguration {

    private String clientId;
    private String clientSecret;

    private String includeUriList = "/*";
    private String excludeUriList;

    private SsoListener ssoListener;

    private String logoutUri = "/sso/logout";

    private static final SsoConfiguration instance = new SsoConfiguration();
    public static SsoConfiguration getInstance() {
        return instance;
    }

    private SsoConfiguration() {

    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getClientSecret() {
        return clientSecret;
    }

    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
    }

    public String getIncludeUriList() {
        return includeUriList;
    }

    public void setIncludeUriList(String includeUriList) {
        this.includeUriList = includeUriList;
    }

    public String getExcludeUriList() {
        return excludeUriList;
    }

    public void setExcludeUriList(String excludeUriList) {
        this.excludeUriList = excludeUriList;
    }

    public SsoListener getSsoListener() {
        return ssoListener;
    }

    public void setSsoListener(SsoListener ssoListener) {
        this.ssoListener = ssoListener;
    }

    public String getLogoutUri() {
        return logoutUri;
    }

    public void setLogoutUri(String logoutUri) {
        this.logoutUri = logoutUri;
    }
}
