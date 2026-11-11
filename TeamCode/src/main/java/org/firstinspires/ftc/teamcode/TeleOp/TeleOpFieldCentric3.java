package org.firstinspires.ftc.teamcode.TeleOp;

import com.arcrobotics.ftclib.drivebase.MecanumDrive;
import com.qualcomm.hardware.motors.GoBILDA5202Series;
import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.IMU;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.robotcore.external.navigation.Pose2D;
import org.firstinspires.ftc.teamcode.GoBildaPinpointDriver;

@com.qualcomm.robotcore.eventloop.opmode.TeleOp(name = "TeleOp")
public class TeleOpFieldCentric3 extends LinearOpMode {

    boolean prev = false;
    boolean MotorOn = false;
    DcMotor kP, dP, kG, dG; //Važiuoklė
    DcMotor pem, sm, pad; //Paėmimas

    GoBildaPinpointDriver odo;
    @Override
    public void runOpMode() throws InterruptedException {
        odo = hardwareMap.get(GoBildaPinpointDriver.class, "pinpoint");
        //Creating Drivetrain Motors and Setting their behaviour to "brake"
        kP = hardwareMap.get(DcMotor.class, "kP");
        dP = hardwareMap.get(DcMotor.class,  "dP");
        kG = hardwareMap.get(DcMotor.class,  "kG");
        dG = hardwareMap.get(DcMotor.class,  "dG");

        pad = hardwareMap.get(DcMotor.class, "pad");
        pem = hardwareMap.get(DcMotor.class, "pem");
        sm = hardwareMap.get(DcMotor.class, "sm");

        kP.setDirection(DcMotorSimple.Direction.REVERSE);
        kG.setDirection(DcMotorSimple.Direction.REVERSE);
        dG.setDirection(DcMotorSimple.Direction.REVERSE);
        dP.setDirection(DcMotorSimple.Direction.REVERSE);

        kP.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        dP.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        kG.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        dG.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        pem.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);


        /// =======================IMU==========================


        //Creating timer variables
        odo.setOffsets(-40.0, -151.0, DistanceUnit.MM);
        odo.setEncoderResolution(GoBildaPinpointDriver.GoBildaOdometryPods.goBILDA_4_BAR_POD);
        odo.setEncoderDirections(GoBildaPinpointDriver.EncoderDirection.FORWARD, GoBildaPinpointDriver.EncoderDirection.FORWARD);

        odo.resetPosAndIMU();
        Pose2D startPos = new Pose2D(DistanceUnit.MM, -8, -9, AngleUnit.RADIANS, 0);
        odo.setPosition(startPos);


        waitForStart();

        while (!isStopRequested()) {

            double t = getRuntime();

            ///==========================Telemetrija=====================
            telemetry.addData("X= ", odo.getPosX());
            telemetry.addData("Z= ", odo.getHeading());

            telemetry.update();


            ///================VAŽIUOKLĖ=========================

/// FieldCentric formulės:
            double didPirmyn = gamepad1.right_stick_x;
            double didBausme = -gamepad1.left_stick_y; ///STRAFE
            double posukis = -gamepad1.left_stick_x;

            Pose2D pos = odo.getPosition();
            double kampas = pos.getHeading(AngleUnit.RADIANS);

            double cos = Math.cos((Math.PI / 2) + kampas);// (-) buvo
            double sin = Math.sin((Math.PI / 2) + kampas);

//            double didBausme = -pirmyn * sin + bausti * cos; ///Global strafe
//            double didPirmyn = pirmyn * cos + bausti * sin; /// Global forward

            double kp, dp, kg, dg;

            kp = didPirmyn + didBausme - posukis;
            dp = didPirmyn - didBausme - posukis;
            kg = didPirmyn + didBausme + posukis;
            dg = didPirmyn - didBausme + posukis;

            kP.setPower(kp*0.8);
            dP.setPower(dp*0.8);
            kG.setPower(kg*0.8);
            dG.setPower(dg*0.8 );

            ///===================Kampo nunulinimas=================
            if (gamepad1.options){
                odo.resetPosAndIMU();
            }
            ///===================Paėmimas==========================
            if (gamepad1.left_bumper){
                pem.setPower(1);//1
            } else if (!gamepad1.left_bumper && !gamepad1.cross) {
                pem.setPower(0.0);//0.0
            }  if (gamepad1.cross) {
                pem.setPower(-0.4);//-0.4
            }
            ///====================Šaudyklė==========================
            if (gamepad1.right_trigger > 0.2){
                sm.setPower(0.7);//0.75
            } else if (gamepad1.right_trigger < 0.2){
                sm.setPower(0.2);//0.2
            }
            ///=====================Padavimas=========================
            if (gamepad1.right_bumper){
                pad.setPower(0.4);//0.6
            } else if (!gamepad1.right_bumper && !gamepad1.cross) {
                pad.setPower(0.0);//0.0
            }  if (gamepad1.cross) {
                pad.setPower(-0.4);//-0.4
            }
        }
    }

}

