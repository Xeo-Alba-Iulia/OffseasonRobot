package org.firstinspires.ftc.teamcode.common.subsystems;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.arcrobotics.ftclib.hardware.motors.MotorEx;
import com.arcrobotics.ftclib.kinematics.HolonomicOdometry;
import com.arcrobotics.ftclib.geometry.Pose2d;
import com.arcrobotics.ftclib.kinematics.Odometry;
public class OdometrySubsystem extends SubsystemBase {

    public static final double TRACKWIDTH =0;
    public static final double CENTER_WHEEL_OFFSET = 0;
    public static final double WHEEL_DIAMETER=1.38;
    public static final double TICKS_PER_REV=8192;

    public static final double DISTANCE_PER_PULSE=Math.PI *WHEEL_DIAMETER/ TICKS_PER_REV;


    // create our encoders
    private Motor leftEncoder;
    private  Motor rightEncoder;
    private  Motor perpEncoder;

    public OdometrySubsystem(Motor leftEncoder, Motor rightEncoder, Motor perpEncoder) {
        this.leftEncoder = leftEncoder;
        this.rightEncoder = rightEncoder;
        this.perpEncoder=perpEncoder;
        register();
    }


    HolonomicOdometry odometry = new HolonomicOdometry(
            leftEncoder::getDistance,
            rightEncoder::getDistance,
            perpEncoder::getDistance,
            TRACKWIDTH, CENTER_WHEEL_OFFSET
    );

    public Pose2d getPose() {
        return odometry.getPose();}

    public void update() {
        odometry.updatePose();

        leftEncoder.setDistancePerPulse(DISTANCE_PER_PULSE);
        rightEncoder.setDistancePerPulse(DISTANCE_PER_PULSE);
        perpEncoder.setDistancePerPulse(DISTANCE_PER_PULSE);}

    public void periodic() {
        odometry.updatePose();

        leftEncoder.setDistancePerPulse(DISTANCE_PER_PULSE);
        rightEncoder.setDistancePerPulse(DISTANCE_PER_PULSE);
        perpEncoder.setDistancePerPulse(DISTANCE_PER_PULSE);
    }

}
