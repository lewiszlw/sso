package lewiszlw.sso.sdk.handler;

import lewiszlw.sso.sdk.constant.HandleResult;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Desc:
 *
 * @author zhanglinwei02
 * @date 2019-05-28
 */
public interface SsoUriHandler {
    /**
     * uri处理器
     */
    HandleResult handle(HttpServletRequest req, HttpServletResponse resp) throws Exception;
}
