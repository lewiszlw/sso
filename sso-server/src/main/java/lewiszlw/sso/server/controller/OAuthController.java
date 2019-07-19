package lewiszlw.sso.server.controller;

import lewiszlw.sso.server.entity.OAuthTokenEntity;
import lewiszlw.sso.server.model.ValidationResult;
import lewiszlw.sso.server.model.WebResponse;
import lewiszlw.sso.server.model.req.AccessTokenReq;
import lewiszlw.sso.server.model.req.RegisterAppReq;
import lewiszlw.sso.server.service.OAuthAppService;
import lewiszlw.sso.server.service.OAuthSerivce;
import lewiszlw.sso.server.service.SsoService;
import lewiszlw.sso.server.util.WebUtils;
import lewiszlw.sso.server.validator.OAuthValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

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
public class OAuthController {

    @Autowired
    private OAuthValidator oauthValidator;

    @Autowired
    private OAuthAppService oauthAppService;

    @Autowired
    private OAuthSerivce oauthSerivce;

    @Autowired
    private SsoService ssoService;

    /**
     * 授权页面
     *
     * @param clientId 客户端id
     * @param redirectUri 跳转uri
     * @param scope 权限范围
     * @param state 状态，可以是随机值或者时间戳，会原封不动返回客户端
     */
    @RequestMapping("/authorize")
    public ModelAndView authorize(@RequestParam("client_id") String clientId,
                                  @RequestParam("redirect_uri") String redirectUri,
                                  @RequestParam(value = "scope", required = false) String scope,
                                  @RequestParam(value = "state", required = false) String state,
                                  HttpServletRequest request,
                                  HttpServletResponse response) throws IOException {
        // 验证clientId存在，redirectUri合法
        ValidationResult paramValidationResult = oauthValidator.validateAuthorize(clientId, redirectUri, scope, state);
        if (!paramValidationResult.isPass()) {
            response.setStatus(400);
            return new ModelAndView("/error").addObject("error", "参数不合法，请检查请求参数");
        }
        // 判断是否已登录
        ValidationResult loginValidationResult = ssoService.validateLogin(request);
        if (loginValidationResult.isPass()) {
            // 303 跳转
            OAuthTokenEntity oAuthTokenEntity = (OAuthTokenEntity) loginValidationResult.getData();
            String code = oauthSerivce.genCode(clientId, oAuthTokenEntity.getUserId());
            redirectUri = WebUtils.genOAuthRedirectUri(redirectUri, code, state);
            return new ModelAndView("redirect:" + redirectUri);
        } else {
            // 未登录
            return new ModelAndView("/login")
                    .addObject("clientId", clientId)
                    .addObject("redirectUri", request.getRequestURL());
        }
    }

    /**
     * 获取令牌
     */
    @RequestMapping(value = "/access_token", method = RequestMethod.POST)
    public WebResponse accessToken(@RequestBody AccessTokenReq req) {
        // 验证clientId、clientSecret, 验证code是否过期
        ValidationResult validationResult = oauthValidator.validateAccessToken(req);
        if (!validationResult.isPass()) {
            return validationResult.transformWebResponse(WebResponse.STATUS_BAD_REQUEST);
        }
        return WebResponse.createOKWebResponse(oauthSerivce.applyAccessToken(req));
    }

    /**
     * 更新令牌
     */
    @RequestMapping(value = "/refresh_token", method = RequestMethod.POST)
    public WebResponse refreshToken() {

        return null;
    }


    /**
     * 注册一个新的OAuth 应用
     */
    @RequestMapping(value = "/register_app", method = RequestMethod.POST)
    public WebResponse registerApp(@RequestBody RegisterAppReq req) {
        ValidationResult validationResult = oauthValidator.validateRegisterApp(req);
        if (!validationResult.isPass()) {
            return validationResult.transformWebResponse(WebResponse.STATUS_BAD_REQUEST);
        }
        return WebResponse.createOKWebResponse(oauthAppService.registerApp(req));
    }
}
