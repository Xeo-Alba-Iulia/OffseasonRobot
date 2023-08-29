package org.firstinspires.ftc.teamcode.common.commands.intakecommands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.common.subsystems.IntakeSubsystem;

public class SpinIntakeCommand extends CommandBase {
    private final IntakeSubsystem intakeSubsystem;
    public SpinIntakeCommand(IntakeSubsystem intakeSubsystem) {
        this.intakeSubsystem = intakeSubsystem;
        addRequirements(intakeSubsystem);
    }

    public void initialize() {
        intakeSubsystem.runIntake();
    }

    public boolean isFinished() {
            return true;
    }
}
