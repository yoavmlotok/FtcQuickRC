package org.firstinspires.ftc.teamcode.drive;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
import org.firstinspires.ftc.teamcode.util.Hardware;

public class FoMecanumDrive {
    private Hardware hardware;

    public void initialize(Hardware hardware) {
        this.hardware = hardware;
    }

    public void drive(double xPower, double yPower, double clockwisePower) {
        double distance = Math.hypot(xPower, yPower);
        double absoluteAngle = Math.atan2(xPower, yPower);
        double relativeAngle = angleWrap(absoluteAngle - (Math.toRadians(robotAngle())));

        double relativeX = Math.cos(relativeAngle) * distance;
        double relativeY = Math.sin(relativeAngle) * distance;

        double absRXPlusAbsRY = Math.abs(relativeX) + Math.abs(relativeY);
        double forwardPower = (Math.round(relativeX / absRXPlusAbsRY * 10.0) / 10.0) * distance;
        double rightPower = (Math.round(relativeY / absRXPlusAbsRY * 10.0) / 10.0) * distance;

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
            rightRearPower  /= max;
            rightFrontPower /= max;
        }

        hardware.leftFront.setPower(leftFrontPower);
        hardware.leftRear.setPower(leftRearPower);
        hardware.rightFront.setPower(rightFrontPower);
        hardware.rightRear.setPower(rightRearPower);
    }

    private double robotAngle() {
        Orientation angles = hardware.imu.getRobotOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES);

        return (AngleUnit.DEGREES.fromUnit(angles.angleUnit,angles.firstAngle));
    }

    private double angleWrap(double angle){
        while (angle < -Math.PI) {
            angle += 2*Math.PI;
        }
        while (angle > Math.PI){
            angle -= 2*Math.PI;
        }
        return angle;
    }
}
