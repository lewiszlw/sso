package lewiszlw.sso.server.convertor;

import lewiszlw.sso.server.constant.Constants;
import lewiszlw.sso.server.constant.OAuthTokenType;
import lewiszlw.sso.server.entity.OAuthTokenEntity;
import lewiszlw.sso.server.util.DateUtils;

/**
 * Desc:
 *
 * @author zhanglinwei02
 * @date 2019-06-03
 */
public class OAuthTokenConverter {

    public static OAuthTokenEntity convertToOAuthTokenEntity(OAuthTokenType type,
                                                             String clientId,
                                                             String token,
                                                             Integer status) {
        OAuthTokenEntity oAuthTokenEntity = new OAuthTokenEntity()
                .setClientId(clientId)
                .setToken(token)
                .setType(type)
                .setStatus(status);
        switch (type) {
            case CODE:
                oAuthTokenEntity.setExpiredAt(DateUtils.genExpiredDate(Constants.CODE_VALID_TIME));
                break;
            case ACCESS_TOKEN:
                oAuthTokenEntity.setExpiredAt(DateUtils.genExpiredDate(Constants.ACCESS_TOKEN_VALID_TIME));
                break;
            case REFRESH_TOKEN:
                oAuthTokenEntity.setExpiredAt(DateUtils.genExpiredDate(Constants.REFRESH_TOKEN_VALID_TIME));
                break;
            default:
                throw new IllegalArgumentException("type 错误");
        }
        return oAuthTokenEntity;
    }

}
