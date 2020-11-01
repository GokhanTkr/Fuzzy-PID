
package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.robot.commands.autonomous.ShootAtStart;
import frc.robot.commands.autonomous.ShootWithVP;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Listener;
import frc.robot.subsystems.SubsysIntake;


public class Robot extends TimedRobot {


    SubsysIntake intakesub;
    DriveTrain DriveTrain;

    Timer    timer    = new Timer();
    Listener Listener = new Listener();
    

    private RobotContainer Container;
    private Command m_autonomousCommand;

    private Command ShootAtStart = new ShootAtStart();
    private Command ShootWithVP  = new ShootWithVP();
    

    @Override
    public void robotInit() {

        intakesub = new SubsysIntake();
        Container = new RobotContainer();

        DriveTrain = Container.drive;
        
    }


    @Override
    public void robotPeriodic() {

        CommandScheduler.getInstance().run();
    }


    @Override
    public void disabledInit() {
    }


    @Override
    public void disabledPeriodic() {
    }


    @Override
    public void autonomousInit() {

        timer.start();
        ShootAtStart.schedule();
    }


    @Override
    public void autonomousPeriodic() {

        if (5.05 >= timer.get() & timer.get() >= Constants.ThreeBallDelay) {
            ShootAtStart.cancel();
            Container.Trajectory().schedule();
        }

        // Turning an angle value with arcade drive, VisionProcessing and network tables.
        if (Listener.HexagonStatus()) {

            if (Listener.RotationValue() > -1 & Listener.RotationValue() < 1) {

                DriveTrain.mdrive.arcadeDrive(1, -Listener.RotationValue());
            }
            if (timer.get() < Constants.FiveBallDelay) {

                ShootWithVP.schedule();
            }
            ShootWithVP.cancel();
        }

        CommandScheduler.getInstance().run();
    }

    @Override
    public void teleopInit() {

        if (m_autonomousCommand != null) {
            m_autonomousCommand.cancel();
        }
    }

    @Override
    public void teleopPeriodic() {
    }

    @Override
    public void testInit() {
        // Cancels all running commands at the start of test mode.
        CommandScheduler.getInstance().cancelAll();
    }

    @Override
    public void testPeriodic() {
    }
}
