package org.firstinspires.ftc.teamcode.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.common.subsystems.Drivetrain;

@Autonomous
public class DirectionDebugDontTouch extends LinearOpMode {
    @Override
    public void runOpMode()  {
        Drivetrain drive = new Drivetrain(hardwareMap);
        waitForStart();
        while(opModeIsActive()) {
            if(isStopRequested())
                return;
            drive.setPower(0.3);
        }

    }
}
