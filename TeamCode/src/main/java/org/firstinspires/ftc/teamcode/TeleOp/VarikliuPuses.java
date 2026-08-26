package org.firstinspires.ftc.teamcode.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp
public class VarikliuPuses extends OpMode {
    DcMotor kp, dp, kg, dg;
    @Override
    public void init() {
        kp = hardwareMap.get(DcMotor.class, "kP");
        kg = hardwareMap.get(DcMotor.class, "kG");
        dg = hardwareMap.get(DcMotor.class, "dG");
        dp = hardwareMap.get(DcMotor.class, "dP");


    }

    @Override
    public void loop() {
        ///  Naudjant vairalazdęs (Joysticks) pažiūrėti kad kai rodo "> 0" suktusi į priekį
        kp.setPower(gamepad1.left_trigger);
        telemetry.addData("kp kairys gaidukas: ", gamepad1.left_trigger);
        kg.setPower(gamepad1.left_stick_y);
        telemetry.addData("kg galia kairė vairalazdė: ", gamepad1.left_stick_y);
        dp.setPower(gamepad1.right_trigger);
        telemetry.addData("dešinio priekinio dešinys gaidukas: ", gamepad1.right_trigger);
        dg.setPower(gamepad1.right_stick_y);
        telemetry.addData("dg dešinė vairalazdė: ", gamepad1.right_stick_y);

    }
}
