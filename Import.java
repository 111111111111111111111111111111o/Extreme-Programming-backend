package com.example.demo.IO;

import com.example.demo.entity.User;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class Import {

    // 从Excel导入联系人
    public static List<User> importUserFromExcel(InputStream inputStream) throws Exception {
        List<User> userList = new ArrayList<>();
        Workbook workbook = new XSSFWorkbook(inputStream);
        Sheet sheet = workbook.getSheetAt(0);

        // 跳过表头
        for (int i = 1; i <= sheet.getLastRowNum(); i++) {
            Row row = sheet.getRow(i);
            User user = new User();
            user.setId((int) row.getCell(0).getNumericCellValue());
            user.setName(row.getCell(1).getStringCellValue());
            user.setPhone1(row.getCell(2).getStringCellValue());
            user.setPhone2(row.getCell(2).getStringCellValue());
            user.setEmail(row.getCell(3).getStringCellValue());
            user.setAddress(row.getCell(4).getStringCellValue());
            user.setIsFavorite("Yes".equals(row.getCell(5).getStringCellValue()));
            userList.add(user);
        }
        workbook.close();
        return userList;
    }
}
