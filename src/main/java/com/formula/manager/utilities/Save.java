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

import java.io.*;

public class Save {

    public static String ApplicationFolder = System.getenv("APPDATA") + "\\Formula Route";

    //public static void saveCarSettings(Car car) throws IOException {
            //File selectedFile = new File(ApplicationFolder + "\\CarSettings.car");

            //FileOutputStream fileOutput = new FileOutputStream(selectedFile);
            //ObjectOutputStream objectStream = new ObjectOutputStream(fileOutput);

            //objectStream.writeObject(car);

            //objectStream.close();
            //fileOutput.close();
    //}

    //public static Car openCarPresets() throws IOException, ClassNotFoundException {
        //Car car;
        //File selectedFile = new File(ApplicationFolder + "\\CarSettings.car");

        //FileInputStream fileInput = new FileInputStream(selectedFile);
        //ObjectInputStream objectStream = new ObjectInputStream(fileInput);

        //car = (Car) objectStream.readObject();

        //objectStream.close();
        //fileInput.close();

        //return car;
    //}

}
