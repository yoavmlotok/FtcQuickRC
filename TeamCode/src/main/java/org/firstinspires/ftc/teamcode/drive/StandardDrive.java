package org.firstinspires.ftc.teamcode.drive;

import org.firstinspires.ftc.teamcode.util.Hardware;

public class StandardDrive {
    private Hardware hardware;

    public void initialize(Hardware hardware) {
        this.hardware = hardware;
    }

    public void drive(double forward, double clockwise) {
        double leftPower  = forward + clockwise;
        double rightPower = forward - clockwise;

        double max = Math.max(Math.abs(leftPower), Math.abs(rightPower));
        if (max > 1) {
            leftPower  /= max;
            rightPower /= max;
        }

        hardware.leftFront.setPower(leftPower);
        hardware.leftRear.setPower(leftPower);
        hardware.rightFront.setPower(rightPower);
        hardware.rightRear.setPower(rightPower);
    }
}
