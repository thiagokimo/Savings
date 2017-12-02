package io.kimo.savings.ui.main;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import io.kimo.savings.R;
import io.kimo.savings.util.ViewBuilder;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.main_container, ViewBuilder.buildSavingsListView(this))
                .commitAllowingStateLoss();
    }
}
