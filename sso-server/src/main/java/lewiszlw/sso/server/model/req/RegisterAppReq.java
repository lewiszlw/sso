package lewiszlw.sso.server.model.req;

import lombok.Data;

/**
 * Desc:
 *
 * @author zhanglinwei02
 * @date 2019-05-29
 */
@Data
public class RegisterAppReq {

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
}
