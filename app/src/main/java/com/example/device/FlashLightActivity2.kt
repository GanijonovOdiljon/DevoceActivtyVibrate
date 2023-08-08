package com.example.device

import android.content.Context
import android.content.pm.PackageManager
import android.hardware.camera2.CameraAccessException
import android.hardware.camera2.CameraManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.device.exit.click
import com.example.device.exit.toast

class FlashLightActivity2 : AppCompatActivity() {

    private var cameraId : String? = null
    private var isFlashAvliable =false
    private lateinit var cameraManger: CameraManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_flash_light2)

        setContentView(R.layout.activity_flash_light2)
        val button: Button = findViewById(R.id.buttonFl)

        cameraManger= getSystemService(Context.CAMERA_SERVICE) as CameraManager
        isFlashAvliable = packageManager.hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH)
        try {
            cameraId = cameraManger.cameraIdList[0] // 0 -> back camera // 1 -> front camera
        } catch (e: CameraAccessException){
            e.printStackTrace()
        }
        button.click {
            if (isFlashAvliable){
                if (button.text.toString().contains("On")){
                    button.text = "FlashLight Off"
                    flashLightOff()
                }
            }else{
                button.text = "FlashLight On"
                flashLightOn()
            }
        }
    }
    fun flashLightOn(){
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                cameraManger.setTorchMode(cameraId!!,true)
            }
        }catch (e:Exception){
            e.printStackTrace()
        }
    }
    fun flashLightOff(){
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                cameraManger.setTorchMode(cameraId!!,false)
            }
        }catch (e:Exception){
            e.printStackTrace()
        }
    }
}