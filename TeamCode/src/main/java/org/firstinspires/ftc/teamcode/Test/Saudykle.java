package org.firstinspires.ftc.teamcode.Test;

import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp
public class Saudykle extends OpMode {
//    MotorEx sm;
    DcMotor pem;
    Motor pad;
    DcMotor sm;

    int x = 0;
    @Override
    public void init() {

//        sm = hardwareMap.get(MotorEx.class, "sm");
//        sm = new MotorEx(hardwareMap, "sm");
        sm = hardwareMap.get(DcMotor.class, "sm");
        pem = hardwareMap.get(DcMotor.class, "pem");
        pad = new Motor(hardwareMap, "pad");
        pad.setRunMode(Motor.RunMode.PositionControl);
        pem.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
//        sm.setRunMode(Motor.RunMode.VelocityControl);
        pad.resetEncoder();

    }

    @Override
    public void loop() {
        if (gamepad1.left_bumper){
            pem.setPower(1);
        } else if (!gamepad1.left_bumper){
            pem.setPower(0);
        }
        if (gamepad1.left_trigger > 0.2){
            sm.setPower(gamepad1.right_trigger);
        }

//        sm.setVelocity(500);
        if (gamepad1.dpad_up){
            pad.set(0.4);
        }else if (!gamepad1.right_bumper && !gamepad1.dpad_down){
            pad.set(0);
        } else if (gamepad1.dpad_down){
            pad.set(-0.4);
        }

//        telemetry.addData("RPM", sm.getVelocity() / 8.96);
//        telemetry.addData("Galia: ", sm.getVelocity()/383);
        telemetry.addData("Galia", sm.getPower());
        telemetry.addData("Pad pozicija: ", pad.getCurrentPosition());
    }
}
