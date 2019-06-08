package lewiszlw.sso.sdk.service;

import lewiszlw.sso.api.dto.AccessTokenDTO;
import lewiszlw.sso.api.dto.UserDTO;
import lewiszlw.sso.sdk.config.SsoConfiguration;
import lewiszlw.sso.api.RemoteSsoService;
import lewiszlw.sso.sdk.constant.Constants;
import lewiszlw.sso.sdk.service.impl.RemoteSsoServiceDubboImpl;
import lewiszlw.sso.sdk.service.impl.RemoteSsoServiceHttpImpl;

/**
 * Desc: sso远程服务代理类
 *
 * @author zhanglinwei02
 * @date 2019-06-05
 */
public class RemoteSsoServiceProxy implements RemoteSsoService {

    private RemoteSsoService httpImpl = new RemoteSsoServiceHttpImpl();
    private RemoteSsoService dubboImpl = new RemoteSsoServiceDubboImpl();

    private static SsoConfiguration ssoConfiguration = SsoConfiguration.getInstance();

    @Override
    public AccessTokenDTO getAccessToken(String code) {
        if (Constants.DUBBO_SERVICE_IMPL.equals(ssoConfiguration.getServiceImpl())) {
            return dubboImpl.getAccessToken(code);
        }
        // 默认使用http实现
        return httpImpl.getAccessToken(code);
    }

    @Override
    public AccessTokenDTO refreshAccessToken(String refreshToken) {
        if (Constants.DUBBO_SERVICE_IMPL.equals(ssoConfiguration.getServiceImpl())) {
            return dubboImpl.refreshAccessToken(refreshToken);
        }
        return httpImpl.refreshAccessToken(refreshToken);
    }

    @Override
    public UserDTO getUser(String accessToken) {
        if (Constants.DUBBO_SERVICE_IMPL.equals(ssoConfiguration.getServiceImpl())) {
            return dubboImpl.getUser(accessToken);
        }
        return httpImpl.getUser(accessToken);
    }

    @Override
    public boolean validateAccessToken(String accessToken) {
        if (Constants.DUBBO_SERVICE_IMPL.equals(ssoConfiguration.getServiceImpl())) {
            return dubboImpl.validateAccessToken(accessToken);
        }
        return httpImpl.validateAccessToken(accessToken);
    }
}
