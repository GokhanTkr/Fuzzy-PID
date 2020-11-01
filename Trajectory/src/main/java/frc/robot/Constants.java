
package frc.robot;

import edu.wpi.first.wpilibj.kinematics.DifferentialDriveKinematics;
import frc.robot.subsystems.Listener;

public final class Constants {
    static Listener Listener = new Listener();

    public final class MotorPorts {
        public static final int LeftFrontMotorPort = 0;

        public static final int RightFrontMotorPort = 1;

        public static final int LeftBackMotorPort = 2;

        public static final int RightBackMotorPort = 3;

        public static final int RightIntakeMotorPort = 4;

        public static final int LeftIntakeMotorPort = 5;

        public static final int LeftShooterMotorPort = 6;

        public static final int RightShooterMotorPort = 7;

        public static final int SpinDexterMotorPort = 8;

    }

        // DEGISECEK::..::..::..::DEGISECEK::..::..::..::DEGISECEK
        public static final double ksVolts = 0.22;
        public static final double kvVoltSecondsPerMeter = 1.98;
        public static final double kaVoltSecondsSquaredPerMeter = 0.2;
        public static final int maxSpeedMetersPerSecond = 10;
        public static final int maxAccelerationMetersPerSecondSq = 2;


        public static final double kRamseteB = 2;
        public static final double kRamseteZeta = 0.7;


        public static final double ks = 5;
        public static final double kv = 3;
        public static final double kp = 0.8;
        public static final double ki = 0.0005;
        public static final double kd = 1.5;

        
        // DEGİSECEK::..::..::..::..::..::DEGİSECEK
        public static final double shooterarea = 5.0;
        public static final double FiveBallDelay = 7.1;
        public static final double ThreeBallDelay = 5.0;
        public static final double kPDriveVel = 8.5;


        public static final double kTrackwidthMeters = 0.69;
        public static final DifferentialDriveKinematics kDriveKinematics =
                new DifferentialDriveKinematics(kTrackwidthMeters);

    public static final class ButtonPorts {
        public static final int xboxport = 10;
        public static final int tankleft = 2;
        public static final int tankright = 5;
        public static final int elevatorport = 10;
    }

    public final static class Equations {
        public static double shooterValue = Listener.DistanceValue() * 0.4910 + 5.939;
        public static double shooterInterval = shooterValue / 12;
    }

}
