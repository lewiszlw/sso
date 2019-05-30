package lewiszlw.sso.sdk.handler;

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
     * @return false: 处理器内部处理，true: 放行
     */
    boolean handle(HttpServletRequest req, HttpServletResponse resp);
}
