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

import com.fazecast.jSerialComm.SerialPort;
import com.formula.frames.controller.MainFrameController;
import javafx.application.Platform;

public class SerialReadder{

    private SerialPort serialPort;
    private String serialPortName;

    static int PORT_RATE = 9600;
    public static int PACKET_SIZE_IN_BYTES = 8;
    private MainFrameController controller;

    public SerialReadder(String serialPortName, MainFrameController controller) {
        this.controller = controller;
        this.serialPortName = serialPortName;
    }

    public SerialReadder(){
        super();
    }

    public boolean connect(){
        SerialPort[] serialPorts = SerialManager.getSerialPortList();

        for (SerialPort port: serialPorts)
            if(port.getDescriptivePortName().equals(serialPortName)) {
                serialPort = port;
                break;
            }

        if (serialPort.isOpen())
            return false;
        else {
            serialPort.openPort();

            serialPort.setBaudRate(SerialReadder.PORT_RATE);
            Platform.runLater(
                    new SerialRunnable(serialPort,controller)
                );

            return serialPort.isOpen();
        }
    }

    public synchronized void close(){
        if(serialPort.isOpen())
            serialPort.closePort();
    }

    public SerialPort getSerialPort() {
        return serialPort;
    }
}

