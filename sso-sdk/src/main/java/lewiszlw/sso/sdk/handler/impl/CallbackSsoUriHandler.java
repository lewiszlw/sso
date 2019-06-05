package lewiszlw.sso.sdk.handler.impl;

import lewiszlw.sso.api.RemoteSsoService;
import lewiszlw.sso.api.dto.AccessTokenDTO;
import lewiszlw.sso.sdk.constant.HandleResult;
import lewiszlw.sso.sdk.handler.SsoUriHandler;
import lewiszlw.sso.sdk.service.RemoteSsoServiceProxy;
import lewiszlw.sso.sdk.util.SsoUtils;
import lewiszlw.sso.sdk.util.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Desc: 回调url处理器
 *
 * @author zhanglinwei02
 * @date 2019-05-29
 */
public class CallbackSsoUriHandler implements SsoUriHandler {

    private static Logger log = LoggerFactory.getLogger(CallbackSsoUriHandler.class);

    private RemoteSsoService remoteSsoService = new RemoteSsoServiceProxy();

    @Override
    public HandleResult handle(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        // 根据code申请access token
        String code = req.getParameter("code");
        if (StringUtils.isEmpty(code)) {
            log.error("回调url code参数丢失，请联系SSO管理员解决！");
            // 重新登录
            return HandleResult.REDIRECT_TO_LOGIN;
        }
        AccessTokenDTO accessTokenDTO = remoteSsoService.getAccessToken(code);
        if (accessTokenDTO == null) {
            log.error("获取access token失败");
            // 重新登录
            return HandleResult.REDIRECT_TO_LOGIN;
        }

        // 设置sso cookie
        WebUtils.setCookie(resp, SsoUtils.genCookieName(), accessTokenDTO.getAccessToken(), accessTokenDTO.getExpiresIn(), false);
        // TODO
        // 转到origin_url
        return HandleResult.REDIRECT_TO_ORIGIN;
    }
}
