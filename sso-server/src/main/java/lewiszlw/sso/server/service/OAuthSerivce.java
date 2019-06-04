package lewiszlw.sso.server.service;

import lewiszlw.sso.server.constant.Constants;
import lewiszlw.sso.server.constant.OAuthTokenType;
import lewiszlw.sso.server.convertor.OAuthTokenConverter;
import lewiszlw.sso.server.entity.OAuthTokenEntity;
import lewiszlw.sso.server.mapper.OAuthTokenMapper;
import lewiszlw.sso.server.model.req.AccessTokenReq;
import lewiszlw.sso.server.model.resp.AccessTokenResp;
import lewiszlw.sso.server.util.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Desc:
 *
 * @author zhanglinwei02
 * @date 2019-06-03
 */
@Service
public class OAuthSerivce {

    @Autowired
    private OAuthTokenMapper oauthTokenMapper;

    public String genCode(String clientId) {
        String code = TokenUtils.createCode();
        OAuthTokenEntity oauthTokenEntity = OAuthTokenConverter
                .convertToOAuthTokenEntity(OAuthTokenType.CODE, clientId, code, Constants.STATUS_VALID);
        oauthTokenMapper.insertOne(oauthTokenEntity);
        return code;
    }

    public boolean validateCode(String code) {
        OAuthTokenEntity oAuthTokenEntity = oauthTokenMapper.selectByTokenAndType(code, OAuthTokenType.CODE);
        if (oAuthTokenEntity == null) {
            return false;
        }
        return oAuthTokenEntity.getExpiredAt().after(new Date());
    }

    public AccessTokenResp applyAccessToken(AccessTokenReq req) {
        // 生成access token 和 refresh token
        String accessToken = TokenUtils.createAccessToken();
        OAuthTokenEntity accessOAuthTokenEntity = OAuthTokenConverter
                .convertToOAuthTokenEntity(OAuthTokenType.ACCESS_TOKEN, accessToken, req.getClientId(), Constants.STATUS_VALID);
        String refreshToken = TokenUtils.createRefreshToken();
        OAuthTokenEntity refreshOAuthTokenEntity = OAuthTokenConverter
                .convertToOAuthTokenEntity(OAuthTokenType.REFRESH_TOKEN, refreshToken, req.getClientId(), Constants.STATUS_VALID);
        oauthTokenMapper.insertOne(accessOAuthTokenEntity);
        oauthTokenMapper.insertOne(refreshOAuthTokenEntity);
        // 过期code
        oauthTokenMapper.updateStatusInvalid(req.getCode());
        return new AccessTokenResp()
                .setAccessToken(accessToken)
                .setExpiresIn(Constants.ACCESS_TOKEN_VALID_TIME)
                .setRefreshToken(refreshToken);
    }
}
