package lewiszlw.sso.server.controller;

import lewiszlw.sso.server.model.ValidationResult;
import lewiszlw.sso.server.model.WebResponse;
import lewiszlw.sso.server.model.req.AccessTokenReq;
import lewiszlw.sso.server.model.req.RegisterAppReq;
import lewiszlw.sso.server.validator.OauthValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Desc: OAuth2.0 授权码模式
 *
 * @author zhanglinwei02
 * @date 2019-05-23
 */
@RestController
@RequestMapping("/oauth")
public class OauthController {

    @Autowired
    private OauthValidator oauthValidator;

    /**
     * 请求授权
     * @param clientId 客户端id
     * @param redirectUri 跳转uri
     * @param scope 权限范围
     * @param state 状态，可以是随机值或者时间戳，会原封不动返回客户端
     */
    @RequestMapping("/authorize")
    public void authorize(@RequestParam("clientId") String clientId,
                                 @RequestParam("redirect_uri") String redirectUri,
                                 @RequestParam("scope") String scope,
                                 @RequestParam("state") String state,
                                 HttpServletRequest request,
                                 HttpServletResponse response) throws IOException {
//        ValidationResult validationResult = oauthValidator.validateAuthorize(clientId, redirectUri, scope, state);
//        if (!validationResult.isPass()) {
//            response.setStatus(400);
//        }
        String code = "code";
        // 302 Found
        response.sendRedirect(redirectUri + "?code=" + code);
    }

    /**
     * 获取令牌
     */
    @RequestMapping(value = "/access_token", method = RequestMethod.POST)
    public String accessToken(@RequestBody AccessTokenReq req) {
        return "";
    }

    /**
     * 更新令牌
     */
    @RequestMapping(value = "/refresh_token", method = RequestMethod.POST)
    public String refreshToken() {
        return "";
    }


    /**
     * 注册一个新的OAuth 应用
     */
    @RequestMapping(value = "/register_app", method = RequestMethod.POST)
    public String registerApp(@RequestBody RegisterAppReq req) {
        return "";
    }
}
