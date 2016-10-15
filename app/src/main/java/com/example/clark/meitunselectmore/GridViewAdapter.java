package com.example.clark.meitunselectmore;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by clark on 2016/10/15.
 */

public class GridViewAdapter extends BaseAdapter {
    private List<Model> mDatas;
    private LayoutInflater mInflater;
    /**
     * 页数下标,从0开始(当前是第几页)
     */
    private int curIndex;
    /**
     * 每一页显示的个数
     */
    private int pageSize;


    public GridViewAdapter(Context context,List<Model> datas, int curIndex, int pageSize) {
        mInflater = LayoutInflater.from(context);
        mDatas = datas;
        this.curIndex = curIndex;
        this.pageSize = pageSize;
    }

    /**
     * 先判断数据集的大小是否足够显示满本页,如果够，则直接返回每一页显示的最大条目个数pageSize,如果不够，则有几项就返回几,(也就是最后一页的时候就显示剩余item)
     */
    @Override
    public int getCount() {
        return mDatas.size() > pageSize * (curIndex + 1) ? pageSize : (mDatas.size() - curIndex * pageSize);
    }

    @Override
    public Object getItem(int position) {
        return mDatas.get(position + curIndex * pageSize);
    }

    @Override
    public long getItemId(int position) {
        return  position + curIndex * pageSize;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null){
            convertView = mInflater.inflate(R.layout.item_gridview,parent,false);
            viewHolder = new ViewHolder();
            viewHolder.tv = (TextView) convertView.findViewById(R.id.textView);
            viewHolder.img = (ImageView) convertView.findViewById(R.id.imageView);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        int pos = position + curIndex * pageSize;
        viewHolder.img.setImageResource(mDatas.get(pos).getIconRes());
        viewHolder.tv.setText(mDatas.get(pos).getName());
        return convertView;
    }

    class ViewHolder{
        public TextView tv;
        public ImageView img;
    }
}
