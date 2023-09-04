package org.firstinspires.ftc.teamcode.common.commands.turretcommands;

import androidx.annotation.NonNull;

import com.arcrobotics.ftclib.command.CommandBase;
import com.arcrobotics.ftclib.geometry.Pose2d;

import org.firstinspires.ftc.teamcode.common.subsystems.TurretSubsystem;

import java.util.function.Supplier;

public class TurretCommand extends CommandBase {
    private final TurretSubsystem turret;
    private final Pose2d robotPose;

    public TurretCommand(TurretSubsystem subsystem, @NonNull Supplier<Pose2d> robotPose) {
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
