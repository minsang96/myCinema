package com.example.swproject;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.SearchView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;

public class Search_Result extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ArrayList<ItemObject> list = new ArrayList();
    String search_data, encodeResult;

    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search__result);

        Intent intent=getIntent();
        String name = intent.getExtras().getString("name"); /*String형*/
        search_data=name;

        tv=findViewById(R.id.result_Text);
        tv.setText("'"+search_data+"'의 검색결과");

        recyclerView = (RecyclerView) findViewById(R.id.result);
        final SearchView searchView= findViewById(R.id.result_searchview);

        list.clear();
        new Description().execute();


        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                // 검색 버튼이 눌러졌을 때 이벤트 처리
                list.clear();
                new Description().execute();
                search_data = query;
                tv.setText("'"+search_data+"'의 검색결과");
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                // 검색어가 변경되었을 때 이벤트 처리
                return false;
            }
        });

        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), recyclerView, new ClickListener() {
            @Override
            public void onClick(View view, int position) {
                Intent intent = new Intent(getApplicationContext(), DetailView.class);
                intent.putExtra("Link", list.get(position).getlink());
                intent.putExtra("Title", list.get(position).getTitle());
                startActivity(intent);

            }

            @Override
            public void onLongClick(View view, int position) {
            }
        }));

    }



    private class Description extends AsyncTask<Void, Void, Void> {

        private ProgressDialog progressDialog;

        @Override

        protected void onPreExecute() {
            super.onPreExecute();

            progressDialog = new ProgressDialog(Search_Result.this);
            progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            progressDialog.setMessage("잠시 기다려 주세요.");
            progressDialog.show();

        }

        protected Void doInBackground(Void... params) {
            try {
                try {
                    encodeResult = URLEncoder.encode(search_data, "EUC_KR");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                Document doc = Jsoup.connect("https://movie.naver.com/movie/search/result.nhn?section=movie&query="+ encodeResult).get();
                Elements mElementDataSize = doc.select("ul.search_list_1").select("li");
                int mElementSize = mElementDataSize.size();

                for (Element elem : mElementDataSize) {
                    System.out.println("mElementSize = " + mElementSize);
                    String my_title = elem.select("dl dt a").text();
                    String my_link = elem.select("p a").attr("href");
                    String my_imgUrl = elem.select("p a img").attr("src");
                    String my_release = elem.select("dd.etc").text();
                    String my_director = elem.select("em.num").text();

                    list.add(new ItemObject(my_title, my_imgUrl, my_link, my_release,"평점 : " + my_director));
                    Log.d("debug :", "List " + mElementDataSize);
                }

                for(int i =0; i<list.size(); i++) {
                    System.out.println("제목 : " + list.get(i).getTitle());
                    System.out.println("링크 : " + list.get(i).getlink());
                    System.out.println("이미지 링크 : " + list.get(i).getImg_url());
                    System.out.println("개봉일 : " + list.get(i).getRelease());
                    System.out.println("감독 : " + list.get(i).getDirector());
                    System.out.println("검색 리스트 사이즈 : "+ list.size());
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        protected void onPostExecute(Void result) {
            SearchAdapter myAdapter = new SearchAdapter(list);
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
            recyclerView.setLayoutManager(layoutManager);
            recyclerView.setAdapter(myAdapter);

            progressDialog.dismiss();
        }

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


