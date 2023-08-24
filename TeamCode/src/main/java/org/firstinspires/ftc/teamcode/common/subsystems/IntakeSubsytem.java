package org.firstinspires.ftc.teamcode.common.subsystems;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.arcrobotics.ftclib.gamepad.ToggleButtonReader;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;


public class IntakeSubsytem extends SubsystemBase {

    private final DcMotor motorIntake;
    private GamepadEx gamepad1;
    ToggleButtonReader toggleButton = new ToggleButtonReader(
            gamepad1, GamepadKeys.Button.A
    );

    public IntakeSubsytem(final HardwareMap hwMap, final String name) {

        motorIntake = hwMap.get(DcMotor.class, "ciuri buri");
    }

    public void intake() {

        if(toggleButton.getState()) {
            motorIntake.setPower(0);
            }
            else {
                motorIntake.setPower(1);
            }
        }
}
