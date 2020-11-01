package frc.robot.commands.autonomous;


import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.SubsysMagazin;
import frc.robot.subsystems.SubsysShooter;


public class ShootAtStart extends CommandBase {


    private final SubsysShooter subsysShooter = frc.robot.subsystems.SubsysShooter.getInstance();
    private final SubsysMagazin subsysMagazin = frc.robot.subsystems.SubsysMagazin.getInstance();


    public ShootAtStart() {

        addRequirements(subsysShooter, subsysMagazin);
    }


    public void initilaze() {
    }


    public void execute() {

        subsysShooter.ShootAtStart();
        subsysMagazin.SpinTheDexterFwd();
    }

    public boolean isFinished() {
        return false;
    }

    public void end(boolean interrupted) {
        
        subsysShooter.stopShooting();
        subsysMagazin.stopRolling();
    }


}
