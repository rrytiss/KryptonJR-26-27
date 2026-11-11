package org.firstinspires.ftc.teamcode.Test;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.robotcore.external.navigation.Pose2D;
import org.firstinspires.ftc.teamcode.GoBildaPinpointDriver;
@Disabled
@TeleOp
public class OdoTestas extends OpMode {
    double X =25, Z = 0;
    double maxGreitis =0.8;
    GoBildaPinpointDriver odo;
    @Override
    public void init() {
        odo = hardwareMap.get(GoBildaPinpointDriver.class, "pinpoint");
        odo.setOffsets(-84.0, -168.0, DistanceUnit.MM);
        odo.setEncoderResolution(GoBildaPinpointDriver.GoBildaOdometryPods.goBILDA_4_BAR_POD);
        odo.setEncoderDirections(GoBildaPinpointDriver.EncoderDirection.FORWARD, GoBildaPinpointDriver.EncoderDirection.FORWARD);

        odo.resetPosAndIMU();
        Pose2D startPos = new Pose2D(DistanceUnit.MM, -8, -9, AngleUnit.RADIANS, 0);
        odo.setPosition(startPos);
        odo.resetPosAndIMU();
    }

    @Override
    public void loop() {
        double Xe = X - odo.getPosX()*(-1);
        double Ze = Z - odo.getHeading();
        telemetry.addData("X", Xe);
        telemetry.addData("X tikras: ", odo.getPosX());
        telemetry.addData("Z", Ze);
        telemetry.addData("Y", odo.getPosY());



        double Tgreitis = Xe * 0.05;
        double greitis = 0;

        if (Tgreitis > maxGreitis) Tgreitis = maxGreitis;
        if (Tgreitis < -maxGreitis) Tgreitis = -maxGreitis;
        double skirtumas = Tgreitis - greitis;


        if (skirtumas > 0.4) {
            greitis += 0.6;
        } else if (skirtumas < -0.4) {
            greitis -= 0.6;
        } else {
            greitis = Tgreitis;
        }
        double PI = Math.PI;
        while (Ze > PI) Ze -= 2 * PI;
        while (Ze < -PI) Ze += 2*PI;
        double posukis = Ze * 0.05;
        double kGalia = greitis + posukis;
        double dGalia = greitis - posukis;

        if (Xe >-0.4 && Xe < 0.4){
            kGalia =0;
            dGalia = 0;
        }



        // 5. Galių paskirstymas kairiajam ir dešiniajam varikliui

        telemetry.addData("Greitis k: ", kGalia);
        telemetry.addData("Greitis d: ", dGalia);
        telemetry.addData("Skirtumas: ", skirtumas);


        odo.update();
    }
}
