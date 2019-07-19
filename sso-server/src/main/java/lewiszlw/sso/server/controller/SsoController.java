package lewiszlw.sso.server.controller;

import lewiszlw.sso.server.constant.Constants;
import lewiszlw.sso.server.entity.AccountEntity;
import lewiszlw.sso.server.entity.OAuthTokenEntity;
import lewiszlw.sso.server.model.ValidationResult;
import lewiszlw.sso.server.model.req.LoginReq;
import lewiszlw.sso.server.service.AccountService;
import lewiszlw.sso.server.service.OAuthSerivce;
import lewiszlw.sso.server.service.SsoService;
import lewiszlw.sso.server.util.WebUtils;
import lewiszlw.sso.server.validator.SsoValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Desc:
 *
 * @author zhanglinwei02
 * @date 2019-05-23
 */
@RestController
@RequestMapping("/sso")
@Slf4j
public class SsoController {

    @Autowired
    private OAuthSerivce oauthSerivce;

    @Autowired
    private SsoService ssoService;

    @Autowired
    private SsoValidator ssoValidator;

    @Autowired
    private AccountService accountService;

    /**
     * 登录页面
     */
    @RequestMapping(value = "/login")
    public ModelAndView login(@RequestParam(value = "redirect_uri", required = false) String redirectUri,
                              @RequestParam(value = "client_id", required = false) String clientId,
                              HttpServletRequest request) {
        // 验证redirectUri
        if (!StringUtils.isEmpty(redirectUri) && !WebUtils.isHttpUri(redirectUri)) {
            return new ModelAndView("/error").addObject("error", "redirect_uri不合法");
        }

        // 验证clientId
        if (!StringUtils.isEmpty(clientId) && !ssoValidator.validateClientId(clientId).isPass()) {
            return new ModelAndView("/error").addObject("error", "clientId 不存在");
        }

        // 判断是否已登录
        ValidationResult validationResult = ssoService.validateLogin(request);
        if (validationResult.isPass()) {
            // TODO 判断是否从授权页面跳转过来，是则不带code
            // 授权页面redirectUri不加host

            // 已登录，直接生成code进行跳转
            OAuthTokenEntity oAuthTokenEntity = (OAuthTokenEntity) validationResult.getData();
            String code = oauthSerivce.genCode(clientId, oAuthTokenEntity.getUserId());
            // 拼接code到redirect_uri
            redirectUri = WebUtils.joinCodeToRedirectUri(redirectUri, code);
            return new ModelAndView("redirect:" + redirectUri);
        } else {
            // 未登录，展示登录页面让用户登录
            return new ModelAndView("/login")
                    .addObject("redirectUri", redirectUri)
                    .addObject("clientId", clientId);
        }
    }

    /**
     * 登录表单提交验证
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ModelAndView login(@ModelAttribute LoginReq req, HttpServletResponse response) {
        // 验证账号密码
        ValidationResult validationResult = accountService.validateAccount(req.getUsername(), req.getPassword());
        if (!validationResult.isPass()) {
            return new ModelAndView("/error").addObject("error", validationResult.getMessage());
        }
        AccountEntity accountEntity = (AccountEntity) validationResult.getData();
        Integer userId = accountEntity.getUserId();

        // 登录成功，种下cookie: access token
        String accessToken = oauthSerivce.applyAccessTokenForLoginSuccess(userId);
        WebUtils.setCookie(response, Constants.SSO_LOGIN_ACCESS_TOKEN_COOKIE_NAME, accessToken, Constants.ACCESS_TOKEN_VALID_TIME, false);

        if (StringUtils.isEmpty(req.getClientId()) || StringUtils.isEmpty(req.getRedirectUri())) {
            // 如果没有redirectUri和clientId，则不进行跳转，直接显示登录成功页面
            return new ModelAndView("/login_success");
        } else {
            // 生成code并进行跳转
            String code = oauthSerivce.genCode(req.getClientId(), userId);
            String redirectUri = req.getRedirectUri();
            redirectUri = WebUtils.joinCodeToRedirectUri(redirectUri, code);
            return new ModelAndView("redirect:" + redirectUri);
        }
    }

    /**
     * 登出
     */
    @RequestMapping("/logout")
    public String logout() {
        return "logout";
    }

    /**
     * 注册
     */
    @RequestMapping("/signup")
    public String signup() {
        return "signup";
    }
}
