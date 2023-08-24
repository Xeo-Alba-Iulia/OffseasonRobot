package org.firstinspires.ftc.teamcode.common.subsystems;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class IntakeSubsystem extends SubsystemBase {
    private final DcMotorEx intakeMotor;
    public IntakeSubsystem(HardwareMap hwMap, String name) {
        intakeMotor = hwMap.get(DcMotorEx.class, name);
    }

    public void runIntake() {
        intakeMotor.setPower(1);
    }

    public void stopIntake() {
        intakeMotor.setPower(0);
    }

    public void reverseIntake(){
        intakeMotor.setPower(-1);
    }

}
