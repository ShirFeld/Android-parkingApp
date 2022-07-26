package com.nameofproject.parkms2;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class CUDPlace {
    private DatabaseReference databaseReference;
    private DatabaseReference databaseReference2;

    public CUDPlace() {
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        databaseReference = db.getReference(Place.class.getSimpleName());
        databaseReference2 = db.getReference("Place");
    }

    public Task<Void> add(Place place) {
        return databaseReference.push().setValue(place);
    }

    public Task<Void> update(String key, HashMap<String, Object> hashMap) {
        return databaseReference.child(key).updateChildren(hashMap);
    }

    public boolean update() {
        final boolean[] isSuccses = {false};
        databaseReference2.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot postSnapshot : snapshot.getChildren()) {
                    boolean isFree = postSnapshot.child("trueorfalse").getValue(Boolean.class);
                    if (!isFree) {
                        databaseReference2.child(postSnapshot.getKey()).child("trueorfalse").setValue(true);
                        isSuccses[0] = true;
                        break;
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        return isSuccses[0];
    }


    public boolean removeFirstPlace() {
        final boolean[] isSuccses = {false};
        databaseReference2.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot postSnapshot : snapshot.getChildren()) {
                    boolean isFree = postSnapshot.child("trueorfalse").getValue(Boolean.class);
                    if (isFree) {
                        databaseReference2.child(postSnapshot.getKey()).child("trueorfalse").setValue(false);
                        isSuccses[0] = true;
                        break;
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        return isSuccses[0];
    }
}
