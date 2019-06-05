package lewiszlw.sso.server.model.req;

import lombok.Data;

/**
 * Desc:
 *
 * @author zhanglinwei02
 * @date 2019-06-04
 */
@Data
public class LoginReq {

    private String username;

    private String password;

    private String clientId;

    private String redirectUri;

}
