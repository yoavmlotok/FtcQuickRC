package org.firstinspires.ftc.teamcode.drive;

import org.firstinspires.ftc.teamcode.util.Hardware;

public class StandardDrive {
    private Hardware hardware;

    private double powerMultiplier;

    public StandardDrive() {
        setPowerMultiplier(1);
    }

    public void initialize(Hardware hardware) {
        this.hardware = hardware;
    }

    public void drive(double forwardPower, double clockwisePower) {
        double leftPower  = forwardPower + clockwisePower;
        double rightPower = forwardPower - clockwisePower;

        double max = Math.max(Math.abs(leftPower), Math.abs(rightPower));
        if (max > 1) {
            leftPower  /= max;
            rightPower /= max;
        }

        hardware.leftFront.setPower(leftPower * powerMultiplier);
        hardware.leftRear.setPower(leftPower * powerMultiplier);
        hardware.rightFront.setPower(rightPower * powerMultiplier);
        hardware.rightRear.setPower(rightPower * powerMultiplier);
    }

    public void setPowerMultiplier(double powerMultiplier) {
        this.powerMultiplier = powerMultiplier;
    }
}
