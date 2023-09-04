package org.firstinspires.ftc.teamcode.opmodes;

import com.arcrobotics.ftclib.command.CommandOpMode;
import com.arcrobotics.ftclib.command.button.Button;
import com.arcrobotics.ftclib.command.button.GamepadButton;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.common.commands.intakecommands.SpinIntakeCommand;
import org.firstinspires.ftc.teamcode.common.commands.intakecommands.StopIntakeCommand;
import org.firstinspires.ftc.teamcode.common.subsystems.IntakeSubsystem;
import org.firstinspires.ftc.teamcode.common.subsystems.OdometrySubsystem;

@TeleOp(name = "comanda")
public class CommandTeleOp extends CommandOpMode {

    @Override
    public void initialize() {
//        MotorEx frontLeft = new MotorEx(hardwareMap, "MotorFrontLeft", Motor.GoBILDA.RPM_435);
//        MotorEx frontRight = new MotorEx(hardwareMap, "MotorFrontRight", Motor.GoBILDA.RPM_435);
//        MotorEx backLeft = new MotorEx(hardwareMap, "MotorBackLeft", Motor.GoBILDA.RPM_435);
//        MotorEx backRight = new MotorEx(hardwareMap, "MotorBackRight", Motor.GoBILDA.RPM_435);
//
//        DriveSubsystem drive = new DriveSubsystem(frontLeft, frontRight, backLeft, backRight);

        Motor intakeMotor = new Motor(hardwareMap, "intake", Motor.GoBILDA.BARE);
        IntakeSubsystem intake = new IntakeSubsystem(intakeMotor);
        SpinIntakeCommand spinIntake = new SpinIntakeCommand(intake);
        StopIntakeCommand stopIntake = new StopIntakeCommand(intake);



        GamepadEx driverOp = new GamepadEx(gamepad1);
        GamepadEx toolsOp = new GamepadEx(gamepad2);
        Button intakeButton = new GamepadButton(driverOp, GamepadKeys.Button.A);

        intakeButton.toggleWhenPressed(spinIntake, stopIntake);

//        DrivetrainCommand driveCommand = new DrivetrainCommand(drive,
//                driverOp::getLeftY,
//                driverOp::getLeftX,
//                driverOp::getRightX);


//        drive.setDefaultCommand(driveCommand);
    }
}
