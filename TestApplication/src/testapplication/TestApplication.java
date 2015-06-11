/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testapplication;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

/**
 *
 * @author george
 */
public class TestApplication {

    public static ArrayList<PersonDetail> list;

    public static PersonDetail pd;

    public static DatabaseConnect dbc;

    public TestApplication() {

        String path = "/home/george/Desktop/Test.xlsx";

        pd = new PersonDetail();

        dbc = new DatabaseConnect();

      //  dbc.insertMultipleReocords();

        list = dbc.fetchPeoples();

        try {

            exportToExcel(path);

        } catch (IOException ex) {

            System.out.print(ex.getMessage());

        }

    }

    public void exportToExcel(String filePath) throws IOException {

        try {

            System.out.print("exporting to excell....");

            HSSFWorkbook hwb = new HSSFWorkbook();
            HSSFSheet sheet = hwb.createSheet("new sheet");

            HSSFRow rowhead = sheet.createRow(0);

            rowhead.createCell((short) 0).setCellValue("firstName");
            rowhead.createCell((short) 1).setCellValue("lastName");
            rowhead.createCell((short) 2).setCellValue("address");
            rowhead.createCell((short) 3).setCellValue("postalCode");
            rowhead.createCell((short) 4).setCellValue("mobileNumber");

            int j = list.size();

            System.out.println("looping through the Arraylist object...." + j);

            for (int i = 0; i <= list.size() - 1; i++) {

                System.out.println("Fetching record at index " + i);

                pd = (PersonDetail) list.get(i);

                HSSFRow row = sheet.createRow((short) i);

                System.out.println("name" + pd.getFirstName());

                row.createCell((short) 0).setCellValue(pd.getFirstName());
                row.createCell((short) 1).setCellValue(pd.getLastName());
                row.createCell((short) 2).setCellValue(pd.getAdrress());
                row.createCell((short) 3).setCellValue(pd.getPostalCode());
                row.createCell((short) 4).setCellValue(pd.getMobileNUmber());

            }

            FileOutputStream fileOut = new FileOutputStream(filePath);

            try {

                hwb.write(fileOut);

            } catch (Exception e1) {

                System.out.println(e1.getMessage());

            } finally {

                if (fileOut != null) {

                    try {

                        fileOut.close();

                    } catch (Exception e3) {

                        System.out.println(e3.getMessage());
                    }

                } else {

                    fileOut.close();
                }
            }
        } catch (Exception e) {

            System.err.println("Execpetion caught ===> " + e);

        }
        System.out.println("Your excel file has been generated!");
    }

    public static void main(String[] args) {

        new TestApplication();

    }

}
