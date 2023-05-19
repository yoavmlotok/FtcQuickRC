package org.firstinspires.ftc.teamcode.drive;

import org.firstinspires.ftc.teamcode.util.Hardware;

public class TankDrive {
    private Hardware hardware;

    private double powerMultiplier;

    public TankDrive() {
        setPowerMultiplier(1);
    }

    public void initialize(Hardware hardware) {
        this.hardware = hardware;
    }

    public void drive(double leftPower, double rightPower) {
        hardware.leftFront.setPower(leftPower * powerMultiplier);
        hardware.leftRear.setPower(leftPower * powerMultiplier);
        hardware.rightFront.setPower(rightPower * powerMultiplier);
        hardware.rightRear.setPower(rightPower * powerMultiplier);
    }

    public void setPowerMultiplier(double powerMultiplier) {
        this.powerMultiplier = powerMultiplier;
    }
}
