package lewiszlw.sso.server.constant;

import lombok.Getter;
import lombok.Setter;

/**
 * Desc:
 *
 * @author zhanglinwei02
 * @date 2019-06-06
 */
public enum TokenStatus {

    INVALID(0), VALID(1);

    @Getter
    @Setter
    private int status;

    TokenStatus (int status) {
        this.status = status;
    }
}
