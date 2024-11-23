package com.example.demo.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.entity.User;
import com.example.demo.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin
@Service
public class UserService extends ServiceImpl<UserMapper, User> {


    @Resource
    UserMapper userMapper;

    public User selectByName(String name) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("name", name);
        return userMapper.selectOne(queryWrapper);
    }

    // 收藏联系人
    public boolean favoriteUser(Integer userId) {
        User user = userMapper.selectById(userId);
        if (user != null) {
            user.setIsFavorite(true);  // 标记为收藏
            return userMapper.updateById(user) > 0;
        }
        return false;
    }

    // 取消收藏联系人
    public boolean unfavoriteUser(Integer userId) {
        User user = userMapper.selectById(userId);
        if (user != null) {
            user.setIsFavorite(false); // 标记为非收藏
            return userMapper.updateById(user) > 0;
        }
        return false;
    }

    // 获取所有收藏联系人
    public List<User> getFavoriteUsers() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("is_favorite", true);  // 查询收藏联系人
        return userMapper.selectList(queryWrapper);
    }
}