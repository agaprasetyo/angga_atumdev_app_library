package id.atumdev.applib.devoptions.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import id.atumdev.applib.R;
import id.atumdev.applib.devoptions.datastore.DomainDataStore;
import id.atumdev.applib.devoptions.model.WsUrlModel;

public class DomainDevOptionActivity extends AppCompatActivity {
    private static final String EXTRA_DEFAULT_DOMAIN = "EXTRA_DEFAULT_DOMAIN";
    private static final String EXTRA_WS_URL_LIST = "EXTRA_WS_URL_LIST";
    private TextView tvCurrentDomain;
    private EditText etChangeDomain;
    private Button btnSave;
    private ListView listView;
    private String defaultDomain;


    public static Intent instanceIntent(@NonNull Context context,
                                        @NonNull String defaultDomain,
                                        ArrayList<WsUrlModel> wsUrlModels) {
        Intent intent = new Intent(context, DomainDevOptionActivity.class);
        intent.putExtra(EXTRA_DEFAULT_DOMAIN, defaultDomain);
        intent.putParcelableArrayListExtra(EXTRA_WS_URL_LIST, wsUrlModels);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_domain_dev_option);
        if (getSupportActionBar() != null) getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        setTitle("WEB SERVICE OPTIONS");

        Bundle bundle = getIntent().getExtras();
        defaultDomain = bundle.getString(EXTRA_DEFAULT_DOMAIN, "");
        ArrayList<WsUrlModel> wsUrlModelList = bundle.getParcelableArrayList(EXTRA_WS_URL_LIST);

        tvCurrentDomain = (TextView) findViewById(R.id.tv_current_domain);
        etChangeDomain = (EditText) findViewById(R.id.et_change_domain);
        btnSave = (Button) findViewById(R.id.btn_save);
        listView = (ListView) findViewById(R.id.list);

        tvCurrentDomain.setText(String.format("Current domain : %s", getCurrentDomain()));
        etChangeDomain.setText(getCurrentDomain());
        btnSave.setOnClickListener(new OnSave());
        listView.setAdapter(new WsUrlListAdapter(this, wsUrlModelList));
    }

    private String getCurrentDomain() {
        return DomainDataStore.getDomainUrl(this, defaultDomain);
    }

    private class OnSave implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            if (etChangeDomain.getText().length() != 0) {
                DomainDataStore.saveDomainUrl(DomainDevOptionActivity.this,
                        etChangeDomain.getText().toString());
                finish();
            } else {
                Toast.makeText(DomainDevOptionActivity.this,
                        "Domain can not be empty!!!", Toast.LENGTH_LONG).show();
            }

        }
    }
}
