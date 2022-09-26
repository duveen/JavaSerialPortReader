package kr.sharenshare.serial;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class IMUSet {

    private static final Logger log = Logger.getLogger(IMUSet.class.getSimpleName());
    private static volatile IMUSet INSTANCE;

    public static IMUSet getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new IMUSet();
        }

        return INSTANCE;
    }

    private final static int ARRAY_SIZE = 24;
    private final List<Querternion> querternions;
    private final List<Acceleration> accelerations;

    private IMUSet() {
        querternions = new ArrayList<>();
        accelerations = new ArrayList<>();
        for (int i = 0; i < ARRAY_SIZE; i++) {
            querternions.add(new Querternion(0, 0, 0, 0));
            accelerations.add(new Acceleration(0, 0, 0));
        }
    }

    public synchronized void updateValue(int index, Querternion u, Acceleration ua) {
        Querternion q = querternions.get(index);
        q.update(u);

        Acceleration a = accelerations.get(index);
        a.update(ua);

        // log.info("Update Sensor: Index = " + index + ", Q = " + q + ", A = " + a);
    }

    public synchronized String printQuerternions() {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < ARRAY_SIZE; i++) {
            Querternion q = querternions.get(i);
            builder.append(q.toString());
            builder.append(" ");

            Acceleration a = accelerations.get(i);
            builder.append(a.toString());
            builder.append(" ");
        }

        return builder.toString().trim();
    }
}
