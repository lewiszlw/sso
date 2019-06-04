package lewiszlw.sso.server.model.resp;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * Desc:
 *
 * @author zhanglinwei02
 * @date 2019-05-29
 */
@Data
@Accessors(chain = true)
public class AccessTokenResp {

    /**
     * 访问令牌
     */
    private String accessToken;

    /**
     * 过期时间（秒）
     */
    private Integer expiresIn;

    /**
     * 更新令牌
     */
    private String refreshToken;
}
