package lewiszlw.sso.server.model.resp;

import lombok.Data;

/**
 * Desc:
 *
 * @author zhanglinwei02
 * @date 2019-05-29
 */
@Data
public class RegisterAppResp {

    /**
     * 客户端 ID
     */
    private String clientId;

    /**
     * 客户端密钥
     */
    private String clientSecret;
}
