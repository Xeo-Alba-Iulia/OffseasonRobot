package org.firstinspires.ftc.teamcode.opmodes;

import com.arcrobotics.ftclib.command.CommandScheduler;
import com.arcrobotics.ftclib.command.button.Button;
import com.arcrobotics.ftclib.command.button.GamepadButton;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.qualcomm.hardware.lynx.LynxModule;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.common.commands.IntakeCommand;
import org.firstinspires.ftc.teamcode.common.subsystems.Drivetrain;
import org.firstinspires.ftc.teamcode.common.subsystems.IntakeSubsystem;
import org.firstinspires.ftc.teamcode.common.subsystems.TurretSubsystem;

import java.util.List;

@TeleOp(name = "armata moldovei")
public class Teleop extends OpMode {
    private Drivetrain drive;

    // Declare gamepads
    private final GamepadEx gamepadEx1 = new GamepadEx(gamepad1);
    private final GamepadEx gamepadEx2 = new GamepadEx(gamepad2);

    // Declare subsystems and commands

    private TurretSubsystem turret;

    private IntakeSubsystem intake;

    private IntakeCommand runIntake;
    private IntakeCommand stopIntake;
    private IntakeCommand reverseIntake;
    Button intakeButton= new GamepadButton(
            gamepadEx1, GamepadKeys.Button.A
    );

    Button reverseIntakeButton = new GamepadButton(
            gamepadEx1, GamepadKeys.Button.B
    );
    List<LynxModule> allHubs = hardwareMap.getAll(LynxModule.class);

    @Override
    public void init() {
        for (LynxModule hub : allHubs) {
            hub.setBulkCachingMode(LynxModule.BulkCachingMode.MANUAL);
        }
        drive = new Drivetrain(hardwareMap);
        intake = new IntakeSubsystem(hardwareMap, "intake");
        runIntake = new IntakeCommand(intake);
        stopIntake= new IntakeCommand(intake);
        reverseIntake=new IntakeCommand(intake);
        CommandScheduler.getInstance().reset();
    }

    @Override
    public void loop() {
        for(LynxModule hub : allHubs) {
            hub.clearBulkCache();
        }
        drive.update(gamepad1);
        intakeButton.whenPressed(runIntake);
        intakeButton.whenReleased(stopIntake);
        reverseIntakeButton.whenPressed(reverseIntake);
    }
}
