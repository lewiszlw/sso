package lewiszlw.sso.server.service;

import lewiszlw.sso.server.convertor.OAuthAppConverter;
import lewiszlw.sso.server.entity.OAuthAppEntity;
import lewiszlw.sso.server.mapper.OAuthAppMapper;
import lewiszlw.sso.server.model.req.RegisterAppReq;
import lewiszlw.sso.server.model.resp.RegisterAppResp;
import lewiszlw.sso.server.util.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Desc:
 *
 * @author zhanglinwei02
 * @date 2019-05-31
 */
@Service
public class OAuthAppService {

    @Autowired
    private OAuthAppMapper oauthAppMapper;

    public RegisterAppResp registerApp(RegisterAppReq req) {
        String clientId = TokenUtils.createClientId();
        String clientSecret = TokenUtils.createClientSecret();
        OAuthAppEntity oauthAppEntity = OAuthAppConverter.convertToOauthAppEntity(req, clientId, clientSecret);
        oauthAppMapper.insertOne(oauthAppEntity);
        return new RegisterAppResp().setClientId(clientId).setClientSecret(clientSecret);
    }

    public OAuthAppEntity queryByClientId(String clientId) {
        return oauthAppMapper.selectByClientId(clientId);
    }
}
