package com.example.demo.IO;

import com.example.demo.entity.User;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

public class Export {

    // 导出所有联系人为Excel
    public static byte[] exportUserToExcel(List<User> userList) throws IOException {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Users");

        // 创建表头
        Row headerRow = sheet.createRow(0);
        headerRow.createCell(0).setCellValue("ID");
        headerRow.createCell(1).setCellValue("Name");
        headerRow.createCell(2).setCellValue("Phone1");
        headerRow.createCell(2).setCellValue("Phone2");
        headerRow.createCell(3).setCellValue("Email");
        headerRow.createCell(4).setCellValue("Address");
        headerRow.createCell(5).setCellValue("Is Favorite");

        // 填充数据
        int rowNum = 1;
        for (User user : userList) {
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(user.getId());
            row.createCell(1).setCellValue(user.getName());
            row.createCell(2).setCellValue(user.getPhone1());
            row.createCell(2).setCellValue(user.getPhone2());
            row.createCell(3).setCellValue(user.getEmail());
            row.createCell(4).setCellValue(user.getAddress());
            row.createCell(5).setCellValue(user.getIsFavorite() ? "Yes" : "No");
        }

        // 输出Excel为字节数组
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        workbook.write(outputStream);
        workbook.close();

        return outputStream.toByteArray();
    }
}
