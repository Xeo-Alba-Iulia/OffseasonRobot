package org.firstinspires.ftc.teamcode.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.subsystems.Drivetrain;

@Autonomous
public class DirectionDebugDontTouch extends LinearOpMode {
    private Drivetrain drive;
    private ElapsedTime timer;
    @Override
    public void runOpMode() throws InterruptedException {
        drive = new Drivetrain(hardwareMap);
        waitForStart();
        while(opModeIsActive()) {
            drive.setPower(0.3);
        }

    }
}
