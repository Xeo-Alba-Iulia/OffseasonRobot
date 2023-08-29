package org.firstinspires.ftc.teamcode.common.commands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.common.subsystems.TurretSubsystem;

public class TurnToScore extends CommandBase {
    private final TurretSubsystem turret;

    public TurnToScore(TurretSubsystem subsystem) {
        this.turret = subsystem;
        addRequirements(subsystem);
    }

    @Override
    public void initialize() {
        double shootingPosition = 270.0;
        turret.setReference(shootingPosition);
    }

    @Override
    public boolean isFinished() {

        return turret.atReference();
    }

}
