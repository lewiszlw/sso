package lewiszlw.sso.server.validator;

import com.google.common.base.Preconditions;
import lewiszlw.sso.server.entity.OAuthAppEntity;
import lewiszlw.sso.server.model.ValidationResult;
import lewiszlw.sso.server.model.req.AccessTokenReq;
import lewiszlw.sso.server.model.req.RegisterAppReq;
import lewiszlw.sso.server.service.OAuthAppService;
import lewiszlw.sso.server.service.OAuthSerivce;
import lewiszlw.sso.server.util.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * Desc:
 *
 * @author zhanglinwei02
 * @date 2019-05-31
 */
@Component
public class OAuthValidator {

    @Autowired
    private OAuthAppService oauthAppService;

    @Autowired
    private OAuthSerivce oAuthSerivce;

    public ValidationResult validateAuthorize(String clientId, String redirectUri, String scope, String state) {
        try {
            Preconditions.checkArgument(Objects.nonNull(oauthAppService.queryByClientId(clientId)), "clientId不存在");
            Preconditions.checkArgument(WebUtils.isHttpUri(redirectUri), "redirectUri格式不正确");

            return ValidationResult.createPassValidationResult();
        } catch (IllegalArgumentException e) {
            return ValidationResult.createFailValidationResult(e.getMessage());
        }
    }

    public ValidationResult validateRegisterApp(RegisterAppReq req) {
        try {
            Preconditions.checkArgument(req != null, "请求为空");
            Preconditions.checkArgument(WebUtils.isHttpUri(req.getAppUrl()), "app url 不合法");
            Preconditions.checkArgument(WebUtils.isHttpUri(req.getAppCallbackUrl()), "callback url 不合法");
            return ValidationResult.createPassValidationResult();
        } catch (IllegalArgumentException e) {
            return ValidationResult.createFailValidationResult(e.getMessage());
        }
    }

    public ValidationResult validateAccessToken(AccessTokenReq req) {
        try {
            OAuthAppEntity oAuthAppEntity = oauthAppService.queryByClientId(req.getClientId());
            Preconditions.checkArgument(Objects.nonNull(oAuthAppEntity), "client id 不正确");
            Preconditions.checkArgument(oAuthAppEntity.getClientSecret().equals(req.getClientSecret()), "client secret 不正确");
            Preconditions.checkArgument(oAuthSerivce.validateCode(req.getCode()), "code 过期");

            return ValidationResult.createPassValidationResult();
        } catch (IllegalArgumentException e) {
            return ValidationResult.createFailValidationResult(e.getMessage());
        }
    }
}
