package com.nameofproject.parkms2;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.security.Key;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String APP_PREFERENCES = "msettings" ;
    private static final int sign_in_from_create = 1; // מאיפה פתחנו את האקטיביטי של התחברות\הרשמה

    SharedPreferences mSettings;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

//        final EditText edit_car_number = findViewById(R.id.trueOrFalse);
        final Switch aSwitch = (Switch) findViewById(R.id.switch1);
//        final Button btn = findViewById(R.id.btn_submit);
//        final Button btn1 = findViewById(R.id.btn1_submit);
        CUDPlace aud = new CUDPlace();



//        btn.setOnClickListener(v->{
//            Place place = new Place(edit_car_number.getText().toString(),aSwitch.getShowText());
//            aud.add(place).addOnSuccessListener(success ->{
//                Toast.makeText(this,"Added!",Toast.LENGTH_SHORT).show();
//            }).addOnFailureListener(error ->{
//                Toast.makeText(this,""+error.getMessage(),Toast.LENGTH_SHORT).show();
//            });
//        });

//



//  כאן נוכל ללחוץ על המשתמש ברשימה ולראות את כל שאר הפרטים. כרגע רואים רק שם

//
//        btn1.setOnClickListener(v->{
//            HashMap<String,Object> hashMap =new HashMap<>();
//
////
////            hashMap.put("parkNumber",edit_car_number.getText().toString());
//            hashMap.put("trueorfalse",mSettings.getBoolean("isChecked",false));
//
//
//
//            aud.update("-MyX_9CJKgzrNt1loGjh",hashMap).addOnSuccessListener(suc->{
//                Toast.makeText(this, "Updated", Toast.LENGTH_SHORT).show();
//
//            }).addOnFailureListener(err->{
//                Toast.makeText(this,""+err.getMessage(),Toast.LENGTH_SHORT).show();
//            });
//
//        });
//        for (int i = 0; i < list.size()-1; i++) {
//            HashMap<String,Object> hashMap =new HashMap<>();
//            hashMap.put("parkNumber",edit_car_number.getText().toString());
//            hashMap.put("trueorfalse",mSettings.getBoolean("isChecked",false));
//            if(hashMap.get(list.get(i).){
//
//            }
//        }
        mSettings = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
        aSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                    aud.update();
                else
                    aud.removeFirstPlace();
                SharedPreferences.Editor editor = mSettings.edit();
                editor.putBoolean("isChecked",isChecked);
                editor.apply();
//                if(isChecked){
//                    mSettings = getSharedPreferences(APP_PREFERENCES,Context.MODE_PRIVATE);
//                    boolean isSwichCheck = mSettings.getBoolean("isChecked",false);
//                }
            }
        });


        if(FirebaseAuth.getInstance().getCurrentUser()==null){  // We check here if the user is logged-in
            /*
            The user is not sign-in.
            option 1 - sign in
            option 2 - register
             */
            Intent signToWebsite = AuthUI.getInstance().createSignInIntentBuilder().build();  // intent is created
            startActivityForResult(signToWebsite,sign_in_from_create);
        }


    }

    // This method will work after onCreate is finished
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == sign_in_from_create) {
            if (resultCode == RESULT_OK){
                Toast.makeText(getApplicationContext(),"Your in!",Toast.LENGTH_LONG).show();
            }
        }
        /*
       If we log in to else it means that the user could not log in or register and he will not be able to log in to the app
        */
        else{
            Toast.makeText(getApplicationContext(),"Your Sign-in / Sign-up  was unsuccessful",Toast.LENGTH_LONG).show();
            finish();
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // returns MenuInflater object  -    מאכלס את התפריט עם מה שהעברנו בקובץ , מעביר את המזהה והכל  XML
        getMenuInflater().inflate(R.menu.menu1,menu); //  חלק שמאל של הפסיק - איפה נמצא הקובץ שבו יש לנו אלמנטים ויזואלים של התפריט. מימין לפסיק - רפרנס בזמן ריצה, איפה נשים את האלמנטים
        return true;
    }
    // menu options
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {  // אנחנו הולכים לitem בזמן ריצה

        if (item.getItemId()==R.id.log_out_item)
            logOut();
        return true;  // האם מה שאני רוצה שיופעל פעל- לא אכפת לי אם זה בהצלחה או לא
    }


    // menu methods
    private void logOut() { //Task = בצורה אסינכרונית
        AuthUI.getInstance().signOut(this) // AuthUI.getInstance() -> gives us this user  , this = who to logout
                .addOnCompleteListener(task ->{
                    Toast.makeText(MainActivity.this,"Logged-out",Toast.LENGTH_LONG).show();  //(where the message will be , what the message is , how much time)
                    MainActivity.this.finish();
                });

    }


//    public void onClickRead(View view) {
//
//        Intent i =new Intent(MainActivity.this,MainActivity2.class);
//        startActivity(i);
//    }
}