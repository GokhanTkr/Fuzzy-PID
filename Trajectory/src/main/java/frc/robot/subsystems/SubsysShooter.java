package frc.robot.subsystems;

import edu.wpi.first.wpilibj.PWMVictorSPX;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class SubsysShooter extends SubsystemBase {
    

    Listener Listener = new Listener();


    public static SubsysShooter INSTANCE;


    public static SubsysShooter getInstance() {
        // Fast (non-synchronized) check to reduce overhead of acquiring a lock when
        // it's not needed
        if (INSTANCE == null) {
            // Lock to make thread safe
            synchronized (SubsysShooter.class) {
                // check nullness again as multiple threads can reach above null check
                if (INSTANCE == null) {
                    INSTANCE = new SubsysShooter();
                }
            }
        }
        return INSTANCE;
    }


    PWMVictorSPX ShooterMotorLeft = new PWMVictorSPX(Constants.MotorPorts.LeftShooterMotorPort);
    PWMVictorSPX ShooterMotorRight = new PWMVictorSPX(Constants.MotorPorts.RightShooterMotorPort);

    // Creating a speed controller group for shooter motors.
    SpeedControllerGroup ShooterMotors = new SpeedControllerGroup(ShooterMotorLeft,
            ShooterMotorRight);


    public void shootTheBall() {
        ShooterMotors.set(0.7);
    }


    public void stopShooting() {
        ShooterMotors.set(0.0);
    }

    
    public void ShootWithVP() {
        if (Listener.DistanceValue() != 0) {
            ShooterMotors.set(Constants.Equations.shooterInterval);
        }

    }


    public void ShootAtStart() {
        ShooterMotors.set(0.2222);
    }


}
