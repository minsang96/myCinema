package com.example.swproject;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.module.AppGlideModule;

import java.util.ArrayList;

public class MA_Adapter extends RecyclerView.Adapter<MA_Adapter.ViewHolder> {

    //데이터 배열 선언
    private ArrayList<MA_Object> M_List;

    public  class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView m_poster;
        private TextView m_name;
        public ViewHolder(View itemView) {
            super(itemView);
            m_poster = (ImageView) itemView.findViewById(R.id.item_photo);
            m_name = (TextView) itemView.findViewById(R.id.item_name);

        }
    }

    //생성자
    public MA_Adapter(ArrayList<MA_Object> list) {
        this.M_List = list;
    }

    @NonNull
    @Override
    public MA_Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_actorphoto, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MA_Adapter.ViewHolder holder, int position) {
        //다 해줬는데도 GlideApp 에러가 나면 rebuild project를 해주자.
        holder.m_name.setText(String.valueOf(M_List.get(position).getM_Name()));
        GlideApp.with(holder.itemView).load(M_List.get(position).getM_Img_url())
                .override(300,400)
                .into(holder.m_poster);
    }

    @Override
    public int getItemCount() { return M_List.size(); }

    public final class MyAppGlideModule extends AppGlideModule {}
}
