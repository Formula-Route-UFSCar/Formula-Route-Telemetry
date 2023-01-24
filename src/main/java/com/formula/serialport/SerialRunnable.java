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
        //mouseTrapCarManager.getMainFrameController().getRotationSeries().getData().add(new XYChart.Data<String,Double>("0",0.));
    }

    enum ReadType{
        None,LFW,RFW,LBW,RBW
    }

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

        while(scanner_stream.hasNextLine()) {
            String received_string = scanner_stream.nextLine();

            inputString = received_string;

            if(!open) {
                if (received_string.indexOf('{') != -1) {
                    //System.out.println(inputString);
                    //System.out.println();
                    jSon = inputString;
                    open = true;
                }
            }else{
                if (received_string.indexOf('}') != -1) {
                    //System.out.println(inputString);
                    //System.out.println();
                    jSon += inputString;
                    ObjectMapper mapper = new ObjectMapper();
                    try {
                        WheelSensor lib = mapper.readValue(jSon, WheelSensor.class);
                        System.out.println(lib.toString());
                    } catch (JsonProcessingException e) {
                        throw new RuntimeException(e);
                    }
                    open = false;
                }else
                    jSon += inputString;
            }

        }

    }
    public static double toDouble(byte[] bytes) {
        return ByteBuffer.wrap(bytes).getDouble();
    }
}
