package org.firstinspires.ftc.teamcode.common.commands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.common.subsystems.IntakeSubsystem;

public class IntakeCommand extends CommandBase {
    private final IntakeSubsystem intakeSubsystem;
    public IntakeCommand(IntakeSubsystem subsystem) {
        intakeSubsystem = subsystem;
        addRequirements(subsystem);
    }

    @Override
    public void initialize() {
        intakeSubsystem.runIntake();
        intakeSubsystem.stopIntake();
        intakeSubsystem.reverseIntake();

    }

    @Override
    public boolean isFinished() {
        return true;
    }
}