package com.example.instagram;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class StartActivity extends AppCompatActivity {



    ImageView imageView1,imageView3;
    Button login,register;
    FirebaseUser firebaseUser;


    private LinearLayout linearLayout;


    @Override
    protected void onStart() {
        super.onStart();

         firebaseUser=FirebaseAuth.getInstance().getCurrentUser();
        if (firebaseUser !=null){
            startActivity(new Intent(StartActivity.this, MainActivity.class));
            finish();
        }

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start_main);
        imageView3=findViewById(R.id.imageView3);
        imageView1=findViewById(R.id.imageView1);
        login=findViewById(R.id.LogIn_main);
        register=findViewById(R.id.Register_main);
        linearLayout=findViewById(R.id.linearLayout);

        linearLayout.animate().alpha(0f).setDuration(10);
        TranslateAnimation animation =new TranslateAnimation(0,0,0,-1000);
        animation.setDuration(900);
        animation.setFillAfter(false);
        animation.setAnimationListener(new MyAnimationListener());
        imageView1.setAnimation(animation);


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(StartActivity.this,LoginActivity.class);
                startActivity(intent);
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(StartActivity.this,RegisterActivity.class);
                startActivity(intent);

            }
        });





    }




    private  class  MyAnimationListener implements Animation.AnimationListener{

        @Override
        public void onAnimationStart(Animation animation) {

        }

        @Override
        public void onAnimationEnd(Animation animation) {
            imageView1.clearAnimation();
            imageView1.setVisibility(View.VISIBLE);
            linearLayout.animate().alpha(1f).setDuration(1000);
        }

        @Override
        public void onAnimationRepeat(Animation animation) {

        }
    }
}