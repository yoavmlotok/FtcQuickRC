package org.firstinspires.ftc.teamcode.opmode.teleop;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.util.Hardware;

@TeleOp(name = "Tank Drive", group = "tank")
public class TankDrive extends OpMode {
    private final Hardware hardware = new Hardware();

    @Override
    public void init() {
        hardware.initialize(hardwareMap);
    }

    @Override
    public void loop() {
        double left  = -gamepad1.left_stick_y;
        double right = -gamepad1.right_stick_y;

        hardware.leftFront.setPower(left);
        hardware.leftRear.setPower(left);
        hardware.rightFront.setPower(right);
        hardware.rightRear.setPower(right);
    }
}
