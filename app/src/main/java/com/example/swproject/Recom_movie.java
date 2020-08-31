package com.example.swproject;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.Serializable;
import java.util.ArrayList;

public class Recom_movie extends AppCompatActivity implements Serializable {
    private static final long serialVersionUID = 1L;
    private RecyclerView recyclerView;
    private ArrayList<ItemObject> usergenre = new ArrayList();     // 유저장르
    TextView MovieType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pm_movie);

        Intent intent=getIntent();
        String name = intent.getExtras().getString("name"); /*String형*/
        ArrayList<ItemObject> list = (ArrayList<ItemObject>) intent.getSerializableExtra("list");
        usergenre = list;

        MovieType=findViewById(R.id.movietype);
        MovieType.setText(name);

        recyclerView = (RecyclerView) findViewById(R.id.plusmovie);

        for(int i=0; i<usergenre.size(); i++) {
            System.out.println("플 제목 : " + usergenre.get(i).getTitle());
            System.out.println("플 이미지 : " + usergenre.get(i).getImg_url());
            System.out.println("플 링크 : " + usergenre.get(i).getlink());
            System.out.println("플 감독 : " + usergenre.get(i).getDirector());
            System.out.println("플 개봉일 : " + usergenre.get(i).getRelease());
        }

        recyclerView.addOnItemTouchListener(new MyHome.RecyclerTouchListener(getApplicationContext(), recyclerView, new MyHome.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                Intent intent = new Intent(getApplicationContext(), DetailView.class);
                intent.putExtra("Link", usergenre.get(position).getlink());
                intent.putExtra("Title", usergenre.get(position).getTitle());

                startActivity(intent);
            }

            @Override
            public void onLongClick(View view, int position) {
            }
        }));

        SearchAdapter myAdapter = new SearchAdapter(usergenre);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(myAdapter);

    }


    public interface ClickListener {
        void onClick(View view, int position);

        void onLongClick(View view, int position);
    }

    public static class RecyclerTouchListener implements RecyclerView.OnItemTouchListener {

        private GestureDetector gestureDetector;
        private Search_Result.ClickListener clickListener;

        public RecyclerTouchListener(Context context, final RecyclerView recyclerView, final Search_Result.ClickListener clickListener) {
            this.clickListener = clickListener;
            gestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {
                @Override
                public boolean onSingleTapUp(MotionEvent e) {
                    return true;
                }

                @Override
                public void onLongPress(MotionEvent e) {
                    View child = recyclerView.findChildViewUnder(e.getX(), e.getY());
                    if (child != null && clickListener != null) {
                        clickListener.onLongClick(child, recyclerView.getChildAdapterPosition(child));
                    }
                }
            });
        }

        @Override
        public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
            View child = rv.findChildViewUnder(e.getX(), e.getY());
            if (child != null && clickListener != null && gestureDetector.onTouchEvent(e)) {
                clickListener.onClick(child, rv.getChildAdapterPosition(child));
            }
            return false;
        }

        @Override
        public void onTouchEvent(RecyclerView rv, MotionEvent e) {
        }

        @Override
        public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {
        }
    }

}


