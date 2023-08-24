package org.firstinspires.ftc.teamcode.subsystems;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.controller.PIDFController;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Turret extends SubsystemBase {
    private DcMotorEx turretMotor;
    public Turret(final HardwareMap hwMap, final String name) {
        turretMotor = hwMap.get(DcMotorEx.class, name);
    }

    private final double kp = 0, ki = 0, kd = 0, kf = 0;
    private PIDFController pid = new PIDFController(kp, ki, kd, kf);

    private final double encoderResolution = 537.7;
    private double degreesToTicks(double degrees) {
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
