package frc.robot.commands.shootgazin;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.SubsysMagazin;


public class spinTheDexterFwd extends CommandBase {


    private final SubsysMagazin subsysMagazin = frc.robot.subsystems.SubsysMagazin.getInstance();


    public spinTheDexterFwd(SubsysMagazin subsysMagazin) {
        addRequirements(this.subsysMagazin);
    }


    public void initilaze() {
    }


    public void execute() {
        subsysMagazin.SpinTheDexterFwd();
    }

    
    public boolean isFinished() {
        return false;
    }


    public void end(boolean interrupted) {
        subsysMagazin.stopRolling();
    }


}
