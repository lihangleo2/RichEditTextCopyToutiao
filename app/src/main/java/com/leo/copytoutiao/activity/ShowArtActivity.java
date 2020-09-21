package com.leo.copytoutiao.activity;

import android.annotation.TargetApi;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.http.SslError;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.leo.copytoutiao.R;
import com.leo.copytoutiao.databinding.ActivityShowArtBinding;
import com.leo.copytoutiao.utils.RichUtils;

import java.util.ArrayList;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

/**
 * Created by leo
 * on 2020/9/21.
 */
public class ShowArtActivity extends AppCompatActivity {
    ActivityShowArtBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_show_art);
        SharedPreferences sharedPreferences = getSharedPreferences("art", MODE_PRIVATE);
        String title = sharedPreferences.getString("title", "title");
        String content = sharedPreferences.getString("content", "");
        Log.e("这是什么情况呢", content);

//        content = "上海专业用户上海专业用户上海专业用户上海专业用户上海专业用户<p></p><p><img src=\"https://greenvalley.oss-cn-shanghai.aliyuncs.com/patient/270f8cbc044b4400bdb098d67b72a859_160_160.png\" style=\"max-width:100%;\"></p>";
        initWebView(content);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(title);

    }


    public void initWebView(String data) {

        WebSettings settings = binding.webView.getSettings();

        //settings.setUseWideViewPort(true);//调整到适合webview的大小，不过尽量不要用，有些手机有问题
        settings.setLoadWithOverviewMode(true);//设置WebView是否使用预览模式加载界面。
        settings.setAllowUniversalAccessFromFileURLs(true);
        settings.setAllowFileAccess(true);
        settings.setAllowFileAccessFromFileURLs(true);


        binding.webView.setVerticalScrollBarEnabled(false);//不能垂直滑动
        binding.webView.setHorizontalScrollBarEnabled(false);//不能水平滑动
        settings.setTextSize(WebSettings.TextSize.NORMAL);//通过设置WebSettings，改变HTML中文字的大小
        settings.setJavaScriptCanOpenWindowsAutomatically(true);//支持通过JS打开新窗口
        //设置WebView属性，能够执行Javascript脚本
        binding.webView.getSettings().setJavaScriptEnabled(true);//设置js可用

        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);//支持内容重新布局
        binding.webView.setWebViewClient(webViewClient);
        binding.webView.setWebChromeClient(new WebChromeClient());
        data = "</Div><head><style>body{font-size:16px}</style>" +
                "<style>img{ width:100% !important;margin-top:0.4em;margin-bottom:0.4em}</style>" +
                "<style>ul{ padding-left: 1em;margin-top:0em}</style>" +
                "<style>ol{ padding-left: 1.2em;margin-top:0em}</style>" +
                "</head>" + data;

        ArrayList<String> arrayList = RichUtils.returnImageUrlsFromHtml(data);
        if (arrayList.size() > 0) {
            for (int i = 0; i < arrayList.size(); i++) {
                if (!arrayList.get(i).contains("http")) {
                    //如果不是http,那么就是本地绝对路径，要加上file
                    data = data.replace(arrayList.get(i), "file://" + arrayList.get(i));
                }
            }
        }

        binding.webView.loadDataWithBaseURL(null, data, "text/html", "utf-8", null);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;

            case R.id.repet_editor:
                Intent intent = new Intent(ShowArtActivity.this, PublishActivity.class);
                intent.putExtra("isFrom", 1);
                startActivity(intent);
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    public WebViewClient webViewClient = new WebViewClient() {
        @Override
        public void onPageFinished(WebView view, String url) {
            Log.e("这里还是有问题啊", "onPageFinished == " + url);
            super.onPageFinished(view, url);
        }


        @Override
        public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
            handler.proceed();
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            Log.e("什么子情况咯", url + "===");
            return super.shouldOverrideUrlLoading(view, url);
        }


        @TargetApi(Build.VERSION_CODES.LOLLIPOP)
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
            String url = request.getUrl().toString();
            Log.e("什么子情况咯", url + "===");
            return super.shouldOverrideUrlLoading(view, request);
        }


    };
}
