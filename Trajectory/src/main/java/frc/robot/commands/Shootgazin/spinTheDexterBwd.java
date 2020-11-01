package frc.robot.commands.shootgazin;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.SubsysMagazin;

public class spinTheDexterBwd extends CommandBase {


    private final SubsysMagazin subsysMagazin = frc.robot.subsystems.SubsysMagazin.getInstance();


    public spinTheDexterBwd() {
        addRequirements(subsysMagazin);
    }


    public void initilaze() {
    }


    public void execute() {
        subsysMagazin.SpinTheDexterBwd();
    }


    public boolean isFinished() {
        return false;
    }

    
    public void end(boolean interrupted) {
        subsysMagazin.stopRolling();
    }


}
