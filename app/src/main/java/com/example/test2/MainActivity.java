package com.example.test2;


import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.test2.databinding.ActivityMainBinding;


public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    Handler handler = new Handler();
    CountViewModel countVM;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        countVM = new ViewModelProvider(this).get(CountViewModel.class);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);


        binding.textAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BackRunnable runnable = new BackRunnable();
                Thread thread = new Thread(runnable);
                thread.setDaemon(true);
                thread.start();
            }
        });

        countVM.count.observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                binding.countTextView.setText(integer + "");
            }
        });
    }

    class BackRunnable implements Runnable {
        public void run() {
            while (true) {


                countVM.setPlusCount();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
    }

    Handler mHandler = new Handler() {
        @NonNull
        @Override
        public String getMessageName(@NonNull Message message) {
            return super.getMessageName(message);

        }

        public void handleMessage(Message msg) {
            if (msg.what == 0) {
//                backText.setText("BackValue : "+msg.arg1);
            }
        }
    };

}