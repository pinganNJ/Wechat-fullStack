package cn.nupt.wechat.service.impl;


import cn.nupt.wechat.mapper.UsersMapper;
import cn.nupt.wechat.pojo.Users;
import cn.nupt.wechat.service.UserService;
import cn.nupt.wechat.utils.MD5Utils;
import org.n3r.idworker.Sid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

/**
 * @AUTHOR PizAn
 * @CREAET 2019-06-05 10:45
 */

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UsersMapper usersMapper;

    @Autowired
    private Sid sid;

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public boolean queryUsernameIsExist(String username) {
        Users users = new Users();
        users.setUsername(username);

        Users result = usersMapper.selectOne(users);

        return result != null ? true : false;
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public Users queryUserForLogin(String username, String password) {

        Example userExample = new Example(Users.class);

        Example.Criteria criteria = userExample.createCriteria();

        criteria.andEqualTo("username", username);
        criteria.andEqualTo("password", password);

        Users result = usersMapper.selectOneByExample(userExample);

        return result;
    }

    @Override
    public Users saveUser(Users users) throws Exception {
        users.setNickname(users.getUsername());
        users.setFaceImage("");
        users.setFaceImageBig("");
        users.setPassword(MD5Utils.getMD5Str(users.getPassword()));

        //通过sid生成唯一id
        users.setId(sid.nextShort());

        //生成二维码
        users.setQrcode("");

        //insert到数据库
        usersMapper.insert(users);
        return users;
    }
}







