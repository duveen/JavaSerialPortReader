package kr.sharenshare.serial;

import com.fazecast.jSerialComm.SerialPort;
import com.fazecast.jSerialComm.SerialPortEvent;
import com.fazecast.jSerialComm.SerialPortMessageListener;

public class MessageListener implements SerialPortMessageListener {

    @Override
    public byte[] getMessageDelimiter() {
        return new byte[]{ (byte)10 };
    }

    @Override
    public boolean delimiterIndicatesEndOfMessage() {
        return true;
    }

    @Override
    public int getListeningEvents() {
        return SerialPort.LISTENING_EVENT_DATA_RECEIVED;
    }

    @Override
    public void serialEvent(SerialPortEvent serialPortEvent) {
        byte[] delimitedMessage = serialPortEvent.getReceivedData();
        System.out.println(new String(delimitedMessage).trim());

//        for (byte b : delimitedMessage) {
//            System.out.print((int)b);
//            System.out.print("(");
//            if (b != 10) {
//                System.out.print((char)b);
//            }
//            System.out.print(")");
//            System.out.print(" ");
//        }
//
//        System.out.println();
    }

}
