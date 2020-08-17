package br.com.projkotlinexamples.ui.activity

import android.content.pm.ActivityInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import br.com.projkotlinexamples.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        requestedOrientation =  (ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }
}