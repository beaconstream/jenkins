package com.travelur.travelconnect.signedinhome.navigationtype;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.travelur.R;
import com.travelur.general.BaseFragment;
import com.travelur.utility.AppConfig;

public class TermsFragment extends BaseFragment implements  View.OnClickListener {

    private WebView webView;
    private ImageView close;
    private TextView title;

    public static TermsFragment newInstance() {
        TermsFragment fragment = new TermsFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_terms, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        view.setClickable(true);
        close = (ImageView) view.findViewById(R.id.close);
        close.setBackgroundResource(R.drawable.ic_action_close_cyan);
        title = (TextView) view.findViewById(R.id.action_bar_title);
        title.setText("Terms of Service");
        webView = (WebView) view.findViewById(R.id.terms_webview);
        webView.getSettings().setTextSize(WebSettings.TextSize.LARGEST);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setUseWideViewPort(true);
        webView.getSettings().setLoadWithOverviewMode(true);
        webView.getSettings().setBuiltInZoomControls(true);
        webView.getSettings().setPluginState(WebSettings.PluginState.ON);
        webView.loadUrl(AppConfig.TermsOfService);
        webView.setWebViewClient(new WebViewClient() {
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                Toast.makeText(getActivity(), description, Toast.LENGTH_SHORT).show();
                //swipeRefreshLayout.setRefreshing(false);
            }

        });

       /* swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipeRefreshLayout);
        swipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary, R.color.colorAccent,R.color.Ash_Gray,R.color.colorPrimary);
        swipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                swipeRefreshLayout.setRefreshing(true);
                //Calling method to get data
                webView.loadUrl(AppConfig.TermsOfService);
                swipeRefreshLayout.setRefreshing(false);
            }
        });*/
        close.setOnClickListener(this);
    }

   /* @Override
    public void onRefresh() {
        //Calling method to get data
        swipeRefreshLayout.setRefreshing(true);
        webView.loadUrl(AppConfig.TermsOfService);
        swipeRefreshLayout.setRefreshing(false);
    }*/

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.close:
                if (getFragmentManager().getBackStackEntryCount() > 0) {
                    getFragmentManager().popBackStack();
                }
                break;
        }
    }
}
