package org.firstinspires.ftc.teamcode.Mechanizmai;


import static android.os.SystemClock.sleep;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class AutoMechanizmai {
    public DcMotor sm;
    public DcMotor pad, pem; //Paėmimas padavimas
    public DcMotor kP, kG, dP, dG;
    double sp, s, p, g;


    public void init (HardwareMap hwMap){
        kP = hwMap.get(DcMotor.class,  "kP");
        dP = hwMap.get(DcMotor.class,  "dP");
        kG = hwMap.get(DcMotor.class,  "kG");
        dG = hwMap.get(DcMotor.class,  "dG");

        pem = hwMap.get(DcMotor.class, "pem");
        sm = hwMap.get(DcMotor.class, "sm");
        pad = hwMap.get(DcMotor.class, "pad");

        kG.setDirection(DcMotorSimple.Direction.REVERSE);
        kP.setDirection(DcMotorSimple.Direction.REVERSE);

        kP.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        dP.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        kG.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        dG.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        sm.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }

    public void ugnis(double s, double p){
        kP.setPower(0);
        dP.setPower(0);
        dG.setPower(0);
        kG.setPower(0);

        sm.setPower(s);
        sleep(1500);
        ///1
        pad.setPower(p);
        sleep(500);
        pad.setPower(0);
        pem.setPower(1);
        sleep(1000);
        ///2
        pem.setPower(0);
        pad.setPower(p);
        sleep(500);
        pem.setPower(0);
        pad.setPower(0);
        sm.setPower(0);
    }
    public void pirmyn(double g){
        kP.setPower(g);
        kG.setPower(g);
        dP.setPower(g);
        dG.setPower(g);

    }
    public void stopViršus(){
        pem.setPower(0);
        pad.setPower(0);
        sm.setPower(0);

    }
    public void stopApačia(){
        kP.setPower(0);
        kG.setPower(0);
        dP.setPower(0);
        dG.setPower(0);
    }


}
