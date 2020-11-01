package frc.robot.commands.intake;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.SubsysIntake;

public class intakeStart extends CommandBase {


    private final SubsysIntake subsysIntake = frc.robot.subsystems.SubsysIntake.getInstance();


    public intakeStart() {

        addRequirements(this.subsysIntake);
    }


    public void initilaze() {
    }


    public void execute() {
        SubsysIntake.intakeStart();
    }

    
    public boolean isFinished() {
        return false;
    }


    public void end(boolean interrupted) {
        SubsysIntake.intakeStop();

    }


}
