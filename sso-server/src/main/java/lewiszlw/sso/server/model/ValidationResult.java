package lewiszlw.sso.server.model;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * Desc:
 *
 * @author zhanglinwei02
 * @date 2019-05-31
 */
@Data
@Accessors(chain = true)
public class ValidationResult {

    private boolean pass;

    private String message;

    private Object data;

    public WebResponse transformWebResponse(Integer status) {
        return new WebResponse().setStatus(status).setMessage(message);
    }

    public static ValidationResult createPassValidationResult() {
        return new ValidationResult().setPass(true).setMessage("验证通过");
    }

    public static ValidationResult createPassValidationResult(Object data) {
        return new ValidationResult().setPass(true).setMessage("验证通过").setData(data);
    }

    public static ValidationResult createFailValidationResult(String message) {
        return new ValidationResult().setPass(false).setMessage(message);
    }
}
