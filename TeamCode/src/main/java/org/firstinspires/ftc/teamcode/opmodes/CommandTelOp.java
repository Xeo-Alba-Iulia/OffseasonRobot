package org.firstinspires.ftc.teamcode.opmodes;

import com.arcrobotics.ftclib.command.CommandOpMode;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.arcrobotics.ftclib.gamepad.ToggleButtonReader;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.arcrobotics.ftclib.hardware.motors.MotorEx;

import org.firstinspires.ftc.teamcode.common.commands.DrivetrainCommand;
import org.firstinspires.ftc.teamcode.common.commands.intakecommands.SpinIntakeCommand;
import org.firstinspires.ftc.teamcode.common.commands.intakecommands.StopIntakeCommand;
import org.firstinspires.ftc.teamcode.common.subsystems.DriveSubsystem;
import org.firstinspires.ftc.teamcode.common.subsystems.IntakeSubsystem;

public class CommandTelOp extends CommandOpMode {

    private MotorEx frontLeft, frontRight, backLeft, backRight;
    private DriveSubsystem drive;
    private DrivetrainCommand driveCommand;

    private Motor intakeMotor;
    private IntakeSubsystem intake;
    private SpinIntakeCommand spinIntake;
    private StopIntakeCommand stopIntake;
    private GamepadEx gamepadEx2, gamepadEx1;
    private ToggleButtonReader intakeToggleButton;

    @Override
    public void initialize() {
        frontLeft = new MotorEx(hardwareMap, "MotorFrontLeft");
        frontRight = new MotorEx(hardwareMap, "MotorFrontRight");
        backLeft = new MotorEx(hardwareMap, "MotorBackLeft");
        backRight = new MotorEx(hardwareMap, "MotorBackRight");

        drive = new DriveSubsystem(frontLeft, frontRight, backLeft, backRight);

        intake = new IntakeSubsystem(intakeMotor);
        spinIntake = new SpinIntakeCommand(intake);
        stopIntake = new StopIntakeCommand(intake);

        intakeToggleButton = new ToggleButtonReader(gamepadEx1, GamepadKeys.Button.A);
        if (intakeToggleButton.getState()) {
            spinIntake.schedule();
        }


        gamepadEx1 = new GamepadEx(gamepad1);
        gamepadEx2 = new GamepadEx(gamepad2);

        driveCommand = new DrivetrainCommand(drive,
                () -> gamepadEx1.getLeftY(),
                () -> gamepadEx1.getLeftX(),
                () -> gamepadEx1.getRightX());


        register(drive);
        drive.setDefaultCommand(driveCommand);




    }
}
