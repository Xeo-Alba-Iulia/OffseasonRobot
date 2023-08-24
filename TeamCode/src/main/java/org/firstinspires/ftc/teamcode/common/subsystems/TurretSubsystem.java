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
        double tolerance = 50;
        pid.setTolerance(tolerance);
    }

    private final double kp = 0;
    private final double ki = 0;
    private final double kd = 0;
    private final double kf = 0;
    private final PIDFController pid = new PIDFController(kp, ki, kd, kf);

    public void setReference(double degrees) {
        pid.setSetPoint(degreesToTicks(degrees));
    }

    public boolean atReference() {
        return pid.atSetPoint();
    }

    private double degreesToTicks(double degrees) {
        double encoderResolution = 537.7;
        return encoderResolution * degrees / 360.0;
    }



    @Override
    public void periodic() {
        double encoderPosition = turretMotor.getCurrentPosition();
        turretMotor.setPower(pid.calculate(encoderPosition));
    }


}
