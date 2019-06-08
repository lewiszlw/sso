package lewiszlw.sso.server.service;

import lewiszlw.sso.server.constant.Constants;
import lewiszlw.sso.server.constant.OAuthTokenType;
import lewiszlw.sso.server.constant.TokenStatus;
import lewiszlw.sso.server.convertor.OAuthTokenConverter;
import lewiszlw.sso.server.entity.OAuthTokenEntity;
import lewiszlw.sso.server.mapper.OAuthTokenMapper;
import lewiszlw.sso.server.model.ValidationResult;
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

    /**
     * 生成code
     */
    public String genCode(String clientId, Integer userId) {
        String code = TokenUtils.createCode();
        OAuthTokenEntity oauthTokenEntity = OAuthTokenConverter
                .convertToValidOAuthTokenEntity(clientId, userId, OAuthTokenType.CODE, code);
        oauthTokenMapper.insertOne(oauthTokenEntity);
        return code;
    }

    /**
     * 判断token/code是否过期
     */
    public ValidationResult validateToken(String token, OAuthTokenType type) {
        OAuthTokenEntity oAuthTokenEntity = oauthTokenMapper.selectByTokenAndType(token, type);
        if (oAuthTokenEntity == null) {
            return ValidationResult.createFailValidationResult("token不存在");
        }
        if (oAuthTokenEntity.getExpiredAt().after(new Date())
                && TokenStatus.VALID.getStatus() == oAuthTokenEntity.getStatus()) {
            return ValidationResult.createPassValidationResult(oAuthTokenEntity);
        }
        return ValidationResult.createFailValidationResult("token过期");
    }

    /**
     * 申请access token
     */
    public AccessTokenResp applyAccessToken(AccessTokenReq req) {
        // 根据code获取userId
        OAuthTokenEntity oAuthTokenEntity = oauthTokenMapper.selectByTokenAndType(req.getCode(), OAuthTokenType.CODE);
        Integer userId = oAuthTokenEntity.getUserId();
        // 生成access token 和 refresh token
        String accessToken = TokenUtils.createAccessToken();
        OAuthTokenEntity accessOAuthTokenEntity = OAuthTokenConverter
                .convertToValidOAuthTokenEntity(req.getClientId(), userId, OAuthTokenType.ACCESS_TOKEN, accessToken);
        String refreshToken = TokenUtils.createRefreshToken();
        OAuthTokenEntity refreshOAuthTokenEntity = OAuthTokenConverter
                .convertToValidOAuthTokenEntity(req.getClientId(), userId, OAuthTokenType.REFRESH_TOKEN, refreshToken);
        oauthTokenMapper.insertOne(accessOAuthTokenEntity);
        oauthTokenMapper.insertOne(refreshOAuthTokenEntity);
        // 过期code
        oauthTokenMapper.updateStatusInvalid(req.getCode());
        return new AccessTokenResp()
                .setAccessToken(accessToken)
                .setExpiresIn(Constants.ACCESS_TOKEN_VALID_TIME)
                .setRefreshToken(refreshToken);
    }

    /**
     * 登录成功后申请access token
     */
    public String applyAccessTokenForLoginSuccess(Integer userId) {
        if (userId == null) {
            return null;
        }
        String accessToken = TokenUtils.createAccessToken();
        OAuthTokenEntity accessOAuthTokenEntity = OAuthTokenConverter
                .convertToValidOAuthTokenEntity(null, userId, OAuthTokenType.ACCESS_TOKEN, accessToken);
        oauthTokenMapper.insertOne(accessOAuthTokenEntity);
        return accessToken;
    }
}
