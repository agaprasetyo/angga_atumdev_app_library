package id.atumdev.applib.devoptions.activity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import id.atumdev.applib.R;
import id.atumdev.applib.devoptions.model.WsUrlModel;

/**
 * Created by Angga.Prasetiyo on 28/09/2015.
 */
public class WsUrlListAdapter extends ArrayAdapter<WsUrlModel> {
    private static final String TAG = WsUrlListAdapter.class.getSimpleName();
    private List<WsUrlModel> dataList;
    private LayoutInflater inflater;

    public WsUrlListAdapter(Context context, ArrayList<WsUrlModel> objects) {
        super(context, R.layout.row_list_ws_url, objects);
        this.inflater = LayoutInflater.from(context);
        this.dataList = objects;
    }


    public int getCount() {
        return dataList.size();
    }

    public WsUrlModel getItem(int position) {
        return dataList.get(position);
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.row_list_ws_url, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.tvName.setText(dataList.get(position).getName());
        viewHolder.tvUrl.setText(dataList.get(position).getUrl());

        return convertView;

    }

    static class ViewHolder {
        private TextView tvName;
        private TextView tvUrl;

        public ViewHolder(View convertView) {
            this.tvName = (TextView) convertView.findViewById(R.id.tv_name);
            this.tvUrl = (TextView) convertView.findViewById(R.id.tv_url);
        }
    }

}
