package org.firstinspires.ftc.teamcode.common.subsystems;

import androidx.annotation.NonNull;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.controller.PIDFController;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class TurretSubsystem extends SubsystemBase {
    private final DcMotorEx turretMotor;
    public TurretSubsystem(@NonNull final HardwareMap hwMap, final String name) {
        turretMotor = hwMap.get(DcMotorEx.class, name);
        turretMotor.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
        pid.setTolerance(tolerance);
        register();
    }

    public static double kp = 0;
    public static double ki = 0;
    public static double kd = 0;
    public static double kf = 0;
    public static double tolerance = 0;

    private final PIDFController pid = new PIDFController(kp, ki, kd, kf);

    public void setReference(double degrees) {
        pid.setSetPoint(degreesToTicks(degrees));
    }

    public boolean atReference() {
        return pid.atSetPoint();
    }

    private int degreesToTicks(double degrees) {
        double encoderResolution = 537.7;
        return (int) (encoderResolution * degrees / 360.0);
    }



    @Override
    public void periodic() {
        double encoderPosition = turretMotor.getCurrentPosition();
        turretMotor.setPower(pid.calculate(encoderPosition));
    }


}
