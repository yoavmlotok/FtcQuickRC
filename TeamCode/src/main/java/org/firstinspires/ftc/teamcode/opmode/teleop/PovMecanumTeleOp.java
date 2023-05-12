package org.firstinspires.ftc.teamcode.opmode.teleop;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.drive.PovMecanumDrive;
import org.firstinspires.ftc.teamcode.util.Hardware;

@TeleOp(name = "P.O.V Mecanum Drive", group = "mecanum")
public class PovMecanumTeleOp extends OpMode {
    private final Hardware hardware = new Hardware();

    private final PovMecanumDrive drive = new PovMecanumDrive();

    @Override
    public void init() {
        hardware.initialize(hardwareMap);
        drive.initialize(hardware);
    }

    @Override
    public void loop() {
        drive.drive(-gamepad1.left_stick_y, gamepad1.left_stick_x, gamepad1.right_stick_x);
    }
}
