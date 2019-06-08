package lewiszlw.sso.server.service;

import lewiszlw.sso.server.entity.AccountEntity;
import lewiszlw.sso.server.mapper.AccountMapper;
import lewiszlw.sso.server.model.ValidationResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Desc:
 *
 * @author zhanglinwei02
 * @date 2019-06-06
 */
@Service
public class AccountService {

    @Autowired
    private AccountMapper accountMapper;

    public ValidationResult validateAccount(String username, String password) {
        AccountEntity accountEntity = accountMapper.selectByUsernameAndPassword(username, password);
        if (accountEntity != null) {
            return ValidationResult.createPassValidationResult(accountEntity);
        }
        return ValidationResult.createFailValidationResult("账号名或密码错误");
    }
}
