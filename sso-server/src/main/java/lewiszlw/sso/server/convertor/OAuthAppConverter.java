package lewiszlw.sso.server.convertor;

import lewiszlw.sso.server.entity.OAuthAppEntity;
import lewiszlw.sso.server.model.req.RegisterAppReq;

/**
 * Desc:
 *
 * @author zhanglinwei02
 * @date 2019-06-03
 */
public class OAuthAppConverter {

    public static OAuthAppEntity convertToOauthAppEntity(RegisterAppReq req, String clientId, String clientSecret) {
        return new OAuthAppEntity()
                .setAppName(req.getAppName())
                .setAppDesc(req.getAppDesc())
                .setAppUrl(req.getAppUrl())
                .setAppCallbackUrl(req.getAppCallbackUrl())
                .setClientId(clientId)
                .setClientSecret(clientSecret);
    }
}
