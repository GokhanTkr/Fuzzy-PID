package frc.robot.subsystems;

import edu.wpi.first.wpilibj.PWMVictorSPX;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class SubsysIntake extends SubsystemBase {


    public static SubsysIntake INSTANCE;

    // Classical getInstance()
    public static SubsysIntake getInstance() {
        // Fast (non-synchronized) check to reduce overhead of acquiring a lock when
        // it's not needed
        if (INSTANCE == null) {
            // Lock to make thread safe
            synchronized (SubsysIntake.class) {
                // check nullness again as multiple threads can reach above null check
                if (INSTANCE == null) {
                    INSTANCE = new SubsysIntake();
                }
            }
        }
        return INSTANCE;
    }

    // Creating a speed controller group for intake motors.
    static PWMVictorSPX IntakeMotorLeft = new PWMVictorSPX(Constants.MotorPorts.LeftIntakeMotorPort);
    static PWMVictorSPX IntakeMotorRight = new PWMVictorSPX(Constants.MotorPorts.RightIntakeMotorPort);


    static SpeedControllerGroup IntakeMotors = new SpeedControllerGroup(IntakeMotorLeft, IntakeMotorRight);


    public SubsysIntake() {
    }


    public static void intakeStart() {
        IntakeMotors.set(0.50);
    }


    public static void intakeStop() {
        IntakeMotors.set(0.0);
    }

    
    public void intakeGetTheBall() {
        IntakeMotors.set(0.55);
    }


}
