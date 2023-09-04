package org.firstinspires.ftc.teamcode.common.subsystems;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.controller.PIDFController;
import com.arcrobotics.ftclib.hardware.motors.MotorEx;

public class ShooterSubsystem extends SubsystemBase {
    private final MotorEx shooterMotor;
    public static double kp = 0.0;
    public static double ki = 0.0;
    public static double kd = 0.0;
    public static double kf = 0.0;
    public static double TOLERANCE = 0.0;

    public PIDFController pid = new PIDFController(kp, ki, kd, kf);

    public ShooterSubsystem(MotorEx shooterMotor) {
        this.shooterMotor = shooterMotor;
        pid.setTolerance(TOLERANCE);
        register();
    }

    public void update() {
        double power = pid.calculate(shooterMotor.getCorrectedVelocity());
        shooterMotor.set(power);
    }

    public void setVelocity(double velo) {
        pid.setSetPoint(velo);
    }

    public boolean isCruising() {
        return pid.atSetPoint();
    }

}
