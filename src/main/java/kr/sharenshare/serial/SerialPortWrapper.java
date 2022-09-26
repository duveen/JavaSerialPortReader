package kr.sharenshare.serial;

import com.fazecast.jSerialComm.SerialPort;

import java.util.Scanner;
import java.util.logging.Logger;

public class SerialPortWrapper {

    public static final Logger log = Logger.getLogger(SerialPortWrapper.class.getSimpleName());

    public static SerialPort getSerialPort() {

        SerialPort[] serialPorts = SerialPort.getCommPorts();
        int index = 0;
        System.out.println("Connectable Serial Port List");
        for (SerialPort serialPort : serialPorts) {
            System.out.println(" > " + index + ": " + serialPort.getSystemPortName());
            index++;
        }

        System.out.print("Select Serial Port Number: ");
        Scanner scanner = new Scanner(System.in);

        index = scanner.nextInt();

        return serialPorts[index];

    }

}
