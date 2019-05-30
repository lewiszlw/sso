package lewiszlw.sso.server.entity;

import lombok.Data;

import java.util.Date;

/**
 * Desc:
 *
 * @author zhanglinwei02
 * @date 2019-05-29
 */
@Data
public class OauthAppEntity {

    private Integer id;

    /**
     * app 名称
     */
    private String appName;

    /**
     * app homepage url
     */
    private String appUrl;

    /**
     * app 描述
     */
    private String appDesc;

    /**
     * app 回调url
     */
    private String appCallbackUrl;

    /**
     * 客户端 ID
     */
    private String clientId;

    /**
     * 客户端密钥
     */
    private String clientSecret;

    /**
     * 创建时间
     */
    private Date createdTime;

    /**
     * 更新时间
     */
    private Date updatedTime;
}
