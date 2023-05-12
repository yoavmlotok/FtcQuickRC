package org.firstinspires.ftc.teamcode.drive;

import org.firstinspires.ftc.teamcode.util.Hardware;

public class TankDrive {
    private Hardware hardware;

    public void initialize(Hardware hardware) {
        this.hardware = hardware;
    }

    public void drive(double leftPower, double rightPower) {
        hardware.leftFront.setPower(leftPower);
        hardware.leftRear.setPower(leftPower);
        hardware.rightFront.setPower(rightPower);
        hardware.rightRear.setPower(rightPower);
    }
}
