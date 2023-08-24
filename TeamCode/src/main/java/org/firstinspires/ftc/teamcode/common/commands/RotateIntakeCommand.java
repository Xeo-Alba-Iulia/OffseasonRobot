package org.firstinspires.ftc.teamcode.common.commands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.common.subsystems.IntakeSubsystem;

public class RotateIntakeCommand extends CommandBase {
    private final IntakeSubsystem intakeSubsystem;
    public RotateIntakeCommand(IntakeSubsystem subsystem) {
        intakeSubsystem = subsystem;
        addRequirements(subsystem);
    }

    @Override
    public void initialize() {
        intakeSubsystem.rotate();
    }

    @Override
    public boolean isFinished() {
        return true;
    }
}
