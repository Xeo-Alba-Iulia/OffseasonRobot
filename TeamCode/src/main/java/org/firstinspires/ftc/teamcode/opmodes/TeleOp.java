package org.firstinspires.ftc.teamcode.opmodes;

import com.qualcomm.hardware.lynx.LynxModule;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotorEx;

import java.util.List;

@com.qualcomm.robotcore.eventloop.opmode.TeleOp(name = "armata moldovei")
public class TeleOp extends OpMode {
//    private Drivetrain drive;
    private DcMotorEx intake;



    @Override
    public void init() {
//        drive = new Drivetrain(hardwareMap);
        intake = hardwareMap.get(DcMotorEx.class, "intake");
        List<LynxModule> allHubs = hardwareMap.getAll(LynxModule.class);
        for(LynxModule hub : allHubs) {
            hub.setBulkCachingMode(LynxModule.BulkCachingMode.AUTO);
        }


    }




    @Override
    public void loop() {
//        drive.update(gamepad1);
        if(gamepad1.a)
            intake.setPower(1);
        else
            intake.setPower(0);
    }
}
