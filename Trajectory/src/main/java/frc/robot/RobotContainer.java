package frc.robot;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

import edu.wpi.first.wpilibj.Filesystem;
import edu.wpi.first.wpilibj.Ultrasonic;
import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj.controller.RamseteController;
import edu.wpi.first.wpilibj.controller.SimpleMotorFeedforward;
import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj.geometry.Translation2d;
import edu.wpi.first.wpilibj.trajectory.Trajectory;
import edu.wpi.first.wpilibj.trajectory.TrajectoryConfig;
import edu.wpi.first.wpilibj.trajectory.TrajectoryGenerator;
import edu.wpi.first.wpilibj.trajectory.TrajectoryUtil;
import edu.wpi.first.wpilibj.trajectory.constraint.DifferentialDriveVoltageConstraint;
import frc.robot.commands.intake.intakeStart;
import frc.robot.commands.autonomous.ShootAtStart;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.SubsysIntake;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.RamseteCommand;


public class RobotContainer {


    ShootAtStart shootAtStart;

    public DriveTrain drive = new DriveTrain();


    Ultrasonic sonic = new Ultrasonic(5, 6);


    public RobotContainer() {
        // Configure the button bindings
        configureButtonBindings();
    }


    private void configureButtonBindings() {
    }


    public Command Trajectory() {

        // Defining the trajectory folders path
        String TrajectoryIlkJSON = "paths/Ilk.wpilib.json";

        // Creating a config for trajectory.
        TrajectoryConfig config = new TrajectoryConfig(Constants.maxSpeedMetersPerSecond, Constants.maxAccelerationMetersPerSecondSq);
    

        config.setKinematics(drive.getKinematics());


        try {
            

            Path trajectoryPath = Filesystem.getDeployDirectory().toPath().resolve(TrajectoryIlkJSON);

            // Defining the trajectory
            System.out.println(trajectoryPath.toString());


            Trajectory trajectory = TrajectoryUtil.fromPathweaverJson(trajectoryPath);

            // Adjusting the motor values with ramsete command.
            RamseteCommand trajectorycommand = new RamseteCommand(trajectory, drive::getPose,

                    new RamseteController(2.0, 0.7), drive.getFeedForward(),

                    drive.getKinematics(),

                    drive::getSpeeds,

                    drive.getleftPID(), drive.getrightPID(),

                    drive::setOutput,

                    drive

            );

            // Try to drive to the trajectory, stop and start the intake.
            return trajectorycommand.andThen(() -> drive.setOutput(0, 0)).andThen(() -> SubsysIntake.intakeStart());

        }
        // Report an error if you can't go to trajectory.
        catch (IOException ex) {
            ex.printStackTrace();
        }
        return Trajectory();

    }

}
