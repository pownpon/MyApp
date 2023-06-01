package android.pownpon.app.applaunch

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.pownpon.app.homepage.MainActivity
import android.pownpon.app.databinding.ActivityLaunchBinding
import android.view.KeyEvent
import java.util.Timer
import kotlin.concurrent.schedule

class LaunchActivity : AppCompatActivity() {

    private lateinit var binding:ActivityLaunchBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLaunchBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Timer().schedule(1500){
            goToMainAct()
        }
    }

    private fun goToMainAct(){
        startActivity(Intent(this@LaunchActivity, MainActivity::class.java))
        finish()
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if(keyCode == KeyEvent.KEYCODE_BACK){
            return true
        }
        return super.onKeyDown(keyCode, event)
    }
}