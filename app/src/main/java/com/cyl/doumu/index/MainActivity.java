package com.cyl.doumu.index;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.cyl.doumu.R;
import com.cyl.doumu.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @description: 主页面
 * @author: Cyl
 * @date: 2018-03-20  17:02
 * @version: V1.0
 */
public class MainActivity extends BaseActivity {

    @BindView(R.id.tvTest)
    TextView tvTest;
    @BindView(R.id.btnTest)
    Button btnTest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        btnTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvTest.setText("ttttt");
            }
        });
    }
}
