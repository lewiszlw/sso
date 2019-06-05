package lewiszlw.sso.sdk.constant;

/**
 * Desc:
 *
 * @author zhanglinwei02
 * @date 2019-05-29
 */
public class Constants {

    public static final String RESERVE_ROBOT_URI = "/sso/robot";
    public static final String RESERVE_CALLBACK_URI = "/sso/callback";
    public static final String RESERVE_LOGOUT_URI = "/sso/logout";

    public static final String SSO_LOGIN_URI = "http://127.0.0.1:8080/sso/login";
    public static final String SSO_OAUTH_ACCESSTOKEN_URL = "http://127.0.0.1:8080/oauth/access_token";

    /**
     * sso远程服务实现方式
     */
    public static final String DUBBO_SERVICE_IMPL = "dubbo";
    public static final String HTTP_SERVICE_IMPL = "http";

    /**
     * http请求超时设置
     */
    public static final int SOCKET_TIMEOUT = 5 * 1000;
    public static final int CONNECT_TIMEOUT = 2 * 1000;

}
