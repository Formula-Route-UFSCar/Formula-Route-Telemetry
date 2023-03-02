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
import com.formula.objects.WheelSensor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

import static com.formula.manager.utilities.Save.ApplicationFolder;

public class XLS {
    public static void createXLSFile(ArrayList<WheelSensor> jsonList) throws IOException {
        XSSFWorkbook wb = new XSSFWorkbook();
        XSSFSheet sheet = wb.createSheet(" Sensor Details");
        Row row = sheet.createRow(0);

        Cell titleyaw = row.createCell(0);
        Cell titlepitch = row.createCell(1);
        Cell titleroll = row.createCell(2);
        Cell titleeulerX = row.createCell(3);
        Cell titleeulerY = row.createCell(4);
        Cell titleeulerZ = row.createCell(5);
        Cell titleaccX = row.createCell(6);
        Cell titleaccY = row.createCell(7);
        Cell titleaccZ = row.createCell(8);
        Cell titlelinearAccX = row.createCell(9);
        Cell titlelinearAccY = row.createCell(10);
        Cell titlelinearAccZ = row.createCell(11);

        titleyaw.setCellValue("yaw");
        titlepitch.setCellValue("pitch");
        titleroll.setCellValue("roll");
        titleeulerX.setCellValue("eulerX");
        titleeulerY.setCellValue("eulerY");
        titleeulerZ.setCellValue("eulerZ");
        titleaccX.setCellValue("accX");
        titleaccY.setCellValue("accY");
        titleaccZ.setCellValue("accZ");
        titlelinearAccX.setCellValue("linearAccX");
        titlelinearAccY.setCellValue("linearAccY");
        titlelinearAccZ.setCellValue("linearAccZ");

        int rowNum = 1;
        int colNum = 0;

        for (int i =0;i<jsonList.size();i++) {
            Row bodyRow = sheet.createRow(i+1);

            Cell yaw = bodyRow.createCell(0);
            Cell pitch = bodyRow.createCell(1);
            Cell roll = bodyRow.createCell(2);
            Cell eulerX = bodyRow.createCell(3);
            Cell eulerY = bodyRow.createCell(4);
            Cell eulerZ = bodyRow.createCell(5);
            Cell accX = bodyRow.createCell(6);
            Cell accY = bodyRow.createCell(7);
            Cell accZ = bodyRow.createCell(8);
            Cell linearAccX = bodyRow.createCell(9);
            Cell linearAccY = bodyRow.createCell(10);
            Cell linearAccZ = bodyRow.createCell(11);

            yaw.setCellValue(jsonList.get(i).getYaw());
            pitch.setCellValue(jsonList.get(i).getPitch());
            roll.setCellValue(jsonList.get(i).getRoll());
            eulerX.setCellValue(jsonList.get(i).getEulerX());
            eulerY.setCellValue(jsonList.get(i).getEulerY());
            eulerZ.setCellValue(jsonList.get(i).getEulerZ());
            accX.setCellValue(jsonList.get(i).getAccX());
            accY.setCellValue(jsonList.get(i).getAccY());
            accZ.setCellValue(jsonList.get(i).getAccX());
            linearAccX.setCellValue(jsonList.get(i).getLinearAccX());
            linearAccY.setCellValue(jsonList.get(i).getLinearAccY());
            linearAccZ.setCellValue(jsonList.get(i).getLinearAccZ());

            colNum = 0;
        }
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
        FileOutputStream outputStream = new FileOutputStream(ApplicationFolder + "\\" + "WhellSensor_" +timeStamp+ ".xlsx");
        wb.write(outputStream);
        System.out.println(" Excel file generated");
    }
}
