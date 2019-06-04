package lewiszlw.sso.demo.app1;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Desc:
 *
 * @author zhanglinwei02
 * @date 2019-06-04
 */
@RestController
public class HomeController {

    @RequestMapping("/home")
    public String home() {
        return "home";
    }

}
