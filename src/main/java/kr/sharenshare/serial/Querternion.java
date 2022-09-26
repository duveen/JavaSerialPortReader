package kr.sharenshare.serial;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Querternion {

    private double w;
    private double x;
    private double y;
    private double z;

    public Querternion(double w, double x, double y, double z) {
        this.w = w;
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public void update(Querternion u) {
        w = u.w;
        x = u.x;
        y = u.y;
        z = u.z;
    }

    @Override
    public String toString() {
        return String.format("%.4f %.4f %.4f %.4f", w, x, y, z);
    }
}
