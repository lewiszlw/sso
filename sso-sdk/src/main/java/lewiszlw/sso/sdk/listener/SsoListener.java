package lewiszlw.sso.sdk.listener;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Desc:
 *
 * @author zhanglinwei02
 * @date 2019-05-29
 */
public interface SsoListener {

    /**
     * 监听登出（登出后调用）
     */
    void onLogout(HttpServletRequest request, HttpServletResponse response);

    /**
     * 监听登入（登入后调用）
     */
    void onLoginSuccess(HttpServletRequest request, HttpServletResponse response);

    /**
     * 监听登录失败
     */
    void onLoginFail(HttpServletRequest request, HttpServletResponse response);
}
