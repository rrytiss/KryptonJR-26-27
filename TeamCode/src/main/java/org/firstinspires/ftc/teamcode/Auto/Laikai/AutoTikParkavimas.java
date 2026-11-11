package org.firstinspires.ftc.teamcode.Auto.Laikai;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@Autonomous(name = "AutoPark")
public class AutoTikParkavimas extends LinearOpMode {
    DcMotor kP, kG, dP, dG, pem, sm, pad;
    double t;
    @Override
    public void runOpMode() throws InterruptedException {
        kP = hardwareMap.get(DcMotor.class,  "kP");
        dP = hardwareMap.get(DcMotor.class,  "dP");
        kG = hardwareMap.get(DcMotor.class,  "kG");
        dG = hardwareMap.get(DcMotor.class,  "dG");

        pem = hardwareMap.get(DcMotor.class, "pem");
        sm = hardwareMap.get(DcMotor.class, "sm");
        pad = hardwareMap.get(DcMotor.class, "pad");

        kG.setDirection(DcMotorSimple.Direction.REVERSE);
        kP.setDirection(DcMotorSimple.Direction.REVERSE);

        kP.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        dP.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        kG.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        dG.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        resetRuntime();
        t = getRuntime();
        waitForStart();
        resetRuntime();
        while(!isStopRequested()){
            telemetry.addData("t= ", t);
            t = getRuntime();
            if (t < 0.5){
                t = getRuntime();
                kP.setPower(0.2);
                dP.setPower(0.2);
                dG.setPower(0.2);
                kG.setPower(0.2);
            } else if (t > 1){
                kP.setPower(0);
                dP.setPower(0);
                dG.setPower(0);
                kG.setPower(0);
            }

        }

    }
}
