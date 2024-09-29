package com.caselab.warship.util;

import com.caselab.warship.model.Point;
import com.caselab.warship.model.Ship;
import lombok.experimental.UtilityClass;

import java.util.List;

@UtilityClass
public class ShipGameUtil {
    public static void hit(Ship ship, Point point) {
        List<Point> coordinates = ship.getCoordinates();
        for (int i = 0; i < coordinates.size(); i++) {
            if (coordinates.get(i).equals(point)) {
                ship.getHitStatus().set(i, true);
            }
        }
    }

    public static boolean isDead(Ship ship) {
        for (Boolean hit : ship.getHitStatus()) {
            if (!hit) {
                return false;
            }
        }

        return true;
    }
}
