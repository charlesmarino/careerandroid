package com.charlesmarino.tustle.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import com.charlesmarino.tustle.R;

/**
 * Fragment for the articles view
 * Created by kirstiebooras on 8/19/15.
 */
public class ArticlesFragment extends Fragment {

    private static final String BLOG_URL = "http://blog.apericore.com";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_articles, container, false);

        WebView webView = (WebView) view.findViewById(R.id.webview);
        //webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl(BLOG_URL);

        return view;

    }

}
