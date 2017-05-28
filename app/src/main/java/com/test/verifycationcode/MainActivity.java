package com.test.verifycationcode;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.test.verifycationcode.view.VerifycationCodeView;

public class MainActivity extends AppCompatActivity {

    private VerifycationCodeView verifycationCodeView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
    }

    /**
     * initView
     */
    private void initView() {
        verifycationCodeView = (VerifycationCodeView) findViewById(R.id.verifycation_code_view);
    }

    /**
     * initData
     */
    private void initData() {
        verifycationCodeView.setAutoCommitListener(new VerifycationCodeView.AutoCommitListener() {
            @Override
            public void autoCommit(String inputText) {
                Toast.makeText(MainActivity.this, inputText, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
