package com.example.demo.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;



@Getter
@Setter
@TableName("user")
public class User {
    @TableId(type= IdType.AUTO)
    private Integer id;
    private String name;
    private  String phone1;
    private  String phone2;
    private String email;
    private String address;

    private Boolean isFavorite;


}