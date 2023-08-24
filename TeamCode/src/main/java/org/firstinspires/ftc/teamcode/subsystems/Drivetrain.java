package org.firstinspires.ftc.teamcode.subsystems;


import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.IMU;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;

public class Drivetrain {

    // Declare our motors
    // Make sure your ID's match your configuration
    DcMotor motorFrontLeft;
    DcMotor motorBackLeft;
    DcMotor motorFrontRight;
    DcMotor motorBackRight;

    IMU imu;


    public Drivetrain(HardwareMap hwMap) {
        motorBackLeft = hwMap.get(DcMotorEx.class, "backleft");
        motorBackRight = hwMap.get(DcMotorEx.class, "backright");
        motorFrontLeft=hwMap.get(DcMotor.class,"frontleft");
        motorFrontRight=hwMap.get(DcMotor.class,"frontright");

        motorBackLeft.setDirection(DcMotorSimple.Direction.FORWARD);

        motorFrontRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        motorBackRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        motorBackLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        motorFrontLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }

    public void setPower(double power) {
        motorBackLeft.setPower(power);
        motorBackRight.setPower(power);
        motorFrontLeft.setPower(power);
        motorFrontRight.setPower(power);
    }

    public void update(Gamepad gamepad1) {
        double drive = -gamepad1.left_stick_y; // Remember, this is reversed!
        double strafe = -gamepad1.left_stick_x * 1.1; // Counteract imperfect strafing
        double turn = gamepad1.right_stick_x;

        // Denominator is the largest motor power (absolute value) or 1
        // This ensures all the powers maintain the same ratio, but only when
        // at least one is out of the range [-1, 1]
        double denominator = Math.max(Math.abs(drive) + Math.abs(strafe) + Math.abs(turn), 1);
        double frontLeftPower = (drive + strafe + turn) / denominator;
        double backLeftPower = (drive - strafe + turn) / denominator;
        double frontRightPower = (drive - strafe - turn ) / denominator;
        double backRightPower = (drive + strafe - turn) / denominator;


        if(gamepad1.left_bumper) {
            frontLeftPower/=3;
            backRightPower/=3;
            frontRightPower/=3;
            backLeftPower/=3;
        }
        motorFrontLeft.setPower(frontLeftPower);
        motorBackLeft.setPower(backLeftPower);
        motorFrontRight.setPower(frontRightPower);
        motorBackRight.setPower(backRightPower);

    }


}
