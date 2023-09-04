package org.firstinspires.ftc.teamcode.opmodes;

import com.arcrobotics.ftclib.command.CommandOpMode;
import com.arcrobotics.ftclib.command.button.Button;
import com.arcrobotics.ftclib.command.button.GamepadButton;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.arcrobotics.ftclib.hardware.motors.MotorEx;
import com.qualcomm.hardware.lynx.LynxModule;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.common.commands.DrivetrainCommand;
import org.firstinspires.ftc.teamcode.common.commands.intakecommands.SpinIntakeCommand;
import org.firstinspires.ftc.teamcode.common.commands.intakecommands.StopIntakeCommand;
import org.firstinspires.ftc.teamcode.common.commands.shootercommands.RunShooterCommand;
import org.firstinspires.ftc.teamcode.common.commands.shootercommands.StopShooterCommand;
import org.firstinspires.ftc.teamcode.common.commands.turretcommands.TurretCommand;
import org.firstinspires.ftc.teamcode.common.subsystems.DriveSubsystem;
import org.firstinspires.ftc.teamcode.common.subsystems.IntakeSubsystem;
import org.firstinspires.ftc.teamcode.common.subsystems.OdometrySubsystem;
import org.firstinspires.ftc.teamcode.common.subsystems.ShooterSubsystem;
import org.firstinspires.ftc.teamcode.common.subsystems.TurretSubsystem;

import java.util.List;

@TeleOp(name = "comanda")
public class Teleop extends CommandOpMode {

    @Override
    public void initialize() {
        List<LynxModule> allHubs = hardwareMap.getAll(LynxModule.class);
        for (LynxModule hub : allHubs) {
            hub.setBulkCachingMode(LynxModule.BulkCachingMode.AUTO);
        }

        MotorEx frontLeft = new MotorEx(hardwareMap, "MotorFrontLeft", Motor.GoBILDA.RPM_435);
        MotorEx frontRight = new MotorEx(hardwareMap, "MotorFrontRight", Motor.GoBILDA.RPM_435);
        MotorEx backLeft = new MotorEx(hardwareMap, "MotorBackLeft", Motor.GoBILDA.RPM_435);
        MotorEx backRight = new MotorEx(hardwareMap, "MotorBackRight", Motor.GoBILDA.RPM_435);
        DriveSubsystem drive = new DriveSubsystem(frontLeft, frontRight, backLeft, backRight);

        OdometrySubsystem odometry = new OdometrySubsystem(
                backLeft.encoder, frontRight.encoder, backLeft.encoder);

        Motor intakeMotor = new Motor(hardwareMap, "intake", Motor.GoBILDA.BARE);
        IntakeSubsystem intake = new IntakeSubsystem(intakeMotor);
        SpinIntakeCommand spinIntake = new SpinIntakeCommand(intake);
        StopIntakeCommand stopIntake = new StopIntakeCommand(intake);

        MotorEx turretMotor = new MotorEx(hardwareMap, "turret");
        TurretSubsystem turret = new TurretSubsystem(turretMotor);
        TurretCommand turnTurret = new TurretCommand(turret, odometry::getPose);
        turret.setDefaultCommand(turnTurret);

        MotorEx shooterMotor = new MotorEx(hardwareMap, "shooter");
        ShooterSubsystem shooter = new ShooterSubsystem(shooterMotor);
        RunShooterCommand runShooter = new RunShooterCommand(shooter);
        StopShooterCommand stopShooter = new StopShooterCommand(shooter);

        GamepadEx driverOp = new GamepadEx(gamepad1);
        GamepadEx toolsOp = new GamepadEx(gamepad2);

        Button intakeButton = new GamepadButton(toolsOp, GamepadKeys.Button.A);
        Button shooterButton = new GamepadButton(toolsOp, GamepadKeys.Button.X);

        intakeButton.toggleWhenPressed(spinIntake, stopIntake);
        shooterButton.toggleWhenPressed(runShooter, stopShooter);

        DrivetrainCommand driveCommand = new DrivetrainCommand(drive,
                driverOp::getLeftY,
                driverOp::getLeftX,
                driverOp::getRightX);
        drive.setDefaultCommand(driveCommand);

    }
}
