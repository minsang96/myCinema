package com.example.swproject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;



public class MainActivity extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener{
    SignInButton Google_Login;
    private static final int RC_SIGN_IN = 100;
    private FirebaseAuth mAuth;
    private GoogleApiClient mGoogleApiClient;
    DatabaseReference database = FirebaseDatabase.getInstance().getReference();
    public static Activity Login;
    private static final String TAG = "MainActivity";
    private long time= 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Login = MainActivity.this;
        setContentView(R.layout.activity_main);
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this, this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();


        mAuth = FirebaseAuth.getInstance();
        Google_Login = findViewById(R.id.Google_Login);

        Google_Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
                startActivityForResult(signInIntent, RC_SIGN_IN);
            }
        });
    }

    @Override
    public void onActivityResult ( int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            if (result.isSuccess()) {
                GoogleSignInAccount account = result.getSignInAccount();
                firebaseAuthWithGoogle(account);
            } else {
            }
        }
    }
    private void firebaseAuthWithGoogle(GoogleSignInAccount acct){
        AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(),null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(!task.isSuccessful()){
                            Toast.makeText(MainActivity.this, "인증 실패", Toast.LENGTH_SHORT).show();

                        }else{
                            String qusr_uid = mAuth.getCurrentUser().getUid();
                            FirebaseDatabase fdatabase = FirebaseDatabase.getInstance();
                            DatabaseReference newclient = fdatabase.getReference("UserData/" + qusr_uid + "/Genre");
                            newclient.addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(DataSnapshot dataSnapshot1) {
                                    boolean asdf = dataSnapshot1.exists();

                                    if(asdf==true)
                                    {
                                        Login.finish();

                                        Intent intent = new Intent(getApplicationContext(), MyHome.class);
                                        startActivity(intent);

                                        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                                    }

                                    else
                                    {
                                        database.child("UserData").child((mAuth.getCurrentUser().getUid())).child("UID").setValue((mAuth.getCurrentUser().getUid()));
                                        database.child("UserData").child((mAuth.getCurrentUser().getUid())).child("E-Mail").setValue((mAuth.getCurrentUser().getEmail()));

                                        Login.finish();
                                        Intent intent = new Intent(getApplicationContext(), SelectGenre.class);
                                        startActivity(intent);

                                        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                                    }
                             }
                                @Override
                                public void onCancelled(DatabaseError error) {
                                }
                            });
                        }
                    }
                });


    }

    public void onBackPressed(){
        if(System.currentTimeMillis()-time>=2000){
            time=System.currentTimeMillis();
            Toast.makeText(getApplicationContext(),"뒤로 버튼을 한번 더 누르면 종료합니다.",Toast.LENGTH_SHORT).show();
        }else if(System.currentTimeMillis()-time<2000){
            finishAffinity();
            System.runFinalization();
            System.exit(0);
    }
    }


    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
    }



}










