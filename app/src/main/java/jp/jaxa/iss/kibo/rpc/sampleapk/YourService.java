package jp.jaxa.iss.kibo.rpc.sampleapk;

import android.util.Log;

import jp.jaxa.iss.kibo.rpc.api.KiboRpcService;

import gov.nasa.arc.astrobee.Result;
import gov.nasa.arc.astrobee.types.Point;
import gov.nasa.arc.astrobee.types.Quaternion;

import org.opencv.core.Mat;

/**
 * Class meant to handle commands from the Ground Data System and execute them in Astrobee
 */

public class YourService extends KiboRpcService {
    private final String TAG = this.getClass().getSimpleName();

    @Override
    protected void runPlan1() {
        Log.i(TAG, "Start mission");
        // the mission starts
        api.startMission();

        // move to a point
        Point point1 = new Point(10.71f, -7.7f, 4.48f); //position 1
        Quaternion quaternion1 = new Quaternion(0f, 0.707f, 0f, 0.707f);
        Result result1 = api.moveTo(point1, quaternion1, false);

        final int LOOP_MAX1 = 5;

        //check result and loop while moveTo api is not succeeded
        int loopCounter1 = 0;
        while (!result1.hasSucceeded() && loopCounter1 < LOOP_MAX1) {
            //retry
            result1 = api.moveTo(point1, quaternion1, false);
            ++loopCounter1;
        }

        // report point1 arrival
        api.reportPoint1Arrival();

        // get a camera image
        Mat image = api.getMatNavCam();

        // irradiate the laser
        api.laserControl(true);

        // take target1 snapshots
        api.takeTarget1Snapshot();

        // turn the laser off
        api.laserControl(false);

        Point point2 = new Point(10.71f, -10.0f, 4.48f); //position 1
        Quaternion quaternion2 = new Quaternion(0f, 0.707f, 0f, 0.707f);
        Result result2 = api.moveTo(point2, quaternion2, false);

        final int LOOP_MAX2 = 5;

        //check result and loop while moveTo api is not succeeded
        int loopCounter2 = 0;
        while (!result2.hasSucceeded() && loopCounter2 < LOOP_MAX2) {
            //retry
            result2 = api.moveTo(point2, quaternion2, false);
            ++loopCounter2;


            // send mission completion
            api.reportMissionCompletion();
        }
    }
}
    //@Override
    //protected void runPlan2(){
        // write here your plan 2
    //}

    //@Override
    //protected void runPlan3(){
        // write here your plan 3
    //}

    // You can add your method
    //private void moveToWrapper(double pos_x, double pos_y, double pos_z,
                               //double qua_x, double qua_y, double qua_z,
                               //double qua_w){

        //final Point point = new Point(pos_x, pos_y, pos_z);
        //final Quaternion quaternion = new Quaternion((float)qua_x, (float)qua_y,
                                                     //(float)qua_z, (float)qua_w);

        //api.moveTo(point, quaternion, true);
    //}

    //private void relativeMoveToWrapper(double pos_x, double pos_y, double pos_z,
                               //double qua_x, double qua_y, double qua_z,
                               //double qua_w) {

        //final Point point = new Point(pos_x, pos_y, pos_z);
        //final Quaternion quaternion = new Quaternion((float) qua_x, (float) qua_y,
                //(float) qua_z, (float) qua_w);

        //api.relativeMoveTo(point, quaternion, true);
    //}

//}

