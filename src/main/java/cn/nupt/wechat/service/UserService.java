package cn.nupt.wechat.service;

import cn.nupt.wechat.pojo.Users;
import org.springframework.stereotype.Service;

/**
 * @AUTHOR PizAn
 * @CREAET 2019-06-05 10:44
 */
@Service
public interface UserService  {


   public boolean queryUsernameIsExist(String username);

   public Users queryUserForLogin(String username,String password);


   Users saveUser(Users users) throws Exception;
}
