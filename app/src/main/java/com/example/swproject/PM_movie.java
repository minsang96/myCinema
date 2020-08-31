package com.example.swproject;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

public class PM_movie extends AppCompatActivity {
    private RecyclerView plusmovie;
    TextView MovieType;
    private ArrayList<ItemObject> list = new ArrayList();
    String URL_link;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pm_movie);


        plusmovie=findViewById(R.id.plusmovie);

        Intent intent=getIntent();
        String name = intent.getExtras().getString("name");
        String Link = intent.getExtras().getString("Link");
        URL_link = Link;

        MovieType=findViewById(R.id.movietype);
        MovieType.setText(name);


        list.clear();
        new Description().execute();


        plusmovie.addOnItemTouchListener(new MyHome.RecyclerTouchListener(getApplicationContext(), plusmovie, new MyHome.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                Intent intent = new Intent(getApplicationContext(), DetailView.class);
                intent.putExtra("Link", list.get(position).getlink());

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

            progressDialog = new ProgressDialog(PM_movie.this);
            progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            progressDialog.setMessage("잠시 기다려 주세요.");
            progressDialog.show();

        }

        protected Void doInBackground(Void... params) {
            try {
                Document doc = Jsoup.connect(URL_link).get();
                Elements mElementDataSize  = doc.select("ul.lst_detail_t1").select("li");
                int mElementSize = mElementDataSize.size();

                for (Element elem : mElementDataSize) {
                    String my_title = elem.select("li dt[class=tit] a").text();
                    String my_link = elem.select("li div[class=thumb] a").attr("href");
                    String my_imgUrl = elem.select("li div[class=thumb] a img").attr("src");
                    Element rElem = elem.select("dl[class=info_txt1] dt").next().first();
                    String my_release = rElem.select("dd").text();
                    Element dElem = elem.select("dl.info_txt1 dt.tit_t2").next().first();
                    String my_director = "감독: " + dElem.select("a").text();
                    list.add(new ItemObject(my_title, my_imgUrl, my_link, my_release,my_director));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        protected void onPostExecute(Void result) {
            SearchAdapter myAdapter = new SearchAdapter(list);
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
            plusmovie.setLayoutManager(layoutManager);
            plusmovie.setAdapter(myAdapter);

            progressDialog.dismiss();
        }

    }

    public interface ClickListener {
        void onClick(View view, int position);

        void onLongClick(View view, int position);
    }

    public static class RecyclerTouchListener implements RecyclerView.OnItemTouchListener {

        private GestureDetector gestureDetector;
        private MyHome.ClickListener clickListener;

        public RecyclerTouchListener(Context context, final RecyclerView recyclerView, final MyHome.ClickListener clickListener) {
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
