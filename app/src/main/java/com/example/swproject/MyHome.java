package com.example.swproject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;

public class MyHome extends AppCompatActivity implements Serializable {
    private static final long serialVersionUID = 1L;
    Button MovieButton;
    Button MapButton;
    Button MyButton;
    Button PlusButton1;
    Button PlusButton2;
    Button PlusButton3;
    Button PlusButton4;
    Button PlusButton5;
    private RecyclerView PopMovieRV;//------------------------------------------------------------------------------------------------------------------
    private RecyclerView UserGenreRV;//---------------------------------------------------------------------------------------------------------------
    private RecyclerView AnotherRV;//----------------------------------------------------------------------------------------------------------------
    private RecyclerView NewRV;//---------------------------------------------------------------------------------------------------------------------
    private RecyclerView ReleaseRV;//---------------------------------------------------------------------------------------------------------------------
    public String search_data;//-------------------------------------------------------------------------------------------------------------------12.03
    public static Activity MHactivity;
    private long time= 0;
    int count=0;

    private FirebaseAuth mAuth;
    FirebaseDatabase fdatabase = FirebaseDatabase.getInstance();
    private static final String TAG = "MyHome";
    int [] moviearray;
    String TITLE,RELEASE,DIRECTOR, POSTER;

    private ArrayList<MH_Object> popmovie=new ArrayList<>();//-------------------------------------------------------------------------------------------------------인기영화 데이터를 담을 리스트
    private ArrayList<ItemObject> usergenre=new ArrayList<>();//------------------------------------------------------------------------------------------------------유저장르데이터를 반영한 영화 데이터를 담을 리스트
    private ArrayList<MH_Object> usergenre2=new ArrayList<>();//------------------------------------------------------------------------------------------------------유저장르데이터를 반영한 영화 데이터를 5개만 담을 리스트
    private ArrayList<MH_Object> anothermovie=new ArrayList<>();//---------------------------------------------------------------------------------------------------유저선호장르를 제외한 영화 데이터를 담을 리스트
    private ArrayList<MH_Object> newmovie=new ArrayList<>();//-------------------------------------------------------------------------------------------------------새로 개봉한 영화의 데이터를 담을 리스트
    private ArrayList<MH_Object> releasemovie=new ArrayList<>();//-------------------------------------------------------------------------------------------------------개봉 예정 영화의 데이터를 담을 리스트
    private ArrayList<GenreObject> genrelink=new ArrayList<>();//-------------------------------------------------------------------------------------------------------유저 추천 영화의 링크를 담을 리스트

    @Override
    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_home);
        MHactivity = MyHome.this;

        mAuth = FirebaseAuth.getInstance();
        String usruid = mAuth.getCurrentUser().getUid();


        MovieButton=findViewById(R.id.Movie);//영화홈 버튼
        MapButton=findViewById(R.id.Map);//지도버튼
        MyButton=findViewById(R.id.My);//내정보 버튼
        PlusButton1=findViewById(R.id.plus1);//인기영화 더보기
        PlusButton2=findViewById(R.id.plus2);//유저 추천영화 더보기
        PlusButton3=findViewById(R.id.plus3);//유저 장으이외 영화
        PlusButton4=findViewById(R.id.plus4);//최신영화
        PlusButton5=findViewById(R.id.plus5);//개봉예정영화

        PopMovieRV=findViewById(R.id.Rv_popmovie);//-------------------------------------------------------------------------------------------------인기영화 리사이클뷰
        UserGenreRV=findViewById(R.id.RV_usergenre);//-----------------------------------------------------------------------------------------------유저장르 리사이클뷰
        AnotherRV=findViewById(R.id.RV_another);//----------------------------------------------------------------------------------------------------유저장르 제외 리사이클뷰
        NewRV=findViewById(R.id.RV_new);//-------------------------------------------------------------------------------------------------------------새로 개봉한 영화 리사이클뷰
        ReleaseRV=findViewById(R.id.RV_release);//-------------------------------------------------------------------------------------------------------------새로 개봉한 영화 리사이클뷰
        SearchView searchView = findViewById(R.id.searchForm);//----------------------------------------------------------------------------------------------------------------------12.03

        new Description().execute();
        new Description3().execute();
        new Description2().execute();
        new Description4().execute();
        new Description5().execute();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {//--------------------------------------------------------------------12.03{검색버튼을 눌렀을 때 이벤트 처리: Search_Result페이지로 검색창에 검색한 텍스트 데이터가 넘어감
            @Override
            public boolean onQueryTextSubmit(String query) {
                // 검색 버튼이 눌러졌을 때 이벤트 처리
                search_data = query;
                Toast.makeText(getApplicationContext(), search_data + "검색 중", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(getApplicationContext(), Search_Result.class);
                intent.putExtra("name",search_data);//다른 클래스로 데이터를 넘김
                startActivity(intent);

                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                // 검색어가 변경되었을 때 이벤트 처리
                return false;
            }

        });//------------------------------------------------------------------------------------------------------------------------------------------12.03 }

        MapButton.setOnClickListener(new View.OnClickListener(){
            @Override

            public void onClick(View view) {//지도페이지로 넘어감
                Intent intent=new Intent(getApplicationContext(),MapsActivity.class);
                startActivity(intent);
            }
        });

        MyButton.setOnClickListener(new View.OnClickListener(){
            @Override

            public void onClick(View view) {//내 정보 페이지로 넘어감
                Intent intent=new Intent(getApplicationContext(),MyInfo.class);
                startActivity(intent);
            }
        });

        MovieButton.setOnClickListener(new View.OnClickListener(){
            @Override

            public void onClick(View view) {//내 정보 페이지로 넘어감
                count++;
                if(count==10) {
                    Toast.makeText(getApplicationContext(),"Choihub에 진입합니다..",Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(getApplicationContext(),PM_movie.class);
                    intent.putExtra("name","Choihub");
                    intent.putExtra("Link","https://movie.naver.com/movie/sdb/browsing/bmovie.nhn?genre=5&open=2019");
                    startActivity(intent);
                }
            }
        });

        // 인기 영화 더보기버튼 클릭
        PlusButton1.setOnClickListener(new View.OnClickListener(){
            @Override

            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),PM_movie.class);
                intent.putExtra("name","인기영화");
                intent.putExtra("Link","https://movie.naver.com/movie/running/current.nhn?view=list&tab=normal&order=likeCount");
                startActivity(intent);
            }
        });
        // 유저 추천 영화 더보기버튼 클릭
        PlusButton2.setOnClickListener (new View.OnClickListener(){
            @Override

            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),Recom_movie.class);
                intent.putExtra("name","유저 추천 영화");
                intent.putExtra("list", usergenre);
                startActivity(intent);
            }
        });
        //이런 영화도 있어요 더보기 버튼 클릭
        PlusButton3.setOnClickListener(new View.OnClickListener(){
            @Override

            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),PM_movie.class);
                intent.putExtra("name","이런 영화도 있어요!");
                intent.putExtra("Link","https://movie.naver.com/movie/running/current.nhn?order=point");
                startActivity(intent);
            }
        });
        // 최신 영화 더보기버튼 클릭
        PlusButton4.setOnClickListener(new View.OnClickListener(){
            @Override

            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),PM_movie.class);
                intent.putExtra("name","최신영화");
                intent.putExtra("Link","https://movie.naver.com/movie/running/current.nhn?view=list&tab=normal&order=open");
                startActivity(intent);
            }
        });
        // 개봉 예정 영화 더보기버튼 클릭
        PlusButton5.setOnClickListener(new View.OnClickListener(){
            @Override

            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),PM_movie.class);
                intent.putExtra("name","개봉 예정 영화");
                intent.putExtra("Link","https://movie.naver.com/movie/running/premovie.nhn");
                startActivity(intent);
            }
        });

        // 인기 영화 리사이클러 뷰 클릭
        PopMovieRV.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), PopMovieRV, new ClickListener() {
            @Override
            public void onClick(View view, int position) {
                Intent intent = new Intent(getApplicationContext(), DetailView.class);
                intent.putExtra("Link", popmovie.get(position).getM_Detail_link());

                startActivity(intent);
            }

            @Override
            public void onLongClick(View view, int position) {
            }
        }));

        // 이런 영화 리사이클러 뷰 클릭
        AnotherRV.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), AnotherRV, new ClickListener() {
            @Override
            public void onClick(View view, int position) {
                Intent intent = new Intent(getApplicationContext(), DetailView.class);
                intent.putExtra("Link", anothermovie.get(position).getM_Detail_link());

                startActivity(intent);
            }

            @Override
            public void onLongClick(View view, int position) {
            }
        }));

        // 최신 영화 리사이클러 뷰 클릭
        NewRV.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), NewRV, new ClickListener() {
            @Override
            public void onClick(View view, int position) {
                Intent intent = new Intent(getApplicationContext(), DetailView.class);
                intent.putExtra("Link", newmovie.get(position).getM_Detail_link());

                startActivity(intent);
            }

            @Override
            public void onLongClick(View view, int position) {
            }
        }));

        // 개봉 예정 영화 리사이클러 뷰 클릭
        ReleaseRV.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), ReleaseRV, new ClickListener() {
            @Override
            public void onClick(View view, int position) {
                Intent intent = new Intent(getApplicationContext(), DetailView.class);
                intent.putExtra("Link", releasemovie.get(position).getM_Detail_link());

                startActivity(intent);
            }

            @Override
            public void onLongClick(View view, int position) {
            }
        }));

        // 유저 추천 영화 리사이클러 뷰 클릭
        UserGenreRV.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), UserGenreRV, new ClickListener() {
            @Override
            public void onClick(View view, int position) {
                Intent intent = new Intent(getApplicationContext(), DetailView.class);
                intent.putExtra("Link", usergenre.get(position).getlink());

                startActivity(intent);
            }

            @Override
            public void onLongClick(View view, int position) {
            }
        }));


//----------------------------------------------------------------------------------------------------------------------------------------------------
        DatabaseReference SF = fdatabase.getReference("UserData/" + usruid + "/Genre/SF");
        DatabaseReference action = fdatabase.getReference("UserData/" + usruid + "/Genre/action");
        DatabaseReference adventure = fdatabase.getReference("UserData/" + usruid + "/Genre/adventure");
        DatabaseReference animation = fdatabase.getReference("UserData/" + usruid + "/Genre/animation");
        DatabaseReference comedy = fdatabase.getReference("UserData/" + usruid + "/Genre/comedy");
        DatabaseReference crime = fdatabase.getReference("UserData/" + usruid + "/Genre/crime");
        DatabaseReference documentary = fdatabase.getReference("UserData/" + usruid + "/Genre/documentary");
        DatabaseReference drama = fdatabase.getReference("UserData/" + usruid + "/Genre/drama");
        DatabaseReference family = fdatabase.getReference("UserData/" + usruid + "/Genre/family");
        DatabaseReference fantasy = fdatabase.getReference("UserData/" + usruid + "/Genre/fantasy");
        DatabaseReference history = fdatabase.getReference("UserData/" + usruid + "/Genre/history");
        DatabaseReference horror = fdatabase.getReference("UserData/" + usruid + "/Genre/horror");
        DatabaseReference musical = fdatabase.getReference("UserData/" + usruid + "/Genre/musical");
        DatabaseReference mystery = fdatabase.getReference("UserData/" + usruid + "/Genre/mystery");
        DatabaseReference romance = fdatabase.getReference("UserData/" + usruid + "/Genre/romance");
        DatabaseReference thrilier = fdatabase.getReference("UserData/" + usruid + "/Genre/thrilier");
        DatabaseReference war = fdatabase.getReference("UserData/" + usruid + "/Genre/war");
        DatabaseReference western = fdatabase.getReference("UserData/" + usruid + "/Genre/western");

        moviearray = new int[19];

        drama.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot1) {
                boolean value = dataSnapshot1.getValue(boolean.class);
                if(value==true) moviearray[0]=1;
                else moviearray[0]=0; }
            @Override
            public void onCancelled(DatabaseError error) { }});

        fantasy.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot1) {
                boolean value = dataSnapshot1.getValue(boolean.class);
                if(value==true) moviearray[1]=2;
                else moviearray[1]=0; }
            @Override
            public void onCancelled(DatabaseError error) { }});

        western.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot1) {
                boolean value = dataSnapshot1.getValue(boolean.class);
                if(value==true) moviearray[2]=3;
                else moviearray[2]=0; }
            @Override
            public void onCancelled(DatabaseError error) { }});

        horror.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot1) {
                boolean value = dataSnapshot1.getValue(boolean.class);
                if(value==true) moviearray[3]=4;
                else moviearray[3]=0; }
            @Override
            public void onCancelled(DatabaseError error) { }});

        romance.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot1) {
                boolean value = dataSnapshot1.getValue(boolean.class);
                if(value==true) moviearray[4]=5;
                else moviearray[4]=0; }
            @Override
            public void onCancelled(DatabaseError error) { }});

        adventure.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot1) {
                boolean value = dataSnapshot1.getValue(boolean.class);
                if(value==true) moviearray[5]=6;
                else moviearray[5]=0; }
            @Override
            public void onCancelled(DatabaseError error) { }});

        thrilier.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot1) {
                boolean value = dataSnapshot1.getValue(boolean.class);
                if(value==true) moviearray[6]=7;
                else moviearray[6]=0; }
            @Override
            public void onCancelled(DatabaseError error) { }});

        documentary.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot1) {
                boolean value = dataSnapshot1.getValue(boolean.class);
                if(value==true) moviearray[9]=10;
                else moviearray[9]=0; }
            @Override
            public void onCancelled(DatabaseError error) { }});

        comedy.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot1) {
                boolean value = dataSnapshot1.getValue(boolean.class);
                if(value==true) moviearray[10]=11;
                else moviearray[10]=0; }
            @Override
            public void onCancelled(DatabaseError error) { }});

        family.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot1) {
                boolean value = dataSnapshot1.getValue(boolean.class);
                if(value==true) moviearray[11]=12;
                else moviearray[11]=0; }
            @Override
            public void onCancelled(DatabaseError error) { }});

        mystery.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot1) {
                boolean value = dataSnapshot1.getValue(boolean.class);
                if(value==true) moviearray[12]=13;
                else moviearray[12]=0; }
            @Override
            public void onCancelled(DatabaseError error) { }});

        war.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot1) {
                boolean value = dataSnapshot1.getValue(boolean.class);
                if(value==true) moviearray[13]=14;
                else moviearray[13]=0; }
            @Override
            public void onCancelled(DatabaseError error) { }});

        animation.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot1) {
                boolean value = dataSnapshot1.getValue(boolean.class);
                if(value==true) moviearray[14]=15;
                else moviearray[14]=0; }
            @Override
            public void onCancelled(DatabaseError error) { }});

        crime.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot1) {
                boolean value = dataSnapshot1.getValue(boolean.class);
                if(value==true) moviearray[15]=16;
                else moviearray[15]=0; }
            @Override
            public void onCancelled(DatabaseError error) { }});

        musical.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot1) {
                boolean value = dataSnapshot1.getValue(boolean.class);
                if(value==true) moviearray[16]=17;
                else moviearray[16]=0; }
            @Override
            public void onCancelled(DatabaseError error) { }});

        SF.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot1) {
                boolean value = dataSnapshot1.getValue(boolean.class);
                if(value==true) moviearray[17]=18;
                else moviearray[17]=0; }
            @Override
            public void onCancelled(DatabaseError error) { }});

        action.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot1) {
                boolean value = dataSnapshot1.getValue(boolean.class);
                if(value==true) moviearray[18]=19;
                else moviearray[18]=0;

                int count=0;
                for(int i=0;i<19;i++) {
                    count = count+moviearray[i];

                }
                Log.d(TAG, "Value is: " + count);



            }
            @Override
            public void onCancelled(DatabaseError error) { }});

    }

    private class Description extends AsyncTask<Void, Void, Void> {

        private ProgressDialog progressDialog;

        @Override

        protected void onPreExecute() {
            super.onPreExecute();

            progressDialog = new ProgressDialog(MyHome.this);
            progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            progressDialog.setMessage("잠시 기다려 주세요.");
            progressDialog.show();

        }

        protected Void doInBackground(Void... params) {
            try {
                Document doc = Jsoup.connect("https://movie.naver.com/movie/running/current.nhn?view=list&tab=normal&order=likeCount").get();
                Elements mElementDataSize  = doc.select("ul.lst_detail_t1").select("li");
                int mElementSize = mElementDataSize.size();

                for (Element elem : mElementDataSize) {
                    System.out.println("mElementSize = " + mElementSize);
                    String m_link = elem.select("a").attr("href");
                    String m_imgUrl = elem.select("a img").attr("src");
                    popmovie.add(new MH_Object(m_imgUrl, m_link));
                    if(popmovie.size() == 5)
                        break;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        protected void onPostExecute(Void result) {
            MH_Adapter PopMovieAdapter=new MH_Adapter(popmovie);
            PopMovieRV.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false));
            PopMovieRV.setAdapter(PopMovieAdapter);

            progressDialog.dismiss();
        }

    }
    private class Description2 extends AsyncTask<Void, Void, Void> {

        private ProgressDialog progressDialog;

        @Override

        protected void onPreExecute() {
            super.onPreExecute();

            progressDialog = new ProgressDialog(MyHome.this);
            progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            progressDialog.setMessage("잠시 기다려 주세요.");
            progressDialog.show();

        }

        protected Void doInBackground(Void... params) {
            try {
                Document doc = Jsoup.connect("https://movie.naver.com/movie/running/current.nhn?view=list&tab=normal&order=open").get();
                Elements mElementDataSize  = doc.select("ul.lst_detail_t1").select("li");
                int mElementSize = mElementDataSize.size();

                for (Element elem : mElementDataSize) {
                    String m_link = elem.select("a").attr("href");
                    String m_imgUrl = elem.select("a img").attr("src");
                    newmovie.add(new MH_Object(m_imgUrl, m_link));
                    if(newmovie.size() == 5)
                        break;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }



        protected void onPostExecute(Void result) {
            MH_Adapter NewMovieAdapter=new MH_Adapter(newmovie);
            NewRV.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false));
            NewRV.setAdapter(NewMovieAdapter);

            progressDialog.dismiss();
        }
    }

    private class Description3 extends AsyncTask<Void, Void, Void> {

        private ProgressDialog progressDialog;

        @Override

        protected void onPreExecute() {
            super.onPreExecute();

            progressDialog = new ProgressDialog(MyHome.this);
            progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            progressDialog.setMessage("잠시 기다려 주세요.");
            progressDialog.show();

        }

        protected Void doInBackground(Void... params) {
            try {
                Document doc = Jsoup.connect("https://movie.naver.com/movie/running/current.nhn?order=point").get();
                Elements mElementDataSize  = doc.select("ul.lst_detail_t1").select("li");
                int mElementSize = mElementDataSize.size();

                for (Element elem : mElementDataSize) {
                    System.out.println("mElementSize = " + mElementSize);
                    String m_link = elem.select("a").attr("href");
                    String m_imgUrl = elem.select("a img").attr("src");
                    anothermovie.add(new MH_Object(m_imgUrl, m_link));
                    if(anothermovie.size() == 5)
                        break;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        protected void onPostExecute(Void result) {
            MH_Adapter AnotherMovieAdapter=new MH_Adapter(anothermovie);
            AnotherRV.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false));
            AnotherRV.setAdapter(AnotherMovieAdapter);

            progressDialog.dismiss();
        }

    }

    private class Description4 extends AsyncTask<Void, Void, Void> {

        private ProgressDialog progressDialog;

        @Override

        protected void onPreExecute() {
            super.onPreExecute();

            progressDialog = new ProgressDialog(MyHome.this);
            progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            progressDialog.setMessage("잠시 기다려 주세요.");
            progressDialog.show();

        }

        protected Void doInBackground(Void... params) {
            try {
                Document doc = Jsoup.connect("https://movie.naver.com/movie/running/premovie.nhn").get();
                Elements mElementDataSize  = doc.select("ul.lst_detail_t1").select("li");
                int mElementSize = mElementDataSize.size();

                for (Element elem : mElementDataSize) {
                    System.out.println("mElementSize = " + mElementSize);
                    String m_link = elem.select("a").attr("href");
                    String m_imgUrl = elem.select("a img").attr("src");
                    releasemovie.add(new MH_Object(m_imgUrl, m_link));
                    if(releasemovie.size() == 5)
                        break;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        protected void onPostExecute(Void result) {
            MH_Adapter ReleaseMovieAdapter=new MH_Adapter(releasemovie);
            ReleaseRV.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false));
            ReleaseRV.setAdapter(ReleaseMovieAdapter);

            progressDialog.dismiss();
        }

    }

    /*
    액션 genre=19
    애니메이션 genre=15
    어드벤처 genre=6
    범죄 genre=16
    뮤지컬 genre=17
    사극
    드라마 genre=1
    가족 genre=12
    판타지 genre=2
    코미디 genre=11
    다큐멘터리 genre=10
    공포 genre=4
    미스테리 genre=13
    로맨스 genre=5
    SF genre=18
    스릴러 genre=7
    전쟁 genre=14
    서부 genre=3
     */

    private class Description5 extends AsyncTask<Void, Void, Void> {

        private ProgressDialog progressDialog;

        @Override

        protected void onPreExecute() {
            super.onPreExecute();

            progressDialog = new ProgressDialog(MyHome.this);
            progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            progressDialog.setMessage("잠시 기다려 주세요.");
            progressDialog.show();

        }

        protected Void doInBackground(Void... params) {
            for (int i = 0; i < moviearray.length; i++) {
                if (moviearray[i] != 0) {
                    try {
                        Document doc = Jsoup.connect("https://movie.naver.com/movie/sdb/browsing/bmovie.nhn?genre="+moviearray[i]+"&open=2019").get();
                        Elements mElementDataSize = doc.select("ul.directory_list").select("li");
                        int mElementSize = mElementDataSize.size();

                        for (Element elem : mElementDataSize) {
                            System.out.println("mElementSize = " + mElementSize);
                            String my_link = elem.select("a").attr("href");
                            if(!my_link.contains("genre"))
                                genrelink.add(new GenreObject(my_link));
                        }
                        for(int j=0; j<genrelink.size(); j++){
                            System.out.println("장르 링크 : " + genrelink.get(j).get_url());
                        }

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            for(int i=0; i<genrelink.size(); i++) {
                try {
                    Document doc = Jsoup.connect("https://movie.naver.com/movie/bi/mi/photoViewPopup.nhn?movieCode" + genrelink.get(i).get_url().substring(genrelink.get(i).get_url().lastIndexOf("="))).get();
                    Elements mElementDataSize = doc.select("body");
                    int mElementSize = mElementDataSize.size();

                    for (Element elem : mElementDataSize) {
                        System.out.println("유저추천 mElementSize = " + mElementSize);
                        String my_poster = elem.select("a img").attr("src");
                        POSTER = my_poster;
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }


                try {
                    Document doc = Jsoup.connect("https://movie.naver.com" + genrelink.get(i).get_url()).get();
                    Elements mElementDataSize = doc.select("div.article");
                    int mElementSize = mElementDataSize.size();

                    for (Element elem : mElementDataSize) {
                        if (elem.select("div.poster a img").attr("alt") != null) {
                            String my_title = elem.select("div.poster a img").attr("alt");
                            TITLE = my_title;
                        }

                        Element iElem = elem.select("dt.step1").next().first();
                        if(iElem!=null) {
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

                        usergenre.add(new ItemObject(TITLE, POSTER, genrelink.get(i).get_url(),RELEASE,"감독 : " + DIRECTOR));
                    }
                    Collections.shuffle(usergenre);

                }
                catch (IOException e) {
                    e.printStackTrace();
                }

            }
            return null;
        }


        protected void onPostExecute(Void result) {
            while(usergenre2.size()<5)
                usergenre2.add(new MH_Object(usergenre.get(usergenre2.size()).getImg_url(), usergenre.get(usergenre2.size()).getlink()));
            MH_Adapter UserMovieAdapter=new MH_Adapter(usergenre2);
            UserGenreRV.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false));
            UserGenreRV.setAdapter(UserMovieAdapter);

            progressDialog.dismiss();
        }

    }


    public interface ClickListener {
        void onClick(View view, int position);

        void onLongClick(View view, int position);
    }

    @Override
    public void onBackPressed(){
        if(System.currentTimeMillis()-time>=2000){
            time=System.currentTimeMillis();
            Toast.makeText(getApplicationContext(),"뒤로 버튼을 한번 더 누르면 종료합니다.",Toast.LENGTH_SHORT).show();
        }else if(System.currentTimeMillis()-time<2000){
            finish();
        }
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
