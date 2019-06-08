package lewiszlw.sso.server.service;

import lewiszlw.sso.server.entity.UserEntity;
import lewiszlw.sso.server.mapper.UserMapper;
import lewiszlw.sso.server.model.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Desc:
 *
 * @author zhanglinwei02
 * @date 2019-06-06
 */
@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public UserVO queryUser(Integer userId) {
        UserEntity userEntity = userMapper.selectById(userId);
        if (userEntity == null) {
            return null;
        }
        return new UserVO()
                .setName(userEntity.getName())
                .setEmail(userEntity.getEmail())
                .setPhone(userEntity.getPhone())
                .setAddress(userEntity.getAddress());
    }
}
