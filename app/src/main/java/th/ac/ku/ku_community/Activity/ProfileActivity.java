package th.ac.ku.ku_community.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import de.hdodenhof.circleimageview.CircleImageView;
import th.ac.ku.ku_community.Activity.Model.User;
import th.ac.ku.ku_community.R;

public class ProfileActivity extends AppCompatActivity {

    FirebaseAuth mAuth;

    DatabaseReference userDB;

    private CircleImageView profileImage;
    private TextView profileName, profileId, profileEmail, profileFaculty, profileMajor, profileCreatedAt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        mAuth = FirebaseAuth.getInstance();
        userDB = FirebaseDatabase.getInstance().getReference("users");

        profileImage = findViewById(R.id.profileImage);
        profileName = findViewById(R.id.profileName);
        profileId = findViewById(R.id.profileId);
        profileEmail = findViewById(R.id.profileEmail);
        profileFaculty = findViewById(R.id.profileFaculty);
        profileMajor = findViewById(R.id.profileMajor);
        profileCreatedAt = findViewById(R.id.profileCreatedAt);

        userDB.child(mAuth.getCurrentUser().getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User user = snapshot.getValue(User.class);
                if (user != null){
                    if (user.getImg() != null){
                        Picasso.get().load("http://i.imgur.com/DvpvklR.png").placeholder(R.drawable.profile).error(R.drawable.profile).into(profileImage);
                    }
                    if (user.getName() == null){
                        profileName.setText("?");
                    }
                    profileId.setText(user.getId());
                    profileEmail.setText(user.getEmail());
                    if (user.getFaculty() == null){
                        profileFaculty.setText("-");
                    }
                    if (user.getMajor() == null){
                        profileMajor.setText("-");
                    }
//                    profileCreatedAt.setText();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void handleEditProfileBtn(View view){
        Intent intent = new Intent(ProfileActivity.this, EditProfileActivity.class);
        startActivity(intent);
    }
}