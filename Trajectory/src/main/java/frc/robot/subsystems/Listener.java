package frc.robot.subsystems;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;

public class Listener {

    public NetworkTableInstance inst = NetworkTableInstance.getDefault();
    public NetworkTable table = inst.getTable("Vp-and-Shooter");


    // Getting the rotationValues via Vision Processing.
    public double RotationValue() {
        NetworkTableEntry rotation = table.getEntry("Rotation");
        return rotation.getDouble(0);
    }

    // Getting the distance values via Vision Processing.
    public double DistanceValue() {
        NetworkTableEntry distance = table.getEntry("Distance");
        return distance.getDouble(0);
    }

    // Getting the Hexagon Status via Vision Processing.
    public boolean HexagonStatus() {
        NetworkTableEntry hexagon = table.getEntry("HexagonStatus");
        return hexagon.getBoolean(false);
    }


}
