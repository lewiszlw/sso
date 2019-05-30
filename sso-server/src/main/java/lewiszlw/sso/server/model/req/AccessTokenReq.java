package lewiszlw.sso.server.model.req;

import lombok.Data;

/**
 * Desc:
 *
 * @author zhanglinwei02
 * @date 2019-05-29
 */
@Data
public class AccessTokenReq {

    /**
     * 客户端的 ID
     */
    private String clientId;

    /**
     * 客户端的密钥
     */
    private String clientSecret;

    /**
     * 授权码
     */
    private String code;
}
