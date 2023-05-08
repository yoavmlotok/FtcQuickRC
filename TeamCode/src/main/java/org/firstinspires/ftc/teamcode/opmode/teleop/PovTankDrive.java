package org.firstinspires.ftc.teamcode.opmode.teleop;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.util.Hardware;

@TeleOp(name = "P.O.V Tank Drive", group = "tank")
public class PovTankDrive extends OpMode {
    private final Hardware hardware = new Hardware();

    @Override
    public void init() {
        hardware.initialize(hardwareMap);
    }

    @Override
    public void loop() {
        double drive = -gamepad1.left_stick_y;
        double turn  = gamepad1.right_stick_x;

        double left  = drive + turn;
        double right = drive - turn;

        double max = Math.max(Math.abs(left), Math.abs(right));
        if (max > 1) {
            left /= max;
            right /= max;
        }

        hardware.leftFront.setPower(left);
        hardware.leftRear.setPower(left);
        hardware.rightFront.setPower(right);
        hardware.rightRear.setPower(right);
    }
}
