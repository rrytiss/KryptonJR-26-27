package org.firstinspires.ftc.teamcode.Test;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.firstinspires.ftc.teamcode.Mechanizmai.AutoMechanizmai;

@Autonomous(name = "Saudymas")
public class AutoŠaudymas extends LinearOpMode {
    AutoMechanizmai auto = new AutoMechanizmai();
    DcMotor pem, sm, pad;
    double t;
    double x = 0;
    @Override
    public void runOpMode() throws InterruptedException {
        auto.init(hardwareMap);

        pem = hardwareMap.get(DcMotor.class, "pem");
        sm = hardwareMap.get(DcMotor.class, "sm");
        pad = hardwareMap.get(DcMotor.class, "pad");



        resetRuntime();
        t = getRuntime();
        double x =0;
        waitForStart();
        resetRuntime();
        while(!isStopRequested()){
            telemetry.addData("t= ", t);
            t = getRuntime();

            sm.setPower(0.4);
            if (t < 2 && x == 0){
                t = getRuntime();
                auto.ugnis(0.7, 0.55);

                x=1;
            }


        }

    }
}
