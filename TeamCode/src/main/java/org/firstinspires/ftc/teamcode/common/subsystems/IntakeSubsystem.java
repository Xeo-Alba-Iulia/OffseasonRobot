package org.firstinspires.ftc.teamcode.common.subsystems;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.hardware.motors.Motor;

public class IntakeSubsystem extends SubsystemBase {
    private final Motor intakeMotor;
    public IntakeSubsystem(Motor intakeMotor) {
        this.intakeMotor = intakeMotor;
        intakeMotor.setInverted(true);
        intakeMotor.setZeroPowerBehavior(Motor.ZeroPowerBehavior.BRAKE);

    }

    public void runIntake() {
        intakeMotor.set(1);
    }

    public void stopIntake() {
        intakeMotor.set(0);
    }

    public void reverseIntake(){
        intakeMotor.set(-1);
    }

}
