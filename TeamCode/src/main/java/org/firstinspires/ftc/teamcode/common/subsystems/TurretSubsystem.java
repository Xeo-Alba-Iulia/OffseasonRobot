package org.firstinspires.ftc.teamcode.common.subsystems;

import androidx.annotation.NonNull;

import com.acmerobotics.dashboard.config.Config;
import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.controller.PIDFController;
import com.arcrobotics.ftclib.geometry.Pose2d;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.arcrobotics.ftclib.hardware.motors.MotorEx;

import org.firstinspires.ftc.teamcode.common.Constants;
@Config
public class TurretSubsystem extends SubsystemBase {
    private final MotorEx turretMotor;

    public TurretSubsystem(@NonNull MotorEx turretMotor) {
        this.turretMotor = turretMotor;
        turretMotor.setZeroPowerBehavior(Motor.ZeroPowerBehavior.BRAKE);
        pid.setTolerance(tolerance);
        register();
    }

    public static double TURRET_RATIO = 0.5; // 1.0 / 16.0 TODO Calculate Turret Ratio

    public static double kp = 0;
    public static double ki = 0;
    public static double kd = 0;
    public static double kf = 0;
    public static double tolerance = 0;

    private final PIDFController pid = new PIDFController(kp, ki, kd, kf);

    /**
     * Sets the target position for the turret's PID controller
     *
     * @param position Position to go to in degrees
     */
    public final void setReference(double position) {
        pid.setSetPoint(degreesToTicks(position));
    }

    public boolean reachedTarget() {
        return pid.atSetPoint();
    }


    private double getAngleToBin(@NonNull Pose2d robotPose) {
        double destinationAngle = Math.atan2(Constants.BIN_POSITION.getX() - robotPose.getX(),
                Constants.BIN_POSITION.getY() - robotPose.getY());
        return destinationAngle - robotPose.getHeading();
    }


    public void updateAutoTrack(Pose2d robotPose) {
        double angle = getAngleToBin(robotPose);
        setReference(angle);
        updateController();
    }

    private void updateController() {
        turretMotor.set(pid.calculate());
    }


    private int degreesToTicks(double degrees) {
        double encoderResolution = 537.7; //TODO Findout the CPR of the motor that will be used for the turret
        double encoderResolutionAtTurretOutput = encoderResolution * TURRET_RATIO;
        return (int) (encoderResolutionAtTurretOutput * degrees / 360.0);
    }

}
