package org.firstinspires.ftc.teamcode.common.commands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.common.subsystems.TurretSubsystem;

public class TurnToScore extends CommandBase {
    private final TurretSubsystem turret;
    private final double ShootingPosition = 270.0;
    public TurnToScore(TurretSubsystem turret) {
        this.turret = turret;
        addRequirements(turret);
    }

    @Override
    public void initialize() {
        turret.setReference(ShootingPosition);
    }

    @Override
    public boolean isFinished() {
        return turret.atReference();
    }

}
