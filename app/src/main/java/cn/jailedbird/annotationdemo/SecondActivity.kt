package cn.jailedbird.annotationdemo

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import cn.jailedbird.annotationdemo.lib_annotation.BindView
import cn.jailedbird.annotationdemo.lib_api.BindViewUtils

@SuppressLint("NonConstantResourceId")
class SecondActivity : AppCompatActivity() {

    /*@InnerKotlinBindView(R.id.textView)
    @InnerJavaBindView(R.id.textView)*/
    @BindView(R.id.textView)
    lateinit var textView0: TextView

    @BindView(R.id.textView1)
    lateinit var textView1: TextView

    @BindView(R.id.textView2)
    lateinit var textView2: TextView

    @BindView(R.id.textView3)
    lateinit var textView3: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        /*InnerKotlinBinding.bindJava(this)*/
        BindViewUtils.bind(this)
        textView0.text = "干死小日本狗"
        /*textView0.text = "干死小日本狗"
        textView1.text = "干死小日本狗"
        textView2.text = "干死小日本狗"

        textView2.setOnClickListener {
            routeByActivityName(
                this@MainActivity,
                "cn.jailedbird.demo.annotation.module.SecondActivity"
            )
        }

        textView3.setOnClickListener {
            routeByActivityName(
                this@MainActivity,
                "cn.jailedbird.demo.annotation.ThirdActivity"
            )
        }*/
    }

    // For quick debug
    private fun routeByActivityName(
        context: Context,
        activityClassName: String,
        bundle: Bundle? = null
    ) {
        val intent = Intent().apply {
            setClassName(context, activityClassName)
        }
        bundle?.let {
            intent.putExtras(it)
        }
        try {
            context.startActivity(intent)
        } catch (e: Exception) {
            val error = if (e.message.isNullOrEmpty()) {
                "活动${activityClassName}打开失败!"
            } else {
                e.message
            }
            log(error)
        }
    }

    private fun log(str: String?) {
        if (str.isNullOrBlank()) {
            return
        }
        Log.d("Abel", str)
    }
}

