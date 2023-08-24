package org.firstinspires.ftc.teamcode.common.commands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.common.subsystems.IntakeSubsytem;

public class IntakeCommand extends CommandBase {

    private final IntakeSubsytem intake;

    public IntakeCommand(IntakeSubsytem intake) {
        this.intake=intake;
        addRequirements(intake);
    }



}
