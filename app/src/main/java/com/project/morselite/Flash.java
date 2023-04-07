package com.project.morselite;


import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;
import android.widget.Toast;

public class Flash {
    private Boolean isLit = false;


    public void emitFlash(String morse, CameraManager cameraManager, String getCameraID) {
        if(!isLit) {
            try {
                // true sets the torch in ON mode
                cameraManager.setTorchMode(getCameraID, true);
                isLit = true;
            } catch (CameraAccessException e) {
                // prints stack trace on standard error
                // output error stream
                e.printStackTrace();
            }
        }
        else{
            try {
                // true sets the torch in OFF mode
                cameraManager.setTorchMode(getCameraID, false);
                isLit = false;
            } catch (CameraAccessException e) {
                // prints stack trace on standard error
                // output error stream
                e.printStackTrace();
            }
        }

    }
}


