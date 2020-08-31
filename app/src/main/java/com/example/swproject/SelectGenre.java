package com.example.swproject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Toast;
import android.widget.ToggleButton;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SelectGenre extends AppCompatActivity{
    private Button  selectgenre_finish;
    private ToggleButton select_action; private ToggleButton select_adventure;
    private ToggleButton select_animation; private ToggleButton select_crime;
    private ToggleButton select_musical;
    private ToggleButton select_drama; private ToggleButton select_family;
    private ToggleButton select_fantasy; private ToggleButton select_comedy;
    private ToggleButton select_documentary; private ToggleButton select_horror;
    private ToggleButton select_mystery; private ToggleButton select_romance;
    private ToggleButton select_sf; private ToggleButton select_thrilier;
    private ToggleButton select_war; private ToggleButton select_western;
    public static Activity SelectGenre;
    MainActivity Login = (MainActivity)MainActivity.Login;
    MyHome myHome = (MyHome)MyHome.MHactivity;
    int count=0;
    private static final String TAG = "SelectGenre";



    private FirebaseAuth auth;
    private DatabaseReference mPostReference;
    DatabaseReference database = FirebaseDatabase.getInstance().getReference();
    FirebaseDatabase fdatabase = FirebaseDatabase.getInstance();

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.select_genre);
        selectgenre_finish = (Button)findViewById(R.id.selectgenre_finish);
        select_action = (ToggleButton) findViewById(R.id.select_action);
        select_adventure = (ToggleButton) findViewById(R.id.select_adventure);
        select_animation = (ToggleButton) findViewById(R.id.select_animation);
        select_crime = (ToggleButton) findViewById(R.id.select_crime);
        select_musical = (ToggleButton) findViewById(R.id.select_musical);
        select_drama = (ToggleButton) findViewById(R.id.select_drama);
        select_family = (ToggleButton) findViewById(R.id.select_family);
        select_fantasy = (ToggleButton) findViewById(R.id.select_fantasy);
        select_comedy = (ToggleButton) findViewById(R.id.select_comedy);
        select_documentary = (ToggleButton) findViewById(R.id.select_documentary);
        select_horror = (ToggleButton) findViewById(R.id.select_horror);
        select_mystery = (ToggleButton) findViewById(R.id.select_mystery);
        select_romance = (ToggleButton) findViewById(R.id.select_romance);
        select_sf = (ToggleButton) findViewById(R.id.select_sf);
        select_thrilier = (ToggleButton) findViewById(R.id.select_thrilier);
        select_war = (ToggleButton) findViewById(R.id.select_war);
        select_western = (ToggleButton) findViewById(R.id.select_western);
        auth = FirebaseAuth.getInstance();
        SelectGenre = SelectGenre.this;
        String usr_uid = auth.getCurrentUser().getUid();

        DatabaseReference SF = fdatabase.getReference("UserData/" + usr_uid + "/Genre/SF");
        DatabaseReference action = fdatabase.getReference("UserData/" + usr_uid + "/Genre/action");
        DatabaseReference adventure = fdatabase.getReference("UserData/" + usr_uid + "/Genre/adventure");
        DatabaseReference animation = fdatabase.getReference("UserData/" + usr_uid + "/Genre/animation");
        DatabaseReference comedy = fdatabase.getReference("UserData/" + usr_uid + "/Genre/comedy");
        DatabaseReference crime = fdatabase.getReference("UserData/" + usr_uid + "/Genre/crime");
        DatabaseReference documentary = fdatabase.getReference("UserData/" + usr_uid + "/Genre/documentary");
        DatabaseReference drama = fdatabase.getReference("UserData/" + usr_uid + "/Genre/drama");
        DatabaseReference family = fdatabase.getReference("UserData/" + usr_uid + "/Genre/family");
        DatabaseReference fantasy = fdatabase.getReference("UserData/" + usr_uid + "/Genre/fantasy");
        DatabaseReference horror = fdatabase.getReference("UserData/" + usr_uid + "/Genre/horror");
        DatabaseReference musical = fdatabase.getReference("UserData/" + usr_uid + "/Genre/musical");
        DatabaseReference mystery = fdatabase.getReference("UserData/" + usr_uid + "/Genre/mystery");
        DatabaseReference romance = fdatabase.getReference("UserData/" + usr_uid + "/Genre/romance");
        DatabaseReference thrilier = fdatabase.getReference("UserData/" + usr_uid + "/Genre/thrilier");
        DatabaseReference war = fdatabase.getReference("UserData/" + usr_uid + "/Genre/war");
        DatabaseReference western = fdatabase.getReference("UserData/" + usr_uid + "/Genre/western");

        SF.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot1) {
                boolean asdf = dataSnapshot1.exists();
                if(asdf==false)
                    database.child("UserData").child((auth.getCurrentUser().getUid())).child("Genre").child("SF").setValue(false);
            }
            @Override
            public void onCancelled(DatabaseError error) {
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });
        action.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot1) {
                boolean asdf = dataSnapshot1.exists();
                if(asdf==false)
                    database.child("UserData").child((auth.getCurrentUser().getUid())).child("Genre").child("action").setValue(false);
            }
            @Override
            public void onCancelled(DatabaseError error) {
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });
        adventure.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot1) {
                boolean asdf = dataSnapshot1.exists();
                if(asdf==false)
                    database.child("UserData").child((auth.getCurrentUser().getUid())).child("Genre").child("adventure").setValue(false);
            }
            @Override
            public void onCancelled(DatabaseError error) {
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });
        animation.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot1) {
                boolean asdf = dataSnapshot1.exists();
                if(asdf==false)
                    database.child("UserData").child((auth.getCurrentUser().getUid())).child("Genre").child("animation").setValue(false);
            }
            @Override
            public void onCancelled(DatabaseError error) {
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });
        comedy.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot1) {
                boolean asdf = dataSnapshot1.exists();
                if(asdf==false)
                    database.child("UserData").child((auth.getCurrentUser().getUid())).child("Genre").child("comedy").setValue(false);
            }
            @Override
            public void onCancelled(DatabaseError error) {
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });
        crime.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot1) {
                boolean asdf = dataSnapshot1.exists();
                if(asdf==false)
                    database.child("UserData").child((auth.getCurrentUser().getUid())).child("Genre").child("crime").setValue(false);
            }
            @Override
            public void onCancelled(DatabaseError error) {
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });
        documentary.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot1) {
                boolean asdf = dataSnapshot1.exists();
                if(asdf==false)
                    database.child("UserData").child((auth.getCurrentUser().getUid())).child("Genre").child("documentary").setValue(false);
            }
            @Override
            public void onCancelled(DatabaseError error) {
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });
        drama.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot1) {
                boolean asdf = dataSnapshot1.exists();
                if(asdf==false)
                    database.child("UserData").child((auth.getCurrentUser().getUid())).child("Genre").child("drama").setValue(false);
            }
            @Override
            public void onCancelled(DatabaseError error) {
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });
        family.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot1) {
                boolean asdf = dataSnapshot1.exists();
                if(asdf==false)
                    database.child("UserData").child((auth.getCurrentUser().getUid())).child("Genre").child("family").setValue(false);
            }
            @Override
            public void onCancelled(DatabaseError error) {
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });
        fantasy.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot1) {
                boolean asdf = dataSnapshot1.exists();
                if(asdf==false)
                    database.child("UserData").child((auth.getCurrentUser().getUid())).child("Genre").child("fantasy").setValue(false);
            }
            @Override
            public void onCancelled(DatabaseError error) {
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });

        horror.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot1) {
                boolean asdf = dataSnapshot1.exists();
                if(asdf==false)
                    database.child("UserData").child((auth.getCurrentUser().getUid())).child("Genre").child("horror").setValue(false);
            }
            @Override
            public void onCancelled(DatabaseError error) {
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });
        musical.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot1) {
                boolean asdf = dataSnapshot1.exists();
                if(asdf==false)
                    database.child("UserData").child((auth.getCurrentUser().getUid())).child("Genre").child("musical").setValue(false);
            }
            @Override
            public void onCancelled(DatabaseError error) {
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });
        mystery.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot1) {
                boolean asdf = dataSnapshot1.exists();
                if(asdf==false)
                    database.child("UserData").child((auth.getCurrentUser().getUid())).child("Genre").child("mystery").setValue(false);
            }
            @Override
            public void onCancelled(DatabaseError error) {
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });
        romance.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot1) {
                boolean asdf = dataSnapshot1.exists();
                if(asdf==false)
                    database.child("UserData").child((auth.getCurrentUser().getUid())).child("Genre").child("romance").setValue(false);
            }
            @Override
            public void onCancelled(DatabaseError error) {
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });
        thrilier.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot1) {
                boolean asdf = dataSnapshot1.exists();
                if(asdf==false)
                    database.child("UserData").child((auth.getCurrentUser().getUid())).child("Genre").child("thrilier").setValue(false);
            }
            @Override
            public void onCancelled(DatabaseError error) {
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });
        war.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot1) {
                boolean asdf = dataSnapshot1.exists();
                if(asdf==false)
                    database.child("UserData").child((auth.getCurrentUser().getUid())).child("Genre").child("war").setValue(false);
            }
            @Override
            public void onCancelled(DatabaseError error) {
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });
        western.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot1) {
                boolean asdf = dataSnapshot1.exists();
                if(asdf==false)
                    database.child("UserData").child((auth.getCurrentUser().getUid())).child("Genre").child("western").setValue(false);
            }
            @Override
            public void onCancelled(DatabaseError error) {
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });




        selectgenre_finish.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                if(count==0)
                {
                    Toast.makeText(getApplicationContext(),"장르를 적어도 1개는 선택해주세요",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    count=0;
                    Login.finish();
                    Intent intent = new Intent(getApplicationContext(), MyHome.class);
                    startActivity(intent);
                    Log.d(TAG, "Value is: " + count);
                    overridePendingTransition(R.anim.sliding_up, R.anim.stay);
                    finish();
                }
            }
        });


        select_action.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked == true){
                    select_action.setBackgroundDrawable(getResources().getDrawable(R.drawable.poster_action_selected));
                    database.child("UserData").child((auth.getCurrentUser().getUid())).child("Genre").child("action").setValue(true);
                    count++;
                } else {
                    select_action.setBackgroundDrawable(getResources().getDrawable(R.drawable.poster_action));
                    database.child("UserData").child((auth.getCurrentUser().getUid())).child("Genre").child("action").setValue(false);
                    count--;
                }

            }
        });

        select_animation.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked == true){
                    select_animation.setBackgroundDrawable(getResources().getDrawable(R.drawable.poster_animation_selected));
                    database.child("UserData").child((auth.getCurrentUser().getUid())).child("Genre").child("animation").setValue(true);
                    count++;
                } else {
                    select_animation.setBackgroundDrawable(getResources().getDrawable(R.drawable.poster_animation));
                    database.child("UserData").child((auth.getCurrentUser().getUid())).child("Genre").child("animation").setValue(false);
                    count--;
                }

            }
        });

        select_adventure.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked == true){
                    select_adventure.setBackgroundDrawable(getResources().getDrawable(R.drawable.poster_adventure_selected));
                    database.child("UserData").child((auth.getCurrentUser().getUid())).child("Genre").child("adventure").setValue(true);
                    count++;
                } else {
                    select_adventure.setBackgroundDrawable(getResources().getDrawable(R.drawable.poster_adventure));
                    database.child("UserData").child((auth.getCurrentUser().getUid())).child("Genre").child("adventure").setValue(false);
                    count--;
                }

            }
        });

        select_crime.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked == true){
                    select_crime.setBackgroundDrawable(getResources().getDrawable(R.drawable.poster_crime_selected));
                    database.child("UserData").child((auth.getCurrentUser().getUid())).child("Genre").child("crime").setValue(true);
                    count++;
                } else {
                    select_crime.setBackgroundDrawable(getResources().getDrawable(R.drawable.poster_crime));
                    database.child("UserData").child((auth.getCurrentUser().getUid())).child("Genre").child("crime").setValue(false);
                    count--;
                }

            }
        });

        select_musical.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked == true){
                    select_musical.setBackgroundDrawable(getResources().getDrawable(R.drawable.poster_musical_selected));
                    database.child("UserData").child((auth.getCurrentUser().getUid())).child("Genre").child("musical").setValue(true);
                    count++;
                } else {
                    select_musical.setBackgroundDrawable(getResources().getDrawable(R.drawable.poster_musical));
                    database.child("UserData").child((auth.getCurrentUser().getUid())).child("Genre").child("musical").setValue(false);
                    count--;
                }

            }
        });

        select_drama.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked == true){
                    select_drama.setBackgroundDrawable(getResources().getDrawable(R.drawable.poster_drama_selected));
                    database.child("UserData").child((auth.getCurrentUser().getUid())).child("Genre").child("drama").setValue(true);
                    count++;
                } else {
                    select_drama.setBackgroundDrawable(getResources().getDrawable(R.drawable.poster_drama));
                    database.child("UserData").child((auth.getCurrentUser().getUid())).child("Genre").child("drama").setValue(false);
                    count--;
                }

            }
        });

        select_family.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked == true){
                    select_family.setBackgroundDrawable(getResources().getDrawable(R.drawable.poster_family_selected));
                    database.child("UserData").child((auth.getCurrentUser().getUid())).child("Genre").child("family").setValue(true);
                    count++;
                } else {
                    select_family.setBackgroundDrawable(getResources().getDrawable(R.drawable.poster_family));
                    database.child("UserData").child((auth.getCurrentUser().getUid())).child("Genre").child("family").setValue(false);
                    count--;
                }

            }
        });

        select_fantasy.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked == true){
                    select_fantasy.setBackgroundDrawable(getResources().getDrawable(R.drawable.poster_fantasy_selected));
                    database.child("UserData").child((auth.getCurrentUser().getUid())).child("Genre").child("fantasy").setValue(true);
                    count++;
                } else {
                    select_fantasy.setBackgroundDrawable(getResources().getDrawable(R.drawable.poster_fantasy));
                    database.child("UserData").child((auth.getCurrentUser().getUid())).child("Genre").child("fantasy").setValue(false);
                    count--;
                }

            }
        });

        select_comedy.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked == true){
                    select_comedy.setBackgroundDrawable(getResources().getDrawable(R.drawable.poster_comedy_selected));
                    database.child("UserData").child((auth.getCurrentUser().getUid())).child("Genre").child("comedy").setValue(true);
                    count++;
                } else {
                    select_comedy.setBackgroundDrawable(getResources().getDrawable(R.drawable.poster_comedy));
                    database.child("UserData").child((auth.getCurrentUser().getUid())).child("Genre").child("comedy").setValue(false);
                    count--;
                }

            }
        });

        select_documentary.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked == true){
                    select_documentary.setBackgroundDrawable(getResources().getDrawable(R.drawable.poster_documentary_selected));
                    database.child("UserData").child((auth.getCurrentUser().getUid())).child("Genre").child("documentary").setValue(true);
                    count++;
                } else {
                    select_documentary.setBackgroundDrawable(getResources().getDrawable(R.drawable.poster_documentary));
                    database.child("UserData").child((auth.getCurrentUser().getUid())).child("Genre").child("documentary").setValue(false);
                    count--;
                }

            }
        });

        select_horror.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked == true){
                    select_horror.setBackgroundDrawable(getResources().getDrawable(R.drawable.poster_horror_selected));
                    database.child("UserData").child((auth.getCurrentUser().getUid())).child("Genre").child("horror").setValue(true);
                    count++;
                } else {
                    select_horror.setBackgroundDrawable(getResources().getDrawable(R.drawable.poster_horror));
                    database.child("UserData").child((auth.getCurrentUser().getUid())).child("Genre").child("horror").setValue(false);
                    count--;
                }

            }
        });

        select_mystery.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked == true){
                    select_mystery.setBackgroundDrawable(getResources().getDrawable(R.drawable.poster_mystery_selected));
                    database.child("UserData").child((auth.getCurrentUser().getUid())).child("Genre").child("mystery").setValue(true);
                    count++;
                } else {
                    select_mystery.setBackgroundDrawable(getResources().getDrawable(R.drawable.poster_mystery));
                    database.child("UserData").child((auth.getCurrentUser().getUid())).child("Genre").child("mystery").setValue(false);
                    count--;
                }

            }
        });

        select_romance.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked == true){
                    select_romance.setBackgroundDrawable(getResources().getDrawable(R.drawable.poster_romance_selected));
                    database.child("UserData").child((auth.getCurrentUser().getUid())).child("Genre").child("romance").setValue(true);
                    count++;
                } else {
                    select_romance.setBackgroundDrawable(getResources().getDrawable(R.drawable.poster_romance));
                    database.child("UserData").child((auth.getCurrentUser().getUid())).child("Genre").child("romance").setValue(false);
                    count--;
                }

            }
        });

        select_sf.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked == true){
                    select_sf.setBackgroundDrawable(getResources().getDrawable(R.drawable.poster_sf_selected));
                    database.child("UserData").child((auth.getCurrentUser().getUid())).child("Genre").child("SF").setValue(true);
                    count++;
                } else {
                    select_sf.setBackgroundDrawable(getResources().getDrawable(R.drawable.poster_sf));
                    database.child("UserData").child((auth.getCurrentUser().getUid())).child("Genre").child("SF").setValue(false);
                    count--;
                }

            }
        });

        select_thrilier.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked == true){
                    select_thrilier.setBackgroundDrawable(getResources().getDrawable(R.drawable.poster_thrilier_selected));
                    database.child("UserData").child((auth.getCurrentUser().getUid())).child("Genre").child("thrilier").setValue(true);
                    count++;
                } else {
                    select_thrilier.setBackgroundDrawable(getResources().getDrawable(R.drawable.poster_thrilier));
                    database.child("UserData").child((auth.getCurrentUser().getUid())).child("Genre").child("thrilier").setValue(false);
                    count--;
                }

            }
        });

        select_war.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked == true){
                    select_war.setBackgroundDrawable(getResources().getDrawable(R.drawable.poster_war_selected));
                    database.child("UserData").child((auth.getCurrentUser().getUid())).child("Genre").child("war").setValue(true);
                    count++;
                } else {
                    select_war.setBackgroundDrawable(getResources().getDrawable(R.drawable.poster_war));
                    database.child("UserData").child((auth.getCurrentUser().getUid())).child("Genre").child("war").setValue(false);
                    count--;
                }

            }
        });

        select_western.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked == true){
                    select_western.setBackgroundDrawable(getResources().getDrawable(R.drawable.poster_western_selected));
                    database.child("UserData").child((auth.getCurrentUser().getUid())).child("Genre").child("western").setValue(true);
                    count++;
                } else {
                    select_western.setBackgroundDrawable(getResources().getDrawable(R.drawable.poster_western));
                    database.child("UserData").child((auth.getCurrentUser().getUid())).child("Genre").child("western").setValue(false);
                    count--;
                }

            }
        });

    } public void onBackPressed(){
        if(count==0)
        {
            Toast.makeText(getApplicationContext(),"장르를 적어도 1개는 선택해주세요",Toast.LENGTH_SHORT).show();
        }
        else
        {
            count=0;
            Login.finish();
            myHome.finish();
            Intent intent = new Intent(getApplicationContext(), MyHome.class);
            startActivity(intent);
            Log.d(TAG, "Value is: " + count);
            overridePendingTransition(R.anim.sliding_up, R.anim.stay);
            finish();
        }
    }
}



