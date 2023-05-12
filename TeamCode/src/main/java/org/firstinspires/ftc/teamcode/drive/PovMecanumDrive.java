package org.firstinspires.ftc.teamcode.drive;

import org.firstinspires.ftc.teamcode.util.Hardware;

public class PovMecanumDrive {
    private Hardware hardware;

    public void initialize(Hardware hardware) {
        this.hardware = hardware;
    }

    public void drive(double forwardPower, double rightPower, double clockwisePower) {
        double leftFrontPower  = forwardPower + rightPower + clockwisePower;
        double leftRearPower   = forwardPower - rightPower + clockwisePower;
        double rightFrontPower = forwardPower - rightPower - clockwisePower;
        double rightRearPower  = forwardPower + rightPower - clockwisePower;

        double max = Math.max(
                Math.max(Math.abs(leftFrontPower), Math.abs(leftRearPower)),
                Math.max(Math.abs(rightFrontPower), Math.abs(rightRearPower))
        );
        if (max > 1) {
            leftFrontPower  /= max;
            leftRearPower   /= max;
            rightFrontPower /= max;
            rightRearPower  /= max;
        }

        hardware.leftFront.setPower(leftFrontPower);
        hardware.leftRear.setPower(leftRearPower);
        hardware.rightFront.setPower(rightFrontPower);
        hardware.rightRear.setPower(rightRearPower);
    }
}
