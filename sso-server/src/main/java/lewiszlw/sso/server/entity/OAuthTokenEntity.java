package lewiszlw.sso.server.entity;

import lewiszlw.sso.server.constant.OAuthTokenType;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * Desc:
 *
 * @author zhanglinwei02
 * @date 2019-06-02
 */
@Data
@Accessors(chain = true)
public class OAuthTokenEntity {

    private Integer id;

    /**
     * 客户端 id
     */
    private String clientId;

    /**
     * 授权用户id
     */
    private Integer userId;

    /**
     * token 类型
     */
    private OAuthTokenType type;

    /**
     * refresh token
     */
    private String token;

    /**
     * 过期时间
     */
    private Date expiredAt;

    /**
     * 状态
     * 1 有效，0 无效
     */
    private Integer status;

    /**
     * 创建时间
     */
    private Date createdTime;

    /**
     * 更新时间
     */
    private Date updatedTime;
}
