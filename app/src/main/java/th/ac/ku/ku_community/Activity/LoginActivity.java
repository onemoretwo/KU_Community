package th.ac.ku.ku_community.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import cn.pedant.SweetAlert.SweetAlertDialog;
import th.ac.ku.ku_community.R;

public class LoginActivity extends AppCompatActivity {

    private EditText emailField, passwordField;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();

        emailField = findViewById(R.id.emailField);
        passwordField = findViewById(R.id.passwordField);
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();

        if (currentUser != null){
            // go to app
        }
    }

    public void handleLoginBtn(View view){
        if (emailField.getText().toString().equals("") || passwordField.getText().toString().equals("")){
            Toast.makeText(this,"กรุณากรอกข้อมูลให้ครบถ้วน", Toast.LENGTH_LONG).show();
            return;
        }

        String email = emailField.getText().toString();
        String password = passwordField.getText().toString();

        final SweetAlertDialog pDialog = new SweetAlertDialog(LoginActivity.this, SweetAlertDialog.PROGRESS_TYPE);
        pDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
        pDialog.setTitleText("กำลังเข้าสู่ระบบ...");
        pDialog.setCancelable(true);
        pDialog.show();

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.i("ii", "onComplete: login success");
                            // go to home
                            pDialog.dismiss();
                            goToHome();
                        } else {
                            // If sign in fails, display a message to the user.
                            pDialog.dismiss();
                            new SweetAlertDialog(LoginActivity.this, SweetAlertDialog.ERROR_TYPE)
                                    .setTitleText("เข้าสู่ระบบไม่สำเร็จ")
                                    .setContentText("ไม่พบบัญชีผู้ใช้ในระบบ!")
                                    .show();
                            // ...
                        }

                        // ...
                    }
                });
    }

    public void handleRegisterBtn(View view){
        Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
        startActivity(intent);
    }

    private void goToHome(){
        Intent homeIntent = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(homeIntent);
        finish();
    }
}