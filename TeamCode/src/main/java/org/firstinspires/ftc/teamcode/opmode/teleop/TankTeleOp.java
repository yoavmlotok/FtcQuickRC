package org.firstinspires.ftc.teamcode.opmode.teleop;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.drive.TankDrive;
import org.firstinspires.ftc.teamcode.util.Hardware;

@TeleOp(name = "Tank Drive", group = "tank")
public class TankTeleOp extends OpMode {
    private final Hardware hardware = new Hardware();

    private final TankDrive drive = new TankDrive();

    @Override
    public void init() {
        hardware.initialize(hardwareMap);
        drive.initialize(hardware);
    }

    @Override
    public void loop() {
        drive.drive(-gamepad1.left_stick_y, -gamepad1.right_stick_y);
    }
}
