package frc.robot.subsystems;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj.controller.SimpleMotorFeedforward;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.PWMVictorSPX;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj.interfaces.Gyro;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveKinematics;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveOdometry;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveWheelSpeeds;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.SPI;
import frc.robot.Constants;

public class DriveTrain extends SubsystemBase {

    // Definition of the drive motors.
    static PWMVictorSPX MotorLeftFront  = new PWMVictorSPX(Constants.MotorPorts.LeftFrontMotorPort);
    static PWMVictorSPX MotorRightFront = new PWMVictorSPX(Constants.MotorPorts.RightFrontMotorPort);
    static PWMVictorSPX MotorLeftBack   = new PWMVictorSPX(Constants.MotorPorts.LeftBackMotorPort);
    static PWMVictorSPX MotorRightBack  = new PWMVictorSPX(Constants.MotorPorts.RightBackMotorPort);


    Pose2d pose;


    public SpeedControllerGroup LeftMotors  = new SpeedControllerGroup(MotorLeftFront, MotorLeftBack);

    public SpeedControllerGroup RightMotors = new SpeedControllerGroup(MotorRightFront, MotorRightBack);


    Gyro gyro = new ADXRS450_Gyro(SPI.Port.kMXP);

    // Encoder definitions.
    Encoder LeftEncoder  = new Encoder(0, 1);
    Encoder RightEncoder = new Encoder(2, 3);

    // PID definitions.
    PIDController leftPID  = new PIDController(Constants.kp, Constants.ki, Constants.kd,0.02);
            
    PIDController rightPID = new PIDController(Constants.kp, Constants.ki,Constants.kd, 0.02);

    // OTHERS::.:::.::..:::..::..::..:..:..::.:..:.:..::..
    DifferentialDriveKinematics   kinematics    = new DifferentialDriveKinematics(0.68);
    DifferentialDriveOdometry     odometry      = new DifferentialDriveOdometry(getHeading());
    public SimpleMotorFeedforward feedforward   = new SimpleMotorFeedforward(Constants.ks,Constants.kv);


    public DifferentialDrive mdrive = new DifferentialDrive(LeftMotors, RightMotors);

    // Update the odometry periodicly.
    public void periodic() {
        pose = odometry.update(getHeading(), getLeftEncoderReading(), getRightEncoderReading());
        
    }

    // Motor feedforward method.
    public SimpleMotorFeedforward getFeedForward() {
        return feedforward;
    }

    // KINEMATIC Methods.
    public DifferentialDriveKinematics getKinematics() {
        return kinematics;
    }

    // PID methods.
    public PIDController getleftPID() {
        if (leftPID.getVelocityError() >10){
            leftPID.setI(0.0);
        }
        return leftPID;
    }


    public PIDController getrightPID() {
        if (rightPID.getVelocityError()> 10){
            rightPID.setI(0.0);
        }
        return rightPID;
    }

    // Pose method
    public Pose2d getPose() {
        return pose;
        
    }


    public DriveTrain() {
    }

    // Getting the encoder values.
    public double getLeftEncoderReading() {
        return LeftEncoder.getRate();
    }


    public double getRightEncoderReading() {
        return RightEncoder.getRate();
    }

    // Getting the gyro angle values.
    public Rotation2d getHeading() {

        Rotation2d angle = Rotation2d.fromDegrees(-gyro.getAngle());

        return angle;

    }

    // Getting the wheel speeds.
    public DifferentialDriveWheelSpeeds getSpeeds() {
        return new DifferentialDriveWheelSpeeds(getLeftEncoderReading(), getRightEncoderReading());
    }

    // Setting the voltage outputs.
    public void setOutput(double leftvolt, double rightvolt) {
        LeftMotors.setVoltage(leftvolt / 12);
        RightMotors.setVoltage(-rightvolt / 12);
    }


    public void fuzzyController(){

        //Changer constans.
        double Kp_Changer = 0.5;
        double Ki_Changer = 0.005;
        double Kd_Changer = 0.2;

        //Error values.
        double errorLeft = getleftPID().getPositionError();
        double errorRight = getrightPID().getPositionError();

        //(Position Error / Velocity Error) %  
        double percentLeftValue = 100/(errorLeft/getleftPID().getVelocityError());
        double percentRightValue = 100/(errorRight/getrightPID().getVelocityError());

        //(Position Error / Velocity Error) % * Changer Values. This tells us how much we need to increase
        // or decrease the controllers constant.
        double P_ChangerLeftValue = (percentLeftValue * Kp_Changer) + (percentLeftValue * -Kp_Changer);
        double I_ChangerLeftValue = (percentLeftValue * Ki_Changer) + (percentLeftValue * -Ki_Changer);
        double D_ChangerLeftValue = (percentLeftValue * Kd_Changer) + (percentLeftValue * -Kd_Changer);

        double P_ChangerRightValue = (percentRightValue * Kp_Changer) + (percentRightValue * -Kp_Changer);
        double I_ChangerRightValue = (percentRightValue * Ki_Changer) + (percentRightValue * -Ki_Changer);
        double D_ChangerRightValue = (percentRightValue * Kd_Changer) + (percentRightValue * -Kd_Changer);

        // setting the fresh tuned constants.
        getleftPID().setPID(Constants.kp + P_ChangerLeftValue, Constants.ki + I_ChangerLeftValue, Constants.kd + D_ChangerLeftValue);

        getrightPID().setPID(Constants.kp + P_ChangerRightValue, Constants.ki + I_ChangerRightValue, Constants.kd + D_ChangerRightValue);
        

    }


}
