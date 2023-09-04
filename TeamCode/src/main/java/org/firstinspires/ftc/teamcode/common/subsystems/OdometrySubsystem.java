package org.firstinspires.ftc.teamcode.common.subsystems;

import androidx.annotation.NonNull;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.geometry.Pose2d;
import com.arcrobotics.ftclib.hardware.motors.MotorEx;
import com.arcrobotics.ftclib.kinematics.HolonomicOdometry;

public class OdometrySubsystem extends SubsystemBase {

    public static final double TRACKWIDTH = 0;
    public static final double CENTER_WHEEL_OFFSET = 0;
    public static final double WHEEL_DIAMETER = 1.38;
    public static final double TICKS_PER_REV = 8192;

    public static final double DISTANCE_PER_PULSE = Math.PI * WHEEL_DIAMETER / TICKS_PER_REV;


    // create our encoders
    private MotorEx.Encoder leftEncoder;
    private MotorEx.Encoder rightEncoder;
    private MotorEx.Encoder perpEncoder;

    public OdometrySubsystem(@NonNull MotorEx.Encoder leftEncoder, MotorEx.Encoder rightEncoder, MotorEx.Encoder perpEncoder) {
        this.leftEncoder = leftEncoder;
        this.rightEncoder = rightEncoder;
        this.perpEncoder = perpEncoder;
        leftEncoder.setDistancePerPulse(DISTANCE_PER_PULSE);
        rightEncoder.setDistancePerPulse(DISTANCE_PER_PULSE);
        perpEncoder.setDistancePerPulse(DISTANCE_PER_PULSE);
        register();
    }


    private final HolonomicOdometry odometry = new HolonomicOdometry(
            () -> leftEncoder.getDistance(),
            () -> rightEncoder.getDistance(),
            () -> perpEncoder.getDistance(),
            TRACKWIDTH, CENTER_WHEEL_OFFSET
    );

    public Pose2d getPose() {
        return odometry.getPose();
    }

    public void update() {
        odometry.updatePose();
    }

    @Override
    public void periodic() {
        update();
    }

}
