package lewiszlw.sso.sdk.constant;

/**
 * Desc:
 *
 * @author zhanglinwei02
 * @date 2019-05-28
 */
public enum HandleResult {
    /**已验证*/
    AUTHORIZED,

    /**免登陆*/
    FREE_ACCESS,

    /**转到登录页面*/
    REDIRECT_TO_LOGIN,

    /**转到原始用户页面*/
    REDIRECT_TO_ORIGIN,

}
