package com.watermelon.luomi.panda.utils;

import android.databinding.BindingAdapter;
import android.graphics.drawable.Drawable;
import android.webkit.MimeTypeMap;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by luomi on 2016-09-20.
 */
public class BindingUtils {

    @BindingAdapter({"android:imageUrl"})
    public static void loadImage(ImageView view, String url) {
        if (url == null)return;
        Picasso.with(view.getContext()).load(url).into(view);
    }

    @BindingAdapter({"android:body","android:css"})
    public static void loadWebView(WebView webView, String body,List<String> css){
        if (body==null&css==null)return;
        WebSettings webViewSettings = webView.getSettings();
        webViewSettings.setJavaScriptEnabled(true);
        webViewSettings.setDefaultTextEncodingName("utf-8");
        webViewSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);
        webViewSettings.setSupportZoom(false);
        String linkCss = "";
        if (css != null){
            for (String csStr : css) {
                linkCss += "<link rel=\"stylesheet\" href="+csStr+" type=\"text/css\">";
            }
        }
        String html = "<html><header>"+linkCss+"</header><body>"+body+"</body></html>";
        webView.loadDataWithBaseURL(null,html,"text/html".toString(),"utf-8",null);
    }
}
