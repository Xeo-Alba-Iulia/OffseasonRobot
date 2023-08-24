package org.firstinspires.ftc.teamcode.common.commands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.common.subsystems.TurretSubsystem;

public class TurnToScore extends CommandBase {
    private final TurretSubsystem turret;
    public TurnToScore(TurretSubsystem turret) {
        this.turret = turret;
        addRequirements(turret);
    }





}
