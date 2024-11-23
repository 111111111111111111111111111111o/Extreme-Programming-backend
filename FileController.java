//package com.example.demo.controller;
//
//
//import ch.qos.logback.core.util.FileUtil;
//import com.example.demo.common.Result;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.multipart.MultipartFile;
//
//import javax.servlet.ServletOutputStream;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
//@RestController
//@RequestMapping("/file")
//public class FileController {
//
//
//    @PostMapping("/upload")
//    public Result upload(MultipartFile file) {
//        String originalFilename = file.getOriginalFilename();
//        FileUtil.
//        FileUtil.extName();
//        return Result.success(file);
//
//
//    }
//
//    @GetMapping("/download/{fileName}")
//    public void download(@PathVariable String fileName, HttpServletResponse response)  throws IOException {
//
//        ServletOutputStream outputStream = response.getOutputStream();
//        outputStream.write(new byte[]);
//    }
//}
