package org.firstinspires.ftc.teamcode.util;

import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.IMU;

import java.util.Arrays;
import java.util.List;

public class Hardware {
    private HardwareMap hardwareMap;

    public DcMotorEx leftFront, leftRear, rightFront, rightRear;
    public List<DcMotorEx> wheels;

    public IMU imu;

    public void initialize(HardwareMap hardwareMap) {
        this.hardwareMap = hardwareMap;

        initializeDriveTrain();

        initializeImu();
    }

    private void initializeDriveTrain() {
        leftFront  = hardwareMap.get(DcMotorEx.class, "leftFront");
        leftRear   = hardwareMap.get(DcMotorEx.class, "leftRear");
        rightFront = hardwareMap.get(DcMotorEx.class, "rightFront");
        rightRear  = hardwareMap.get(DcMotorEx.class, "rightRear");
        wheels = Arrays.asList(leftFront, leftRear, rightFront, rightRear);

        for (DcMotorEx motor : wheels) {
            // TODO: If motor encoders are connected change runmode to RUN_USING_ENCODER
            motor.setMode(DcMotorEx.RunMode.RUN_WITHOUT_ENCODER);
            motor.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
        }

        // TODO: Change motor directions according to your own robot
        leftFront.setDirection(DcMotorEx.Direction.FORWARD);
        leftRear.setDirection(DcMotorEx.Direction.FORWARD);
        rightFront.setDirection(DcMotorEx.Direction.REVERSE);
        rightRear.setDirection(DcMotorEx.Direction.REVERSE);
    }

    private void initializeImu() {
        imu = hardwareMap.get(IMU.class, "imu");

        // TODO: Change imuOrientation to match the imu orientation on your robot
        RevHubOrientationOnRobot imuOrientation = new RevHubOrientationOnRobot(
                RevHubOrientationOnRobot.LogoFacingDirection.UP,
                RevHubOrientationOnRobot.UsbFacingDirection.BACKWARD
        );

        imu.initialize(new IMU.Parameters(imuOrientation));
    }
}
