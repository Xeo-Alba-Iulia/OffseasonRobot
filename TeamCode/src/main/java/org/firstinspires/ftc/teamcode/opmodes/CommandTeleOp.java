package org.firstinspires.ftc.teamcode.opmodes;

import com.arcrobotics.ftclib.command.CommandOpMode;
import com.arcrobotics.ftclib.command.button.Button;
import com.arcrobotics.ftclib.command.button.GamepadButton;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.arcrobotics.ftclib.hardware.motors.MotorEx;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.common.commands.DrivetrainCommand;
import org.firstinspires.ftc.teamcode.common.commands.intakecommands.SpinIntakeCommand;
import org.firstinspires.ftc.teamcode.common.commands.intakecommands.StopIntakeCommand;
import org.firstinspires.ftc.teamcode.common.subsystems.DriveSubsystem;
import org.firstinspires.ftc.teamcode.common.subsystems.IntakeSubsystem;

@TeleOp(name = "comanda")
public class CommandTeleOp extends CommandOpMode {




    @Override
    public void initialize() {
        MotorEx frontLeft, frontRight, backLeft, backRight;
        DriveSubsystem drive;
        DrivetrainCommand driveCommand;
        Motor intakeMotor;
        IntakeSubsystem intake;
        SpinIntakeCommand spinIntake;
        StopIntakeCommand stopIntake;
        GamepadEx driverOp, systemOp;
         Button intakeButton;
        frontLeft = new MotorEx(hardwareMap, "MotorFrontLeft", Motor.GoBILDA.RPM_435);
        frontRight = new MotorEx(hardwareMap, "MotorFrontRight", Motor.GoBILDA.RPM_435);
        backLeft = new MotorEx(hardwareMap, "MotorBackLeft", Motor.GoBILDA.RPM_435);
        backRight = new MotorEx(hardwareMap, "MotorBackRight", Motor.GoBILDA.RPM_435);

        drive = new DriveSubsystem(frontLeft, frontRight, backLeft, backRight);

        intakeMotor = new Motor(hardwareMap, "intake", Motor.GoBILDA.BARE);
        intake = new IntakeSubsystem(intakeMotor);
        spinIntake = new SpinIntakeCommand(intake);
        stopIntake = new StopIntakeCommand(intake);



        driverOp = new GamepadEx(gamepad1);
        systemOp = new GamepadEx(gamepad2);
        intakeButton = new GamepadButton(driverOp, GamepadKeys.Button.A);

        intakeButton.toggleWhenPressed(spinIntake, stopIntake);

        driveCommand = new DrivetrainCommand(drive,
                driverOp::getLeftY,
                driverOp::getLeftX,
                driverOp::getRightX);


        register(drive);
        drive.setDefaultCommand(driveCommand);

    }
}
