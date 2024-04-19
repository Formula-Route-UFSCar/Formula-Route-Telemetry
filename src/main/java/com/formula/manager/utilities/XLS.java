/*
 * MIT License
 *
 * Copyright (c)2023 Matheus Markies
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.formula.manager.utilities;

import java.io.File;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

import static com.formula.manager.utilities.Save.ApplicationFolder;

public class XLS {
    public static void createXLSFile(ArrayList<String> jsonList) throws IOException {
        //XSSFWorkbook wb = new XSSFWorkbook();
        //XSSFSheet sheet = wb.createSheet(" Sensor Details");
        //Row row = sheet.createRow(0);

        //Cell titleyaw = row.createCell(0);
        //titleyaw.setCellValue("yaw");

        int rowNum = 1;
        int colNum = 0;

        for (int i =0;i<jsonList.size();i++) {
            //Row bodyRow = sheet.createRow(i+1);

            //Cell yaw = bodyRow.createCell(0);
            //yaw.setCellValue(jsonList.get(i).getYaw());

            colNum = 0;
        }
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
        FileOutputStream outputStream = new FileOutputStream(ApplicationFolder + "\\" + "WhellSensor_" +timeStamp+ ".xlsx");
        //wb.write(outputStream);
        System.out.println(" Excel file generated");
    }
}
