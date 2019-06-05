package lewiszlw.sso.server.controller;

import lewiszlw.sso.server.model.req.LoginReq;
import lewiszlw.sso.server.service.OAuthSerivce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;

/**
 * Desc:
 *
 * @author zhanglinwei02
 * @date 2019-05-23
 */
@RestController
@RequestMapping("/sso")
public class SsoController {

    @Autowired
    private OAuthSerivce oauthSerivce;

    /**
     * 登录页面
     */
    @RequestMapping(value = "/login")
    public ModelAndView login(@RequestParam(value = "redirect_uri", required = false) String redirectUri,
                              @RequestParam(value = "client_id", required = false) String clientId) {
        // 验证redirectUri
        // TODO
        ModelAndView modelAndView = new ModelAndView("/login");
        modelAndView.addObject("redirectUri", redirectUri);
        modelAndView.addObject("clientId", clientId);
        return modelAndView;
    }

    /**
     * 登录表单提交验证
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ModelAndView login(@ModelAttribute LoginReq req, HttpServletResponse response) {
        // 验证账号密码
        // TODO
        if (StringUtils.isEmpty(req.getClientId()) || StringUtils.isEmpty(req.getRedirectUri())) {
            return new ModelAndView("/login_success");
        } else {
            String code = oauthSerivce.genCode(req.getClientId());
            String redirectUri = req.getRedirectUri();
            // TODO 判断是否加?code
            redirectUri += "&code=" + code;
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
