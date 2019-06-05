package cn.nupt.wechat.controller;

import cn.nupt.wechat.pojo.Users;
import cn.nupt.wechat.utils.WechatJSONResult;
import com.github.pagehelper.util.StringUtil;
import com.mysql.cj.util.StringUtils;
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

    @RequestMapping(value = "/registOrLogin", method = RequestMethod.POST)
    public WechatJSONResult registOrLogin(@RequestBody Users users) {

        //1.判断用户名或者密码是否为空
        if (users.getUsername() == null || users.getPassword() == null) {
            return WechatJSONResult.errorMsg("用户名或密码不能为空!");
        }




        1. name pass 存在
        2 service querynameisExist


        return WechatJSONResult.ok();
    }


}
