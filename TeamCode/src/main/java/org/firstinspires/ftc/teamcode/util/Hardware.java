package org.firstinspires.ftc.teamcode.util;

import com.arcrobotics.ftclib.hardware.motors.MotorEx;
import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.IMU;

import java.util.Arrays;
import java.util.List;

public class Hardware {
    private HardwareMap hardwareMap;

    public MotorEx leftFront, rightFront, leftRear, rightRear;
    public List<MotorEx> wheels;

    public IMU imu;

    public void initialize(HardwareMap hardwareMap) {
        this.hardwareMap = hardwareMap;

        initializeDriveTrain();

        initializeImu();
    }

    private void initializeDriveTrain() {
        leftFront  = new MotorEx(hardwareMap, "leftFront");
        rightFront = new MotorEx(hardwareMap, "rightFront");
        leftRear   = new MotorEx(hardwareMap, "leftRear");
        rightRear  = new MotorEx(hardwareMap, "rightRear");
        wheels = Arrays.asList(leftFront, rightFront, leftRear, rightRear);

        for (MotorEx motor : wheels) {
            motor.stopAndResetEncoder();
            motor.setRunMode(MotorEx.RunMode.RawPower);
            motor.setZeroPowerBehavior(MotorEx.ZeroPowerBehavior.BRAKE);
        }

        // TODO: Reverse the motors according to your own robot
        leftFront.setInverted(false);
        leftRear.setInverted(false);
        rightFront.setInverted(true);
        rightRear.setInverted(true);
    }

    private void initializeImu() {
        imu = hardwareMap.get(IMU.class, "imu");

        // TODO: Change imuOrientation to match the imu orientation on your robot
        RevHubOrientationOnRobot imuOrientation = new RevHubOrientationOnRobot(
                RevHubOrientationOnRobot.LogoFacingDirection.UP,
                RevHubOrientationOnRobot.UsbFacingDirection.BACKWARD
        );

        imu.initialize(new IMU.Parameters(imuOrientation));

        imu.resetYaw();
    }
}
