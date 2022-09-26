package kr.sharenshare.serial;

import com.fazecast.jSerialComm.SerialPort;
import com.fazecast.jSerialComm.SerialPortEvent;
import com.fazecast.jSerialComm.SerialPortMessageListener;

public class IMUMessageListener implements SerialPortMessageListener {

    private final IMUSet imuSet;

    public IMUMessageListener(IMUSet imuSet) {
        this.imuSet = imuSet;
    }

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
        String message = new String(delimitedMessage).trim();

        if (message.split(",").length == 10) {
            MeasurementMessage mm = new MeasurementMessage(message);
            imuSet.updateValue(mm.getIndex(), mm.getQuerternion(), mm.getAcceleration());
        } else {
            System.out.println("Unknown Message: " + message);
        }


    }

}
