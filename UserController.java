package com.example.demo.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.demo.IO.Export;
import com.example.demo.IO.Import;
import com.example.demo.common.Result;
import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/add")
    public Result add(@RequestBody User user) {
        try {
            userService.save(user);
        } catch (Exception e) {
            return Result.error("error");
        }
        return Result.success();
    }


    @PutMapping("/update/{id}")
    public Result update(@RequestBody User user) {
        try {
            userService.updateById(user);
        } catch (Exception e) {
            return Result.error("error");
        }
        return Result.success();
    }


    @DeleteMapping("/delete/{id}")
    public Result delete(@PathVariable Integer id) {
        try {
            userService.removeById(id);
        } catch (Exception e) {
            return Result.error("error");
        }
        return Result.success();
    }

    @PostMapping("/delete/batch")
    public Result batchDelete(@RequestBody List<Integer> ids) {
        try {
            userService.removeBatchByIds(ids);
        } catch (Exception e) {
            return Result.error("error");
        }
        return Result.success();
    }


    @GetMapping("/selectAll")
    public Result selectAll() {
        try {
            List<User> userList = userService.list(new QueryWrapper<User>().orderByAsc("id"));
            return Result.success(userList);
        } catch (Exception e) {
            return Result.error("error");
        }
    }

    @GetMapping("/selectById/{id}")
    public Result selectById(@PathVariable Integer id) {
        try {
            User user = userService.getById(id);
            return Result.success(user);
        } catch (Exception e) {
            return Result.error("error");
        }
    }

    @PostMapping("/favorite/{id}")
    public Result favorite(@PathVariable Integer id) {
        boolean success = userService.favoriteUser(id);
        if (success) {
            return Result.success();
        } else {
            return Result.error("收藏失败");
        }
    }

    // 取消收藏联系人
    @PostMapping("/unfavorite/{id}")
    public Result unfavorite(@PathVariable Integer id) {
        boolean success = userService.unfavoriteUser(id);
        if (success) {
            return Result.success();
        } else {
            return Result.error("取消收藏失败");
        }
    }

    // 获取所有收藏联系人
    @GetMapping("/favorites")
    public Result getFavoriteUsers() {
        List<User> favoriteUsers = userService.getFavoriteUsers();
        return Result.success(favoriteUsers);
    }



    @GetMapping("/export")
    public ResponseEntity<byte[]> exportUsers() {
        try {
            List<User> users = userService.list();
            byte[] excelData = Export.exportUserToExcel(users);

            // 设置Excel文件为下载类型
            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-Disposition", "attachment; filename=users.xlsx");

            return new ResponseEntity<>(excelData, headers, HttpStatus.OK);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
@PostMapping("/import")
public Result importUsers(@RequestParam("file") MultipartFile file) {
    try {
        List<User> userList = Import.importUserFromExcel(file.getInputStream());
        for (User user : userList) {
            userService.save(user);  // 保存到数据库
        }
        return Result.success();
    } catch (Exception e) {
        return Result.error("导入失败");
    }
}
}
