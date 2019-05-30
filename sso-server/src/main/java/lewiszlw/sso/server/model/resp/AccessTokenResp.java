package lewiszlw.sso.server.model.resp;

import lewiszlw.sso.server.constant.TokenType;
import lombok.Data;

/**
 * Desc:
 *
 * @author zhanglinwei02
 * @date 2019-05-29
 */
@Data
public class AccessTokenResp {

    /**
     * 访问令牌
     */
    private String accessToken;

    /**
     * 令牌类型
     */
    private TokenType tokenType;

    /**
     * 过期时间（秒）
     */
    private Integer expiresIn;

    /**
     * 更新令牌
     */
    private String refreshToken;
}
