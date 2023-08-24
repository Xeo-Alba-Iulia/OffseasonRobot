package org.firstinspires.ftc.teamcode.common.subsystems;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.controller.PIDFController;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class TurretSubsystem extends SubsystemBase {
    private DcMotorEx turretMotor;
    public TurretSubsystem(final HardwareMap hwMap, final String name) {
        turretMotor = hwMap.get(DcMotorEx.class, name);
    }

    private final double kp = 0;
    private final double ki = 0;
    private final double kd = 0;
    private final double kf = 0;
    private PIDFController pid = new PIDFController(kp, ki, kd, kf);

    private double degreesToTicks(double degrees) {
        double encoderResolution = 537.7;
        return encoderResolution * degrees / 360.0;
    }

    public void spinTo(double degrees) {
        pid.setSetPoint(degreesToTicks(degrees));
    }


    @Override
    public void periodic() {
        double encoderPosition = turretMotor.getCurrentPosition();
        turretMotor.setPower(pid.calculate(encoderPosition));
    }
}
