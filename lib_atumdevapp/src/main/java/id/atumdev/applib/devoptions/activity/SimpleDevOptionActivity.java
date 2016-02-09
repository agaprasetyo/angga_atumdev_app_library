package id.atumdev.applib.devoptions.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.DrawableRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import id.atumdev.applib.R;
import id.atumdev.applib.devoptions.datastore.DomainDataStore;
import id.atumdev.applib.devoptions.model.WsUrlModel;

public abstract class SimpleDevOptionActivity extends AppCompatActivity {


    private ImageView ivIconApp;
    private Button btnWsOpt;
    private Button btnCompiledRepo;
    private Button btnSourceRepo;

    private TextView tvAppName;
    private TextView tvAppVer;
    private TextView tvAppVerCode;
    private TextView tvAppBuildType;
    private TextView tvAppId;
    private TextView tvBuildTime;
    private TextView tvAuthor;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_dev_option);
        try {
            getActionBar().setDisplayShowHomeEnabled(false);
        } catch (Exception e) {
            e.printStackTrace();
        }

        setTitle("DEVELOPER OPTIONS");

        tvAppName = (TextView) findViewById(R.id.tv_app_detail_name);
        tvAppVer = (TextView) findViewById(R.id.tv_app_detail_version);
        tvAppVerCode = (TextView) findViewById(R.id.tv_app_detail_version_code);
        tvAppBuildType = (TextView) findViewById(R.id.tv_app_detail_build_type);
        tvAppId = (TextView) findViewById(R.id.tv_app_detail_id);
        tvBuildTime = (TextView) findViewById(R.id.tv_app_detail_build_time);
        tvAuthor = (TextView) findViewById(R.id.tv_app_detail_author);
        ivIconApp = (ImageView) findViewById(R.id.iv_icon_app);
        btnWsOpt = (Button) findViewById(R.id.btn_opt_domain);
        btnCompiledRepo = (Button) findViewById(R.id.btn_compiled_repo);
        btnSourceRepo = (Button) findViewById(R.id.btn_source_repo);


        ivIconApp.setImageResource(getImageResIdIconApp());
        tvAppName.setText(String.format(": %s", getAppName()));
        tvAppId.setText(String.format(": %s", getAppId()));
        tvAppVer.setText(String.format(": %s", getAppVersion()));
        tvAppVerCode.setText(String.format(": %s", getAppVersionCode()));
        tvAppBuildType.setText(String.format(": %s", getAppBuildType()));
        tvBuildTime.setText(String.format(": %s", getBuildTimeFormat()));
        tvAuthor.setText(String.format(": %s", getAuthor()));

        btnWsOpt.setOnClickListener(new OpenDomainOpt());
        btnCompiledRepo.setOnClickListener(new OpenCompiledRepo());
        btnSourceRepo.setOnClickListener(new OpenSourceRepo());
    }

    public String getBuildTimeFormat() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm z", Locale.getDefault());
        return sdf.format(new Date(getAppBuildTime()));
    }

    protected abstract String getAppName();

    protected abstract String getAppVersion();

    protected abstract String getAppVersionCode();

    protected abstract String getAppBuildType();

    protected abstract String getAppId();

    protected abstract long getAppBuildTime();

    protected abstract ArrayList<WsUrlModel> getWsUrlList();

    protected abstract String getCompiledRepoUrl();

    protected abstract String getSourceRepoUrl();

    protected abstract String getAuthor();

    @DrawableRes
    protected abstract int getImageResIdIconApp();

    protected abstract String getDefaultDomain();

    private class OpenDomainOpt implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            startActivity(DomainDevOptionActivity.instanceIntent(SimpleDevOptionActivity.this, getDefaultDomain(), getWsUrlList()));
        }
    }

    private class OpenCompiledRepo implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            Intent i = new Intent(Intent.ACTION_VIEW);
            i.setData(Uri.parse(getCompiledRepoUrl()));
            startActivity(i);
        }
    }

    private class OpenSourceRepo implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            Intent i = new Intent(Intent.ACTION_VIEW);
            i.setData(Uri.parse(getSourceRepoUrl()));
            startActivity(i);
        }
    }
}
