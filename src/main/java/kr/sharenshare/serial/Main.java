package kr.sharenshare.serial;

import com.fazecast.jSerialComm.SerialPort;

public class Main {

    public static void main(String[] args) {

        IMUSet imuSet = IMUSet.getInstance();

        Thread serverThread = new Thread(new ServerExecutable(imuSet));
        serverThread.start();

        SerialPort serialPort = SerialPortWrapper.getSerialPort();
        serialPort.setBaudRate(115200);

        if (!serialPort.openPort()) throw new RuntimeException("Can't Open Port");
        System.out.println("Open Port: " + serialPort.getSystemPortName());

        System.out.println("Added: MessageListener");
        serialPort.addDataListener(new IMUMessageListener(imuSet));

        while (serialPort.isOpen()) ;

        serialPort.removeDataListener();
        System.out.println("Closed Port");
        serialPort.closePort();

    }

}
