package lewiszlw.sso.server.controller;

import lewiszlw.sso.server.constant.Constants;
import lewiszlw.sso.server.constant.OAuthTokenType;
import lewiszlw.sso.server.entity.OAuthTokenEntity;
import lewiszlw.sso.server.model.ValidationResult;
import lewiszlw.sso.server.model.WebResponse;
import lewiszlw.sso.server.service.OAuthSerivce;
import lewiszlw.sso.server.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * Desc:
 *
 * @author zhanglinwei02
 * @date 2019-06-04
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private OAuthSerivce oAuthSerivce;

    @Autowired
    private UserService userService;

    @RequestMapping("/access-token")
    public WebResponse getUser(@RequestParam(value = "access_token", required = false) String accessToken,
                               HttpServletRequest request) {
        String accessTokenFromReq = null;
        if (!StringUtils.isEmpty(accessToken)) {
            // 首先看查询参数是否有access_token
            accessTokenFromReq = accessToken;
        } else if (!StringUtils.isEmpty(request.getHeader(Constants.ACCESS_TOKEN_HEADER_NAME))) {
            // 查询参数没有，则判定request header是否携带access_token
            accessTokenFromReq = request.getHeader(Constants.ACCESS_TOKEN_HEADER_NAME);
        }

        if (StringUtils.isEmpty(accessTokenFromReq)) {
            return WebResponse.createWebResponse(WebResponse.STATUS_UNAUTHORIZED, "access token 缺失");
        }

        ValidationResult validationResult = oAuthSerivce.validateToken(accessTokenFromReq, OAuthTokenType.ACCESS_TOKEN);
        if (validationResult.isPass()) {
            OAuthTokenEntity oAuthTokenEntity = (OAuthTokenEntity) validationResult.getData();
            return WebResponse.createOKWebResponse(userService.queryUser(oAuthTokenEntity.getUserId()));
        } else {
            return WebResponse.createWebResponse(WebResponse.STATUS_UNAUTHORIZED, "access token 失效");
        }
    }

}
