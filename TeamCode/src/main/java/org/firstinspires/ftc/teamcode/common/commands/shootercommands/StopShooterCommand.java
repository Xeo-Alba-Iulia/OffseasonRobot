package org.firstinspires.ftc.teamcode.common.commands.shootercommands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.common.subsystems.ShooterSubsystem;

public class StopShooterCommand extends CommandBase {
    private final ShooterSubsystem shooter;

    public StopShooterCommand(ShooterSubsystem shooter) {
        this.shooter = shooter;
        addRequirements(shooter);
    }

    @Override
    public void initialize() {
        shooter.setVelocity(0.0);
    }

    @Override
    public void execute() {
        shooter.update();
    }

    @Override
    public boolean isFinished() {
        return shooter.isCruising();
    }


}
