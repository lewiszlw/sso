package lewiszlw.sso.sdk.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
import lewiszlw.sso.api.RemoteSsoService;
import lewiszlw.sso.api.dto.AccessTokenDTO;
import lewiszlw.sso.api.dto.UserDTO;
import lewiszlw.sso.api.dto.WebResponseDTO;
import lewiszlw.sso.sdk.config.SsoConfiguration;
import lewiszlw.sso.sdk.constant.Constants;
import lewiszlw.sso.sdk.util.HttpUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * Desc:
 *
 * @author zhanglinwei02
 * @date 2019-06-05
 */
public class RemoteSsoServiceHttpImpl implements RemoteSsoService {

    private SsoConfiguration ssoConfiguration = SsoConfiguration.getInstance();

    @Override
    public AccessTokenDTO getAccessToken(String code) {
        JSONObject content = new JSONObject();
        content.put("clientId", ssoConfiguration.getClientId());
        content.put("clientSecret", ssoConfiguration.getClientSecret());
        content.put("code", code);

        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json;charset=UTF-8");
        JSONObject jsonObject = HttpUtils
                .doPost(Constants.SSO_OAUTH_ACCESSTOKEN_URL, content.toJSONString(), JSONObject.class, headers);

        AccessTokenDTO accessTokenDTO = jsonObject.getObject("data", AccessTokenDTO.class);
        return accessTokenDTO;
    }

    @Override
    public AccessTokenDTO refreshAccessToken(String refreshToken) {
        return null;
    }

    @Override
    public UserDTO getUser(String accessToken) {
        return null;
    }
}
