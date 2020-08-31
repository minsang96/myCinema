package com.example.swproject;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MyInfo extends AppCompatActivity {
    private FirebaseAuth mAuth;
    DatabaseReference database = FirebaseDatabase.getInstance().getReference();
    private Button modify_btn;
    private TextView infoTitle;
    private ImageView CircularImageView;
    private TextView Displayname;
    private TextView Email;
    private TextView tip;
    private TextView genre_list;
    MyHome myHome = (MyHome)MyHome.MHactivity;



    //cj
    private Button btn_logout;
    private void signOut() {
        FirebaseAuth.getInstance().signOut();
    }
    private static final String TAG = "MyInfo";
    private DatabaseReference mDatabase;
    List<String> mList = new ArrayList<String>();


    //cj

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.myinfo);

        infoTitle = (TextView) findViewById(R.id.InfoTitle); // 화면 상단 "내정보" 출력
        CircularImageView = (ImageView) findViewById(R.id.CircularImageView); // 프로필 이미지
        Displayname = (TextView) findViewById(R.id.Displayname); // 구글 닉네임
        Email = (TextView) findViewById(R.id.Email); // 구글 이메일
        //textView8 = (TextView) findViewById(R.id.textView8); // "좋아하는 장르" 텍스트 출력
        //genre_list = (TextView) findViewById(R.id.genre_list); // 파이어베이스에 등록된 좋아하는 장르 데이터들 출력하는거. 근데 텍스트뷰보단 리스트뷰가 좋을거 같음
        btn_logout = (Button) findViewById(R.id.btn_logout);  //로그아웃 버튼
        modify_btn = (Button) findViewById(R.id.modify_btn);
        tip = (TextView) findViewById(R.id.textView2);
        mAuth = FirebaseAuth.getInstance();

        Displayname.setText(mAuth.getCurrentUser().getDisplayName());
        Email.setText(mAuth.getCurrentUser().getEmail());
        GlideApp.with(CircularImageView).load(mAuth.getCurrentUser().getPhotoUrl())
                .override(300, 400)
                .into(CircularImageView);

        //cj
        btn_logout.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                myHome.finish();
                signOut();
                finish();
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);


                //overridePendingTransition(R .anim.sliding_down, R.anim.stay);
            }
        });

        modify_btn.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {

                database.child("UserData").child((mAuth.getCurrentUser().getUid())).child("Genre").child("SF").setValue(false);
                database.child("UserData").child((mAuth.getCurrentUser().getUid())).child("Genre").child("action").setValue(false);
                database.child("UserData").child((mAuth.getCurrentUser().getUid())).child("Genre").child("adventure").setValue(false);
                database.child("UserData").child((mAuth.getCurrentUser().getUid())).child("Genre").child("animation"). setValue(false);
                database.child("UserData").child((mAuth.getCurrentUser().getUid())).child("Genre").child("comedy").setValue(false);
                database.child("UserData").child((mAuth.getCurrentUser().getUid())).child("Genre").child("crime").setValue(false);
                database.child("UserData").child((mAuth.getCurrentUser().getUid())).child("Genre").child("documentary").setValue(false);
                database.child("UserData").child((mAuth.getCurrentUser().getUid())).child("Genre").child("drama").setValue(false);
                database.child("UserData").child((mAuth.getCurrentUser().getUid())).child("Genre").child("family").setValue(false);
                database.child("UserData").child((mAuth.getCurrentUser().getUid())).child("Genre").child("fantasy").setValue(false);
                database.child("UserData").child((mAuth.getCurrentUser().getUid())).child("Genre").child("horror").setValue(false);
                database.child("UserData").child((mAuth.getCurrentUser().getUid())).child("Genre").child("musical").setValue(false);
                database.child("UserData").child((mAuth.getCurrentUser().getUid())).child("Genre").child("mystery").setValue(false);
                database.child("UserData").child((mAuth.getCurrentUser().getUid())).child("Genre").child("romance").setValue(false);
                database.child("UserData").child((mAuth.getCurrentUser().getUid())).child("Genre").child("thrilier").setValue(false);
                database.child("UserData").child((mAuth.getCurrentUser().getUid())).child("Genre").child("war").setValue(false);
                database.child("UserData").child((mAuth.getCurrentUser().getUid())).child("Genre").child("western").setValue(false);
                finish();
                Intent intent = new Intent(getApplicationContext(), SelectGenre.class);
                startActivity(intent);


                //overridePendingTransition(R.anim.sliding_down, R.anim.stay);
            }
        });
        //cj

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        String usruid = mAuth.getCurrentUser().getUid();

        //cj
        DatabaseReference SF = database.getReference("UserData/" + usruid + "/Genre/SF");
        DatabaseReference action = database.getReference("UserData/" + usruid + "/Genre/action");
        DatabaseReference adventure = database.getReference("UserData/" + usruid + "/Genre/adventure");
        DatabaseReference animation = database.getReference("UserData/" + usruid + "/Genre/animation");
        DatabaseReference comedy = database.getReference("UserData/" + usruid + "/Genre/comedy");
        DatabaseReference crime = database.getReference("UserData/" + usruid + "/Genre/crime");
        DatabaseReference documentary = database.getReference("UserData/" + usruid + "/Genre/documentary");
        DatabaseReference drama = database.getReference("UserData/" + usruid + "/Genre/drama");
        DatabaseReference family = database.getReference("UserData/" + usruid + "/Genre/family");
        DatabaseReference fantasy = database.getReference("UserData/" + usruid + "/Genre/fantasy");
        DatabaseReference horror = database.getReference("UserData/" + usruid + "/Genre/horror");
        DatabaseReference musical = database.getReference("UserData/" + usruid + "/Genre/musical");
        DatabaseReference mystery = database.getReference("UserData/" + usruid + "/Genre/mystery");
        DatabaseReference romance = database.getReference("UserData/" + usruid + "/Genre/romance");
        DatabaseReference thrilier = database.getReference("UserData/" + usruid + "/Genre/thrilier");
        DatabaseReference war = database.getReference("UserData/" + usruid + "/Genre/war");
        DatabaseReference western = database.getReference("UserData/" + usruid + "/Genre/western");

        SF.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot1) {
                boolean value1 = dataSnapshot1.getValue(boolean.class);
                String qwer = String.valueOf(value1);
                if (qwer.equals("true"))
                    mList.add("· SF\n");
            }
            @Override
            public void onCancelled(DatabaseError error) {
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });
        action.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot1) {
                boolean value1 = dataSnapshot1.getValue(boolean.class);
                String qwer = String.valueOf(value1);
                if (qwer.equals("true"))
                    mList.add("· 액션\n");

            }
            @Override
            public void onCancelled(DatabaseError error) {
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });
        adventure.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot1) {
                boolean value1 = dataSnapshot1.getValue(boolean.class);
                String qwer = String.valueOf(value1);
                if (qwer.equals("true"))
                    mList.add("· 어드벤쳐\n");
            }
            @Override
            public void onCancelled(DatabaseError error) {
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });
        animation.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot1) {
                boolean value1 = dataSnapshot1.getValue(boolean.class);
                String qwer = String.valueOf(value1);
                if (qwer.equals("true"))
                    mList.add("· 애니메이션\n");
            }
            @Override
            public void onCancelled(DatabaseError error) {
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });
        comedy.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot1) {
                boolean value1 = dataSnapshot1.getValue(boolean.class);
                String qwer = String.valueOf(value1);
                if (qwer.equals("true"))
                    mList.add("· 코미디\n");
            }
            @Override
            public void onCancelled(DatabaseError error) {
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });
        crime.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot1) {
                boolean value1 = dataSnapshot1.getValue(boolean.class);
                String qwer = String.valueOf(value1);
                if (qwer.equals("true"))
                    mList.add("· 범죄\n");
            }
            @Override
            public void onCancelled(DatabaseError error) {
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });
        documentary.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot1) {
                boolean value1 = dataSnapshot1.getValue(boolean.class);
                String qwer = String.valueOf(value1);
                if (qwer.equals("true"))
                    mList.add("· 다큐멘터리\n");
            }
            @Override
            public void onCancelled(DatabaseError error) {
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });
        drama.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot1) {
                boolean value1 = dataSnapshot1.getValue(boolean.class);
                String qwer = String.valueOf(value1);
                if (qwer.equals("true"))
                    mList.add("· 드라마\n");
            }
            @Override
            public void onCancelled(DatabaseError error) {
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });
        family.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot1) {
                boolean value1 = dataSnapshot1.getValue(boolean.class);
                String qwer = String.valueOf(value1);
                if (qwer.equals("true"))
                    mList.add("· 가족\n");
            }
            @Override
            public void onCancelled(DatabaseError error) {
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });
        fantasy.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot1) {
                boolean value1 = dataSnapshot1.getValue(boolean.class);
                String qwer = String.valueOf(value1);
                if (qwer.equals("true"))
                    mList.add("· 판타지\n");
            }
            @Override
            public void onCancelled(DatabaseError error) {
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });

        horror.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot1) {
                boolean value1 = dataSnapshot1.getValue(boolean.class);
                String qwer = String.valueOf(value1);
                if (qwer.equals("true"))
                    mList.add("· 공포\n");
            }
            @Override
            public void onCancelled(DatabaseError error) {
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });
        musical.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot1) {
                boolean value1 = dataSnapshot1.getValue(boolean.class);
                String qwer = String.valueOf(value1);
                if (qwer.equals("true"))
                    mList.add("· 뮤지컬\n");
            }
            @Override
            public void onCancelled(DatabaseError error) {
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });
        mystery.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot1) {
                boolean value1 = dataSnapshot1.getValue(boolean.class);
                String qwer = String.valueOf(value1);
                if (qwer.equals("true"))
                    mList.add("· 미스테리\n");
            }
            @Override
            public void onCancelled(DatabaseError error) {
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });
        romance.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot1) {
                boolean value1 = dataSnapshot1.getValue(boolean.class);
                String qwer = String.valueOf(value1);
                if (qwer.equals("true"))
                    mList.add("· 로맨스\n");
            }
            @Override
            public void onCancelled(DatabaseError error) {
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });
        thrilier.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot1) {
                boolean value1 = dataSnapshot1.getValue(boolean.class);
                String qwer = String.valueOf(value1);
                if (qwer.equals("true"))
                    mList.add("· 스릴러\n");
            }
            @Override
            public void onCancelled(DatabaseError error) {
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });
        war.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot1) {
                boolean value1 = dataSnapshot1.getValue(boolean.class);
                String qwer = String.valueOf(value1);
                if (qwer.equals("true"))
                    mList.add("· 전쟁\n");
            }
            @Override
            public void onCancelled(DatabaseError error) {
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });
        western.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot1) {
                boolean value1 = dataSnapshot1.getValue(boolean.class);
                String qwer = String.valueOf(value1);
                if (qwer.equals("true")) {
                    mList.add("· 서부\n");
                }
                genre_list = (TextView) findViewById(R.id.genre1);
                String text1 = mList.toString();
                String text2 = getString(R.string.genreprint);
                String text3 = text1.replace("[", "");
                text1 = text3.replace(",", "");
                text3 = text1.replace(" ", "");
                text1 = text3.replace("]", "");
                genre_list.setText(String.format(text2, text1));
            }
            @Override
            public void onCancelled(DatabaseError error) {
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });
        //cj

        double dValue = Math.random();
        int iValue = (int)(dValue * 10);

        if(iValue == 1)
            tip.setText("# TIP #\n좋아하는 장르선택은 메인화면의\n유저 추천 영화에 영향을 끼칩니다.");
        else if(iValue == 2)
            tip.setText("# TIP #\n수정하기 버튼을 누르면\n좋아하는 장르를 다시 설정할 수 있습니다.");
        else if(iValue == 3)
            tip.setText("# TIP #\n메인화면의 영화관 버튼은\n반경 10km내의 영화관을 찾아줍니다.");
        else if(iValue == 4)
            tip.setText("# TIP #\n현재 검색기능은 영화제목으로만\n검색이 가능합니다.");
        else if(iValue == 51)
            tip.setText("# TIP #\n이 어플은 NAVER 영화 웹 사이트의 정보를\n기반으로 제작되었습니다.");
        if(iValue == 6)
            tip.setText("# TIP #\n좋아하는 장르선택은 메인화면의\n유저 추천 영화에 영향을 끼칩니다.");
        else if(iValue == 7)
            tip.setText("# TIP #\n수정하기 버튼을 누르면\n좋아하는 장르를 다시 설정할 수 있습니다.");
        else if(iValue == 8)
            tip.setText("# TIP #\n메인화면의 영화관 버튼은\n반경 10km내의 영화관을 찾아줍니다.");
        else if(iValue == 9)
            tip.setText("# TIP #\n현재 검색기능은 영화제목으로만\n검색이 가능합니다.");
        else if(iValue == 0)
            tip.setText("# TIP #\n이 어플은 NAVER 영화 웹 사이트의 정보를\n기반으로 제작되었습니다.");



    }
}
