package lewiszlw.sso.server.validator;

import com.google.common.base.Preconditions;
import lewiszlw.sso.server.model.ValidationResult;
import lewiszlw.sso.server.service.OauthAppService;
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
public class OauthValidator {

    @Autowired
    private OauthAppService oauthAppService;

    public ValidationResult validateAuthorize(String clientId, String redirectUri, String scope, String state) {
        try {
            Preconditions.checkArgument(Objects.nonNull(oauthAppService.queryByClientId(clientId)), "clientId不存在");

            return ValidationResult.createPassValidationResult();
        } catch (IllegalArgumentException e) {
            return ValidationResult.createFailValidationResult(e.getMessage());
        }
    }
}
