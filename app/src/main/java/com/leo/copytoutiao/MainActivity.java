package com.leo.copytoutiao;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Toast;

import com.leo.copytoutiao.activity.PublishActivity;
import com.leo.copytoutiao.activity.ShowArtActivity;
import com.leo.copytoutiao.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        binding.buttonPublish.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, PublishActivity.class));
        });

        binding.buttonLook.setOnClickListener(v -> {
            SharedPreferences sharedPreferences = getSharedPreferences("art", MODE_PRIVATE);
            String title = sharedPreferences.getString("title", "title");
            if (TextUtils.isEmpty(title)) {
                Toast.makeText(MainActivity.this, "暂无发布的文章~", Toast.LENGTH_SHORT).show();
            } else {
                startActivity(new Intent(MainActivity.this, ShowArtActivity.class));
            }
        });
    }
}
