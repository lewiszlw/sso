package lewiszlw.sso.server.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
     * 登录
     */
    @RequestMapping("/login")
    public String login() {
        return "login";
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
