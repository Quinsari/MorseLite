package com.project.morselite;


import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;
import android.widget.Button;
import android.widget.Toast;

import java.util.logging.Handler;

public class Flash implements Runnable{

//    private volatile boolean stop = false;
    private String mMorse;
    private Thread button;
    private CameraManager mCameraManager;
    private String mGetCameraID;
    private int unit = 200;
    private Boolean isLit = false;

    public Flash(CameraManager cameraManager, String getCameraID) {
        mMorse = "";
        mCameraManager = cameraManager;
        mGetCameraID = getCameraID;
    }

    public void updateMorse(String morse) {
        mMorse = morse;
    }

    public void run(){
        try {
            convertToFlash(mMorse, mCameraManager, mGetCameraID);
        } catch(InterruptedException e){}
    }



    public void convertToFlash(String morse, CameraManager cameraManager, String getCameraID) throws InterruptedException {
        String[] morseArr = morse.split(" ");
        for(String a: morseArr){
            for(int i = 0; i < a.length(); i++){
                    switch (a.charAt(i)) {
                        case '.':
                            emitFlash(1, cameraManager, getCameraID);
                            break;
                        case '-':
                            emitFlash(2, cameraManager, getCameraID);
                            break;
                        case '/':
                            emitFlash(4, cameraManager, getCameraID);
                        default:
                            break;
                    }
            }
            emitFlash(3, cameraManager, getCameraID);
        }
    }



    public void emitFlash(int condition, CameraManager cameraManager, String getCameraID) throws InterruptedException {
        switch(condition){
            case 1:
                try{
                    cameraManager.setTorchMode(getCameraID, true);
                    isLit = true;
                }   catch (CameraAccessException e) {
                    e.printStackTrace();
                }
                Thread.sleep(unit);
                try {
                    cameraManager.setTorchMode(getCameraID, false);
                    isLit = false;
                } catch (CameraAccessException e) {
                    e.printStackTrace();
                }
                Thread.sleep(unit);
                break;
            case 2:
                try{
                    cameraManager.setTorchMode(getCameraID, true);
                    isLit = true;
                }   catch (CameraAccessException e) {
                    e.printStackTrace();
                }
                Thread.sleep(unit * 3);
                try {
                    cameraManager.setTorchMode(getCameraID, false);
                    isLit = false;
                } catch (CameraAccessException e) {
                    e.printStackTrace();
                }
                Thread.sleep(unit);
                break;
            case 3:
                Thread.sleep(unit * 3);
                break;
            case 4:
                Thread.sleep(unit * 7);
                break;
            default:
                break;
        }

    }
}


