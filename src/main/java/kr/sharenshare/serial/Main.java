package kr.sharenshare.serial;

import com.fazecast.jSerialComm.SerialPort;

public class Main {

    public static void main(String[] args) {

        SerialPort serialPort = SerialPortWrapper.getSerialPort();
        serialPort.setBaudRate(230400);

        if (!serialPort.openPort()) throw new RuntimeException("Can't Open Port");
        serialPort.addDataListener(new MessageListener());

        while (serialPort.isOpen()) ;

        serialPort.removeDataListener();
        System.out.println("Closed Port");
        serialPort.closePort();

    }

}
