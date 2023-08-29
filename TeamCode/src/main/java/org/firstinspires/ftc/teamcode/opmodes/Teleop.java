package org.firstinspires.ftc.teamcode.opmodes;

import com.arcrobotics.ftclib.command.CommandScheduler;
import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.button.Button;
import com.arcrobotics.ftclib.command.button.GamepadButton;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.arcrobotics.ftclib.gamepad.ToggleButtonReader;
import com.qualcomm.hardware.lynx.LynxModule;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.common.commands.IntakeCommand;
import org.firstinspires.ftc.teamcode.common.subsystems.IntakeSubsystem;
import org.firstinspires.ftc.teamcode.common.subsystems.TurretSubsystem;

import java.util.List;

@TeleOp(name = "ciuri buri")
public class Teleop extends OpMode {
//    private Drivetrain drive;

    Button intakeButton;
    List<LynxModule> allHubs;

    // Declare subsystems and commands
    // Declare gamepads
    private GamepadEx gamepadEx1;
    private GamepadEx gamepadEx2;
    private TurretSubsystem turret;
    private IntakeSubsystem intake;
    private IntakeCommand runIntake;
    private IntakeCommand stopIntake;
    private IntakeCommand reverseIntake;
    ToggleButtonReader intakeToggle;

    @Override
    public void init() {
        allHubs = hardwareMap.getAll(LynxModule.class);
        for (LynxModule hub : allHubs) {
            hub.setBulkCachingMode(LynxModule.BulkCachingMode.MANUAL);
        }
        gamepadEx1 = new GamepadEx(gamepad1);
        gamepadEx2 = new GamepadEx(gamepad2);
//        drive = new Drivetrain(hardwareMap);
        intake = new IntakeSubsystem(hardwareMap, "intake");
        runIntake = new IntakeCommand(intake);
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
