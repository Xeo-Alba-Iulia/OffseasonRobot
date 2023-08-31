package org.firstinspires.ftc.teamcode.common.commands.TurretCommands;

import androidx.annotation.NonNull;

import com.arcrobotics.ftclib.command.CommandBase;
import com.arcrobotics.ftclib.geometry.Vector2d;

import org.firstinspires.ftc.teamcode.common.subsystems.TurretSubsystem;

import java.util.function.Supplier;

public class TurnToScore extends CommandBase {
    private final TurretSubsystem turret;
    private final Vector2d robotPose;

    public TurnToScore(TurretSubsystem subsystem, @NonNull Supplier<Vector2d> robotPose) {
        this.turret = subsystem;
        this.robotPose = robotPose.get();
        addRequirements(subsystem);
    }



    @Override
    public void execute() {
        turret.updateAutoTrack(robotPose);
    }

    @Override
    public boolean isFinished() {
       return turret.reachedTarget();
    }

}
