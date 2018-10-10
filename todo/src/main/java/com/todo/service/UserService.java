package com.todo.service;

import com.todo.dao.UserDao;
import com.todo.entity.User;
import com.todo.util.wx.WXTocken;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.List;

@Service
public class UserService {
    private static final Logger logger = LogManager.getLogger(WXTocken.class.getName());

    @Autowired
    private UserDao userDao;
    @Transactional
    public void add(){
        User user=new User();
        user.setAccount("ddd");
        user.setPasw("ddd");
        user.setName("测试");
        userDao.add(user);
    }
    @Transactional
    public List<User> getByOpenId(String openId){
        return userDao.getByOpenId(openId);
    }
    @Transactional
    public List<User> getByAccount(String account){
        return userDao.getByAccount(account);
    }
    @Transactional
    public boolean wxIsBind(String wxOpenId){
        logger.info("wxIsBind- openId:="+wxOpenId);
        return userDao.openidIsBind(wxOpenId)>0;
    }
    @Transactional
    public boolean registerWithWxOpenid(User user) throws Exception {
        int a=userDao.accountIsBind(user.getAccount());
        if(a>0){
            logger.error("帐号："+user.getAccount()+" 被绑定");
            throw new RuntimeException("帐号被绑定");
        }
        a=userDao.openidIsBind(user.getWxOpenId());
        if(a>0){
            logger.error("微信openid："+user.getWxOpenId()+" 被绑定");
            throw new RuntimeException("微信被绑定");
        }
        try{
            user.setCreateTime(new Timestamp(System.currentTimeMillis()));
            userDao.registerWithWxOpenid(user);
            return true;
        }catch (RuntimeException e){
           logger.error(e.getMessage());
           throw  new Exception("绑定失败！");
        }
    }
    @Transactional
    public boolean bindWX(User user) throws Exception {
        int a=userDao.accountIsBind(user.getAccount());
        if(a>0){
            logger.error("帐号："+user.getAccount()+" 被绑定");
            throw new RuntimeException("帐号被绑定");
        }
        a=userDao.openidIsBind(user.getWxOpenId());
        if(a>0){
            logger.error("微信openid："+user.getWxOpenId()+" 被绑定");
            throw new RuntimeException("微信被绑定");
        }
        try{
            userDao.bingWxOpenId(user);
            return true;
        }catch (RuntimeException e){
            logger.error(e.getMessage());
            throw  new Exception("绑定失败！");
        }
    }

}
