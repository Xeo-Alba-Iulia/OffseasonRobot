package org.firstinspires.ftc.teamcode.common.commands.shootercommands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.common.subsystems.ShooterSubsystem;

public class RunShooterCommand extends CommandBase {
    private final ShooterSubsystem shooter;
    private static double velocity = 0.0; // in ticks / second

    public RunShooterCommand(ShooterSubsystem shooter) {
        this.shooter = shooter;
        addRequirements(shooter);
    }

    @Override
    public void initialize() {
        shooter.setVelocity(velocity);
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
