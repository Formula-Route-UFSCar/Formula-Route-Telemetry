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

package com.formula.serialport;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fazecast.jSerialComm.SerialPort;
import com.fazecast.jSerialComm.SerialPortEvent;
import com.fazecast.jSerialComm.SerialPortPacketListener;
import com.formula.frames.controller.MainFrameController;
import com.formula.objects.WheelSensor;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;

public class SerialRunnable implements SerialPortPacketListener, Runnable {

    private final SerialPort port;
    private MainFrameController controller;

    public SerialRunnable(SerialPort port, MainFrameController controller) {
        this.controller = controller;
        this.port = port;
    }

    @Override
    public int getPacketSize() {
        return SerialReadder.PACKET_SIZE_IN_BYTES;
    }

    @Override
    public int getListeningEvents() {
        return SerialPort.LISTENING_EVENT_DATA_AVAILABLE;
    }

    @Override
    public void run() {
        port.addDataListener(this);
    }

    enum ReadType{
        None,Wheel,Break
    }

    public enum Wheel{
        None,LFW,RFW,LBW,RBW
    }
    Wheel wheelSide;
    ReadType readType = null;
    int readingSensorIndex = 0;
    boolean getReadType = true;
    private final byte[] buffer = new byte[2048];

    String jSon = "";

    boolean open = false;
    @Override
    public void serialEvent(SerialPortEvent event) {
        if (event.getEventType() != SerialPort.LISTENING_EVENT_DATA_AVAILABLE)
            return;
        byte[] buffer = new byte[port.bytesAvailable()];

        String inputString = new String(buffer, StandardCharsets.UTF_16LE);

        Scanner scanner_stream = new Scanner(port.getInputStream());

        while (scanner_stream.hasNextLine()) {
            String received_string = scanner_stream.nextLine();

            inputString = received_string;

            //if (readType == ReadType.None){
                //switch (inputString){
                    //case "LFW:":readType = ReadType.Wheel;
                        //wheelSide = Wheel.LFW;
                        //break;
                    //case "RFW:":readType = ReadType.Wheel;
                        //wheelSide = Wheel.RFW;
                        //break;
                    //case "LBW:":readType = ReadType.Wheel;
                        //wheelSide = Wheel.LBW;
                        //break;
                    //case "RBW:":readType = ReadType.Wheel;
                        //wheelSide = Wheel.RBW;
                        //break;
                    //default:
                        //readType = ReadType.None;
                        //break;
                //}
            //}

            //if (readType == ReadType.Wheel)
                if (!open) {
                    if (received_string.indexOf('{') != -1) {
                        jSon = inputString;
                        open = true;
                    }
                } else {
                    if (received_string.indexOf('}') != -1) {
                        jSon += inputString;
                        ObjectMapper mapper = new ObjectMapper();
                        try {
                            WheelSensor wheel = mapper.readValue(jSon, WheelSensor.class);
                            controller.getDataList().add(wheel);

                            wheel.setWheel(wheelSide);
                            controller.getYaw_car_image().setRotate(-wheel.getRoll()); //Center
                            controller.getRoll_car_image().setRotate(wheel.getYaw() + 90); //Right
                            controller.getPitch_car_image().setRotate(wheel.getPitch());

                            //System.out.println(wheel.toString());
                        } catch (JsonProcessingException e) {
                            System.err.println(e);
                        }finally {
                            readType = ReadType.None;
                        }
                        readType = ReadType.None;
                        open = false;
                    } else
                        jSon += inputString;
                }
        }
    }
    public static double toDouble(byte[] bytes) {
        return ByteBuffer.wrap(bytes).getDouble();
    }
}
