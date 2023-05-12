package org.firstinspires.ftc.teamcode.opmode.teleop;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.drive.FoMecanumDrive;
import org.firstinspires.ftc.teamcode.util.Hardware;

@TeleOp(name = "F.O Mecanum Drive", group = "mecanum")
public class FoMecanumTeleOp extends OpMode {
    private final Hardware hardware = new Hardware();

    private final FoMecanumDrive drive = new FoMecanumDrive();

    @Override
    public void init() {
        hardware.initialize(hardwareMap);
        drive.initialize(hardware);
    }

    @Override
    public void loop() {
        drive.drive(gamepad1.left_stick_x, -gamepad1.left_stick_y, gamepad1.right_stick_x);
    }
}
