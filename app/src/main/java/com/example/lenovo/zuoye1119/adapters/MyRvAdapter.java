package com.example.lenovo.zuoye1119.adapters;

import android.content.Context;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.lenovo.zuoye1119.R;
import com.example.lenovo.zuoye1119.beans.DatasBean;


import java.util.List;

import androidx.recyclerview.widget.RecyclerView;


public class MyRvAdapter extends RecyclerView.Adapter<MyRvAdapter.ViewHolder>{
    private List<DatasBean> datas;
    private Context context;

    public MyRvAdapter(List<DatasBean> datas, Context context) {
        this.datas = datas;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.main_item_layout,null);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        Glide.with(context).load(datas.get(position).getEnvelopePic())
                .into(holder.envelopePic);
        holder.title.setText(datas.get(position).getTitle());
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                listener.OnItemLong(v,position);
                return false;
            }
        });
    }
    @Override
    public int getItemCount() {
        return datas.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView envelopePic;
        TextView title;
        public ViewHolder(View itemView) {
            super(itemView);
            envelopePic = itemView.findViewById(R.id.envelopePic);
            title = itemView.findViewById(R.id.title);
        }
    }
    private OnItemClickLongListener listener;

    public void setListener(OnItemClickLongListener listener) {
        this.listener = listener;
    }

    public interface OnItemClickLongListener{
        void OnItemLong(View view, int position);
    }
}
