package org.firstinspires.ftc.teamcode.opmode.teleop;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.util.Hardware;

@TeleOp(name = "P.O.V Mecanum Drive", group = "mecanum")
public class PovMecanumDrive extends OpMode {
    private final Hardware hardware = new Hardware();

    @Override
    public void init() {
        hardware.initialize(hardwareMap);
    }

    @Override
    public void loop() {
        double drive  = -gamepad1.left_stick_y;
        double strafe = gamepad1.left_stick_x;
        double turn   = gamepad1.right_stick_x;

        double leftFront  = drive + strafe + turn;
        double leftRear   = drive - strafe + turn;
        double rightFront = drive - strafe - turn;
        double rightRear  = drive + strafe - turn;

        double max = Math.max(
                        Math.max(Math.abs(leftFront),Math.abs(leftRear)),
                        Math.max(Math.abs(rightFront),Math.abs(rightRear))
        );
        if (max > 1) {
            leftFront  /= max;
            leftRear   /= max;
            rightFront /= max;
            rightRear  /= max;
        }

        hardware.leftFront.setPower(leftFront);
        hardware.leftRear.setPower(leftRear);
        hardware.rightFront.setPower(rightFront);
        hardware.rightRear.setPower(rightRear);
    }
}
