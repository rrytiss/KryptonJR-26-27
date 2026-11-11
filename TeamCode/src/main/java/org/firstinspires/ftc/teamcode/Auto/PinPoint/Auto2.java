package org.firstinspires.ftc.teamcode.Auto.PinPoint;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.robotcore.external.navigation.Pose2D;
import org.firstinspires.ftc.teamcode.GoBildaPinpointDriver;

@Autonomous
public class Auto2 extends LinearOpMode {
    DcMotor kP, kG, dP, dG, pem, sm, pad;
    double v = 0.4;
    double t;
    int x = 0; //Veiksmas
    double maxGreitis = 0.5, minGreitis = 0.2;

    GoBildaPinpointDriver odo;

    double X = -0, Z = 0;
    @Override
    public void runOpMode() throws InterruptedException {

        odo = hardwareMap.get(GoBildaPinpointDriver.class, "pinpoint");

        kP = hardwareMap.get(DcMotor.class,  "kP");
        dP = hardwareMap.get(DcMotor.class,  "dP");
        kG = hardwareMap.get(DcMotor.class,  "kG");
        dG = hardwareMap.get(DcMotor.class,  "dG");

        pem = hardwareMap.get(DcMotor.class, "pem");
        sm = hardwareMap.get(DcMotor.class, "sm");
        pad = hardwareMap.get(DcMotor.class, "pad");

        dG.setDirection(DcMotorSimple.Direction.REVERSE);
        dP.setDirection(DcMotorSimple.Direction.REVERSE);

        kP.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        dP.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        kG.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        dG.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        odo.setOffsets(-84.0, -168.0, DistanceUnit.MM);
        odo.setEncoderResolution(GoBildaPinpointDriver.GoBildaOdometryPods.goBILDA_4_BAR_POD);
        odo.setEncoderDirections(GoBildaPinpointDriver.EncoderDirection.FORWARD, GoBildaPinpointDriver.EncoderDirection.FORWARD);

        odo.resetPosAndIMU();
        Pose2D startPos = new Pose2D(DistanceUnit.MM, -8, -9, AngleUnit.RADIANS, 0);
        odo.setPosition(startPos);


        resetRuntime();
        t = getRuntime();
        waitForStart();
        odo.resetPosAndIMU();
        resetRuntime();
        while(!isStopRequested()){

            odo.update();
            t = getRuntime();
            double Xe = X - odo.getPosX()*-1;
            double Ze = Z - odo.getHeading();
            double Tgreitis = Xe * 0.05;
            double greitis = 0;

            if (Tgreitis > maxGreitis) Tgreitis = maxGreitis;
            if (Tgreitis < -maxGreitis) Tgreitis = -maxGreitis;
            double skirtumas = Tgreitis - greitis;


            if (skirtumas > 0.3) {
                greitis += 0.3;
            } else if (skirtumas < -0.3) {
                greitis -= 0.3;
            } else {
                greitis = Tgreitis;
            }
            double PI = Math.PI;
            while (Ze > PI) Ze -= 2 * PI;
            while (Ze < -PI) Ze += 2*PI;

            double posukis = Ze * 0.05;



            // 5. Galių paskirstymas kairiajam ir dešiniajam varikliui
            double kGalia = greitis + posukis;
            double dGalia = greitis - posukis;

            if (Xe >-0.4 && Xe < 0.4){
                kGalia =0;
                dGalia = 0;
            }


            // Apribojame, kad neviršytų variklio ribų [-1.0 ir 1.0]
            kGalia = Math.max(-1.0, Math.min(1.0, kGalia));
            dGalia = Math.max(-1.0, Math.min(1.0, dGalia));
            telemetry.addData("X= ", Xe);
            telemetry.addData("Z= ", Ze);
            telemetry.update();

//            sm.setPower(0.75);
//            sleep(3000);
//            pad.setPower(0.4);
//            sleep(500);
//            pad.setPower(0);
//            pem.setPower(1);
//            sleep(600);
//            pem.setPower(0);
//            pad.setPower(0.4);

            if (Xe >= -1.5 && Xe <= 1.5){
                dG.setPower(0);
                dP.setPower(0);
                kG.setPower(0);
               kP.setPower(0);
            }else
            dG.setPower(dGalia);
            dP.setPower(dGalia);
            kG.setPower(kGalia);
            kP.setPower(kGalia);

        }
    }
}
