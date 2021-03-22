package com.example.gameHelper.Adapter;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.gameHelper.R;
import com.example.gameHelper.Struct.GridViewItem;

import java.util.List;

public class DefaultGridAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    private List<GridViewItem> list;
    public DefaultGridAdapter(Context context,int layout, List<GridViewItem> list){
        this.context = context;
        this.list = list;
        this.layout = layout;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if(convertView == null){
            convertView = LayoutInflater.from(context).inflate(layout,parent,false);
            holder = new ViewHolder();
            holder.icon = convertView.findViewById(R.id.grid_view_item_icon);
            holder.name = convertView.findViewById(R.id.grid_view_item_name);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }
        GridViewItem item =  list.get(position);
        holder.icon.setImageResource(item.getIcon_rid());
        holder.name.setText(item.getName());
        return convertView;
    }
    class ViewHolder{
        ImageView icon;
        TextView name;
    }
}
