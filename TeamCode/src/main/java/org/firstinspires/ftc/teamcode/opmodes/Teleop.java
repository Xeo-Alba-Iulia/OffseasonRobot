package org.firstinspires.ftc.teamcode.opmodes;

import com.arcrobotics.ftclib.command.CommandScheduler;
import com.arcrobotics.ftclib.command.button.GamepadButton;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.arcrobotics.ftclib.gamepad.ToggleButtonReader;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.qualcomm.hardware.lynx.LynxModule;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.common.commands.intakecommands.SpinIntakeCommand;
import org.firstinspires.ftc.teamcode.common.subsystems.DriveSubsystem;
import org.firstinspires.ftc.teamcode.common.subsystems.IntakeSubsystem;
import org.firstinspires.ftc.teamcode.common.subsystems.TurretSubsystem;

import java.util.List;

@TeleOp(name = "ciuri buri")
public class Teleop extends OpMode {
    private DriveSubsystem drive;


    // Declare subsystems and commands
    // Declare gamepads
    private GamepadEx gamepadEx1;
    private GamepadEx gamepadEx2;
    private TurretSubsystem turret;
    private IntakeSubsystem intake;
    private SpinIntakeCommand runIntake;
    private SpinIntakeCommand stopIntake;
    private SpinIntakeCommand reverseIntake;
    private GamepadButton intakeButton;
    ToggleButtonReader intakeToggle;
    List<LynxModule> allHubs;

    @Override
    public void init() {
        allHubs = hardwareMap.getAll(LynxModule.class);
        for (LynxModule hub : allHubs) {
            hub.setBulkCachingMode(LynxModule.BulkCachingMode.MANUAL);
        }
        gamepadEx1 = new GamepadEx(gamepad1);
        gamepadEx2 = new GamepadEx(gamepad2);
//        drive = new Drivetrain(hardwareMap);
//        drive = new DriveSubsystem(hardwareMap);
        intake = new IntakeSubsystem(new Motor(hardwareMap, "intake"));
        runIntake = new SpinIntakeCommand(intake);
        CommandScheduler.getInstance();
        intakeButton = new GamepadButton(
                gamepadEx2, GamepadKeys.Button.A
        );
         intakeToggle = new ToggleButtonReader(gamepadEx2, GamepadKeys.Button.A);
    }

    private void readGamepads() {
        gamepadEx1.readButtons();
        gamepadEx2.readButtons();
    }

    public void intake() {
        if(intakeToggle.getState()) {
            intake.runIntake();
        }
        else {
            intake.stopIntake();
        }
        intakeToggle.readValue();
    }

    @Override
    public void loop() {
        for (LynxModule hub : allHubs) {
            hub.clearBulkCache();
        }
        readGamepads();
//        drive.update(gamepad1);
        intake();
        CommandScheduler.getInstance().run();
    }
}
