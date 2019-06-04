package lewiszlw.sso.server.controller;

import lewiszlw.sso.server.model.req.LoginReq;
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

    /**
     * 登录页面
     */
    @RequestMapping(value = "/login")
    public ModelAndView login(@RequestParam("redirect_uri") String redirectUri) {
        // 验证redirectUri
        // TODO
        ModelAndView modelAndView = new ModelAndView("/login");
        modelAndView.addObject("redirect_uri", redirectUri);
        return modelAndView;
    }

    /**
     * 登录表单提交验证
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ModelAndView login(@ModelAttribute LoginReq req, HttpServletResponse response) {
        // 验证账号密码
        // TODO
        if (StringUtils.isEmpty(req.getRedirectUri())) {
            return new ModelAndView("/login_success");
        } else {
            return new ModelAndView("redirect:" + req.getRedirectUri());
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
