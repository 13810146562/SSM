package com.zqq.ssm.controller;

import com.zqq.ssm.pojo.User;
import com.zqq.ssm.service.IUserService;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @Author zqq
 * @Date 2019/12/12  10:20
 */
@Controller("excelController")
@RequestMapping("/excel")
public class ExcelController {
    @Resource(name = "userService")
    private IUserService userService;

    /**
     * 导出用户信息
     *
     * @param response
     */
    @RequestMapping("/downUserExcel")
    public void downUserExcel(HttpServletResponse response) throws Exception {
        response.setContentType("application/binary;charset=UTF-8");
        response.setHeader("Content-disposition", "attachment;filename=users.xls");
        //获取所有用户信息
        List<User> users = userService.findAllUser();
        ServletOutputStream out = response.getOutputStream();
        outExcelUtil(users, out);
        out.flush();
        out.close();
    }

    /**
     * 通过excle导入用户信息
     *
     * @param userExcel
     * @return 返回初始页面
     * @throws Exception
     */
    @RequestMapping("/addUserByExcel")
    public String addUserByExcel(@RequestParam("userExcel") MultipartFile userExcel) throws Exception {
        if (userExcel.isEmpty()) {
            throw new Exception("文件不存在");
        }
        InputStream inputStream = userExcel.getInputStream();
        List<List<Object>> listObjects = getBankListByExcel(inputStream, userExcel.getOriginalFilename());
        List<User> users = new ArrayList<>();
        for (List<Object> objects : Objects.requireNonNull(listObjects)) {
            User user = new User();
            user.setUsername(String.valueOf(objects.get(0)));
            user.setPassword(String.valueOf(objects.get(1)));
            users.add(user);
        }
        users.forEach(System.out::println);

        return "redirect:/user/findAllUser.action";
    }

    /**
     * 解析excle
     *
     * @param inputStream
     * @param filename
     * @return
     * @throws Exception
     */
    private List<List<Object>> getBankListByExcel(InputStream inputStream, String filename) throws Exception {
        Workbook workbook = getWorkbook(inputStream, filename);
        List<List<Object>> listObjects = new ArrayList<>();
        for (int i = 0; i < workbook.getNumberOfSheets(); i++) {
            Sheet sheet = workbook.getSheetAt(i);
            if (null == sheet) {
                continue;
            }
            for (int j = sheet.getFirstRowNum() + 1; j < sheet.getLastRowNum(); j++) {
                Row row = sheet.getRow(j);
                if (row == null || row.getFirstCellNum() == j) {
                    continue;
                }
                List<Object> objects = new ArrayList<>();
                for (int c = row.getFirstCellNum(); c < row.getLastCellNum(); c++) {
                    Cell cell = row.getCell(c);
                    objects.add(getCellValue(cell));
                }
                listObjects.add(objects);
            }
        }
        return listObjects;
    }

    /**
     * 格式化表格数据
     *
     * @param cell
     * @return
     */
    private Object getCellValue(Cell cell) {
        Object value = null;
        DecimalFormat decimalFormat = new DecimalFormat("0");//格式化number String字符
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");//格式化日期
        DecimalFormat decimalFormat1 = new DecimalFormat("0.00");//格式化数字

        switch (cell.getCellType()) {
            case Cell.CELL_TYPE_STRING:
                value = cell.getRichStringCellValue().getString();
                break;
            case Cell.CELL_TYPE_NUMERIC:
                if ("General".equals(cell.getCellStyle().getDataFormatString())) {
                    value = decimalFormat.format(cell.getNumericCellValue());
                } else if ("m/d/yy".equals(cell.getCellStyle().getDataFormatString())) {
                    value = simpleDateFormat.format(cell.getDateCellValue());
                } else {
                    value = decimalFormat1.format(cell.getNumericCellValue());
                }
                break;
            case Cell.CELL_TYPE_BOOLEAN:
                value = cell.getBooleanCellValue();
                break;
            case Cell.CELL_TYPE_BLANK:
                value = "";
                break;
            default:
                break;
        }
        return value;
    }

    /**
     * 根据文件类型，适应excel的版本
     *
     * @param inputStream
     * @param filename
     * @return
     * @throws Exception
     */
    private Workbook getWorkbook(InputStream inputStream, String filename) throws Exception {
        Workbook workbook;
        String fileType = filename.substring(filename.lastIndexOf("."));
        if (".xls".equals(fileType)) {
            workbook = new HSSFWorkbook(inputStream);//2003
        } else if (".xlsx".equals(fileType)) {
            workbook = new XSSFWorkbook(inputStream);//2007
        } else {
            throw new Exception("文件格式不正确");
        }
        return workbook;
    }

    /**
     * 导出excel方法
     *
     * @param users
     * @param out
     */
    private void outExcelUtil(List<User> users, OutputStream out) throws IOException {
        //1、创建workbook
        Workbook workbook = new HSSFWorkbook();
        //2、在workbook中添加sheet
        Sheet sheet = workbook.createSheet("用户清单");
        //3、在sheet中添加表头第0行
        Row row = sheet.createRow(0);
        //创建格式
        CellStyle cellStyle = workbook.createCellStyle();
        //定义居中
        cellStyle.setAlignment(CellStyle.ALIGN_CENTER);
        Cell cellTitle;
        String[] titles = {"userId", "userName", "userPassword"};
        for (int i = 0; i < titles.length; i++) {
            cellTitle = row.createCell(i);
            cellTitle.setCellValue(titles[i]);
            cellTitle.setCellStyle(cellStyle);
        }
        //4、写入user数据
        for (int i = 0; i < users.size(); i++) {
            row = sheet.createRow(i + 1);
            row.createCell(0).setCellValue(users.get(i).getUserid());
            row.createCell(1).setCellValue(users.get(i).getUsername());
            row.createCell(2).setCellValue(users.get(i).getPassword());
        }
        workbook.write(out);
    }

}
