package com.ikamobile.jxl;

import jxl.Workbook;
import jxl.write.*;
import jxl.write.Number;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by xdb20 on 2015/12/7.
 */
public class JxlApplication {

    public static void main(String[] args) throws Exception {
        main2("保险1");
        main1("保险1",1);
    }

    public static void main2(String shn) throws Exception {

        HSSFWorkbook wb = new HSSFWorkbook();
        FileOutputStream fileOut = new FileOutputStream("c:/tmp/workbook.xls");

        HSSFSheet sheet1 = wb.createSheet(shn);
        long st = System.currentTimeMillis();

        for (int i = 0; i < 30000; i++) {
            Row row = sheet1.createRow(i);
            for (int j = 0; j < 20; j++) {
                org.apache.poi.ss.usermodel.Cell cell = row.createCell(j);
                if (j == 0) {
                    cell.setCellValue("BF20151207000216:" + j + " - " + i);
                }
                if (j == 1) {
                    cell.setCellValue("781-8516517843:" + j + " - " + i);
                }
                if (j == 2) {
                    cell.setCellValue("上官青子");
                }
                if (j == 3) {
                    cell.setCellValue("航空旅客意外伤害险");
                }
                if (j == 4) {
                    cell.setCellValue(600000);
                }
                if (j == 5) {
                    cell.setCellValue(20.0);
                }
                if (j == 6) {
                    cell.setCellValue(4.8);
                }
                if (j == 7) {
                    cell.setCellValue("海思科药业集团股份有限公司");
                }
                if (j == 8) {
                    cell.setCellValue("2015-12-07");
                }
                if (j > 8)
                    cell.setCellValue("context:" + j + " - " + i);
            }
        }


        wb.write(fileOut);
        fileOut.close();


        long et = System.currentTimeMillis();
        System.out.println("poi use time: " + (et - st));

    }

    public static void main1(String shn,int idx) {
        WritableWorkbook wwb = null;
        try {
            wwb = Workbook.createWorkbook(new File("c:/tmp/test.xls"));

            WritableSheet sheet = wwb.createSheet(shn, idx);

            long st = System.currentTimeMillis();
            for (int i = 0; i < 30000; i++) {
                for (int j = 0; j < 20; j++) {


                    Label label = null;
                    if (j == 0) {
                        label = new Label(j, i, "BF20151207000216:" + j + " - " + i);
                    }
                    if (j == 1) {
                        label = new Label(j, i, "781-8516517843:" + j + " - " + i);
                    }
                    if (j == 2) {
                        label = new Label(j, i, "上官青子");
                    }
                    if (j == 3) {
                        label = new Label(j, i, "航空旅客意外伤害险");
                    }
                    if (j == 4) {
                        jxl.write.Number label2 = new Number(j, i, 600000);
                        sheet.addCell(label2);

                    }
                    if (j == 5) {
                        jxl.write.Number label2 = new Number(j, i, 20.0);
                        sheet.addCell(label2);
                    }
                    if (j == 6) {
                        jxl.write.Number label2 = new Number(j, i, 4.8);
                        sheet.addCell(label2);
                    }
                    if (j == 7) {
                        label = new Label(j, i, "海思科药业集团股份有限公司");
                    }
                    if (j == 8) {
                        label = new Label(j, i, "context:" + j + " - " + i);
                    }
                    if (j > 8) {
                        label = new Label(j, i, "context:" + j + " - " + i);
                    }
                    if (label != null)
                        sheet.addCell(label);
                }
            }

            wwb.write();
            wwb.close();

            long et = System.currentTimeMillis();
            System.out.println("jxl use time: " + (et - st));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (WriteException e) {
            e.printStackTrace();
        }


    }
}
