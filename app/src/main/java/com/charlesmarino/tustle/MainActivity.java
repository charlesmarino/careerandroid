package com.charlesmarino.tustle;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.linkedin.platform.LISession;
import com.linkedin.platform.LISessionManager;
import com.linkedin.platform.errors.LIAuthError;
import com.linkedin.platform.listeners.AuthListener;
import com.linkedin.platform.utils.Scope;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    public static final String PACKAGE_MOBILE_SDK_SAMPLE_APP = "com.linkedin.android.mobilesdksampleapp";
    FloatingActionButton fab;
    TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupToolbar();
        setupFab();
        setUpdateState();
    }

    private void setupFab(){
        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(this);
    }

    private void setupToolbar(){
        Toolbar toolbar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);
        // Show menu icon
        final ActionBar ab = getSupportActionBar();
        ab.setHomeAsUpIndicator(R.drawable.ic_fab_star);
        ab.setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.fab) {
String TAG ="d";
            try {
                PackageInfo info = getPackageManager().getPackageInfo(
                        PACKAGE_MOBILE_SDK_SAMPLE_APP,
                        PackageManager.GET_SIGNATURES);
                for (Signature signature : info.signatures) {
                    MessageDigest md = MessageDigest.getInstance("SHA");
                    md.update(signature.toByteArray());

                    ((TextView) findViewById(R.id.text)).setText(info.packageName);
                    ((TextView) findViewById(R.id.text2)).setText(Base64.encodeToString(md.digest(), Base64.NO_WRAP));
                }
            } catch (PackageManager.NameNotFoundException e) {
                Log.d(TAG, e.getMessage(), e);
            } catch (NoSuchAlgorithmException e) {
                Log.d(TAG, e.getMessage(), e);
            }

            final Activity thisActivity = this;
            LISessionManager.getInstance(getApplicationContext()).init(thisActivity, buildScope(), new AuthListener() {
                @Override
                public void onAuthSuccess() {
                    setUpdateState();
                    Toast.makeText(getApplicationContext(), "success" + LISessionManager.getInstance(getApplicationContext()).getSession().getAccessToken().toString(), Toast.LENGTH_LONG).show();
                }

                @Override
                public void onAuthError(LIAuthError error) {
                    setUpdateState();
//                    ((TextView) findViewById(R.id.at)).setText(error.toString());
                    Toast.makeText(getApplicationContext(), "failed " + error.toString(), Toast.LENGTH_LONG).show();
                }
            }, true);
        }

    }

    private void setUpdateState() {
        LISessionManager sessionManager = LISessionManager.getInstance(getApplicationContext());
        LISession session = sessionManager.getSession();
        boolean accessTokenValid = session.isValid();

        ((TextView) findViewById(R.id.text)).setText(
                accessTokenValid ? session.getAccessToken().toString() : "Sync with LinkedIn to enable these buttons");
//        ((Button) findViewById(R.id.apiCall)).setEnabled(accessTokenValid);
//        ((Button) findViewById(R.id.deeplink)).setEnabled(accessTokenValid);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        LISessionManager.getInstance(getApplicationContext()).onActivityResult(this, requestCode, resultCode, data);
    }


    private static Scope buildScope() {
        return Scope.build(Scope.R_BASICPROFILE, Scope.W_SHARE);
    }
}
