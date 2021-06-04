package com.example.instagram;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class RegisterActivity extends AppCompatActivity {

    EditText username, email, password,phone;
    Button register;
    TextView textView;
    private ProgressDialog pd;
    private DatabaseReference reference;
    FirebaseAuth auth;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        username = findViewById(R.id.NameR);
        email = findViewById(R.id.EmailAddressR);
        password = findViewById(R.id.PasswordR);
        register = findViewById(R.id.Register1);
        phone=findViewById(R.id.PhoneR);
        textView = findViewById(R.id.textViewR);

        reference=FirebaseDatabase.getInstance().getReference();
        auth=FirebaseAuth.getInstance();
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
            }
        });
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                pd = new ProgressDialog(RegisterActivity.this);
                pd.setMessage("Please Wait.....");
                pd.show();

                String str_username = username.getText().toString();
                String str_phone=phone.getText().toString();
                String str_email = email.getText().toString();
                String str_password = password.getText().toString();

                if (TextUtils.isEmpty(str_username) || TextUtils.isEmpty(str_email) ||TextUtils.isEmpty(str_phone)|| TextUtils.isEmpty(str_password)) {

                    Toast.makeText(RegisterActivity.this, "All Fields are Required ", Toast.LENGTH_SHORT).show();
                } else if (str_password.length() < 6) {
                    Toast.makeText(RegisterActivity.this, "Password must have 6 character", Toast.LENGTH_SHORT).show();
                } else {

                    register(str_username,str_phone,str_email,str_password);

                }


            }
        });

    }

    private void register(final String username,final String phone, final String email,final String password) {

        auth.createUserWithEmailAndPassword(email,password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(AuthResult authResult) {

                HashMap<String,Object>map=new HashMap<>();
                map.put("username",username);
                map.put("phone",phone);
                map.put("email",email);
                map.put("password",password);
                map.put("imageurl","https://firebasestorage.googleapis.com/v0/b/instagramapp-b8128.appspot.com/o/placeholder.png?alt=media&token=f736fc59-5eb8-455d-83a1-bd27176c606f");
                map.put("id",auth.getCurrentUser().getUid());
                map.put("bio","");
                reference.child("Users").child(auth.getCurrentUser().getUid()).setValue(map).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            pd.dismiss();
                            Toast.makeText(RegisterActivity.this, "Register Successfully", Toast.LENGTH_SHORT).show();
                            Intent intent=new Intent(RegisterActivity.this, MainActivity.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(intent);

                            finish();


                        }
                    }
                });



            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                pd.dismiss();
                Toast.makeText(RegisterActivity.this,e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }


}


