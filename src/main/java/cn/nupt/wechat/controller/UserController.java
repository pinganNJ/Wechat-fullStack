package cn.nupt.wechat.controller;

import cn.nupt.wechat.pojo.Users;
import cn.nupt.wechat.pojo.vo.UsersVO;
import cn.nupt.wechat.service.UserService;
import cn.nupt.wechat.utils.MD5Utils;
import cn.nupt.wechat.utils.WechatJSONResult;
import com.github.pagehelper.util.StringUtil;
import com.mysql.cj.util.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @AUTHOR PizAn
 * @CREAET 2019-06-05 10:17
 */


@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/registOrLogin", method = RequestMethod.POST)
    public WechatJSONResult registOrLogin(@RequestBody Users users) throws Exception {

        //1.判断用户名或者密码是否为空
        if (users.getUsername() == null || users.getPassword() == null) {
            return WechatJSONResult.errorMsg("用户名或密码不能为空!");
        }


        boolean queryUsernameIsExist = userService.queryUsernameIsExist(users.getUsername());
        Users userResult = null;
        //1.1 用户名存在，登陆
        if (queryUsernameIsExist) {

            userResult = userService.queryUserForLogin(users.getUsername(), MD5Utils.getMD5Str(users.getPassword()));
            if (userResult == null) {
                return WechatJSONResult.errorMsg("用户名或密码不正确!");
            }


        } else {
            // 1.2 用户名不存在，注册
            userResult = userService.saveUser(users);

        }

        UsersVO usersVO = new UsersVO();
        //controller传到前端不需要注册时的全部数据，所以将userResult变成usersVO
        BeanUtils.copyProperties(userResult, usersVO);

        return WechatJSONResult.ok(usersVO);
    }


}
