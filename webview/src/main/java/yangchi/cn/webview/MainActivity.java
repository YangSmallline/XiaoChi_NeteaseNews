package yangchi.cn.webview;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.webkit.JavascriptInterface;
import android.webkit.JsPromptResult;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private WebView mWebView;
    private ImageView mImageView;

    @SuppressLint("JavascriptInterface")
    @JavascriptInterface
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        mImageView = findViewById(R.id.iv_main);
        mWebView = findViewById(R.id.webview);
        mWebView.getSettings().setAllowFileAccess(true);
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.getSettings().setBuiltInZoomControls(true);
        mWebView.loadUrl("file:///android_asset/my.html");

        mWebView.addJavascriptInterface(new My(),"demo");
        mWebView.addJavascriptInterface(new My(),"demo1");

        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.loadUrl("file:///android_asset/my.html");

//        mWebView.setWebViewClient(new WebViewClient(){
//
//            @Override
//            public boolean shouldOverrideUrlLoading(WebView view, String url) {
//                mWebView.loadUrl(url);
//                return super.shouldOverrideUrlLoading(view, url);
//            }

//            @Override
//            public void onPageStarted(WebView view, String url, Bitmap favicon) {
//                Toast.makeText(getApplicationContext(),"开始加载",Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void onPageFinished(WebView view, String url) {
//                Toast.makeText(getApplicationContext(),"加载结束",Toast.LENGTH_SHORT).show();
//            }

            //6.0
//            @Override
//            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
//                mWebView.loadUrl("http://www.sina.com.cn");
//                //页面找不到的情况下面处理,本地加载失败html
//            }
//
//            @Override
//            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
//                super.onReceivedError(view, errorCode, description, failingUrl);
//            }
//
//            //监控网页上资源的情况
//            @Override
//            public void onLoadResource(WebView view, String url) {
//                Log.i("tag", "onLoadResource: "+url);
//                super.onLoadResource(view, url);
//            }


        mWebView.setWebChromeClient(new WebChromeClient(){
            @Override
            public boolean onJsPrompt(WebView view, String url, String message, String defaultValue, JsPromptResult result) {
                //从这里赋值给js的promot
                result.confirm("支付成功");
                return super.onJsPrompt(view, url, message, defaultValue, result);
            }

            //捕获js-alert
            @Override
            public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
                Toast.makeText(getApplicationContext(),message,Toast.LENGTH_SHORT).show();
                result.confirm();
                return true;
            }});
//
//            @Override
//            public void onProgressChanged(WebView view, int newProgress) {
//                Log.i("tag", "onProgressChanged: "+newProgress);
//                super.onProgressChanged(view, newProgress);
//            }
//
//            @Override
//            public void onReceivedIcon(WebView view, Bitmap icon) {
//                //接收标签的图标
////                mImageView.setImageBitmap(icon);
//                super.onReceivedIcon(view, icon);
//            }
//
//            @Override
//            public void onReceivedTitle(WebView view, String title) {
//                super.onReceivedTitle(view, title);
//            }
//        });
    }


    @Override
    public void onBackPressed() {
        if (mWebView.canGoBack()){
            mWebView.goBack();
        }
    }

    class My {
        @JavascriptInterface
        public void get(){
            Toast.makeText(getApplicationContext(),"woqu",Toast.LENGTH_SHORT).show();
        }
        @JavascriptInterface
        public void get1(){
            Toast.makeText(getApplicationContext(),"javaScript调用了我",Toast.LENGTH_SHORT).show();
        }
    }


}
