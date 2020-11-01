package frc.robot.subsystems;

import edu.wpi.first.wpilibj.PWMVictorSPX;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class SubsysMagazin extends SubsystemBase {


    public static SubsysMagazin INSTANCE;

  
    public static SubsysMagazin getInstance() {
        // Fast (non-synchronized) check to reduce overhead of acquiring a lock when
        // it's not needed
        if (INSTANCE == null) {
            // Lock to make thread safe
            synchronized (SubsysMagazin.class) {
                // check nullness again as multiple threads can reach above null check
                if (INSTANCE == null) {
                    INSTANCE = new SubsysMagazin();
                }
            }
        }
        return INSTANCE;
    }

    // Creating a speed controller group for Magazin Motors 
    PWMVictorSPX DexterMotor = new PWMVictorSPX(Constants.MotorPorts.SpinDexterMotorPort);


    public void SpinTheDexterFwd() {
        DexterMotor.set(0.7);
    }


    public void SpinTheDexterBwd() {
        DexterMotor.set(-0.7);
    }

    
    public void stopRolling() {

        DexterMotor.set(0.0);
    }


}
