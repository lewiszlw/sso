package lewiszlw.sso.server.convertor;

import lewiszlw.sso.server.constant.Constants;
import lewiszlw.sso.server.constant.OAuthTokenType;
import lewiszlw.sso.server.constant.TokenStatus;
import lewiszlw.sso.server.entity.OAuthTokenEntity;
import lewiszlw.sso.server.util.DateUtils;

/**
 * Desc:
 *
 * @author zhanglinwei02
 * @date 2019-06-03
 */
public class OAuthTokenConverter {

    public static OAuthTokenEntity convertToValidOAuthTokenEntity(String clientId,
                                                             Integer userId,
                                                             OAuthTokenType type,
                                                             String token) {
        OAuthTokenEntity oAuthTokenEntity = new OAuthTokenEntity()
                .setClientId(clientId)
                .setUserId(userId)
                .setToken(token)
                .setType(type)
                .setStatus(TokenStatus.VALID.getStatus());
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
                throw new IllegalArgumentException("OAuthTokenType 错误");
        }
        return oAuthTokenEntity;
    }

}
