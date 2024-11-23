package com.example.demo.controller;


import com.example.demo.common.Result;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/web")
public class WebController {



    @GetMapping("/hello")
    public Result hello(String name) {
        return Result.success(name);
    }


    @PostMapping("/post")
    public Result post(@RequestBody Obj obj) {
        System.out.println( obj.getName() == null ?"is null":obj.getName().isEmpty());
        return Result.success(obj);
    }


    @PutMapping("/put")
    public Result put(@RequestBody Obj obj) {
        return Result.success(obj);
    }

    @DeleteMapping("/delete")
    public Result delete(@RequestBody List<Integer> ids) {
        return Result.success(ids);
    }
}