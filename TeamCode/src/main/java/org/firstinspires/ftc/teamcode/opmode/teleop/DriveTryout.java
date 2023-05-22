package org.firstinspires.ftc.teamcode.opmode.teleop;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotorEx;

import org.firstinspires.ftc.teamcode.drive.FoMecanumDrive;
import org.firstinspires.ftc.teamcode.drive.PovMecanumDrive;
import org.firstinspires.ftc.teamcode.drive.StandardDrive;
import org.firstinspires.ftc.teamcode.drive.TankDrive;
import org.firstinspires.ftc.teamcode.util.Hardware;

@TeleOp(name = "Drive Tryout", group = "tryout")
public class DriveTryout extends LinearOpMode {
    private final Hardware hardware = new Hardware();

    private final FoMecanumDrive foMecanumDrive = new FoMecanumDrive();
    private final PovMecanumDrive povMecanumDrive = new PovMecanumDrive();
    private final StandardDrive standardDrive = new StandardDrive();
    private final TankDrive tankDrive = new TankDrive();

    @Override
    public void runOpMode() {
        hardware.initialize(hardwareMap);

        foMecanumDrive.initialize(hardware);
        povMecanumDrive.initialize(hardware);
        standardDrive.initialize(hardware);
        tankDrive.initialize(hardware);

        waitForStart();

        while (opModeIsActive()) {
            telemetry.addLine(
                    "Use The D-Pad and B Button to navigate" +
                            "\nPress UP to try field oriented mecanum drive" +
                            "\nPress RIGHT to try point of view mecanum drive" +
                            "\nPress LEFT to try standard drive" +
                            "\nPress DOWN to try tank drive"
            );
            telemetry.update();

            if (gamepad1.dpad_up) {
                runFoMecanumDrive();
            }
            if (gamepad1.dpad_right) {
                runPovMecanumDrive();
            }
            if (gamepad1.dpad_left) {
                runStandardDrive();
            }
            if (gamepad1.dpad_down) {
                runTankDrive();
            }
        }
    }

    private void runFoMecanumDrive() {
        while (opModeIsActive()) {
            telemetry.addLine(
                    "Forwards and Backwards: left stick y" +
                            "\nRight and left: left stick x" +
                            "\nTurn: right stick x \n" +
                            "\nNote that the controls are based on the robot's orientation upon startup" +
                            "\nIt is recommended to start the robot facing the desired forward \n" +
                            "\nPress B to return and try other drives"
            );
            telemetry.update();

            foMecanumDrive.drive(gamepad1.left_stick_x, -gamepad1.left_stick_y, gamepad1.right_stick_x);

            if (gamepad1.b) {
                for (DcMotorEx motor : hardware.wheels) {
                    motor.setPower(0);
                }
                break;
            }
        }
    }

    private void runPovMecanumDrive() {
        while (opModeIsActive()) {
            telemetry.addLine(
                    "Forwards and backwards: left stick y" +
                            "\nRight and left: left stick x" +
                            "\nTurn: right stick x \n" +
                            "\nPress B to return and try other drives"
            );
            telemetry.update();

            povMecanumDrive.drive(-gamepad1.left_stick_y, gamepad1.left_stick_x, gamepad1.right_stick_x);

            if (gamepad1.b) {
                for (DcMotorEx motor : hardware.wheels) {
                    motor.setPower(0);
                }
                break;
            }
        }
    }

    private void runStandardDrive() {
        while (opModeIsActive()) {
            telemetry.addLine(
                    "Forwards and backwards: left stick y" +
                            "\nTurn: right stick x \n" +
                            "\nPress B to return and try other drives"
            );
            telemetry.update();

            standardDrive.drive(-gamepad1.left_stick_y, gamepad1.right_stick_x);

            if (gamepad1.b) {
                for (DcMotorEx motor : hardware.wheels) {
                    motor.setPower(0);
                }
                break;
            }
        }
    }

    private void runTankDrive() {
        while (opModeIsActive()) {
            telemetry.addLine(
                    "Left Side Power: left stick y" +
                            "\nRight Side Power: right stick y \n" +
                            "\nPress B to return and try other drives"
            );
            telemetry.update();

            tankDrive.drive(-gamepad1.left_stick_y, -gamepad1.right_stick_y);

            if (gamepad1.b) {
                for (DcMotorEx motor : hardware.wheels) {
                    motor.setPower(0);
                }
                break;
            }
        }
    }
}
