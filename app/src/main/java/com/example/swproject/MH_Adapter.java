package com.example.swproject;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.module.AppGlideModule;

import java.util.ArrayList;

public class MH_Adapter extends RecyclerView.Adapter<MH_Adapter.ViewHolder> {

    //데이터 배열 선언
    private ArrayList<MH_Object> M_List;

    public  class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView m_poster;

        public ViewHolder(View itemView) {
            super(itemView);
            m_poster = (ImageView) itemView.findViewById(R.id.item_thumnail);

        }
    }

    //생성자
    public MH_Adapter(ArrayList<MH_Object> list) {
        this.M_List = list;
    }

    @NonNull
    @Override
    public MH_Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_movie, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MH_Adapter.ViewHolder holder, int position) {
            //다 해줬는데도 GlideApp 에러가 나면 rebuild project를 해주자.
            GlideApp.with(holder.m_poster).load(M_List.get(position).getM_Img_url())
                    .override(300, 400)
                    .into(holder.m_poster);
    }

    @Override
    public int getItemCount() { return M_List.size(); }

    public final class MyAppGlideModule extends AppGlideModule {}
}
