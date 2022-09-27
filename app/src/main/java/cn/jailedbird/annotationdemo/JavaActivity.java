package cn.jailedbird.annotationdemo;

import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import cn.jailedbird.annotationdemo.lib_annotation.BindView;

public class JavaActivity extends AppCompatActivity {
    @BindView(R.id.textView)
    TextView textView0;
    @BindView(R.id.textView1)
    TextView textView1;
    @BindView(R.id.textView2)
    TextView textView2;
    @BindView(R.id.textView3)
    TextView textView3;


}
