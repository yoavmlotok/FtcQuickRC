package org.firstinspires.ftc.teamcode.opmode.teleop;

import com.arcrobotics.ftclib.drivebase.DifferentialDrive;
import com.arcrobotics.ftclib.drivebase.MecanumDrive;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.hardware.motors.MotorEx;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.teamcode.util.Hardware;

@TeleOp(name = "Drive Tryout", group = "tryout")
public class DriveTryout extends LinearOpMode {
    private final Hardware hardware = new Hardware();

    private GamepadEx driverPad;

    private MecanumDrive mecanumDrive;
    private DifferentialDrive differentialDrive;

    @Override
    public void runOpMode() {
        hardware.initialize(hardwareMap);

        driverPad = new GamepadEx(gamepad1);

        mecanumDrive      = new MecanumDrive(hardware.leftFront, hardware.rightFront, hardware.leftRear, hardware.rightRear);
        differentialDrive = new DifferentialDrive(hardware.leftFront, hardware.rightFront, hardware.leftRear, hardware.rightRear);

        waitForStart();

        while (opModeIsActive()) {
            telemetry.addLine(
                            "Press UP to try field oriented mecanum drive" +
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

            mecanumDrive.driveFieldCentric(driverPad.getLeftX(), driverPad.getLeftY(), driverPad.getRightX(), hardware.imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.DEGREES));

            if (gamepad1.b) {
                for (MotorEx motor : hardware.wheels) {
                    motor.stopMotor();
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

            mecanumDrive.driveRobotCentric(driverPad.getLeftX(), driverPad.getLeftY(), driverPad.getRightX());

            if (gamepad1.b) {
                for (MotorEx motor : hardware.wheels) {
                    motor.stopMotor();
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

            differentialDrive.arcadeDrive(driverPad.getLeftY(), driverPad.getRightX());

            if (gamepad1.b) {
                for (MotorEx motor : hardware.wheels) {
                    motor.stopMotor();
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

            differentialDrive.tankDrive(driverPad.getLeftY(), driverPad.getRightY());

            if (gamepad1.b) {
                for (MotorEx motor : hardware.wheels) {
                    motor.stopMotor();
                }
                break;
            }
        }
    }
}
