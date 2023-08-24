package org.firstinspires.ftc.teamcode.opmodes;

import com.qualcomm.hardware.lynx.LynxModule;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotorEx;

import org.firstinspires.ftc.teamcode.subsystems.Drivetrain;

import java.util.List;

@com.qualcomm.robotcore.eventloop.opmode.TeleOp(name = "armata moldovei")
public class TeleOp extends OpMode {
    private Drivetrain drive;
    private DcMotorEx intake;

    boolean currentA;
    boolean lastA;
    boolean intakeToggle;

    @Override
    public void init() {
        drive = new Drivetrain(hardwareMap);
        intake = hardwareMap.get(DcMotorEx.class, "intake");
        List<LynxModule> allHubs = hardwareMap.getAll(LynxModule.class);
        for (LynxModule hub : allHubs) {
            hub.setBulkCachingMode(LynxModule.BulkCachingMode.AUTO);
        }
    }

    public void intake() {
        lastA = currentA;
        currentA = gamepad1.a;
        if (currentA) {
            intakeToggle = !intakeToggle;
            if (intakeToggle) {
                intake.setPower(0);
            } else {
                intake.setPower(1);
            }
        }
    }


    @Override
    public void loop() {
//        drive.update(gamepad1);
        intake();
    }
}
