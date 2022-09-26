package kr.sharenshare.serial;

import lombok.Getter;

@Getter
public class MeasurementMessage {

    private final int channel;
    private final int index;
    private final Querternion querternion;
    private final Acceleration acceleration;
    private final int battery;
    private final int timestamp;

    public MeasurementMessage(String message) {
        try {
            String[] strings = message.split(",");
            String[] channelAndIndex = strings[0].split("-");

            channel = Integer.parseInt(channelAndIndex[0]);
            index = Integer.parseInt(channelAndIndex[1]);

            double z = Double.parseDouble(strings[1]);
            double y = Double.parseDouble(strings[2]);
            double x = Double.parseDouble(strings[3]);
            double w = Double.parseDouble(strings[4]);

            querternion = new Querternion(w, x, y, z);

            double ax = Double.parseDouble(strings[5]);
            double ay = Double.parseDouble(strings[6]);
            double az = Double.parseDouble(strings[7]);

            acceleration = new Acceleration(ax, ay, az);

            battery = Integer.parseInt(strings[8]);

            timestamp = Integer.parseInt(strings[9]);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
