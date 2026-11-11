package org.firstinspires.ftc.teamcode.Auto.Laikai.Toli;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@Autonomous(name = "Toli1xTiesiai+Išorė+Park")
public class AutoTiesiaiPark extends LinearOpMode {
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
            telemetry.addData("t= ", t);
            t = getRuntime();


            if (t < 2 && x == 0){
                t = getRuntime();
                kP.setPower(0);
                dP.setPower(0);
                dG.setPower(0);
                kG.setPower(0);
                sm.setPower(0.7);
                sleep(1500);
                ///1
                pad.setPower(0.55);
                sleep(500);
                pad.setPower(0);
                pem.setPower(1);
                sleep(1000);
                ///2
                pem.setPower(0);
                pad.setPower(0.5);
                sleep(500);
                pem.setPower(0);
                pad.setPower(0);
                sm.setPower(0);

                x=1;
            }
            if (t > 2 && x == 1){
                t = getRuntime();
                kP.setPower(0.2);
                dP.setPower(0.2);
                dG.setPower(0.2);
                kG.setPower(0.2);
                pad.setPower(0);
                pem.setPower(0);
                sm.setPower(0);
                sleep(2000);
                x = 2;

            }
            if (x == 2 && t > 3){
                kP.setPower(0.6);
                dP.setPower(-0.6);
                dG.setPower(0.6);
                kG.setPower(-0.6);
                sleep(1400);
                x=3;
            }
            if (x==3 && t > 3){
                kP.setPower(-0);
                dP.setPower(0);
                dG.setPower(-0);
                kG.setPower(0);
            }

        }

    }
}
