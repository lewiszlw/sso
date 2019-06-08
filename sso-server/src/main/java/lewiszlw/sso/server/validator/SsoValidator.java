package lewiszlw.sso.server.validator;

import lewiszlw.sso.server.entity.OAuthAppEntity;
import lewiszlw.sso.server.model.ValidationResult;
import lewiszlw.sso.server.service.OAuthAppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Desc:
 *
 * @author zhanglinwei02
 * @date 2019-06-06
 */
@Component
public class SsoValidator {

    @Autowired
    private OAuthAppService oAuthAppService;

    public ValidationResult validateClientId(String clientId) {
        OAuthAppEntity oAuthAppEntity = oAuthAppService.queryByClientId(clientId);
        if (oAuthAppEntity != null) {
            return ValidationResult.createPassValidationResult();
        }
        return ValidationResult.createFailValidationResult("clientId不存在");
    }
}
