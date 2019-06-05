package lewiszlw.sso.api.dto;

import java.io.Serializable;

/**
 * Desc:
 *
 * @author zhanglinwei02
 * @date 2019-06-05
 */
public class AccessTokenDTO implements Serializable {
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

    /**
     * 更新令牌过期时间 (秒)
     */
    private Integer refreshExpiresIn;

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public Integer getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(Integer expiresIn) {
        this.expiresIn = expiresIn;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public Integer getRefreshExpiresIn() {
        return refreshExpiresIn;
    }

    public void setRefreshExpiresIn(Integer refreshExpiresIn) {
        this.refreshExpiresIn = refreshExpiresIn;
    }
}
