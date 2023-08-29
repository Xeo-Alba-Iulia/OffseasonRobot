package org.firstinspires.ftc.teamcode.common.commands.intakecommands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.common.subsystems.IntakeSubsystem;

public class StopIntakeCommand extends CommandBase {
    private final IntakeSubsystem intakeSubsystem;
    public StopIntakeCommand(IntakeSubsystem intakeSubsystem) {
        this.intakeSubsystem = intakeSubsystem;
        addRequirements(intakeSubsystem);
    }

    public void initialize() {
        intakeSubsystem.stopIntake();
    }

    public boolean isFinished() {
        return true;
    }
}
