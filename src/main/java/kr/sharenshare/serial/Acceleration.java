package kr.sharenshare.serial;

import lombok.Getter;

@Getter
public class Acceleration {

    private double x;
    private double y;
    private double z;

    public Acceleration(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public void update(Acceleration ua) {
        x = ua.x;
        y = ua.y;
        z = ua.z;
    }

    @Override
    public String toString() {
        return String.format("%.3f %.3f %.3f", x, y, z);
    }

}
