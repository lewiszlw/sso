package lewiszlw.sso.sdk.service.impl;

import lewiszlw.sso.api.SsoDubboService;
import lewiszlw.sso.api.dto.AccessTokenDTO;
import lewiszlw.sso.api.RemoteSsoService;
import lewiszlw.sso.api.dto.UserDTO;
import org.apache.dubbo.config.annotation.Reference;

/**
 * Desc:
 *
 * @author zhanglinwei02
 * @date 2019-06-05
 */
public class RemoteSsoServiceDubboImpl implements RemoteSsoService {

    @Reference
    private SsoDubboService ssoDubboService;

    @Override
    public AccessTokenDTO getAccessToken(String code) {
        return ssoDubboService.getAccessToken(code);
    }

    @Override
    public AccessTokenDTO refreshAccessToken(String refreshToken) {
        return ssoDubboService.refreshAccessToken(refreshToken);
    }

    @Override
    public UserDTO getUser(String accessToken) {
        return ssoDubboService.getUser(accessToken);
    }

    @Override
    public boolean validateAccessToken(String accessToken) {
        return ssoDubboService.validateAccessToken(accessToken);
    }
}
