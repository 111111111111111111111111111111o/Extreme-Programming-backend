package com.example.demo.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.entity.User;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin
public interface UserMapper extends BaseMapper <User> {


}