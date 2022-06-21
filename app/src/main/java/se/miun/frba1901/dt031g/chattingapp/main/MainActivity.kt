package se.miun.frba1901.dt031g.chattingapp.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import se.miun.frba1901.dt031g.chattingapp.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.my_toolbar));
    }
}