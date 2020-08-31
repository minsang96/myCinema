package com.example.swproject;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
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

public class DetailView extends AppCompatActivity {
    private ImageView poster;
    private TextView title;
    private TextView info;
    private TextView director;
    private TextView country;
    private TextView runtime;
    private TextView release;
    private TextView actor;
    private TextView grade;
    private TextView story;
    private TextView story2;
    private Button link_btn;

    private RecyclerView actorphotoRV;
    private ArrayList<MA_Object> actor_photo=new ArrayList<>();

    String link;
    String POSTER,TITLE, INFO, DIRECTOR, COUNTRY, RUNTIME, RELEASE, ACTOR, GRADE, STORY, STORY2, ACTORNAME, ACTORPHOTO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detailview);

        Intent intent = getIntent();
        link = intent.getStringExtra("Link");

        poster=findViewById(R.id.detail_poster);
        title=findViewById(R.id.detail_title);
        info=findViewById(R.id.detail_info);
        director=findViewById(R.id.detail_director);
        country=findViewById(R.id.detail_country);
        runtime=findViewById(R.id.detail_runtime);
        release=findViewById(R.id.detail_release);
        actor=findViewById(R.id.detail_actor);
        grade=findViewById(R.id.detail_grade);
        story=findViewById(R.id.detail_story);
        story2=findViewById(R.id.detail_story2);
        link_btn=findViewById(R.id.linkbtn);

        actorphotoRV=findViewById(R.id.actor_photoRV);
        new Description().execute();

        link_btn.setOnClickListener(new View.OnClickListener(){
            @Override

            public void onClick(View view) {//지도페이지로 넘어감
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://movie.naver.com" + link));
                startActivity(intent);

            }
        });
    }


    private class Description extends AsyncTask<Void, Void, Void> {

        private ProgressDialog progressDialog;

        @Override

        protected void onPreExecute() {
            super.onPreExecute();

            progressDialog = new ProgressDialog(DetailView.this);
            progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            progressDialog.setMessage("잠시 기다려 주세요.");
            progressDialog.show();

            progressDialog.dismiss();
        }

        protected Void doInBackground(Void... params) {

            try {
                Document doc = Jsoup.connect("https://movie.naver.com" + link).get();
                Elements mElementDataSize = doc.select("div.article");
                Elements mElementDataSize2 = doc.select("div.people").select("li");

                int mElementSize = mElementDataSize.size();
                int mElementSize2 = mElementDataSize2.size();
                for (Element elem : mElementDataSize) {

                    System.out.println("mElementSize = " + mElementSize);
                    System.out.println("mElementSize2 = " + mElementSize2);
                    
                    if(elem.select("div.poster a img").attr("alt")!=null) {
                        String my_title = elem.select("div.poster a img").attr("alt");
                        TITLE = my_title;
                    }

                    for(Element elem2 : mElementDataSize2) {
                        if (elem2.select("a img") != null) {
                            ACTORNAME = elem2.select("a img").attr("alt");
                            ACTORPHOTO = elem2.select("a img").attr("src");

                            actor_photo.add(new MA_Object(ACTORPHOTO, ACTORNAME));
                        }
                    }

                    Element iElem = elem.select("dt.step1").next().first();

                    if(iElem!=null) {
                        if (iElem.select("span").first() != null) {
                            String my_info = iElem.select("span").first().text();
                            INFO = my_info;
                        }
                        if (iElem.select("span").next().first() != null) {
                            String my_country = iElem.select("span").next().first().text();
                            COUNTRY = my_country;
                        }
                        if (iElem.select("span").next().next().first() != null) {
                            String my_runtime = iElem.select("span").next().next().first().text();
                            RUNTIME = my_runtime;
                        }
                        if (iElem.select("span").next().next().next().first() != null) {
                            String my_release = iElem.select("span").next().next().next().first().text();
                            RELEASE = my_release;
                        }
                    }

                    Element rElem = elem.select("dt.step2").next().first();

                    if(rElem!=null) {
                        String my_director = rElem.select("dd").text();
                        DIRECTOR = my_director;
                    }
                    Element aElem = elem.select("dt.step3").next().first();
                    if(aElem!=null) {
                        String my_actor = aElem.select("dd p").text();
                        ACTOR = my_actor;
                    }

                    Element gElem = elem.select("dt.step4").next().first();

                    if(gElem!=null) {
                        String my_grade = gElem.select("dd").text();
                        GRADE = my_grade;
                    }
                    if(elem.select("h5.h_tx_story")!=null) {
                        String my_story = elem.select("h5.h_tx_story").text();
                        STORY = my_story;
                    }
                    if(elem.select("p.con_tx").first()!=null) {
                        String my_story2 = elem.select("p.con_tx").first().text();
                        STORY2 = my_story2;
                    }

                    POSTER = link.substring(link.lastIndexOf("="));
                }

            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
                Document doc = Jsoup.connect("https://movie.naver.com/movie/bi/mi/photoViewPopup.nhn?movieCode"+POSTER).get();
                Elements mElementDataSize = doc.select("body");
                int mElementSize = mElementDataSize.size();

                for (Element elem : mElementDataSize) {
                    String my_poster = elem.select("a img").attr("src");

                    POSTER= my_poster;

                }

            } catch (IOException e) {
                e.printStackTrace();
            }


            return null;
        }

        protected void onPostExecute(Void result) {
            MA_Adapter myAdapter=new MA_Adapter(actor_photo);
            actorphotoRV.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false));
            actorphotoRV.setAdapter(myAdapter);

            GlideApp.with(poster).load(POSTER)
                    .override(300, 400)
                    .into(poster);

            title.setText(TITLE);
            info.setText(INFO);
            country.setText(COUNTRY);
            runtime.setText(RUNTIME);
            release.setText(RELEASE);
            director.setText(DIRECTOR);
            actor.setText(ACTOR);
            grade.setText(GRADE);
            story.setText(STORY);
            story2.setText(STORY2);

            progressDialog.dismiss();
        }

    }
}
