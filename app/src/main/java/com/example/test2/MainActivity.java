package com.example.test2;


import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.example.test2.databinding.ActivityMainBinding;


public class MainActivity extends AppCompatActivity {
    private static final String TAG = "##MainActivity";
    Thread thread;
    boolean ing = false;
    CountViewModel countViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        countViewModel = new ViewModelProvider(this).get(CountViewModel.class);
        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        countViewModel.initCount();
        binding.textAdd.setOnClickListener(view -> {
            if (!ing) {
                ing = true;
                thread = new Thread(() -> {
                    while (ing) {
                        countViewModel.setPlusCount();
                        try {
                            Thread.sleep(1000);
                        } catch (Exception e) {
                            Log.d(TAG, "onCreate: e : " + e.getMessage());
                        }
                    }
                });
                thread.start();
            }
        });

        countViewModel.count.observe(this, count -> binding.countTextView.setText(count.toString()));
    }

    @Override
    protected void onResume() {
        super.onResume();
        ing = false;
        countViewModel.initCount();
    }
}