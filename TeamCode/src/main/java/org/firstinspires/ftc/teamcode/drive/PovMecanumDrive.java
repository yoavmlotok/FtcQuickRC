package org.firstinspires.ftc.teamcode.drive;

import org.firstinspires.ftc.teamcode.util.Hardware;

public class PovMecanumDrive {
    private Hardware hardware;

    private double powerMultiplier;

    public PovMecanumDrive() {
        setPowerMultiplier(1);
    }

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

        hardware.leftFront.setPower(leftFrontPower * powerMultiplier);
        hardware.leftRear.setPower(leftRearPower * powerMultiplier);
        hardware.rightFront.setPower(rightFrontPower * powerMultiplier);
        hardware.rightRear.setPower(rightRearPower * powerMultiplier);
    }

    public void setPowerMultiplier(double powerMultiplier) {
        this.powerMultiplier = powerMultiplier;
    }
}
