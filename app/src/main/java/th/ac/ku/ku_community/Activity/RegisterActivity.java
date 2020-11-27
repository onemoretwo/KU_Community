package th.ac.ku.ku_community.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
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

public class RegisterActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;

    private EditText registerEmailField, registerIdField, registerPasswordField, registerConfirmPasswordField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mAuth = FirebaseAuth.getInstance();

        registerEmailField = findViewById(R.id.registerEmailField);
        registerIdField = findViewById(R.id.registerIdField);
        registerPasswordField = findViewById(R.id.registerPasswordField);
        registerConfirmPasswordField = findViewById(R.id.registerConfirmPasswordField);
    }

    public void handleRegisterSubmitBtn(View view) {

        String email = registerEmailField.getText().toString().trim();
        String id = registerIdField.getText().toString().trim();
        String password = registerPasswordField.getText().toString().trim();
        String confirmPassword = registerConfirmPasswordField.getText().toString().trim();

        if (email.equals("") || id.equals("") || password.equals("") || confirmPassword.equals("")) {
            Toast.makeText(this, "กรุณากรอกข้อมูลให้ครบถ้วน", Toast.LENGTH_LONG).show();
            return;
        } else if (!password.equals(confirmPassword)) {
            Toast.makeText(this, "Password ไม่ตรงกัน กรุณากรอกใหม่", Toast.LENGTH_LONG).show();
            return;
        }

        final SweetAlertDialog pDialog = new SweetAlertDialog(RegisterActivity.this, SweetAlertDialog.PROGRESS_TYPE);
        pDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
        pDialog.setTitleText("กำลังสร้างบัญชีผู้ใช้...");
        pDialog.setCancelable(true);
        pDialog.show();

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            // go to home
                            pDialog.dismiss();
                            goToHome();
                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(RegisterActivity.this, "Create account fail",
                                    Toast.LENGTH_SHORT).show();
                            pDialog.dismiss();
                        }
                    }
                });
    }

    public void handleBackBtn(View view) {
        finish();
    }

    public void goToHome(){
        Intent homeIntent = new Intent(RegisterActivity.this, MainActivity.class);
        startActivity(homeIntent);
        finish();
    }
}