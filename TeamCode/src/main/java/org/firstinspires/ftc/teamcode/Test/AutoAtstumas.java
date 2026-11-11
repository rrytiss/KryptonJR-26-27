package org.firstinspires.ftc.teamcode.Test;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@Autonomous
public class AutoAtstumas extends LinearOpMode {
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
        sm.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        resetRuntime();
        t = getRuntime();
        double x =0;
        waitForStart();
        resetRuntime();
        while(!isStopRequested()){

            if (x == 0){

                kP.setPower(0.6);
                dP.setPower(0.6);
                dG.setPower(0.6);
                kG.setPower(0.6);
                pad.setPower(0);
                pem.setPower(0);
                sm.setPower(0);
                sleep(600); ///1000ms = 104cm 600ms = 60cm
                x = 1;

            }

            if (x==1){
                kP.setPower(-0);
                dP.setPower(0);
                dG.setPower(-0);
                kG.setPower(0);
            }

        }

    }
}
