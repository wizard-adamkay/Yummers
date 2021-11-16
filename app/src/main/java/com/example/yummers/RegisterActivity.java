package com.example.yummers;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.yummers.models.Business;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class RegisterActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();
    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            currentUser.reload();
        }
    }

    public void register(View view){
        EditText businessNameET = findViewById(R.id.businessName);
        EditText businessAddressET = findViewById(R.id.businessAddress);
        EditText businessPhoneET = findViewById(R.id.businessPhone);
        EditText passwordET = findViewById(R.id.password);
        EditText emailET = findViewById(R.id.email);
        String businessName = businessNameET.getText().toString();
        String businessAddress = businessAddressET.getText().toString();
        String businessPhone = businessPhoneET.getText().toString();
        String password = passwordET.getText().toString();
        String email = emailET.getText().toString();
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("asdf", "createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            Business business = new Business(businessName, businessAddress, businessPhone);
                            Log.d("asdf",business.toString());
                            //updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w("asdf", "createUserWithEmail:failure", task.getException());
                            Toast.makeText(RegisterActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            //updateUI(null);
                        }
                    }
                });
    }
}