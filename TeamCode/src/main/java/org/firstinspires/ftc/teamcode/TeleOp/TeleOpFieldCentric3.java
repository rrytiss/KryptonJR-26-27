package org.firstinspires.ftc.teamcode.TeleOp;

import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.IMU;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;

@com.qualcomm.robotcore.eventloop.opmode.TeleOp(name = "FieldCentricTele")
public class TeleOpFieldCentric3 extends LinearOpMode {


    DcMotor kP, dP, kG, dG; //Važiuoklė
    IMU imu;
    DcMotor pem; //Paėmimas


    @Override
    public void runOpMode() throws InterruptedException {

        //Creating Drivetrain Motors and Setting their behaviour to "brake"
        kP = hardwareMap.get(DcMotor.class, "kP");
        dP = hardwareMap.get(DcMotor.class,  "dP");
        kG = hardwareMap.get(DcMotor.class,  "kG");
        dG = hardwareMap.get(DcMotor.class,  "dG");

        pem = hardwareMap.get(DcMotor.class, "pem");

        kP.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        dP.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        kG.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        dG.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);



        pem.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);







        /// =======================IMU==========================
        imu = hardwareMap.get(IMU.class, "imu");
        IMU.Parameters parameters= new IMU.Parameters(new RevHubOrientationOnRobot(
                RevHubOrientationOnRobot.LogoFacingDirection.DOWN,
                RevHubOrientationOnRobot.UsbFacingDirection.BACKWARD
        ));
        imu.initialize(parameters);
        imu.resetYaw();

        //Creating timer variables



        waitForStart();

        while (!isStopRequested()) {

            double t = getRuntime();
            double kampas = imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.DEGREES);
            ///==========================Telemetrija=====================

//            telemetry.addData("Kampas: ", kampas);
            telemetry.update();


            ///================VAŽIUOKLĖ=========================
            double pirmyn = -gamepad1.left_stick_y;
            double bausti = -gamepad1.left_stick_x; ///STRAFE
            double posukis = gamepad1.right_stick_x;

/// FieldCentric formulės:
//            double cos = Math.cos((Math.PI / 2) + kampas);// (-) buvo
//            double sin = Math.sin((Math.PI / 2) + kampas);

//            double didBausme = pirmyn * cos + bausti * sin; ///Global strafe
//            double didPirmyn = pirmyn * cos - bausti * sin; /// Global forward

            /// Važiuoklės vairiklių galios

            double kp, dp, kg, dg;

            kp = pirmyn + bausti + posukis;
            dp = pirmyn - bausti - posukis;
            kg = pirmyn - bausti + posukis;
            dg = pirmyn + bausti - posukis;
/// Variklių galios sudėti arba nuiimti pliusus jei sukasi ne į tas puses
            double greitis = 0.8;
            kP.setPower(kp * greitis);
            dP.setPower(dp * greitis);
            kG.setPower(-kg * greitis);
            dG.setPower(-dg * greitis);


            ///===================Kampo nunulinimas=================
//            if (gamepad1.options){
//                imu.resetYaw();
//            }
            ///===================Paėmimas==========================
            if (gamepad1.left_bumper){

                pem.setPower(0.5);
            } else if (!gamepad1.left_bumper && !gamepad1.right_bumper) {
                pem.setPower(0.0);
            }

            telemetry.update();
        }

    }

}

