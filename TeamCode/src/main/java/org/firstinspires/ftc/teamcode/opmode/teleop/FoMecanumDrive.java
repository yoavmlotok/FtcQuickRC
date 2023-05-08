package org.firstinspires.ftc.teamcode.opmode.teleop;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
import org.firstinspires.ftc.teamcode.util.Hardware;

@TeleOp(name = "F.O Mecanum Drive", group = "mecanum")
public class FoMecanumDrive extends OpMode {
    private final Hardware hardware = new Hardware();

    @Override
    public void init() {
        hardware.initialize(hardwareMap);
    }

    @Override
    public void loop() {
        double x = gamepad1.left_stick_x;
        double y = -gamepad1.left_stick_y;
        double turnPower = gamepad1.right_stick_x;

        double distance = Math.hypot(x, y);
        double absoluteAngle = Math.atan2(y, x);
        double relativeAngle = angleWrap(absoluteAngle - (Math.toRadians(robotAngle())));

        double relativeX = Math.cos(relativeAngle) * distance;
        double relativeY = Math.sin(relativeAngle) * distance;

        double rXrY = Math.abs(relativeX) + Math.abs(relativeY);
        double speedX = (Math.round(relativeX / rXrY * 10.0) / 10.0) * distance;
        double speedY = (Math.round(relativeY / rXrY * 10.0) / 10.0) * distance;

        double lfP = speedX + speedY + turnPower;
        double lbP = -speedX + speedY + turnPower;
        double rbP = speedX + speedY - turnPower;
        double rfP = -speedX + speedY - turnPower;

        double max = Math.max(1.0, Math.abs(lfP));
        max = Math.max(max, Math.abs(lbP));
        max = Math.max(max, Math.abs(rbP));
        max = Math.max(max, Math.abs(rfP));

        lfP /= max;
        lbP /= max;
        rbP /= max;
        rfP /= max;

        hardware.leftFront.setPower(lfP);
        hardware.leftRear.setPower(lbP);
        hardware.rightFront.setPower(rfP);
        hardware.rightRear.setPower(rbP);
    }

    public double robotAngle() {
        Orientation angles = hardware.imu.getRobotOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES);

        return(AngleUnit.DEGREES.fromUnit(angles.angleUnit,angles.firstAngle));
    }

    public double angleWrap(double angle){
        while (angle < -Math.PI) {
            angle += 2*Math.PI;
        }
        while (angle > Math.PI){
            angle -= 2*Math.PI;
        }
        return angle;
    }
}
