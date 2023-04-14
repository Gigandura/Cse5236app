package com.example.cse_5236_app.ui.notifications;

import android.content.ActivityNotFoundException;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

import com.bumptech.glide.Glide;
import com.example.cse_5236_app.R;
import com.example.cse_5236_app.databinding.FragmentNotificationsBinding;
import com.example.cse_5236_app.model.User;
import com.example.cse_5236_app.ui.Login.LoginActivity;
import com.example.cse_5236_app.ui.MainActivity;
import com.example.cse_5236_app.ui.dashboard.DashboardActivity;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;


public class NotificationsFragment extends DialogFragment implements View.OnClickListener {

    private FragmentNotificationsBinding binding;

    private String userid;
    private User user;

    private FirebaseDatabase fd;
    private SharedPreferences sharedPref;
    private boolean connectedToDb;

    ImageView profilePic;
    TextView userNameText;
    TextView userDesc;

    DatabaseReference connection;
    ActivityResultLauncher<String> mGetContent = registerForActivityResult(new ActivityResultContracts.GetContent(),
            uri -> {
                // Handle the returned Uri
                StorageReference storageReference = FirebaseStorage.getInstance().getReference();
                StorageReference fileRef = storageReference.child(System.currentTimeMillis() + "." + getFileExtension(uri));
                fileRef.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        Toast.makeText(getContext(), "Uploading succeeded", Toast.LENGTH_SHORT).show();
                        Log.v("Notification Fragment","upload success");
                        fileRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {
//                                user.setImageUri(uri.toString());
                                fd.getReference().child("users").child(user.getUsername()).child("image").setValue(uri.toString());
                            }
                        });
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.e("Notification Fragment", "failure to upload picture");
                    }
                });
            });;

    private String getFileExtension(Uri uri) {
        ContentResolver cr = getContext().getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cr.getType(uri));
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentNotificationsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        BottomNavigationView bottomNavigationView=getActivity().findViewById(R.id.nav_view);
        bottomNavigationView.setSelectedItemId(R.id.navigation_notifications);

        // Perform item selected listener
        bottomNavigationView.setOnItemSelectedListener(new BottomNavigationView.OnItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch(item.getItemId())
                {
                    case R.id.navigation_dashboard:
                        userid = getActivity().getIntent().getStringExtra("userid");
                        Intent intent = new Intent(getActivity(), DashboardActivity.class);
                        intent.putExtra("userid", userid);
                        startActivity(intent);
                        getActivity().overridePendingTransition(0,0);
                        return true;
                    case R.id.navigation_notifications:
                        return true;
                    case R.id.navigation_home:
                        userid = getActivity().getIntent().getStringExtra("userid");
                        Intent intent2 = new Intent(getActivity(), MainActivity.class);
                        intent2.putExtra("userid", userid);
                        startActivity(intent2);
                        getActivity().overridePendingTransition(0,0);
                        return true;
                }
                return false;
            }
        });

        Log.v("Notification Fragment", "OnCreateView");

        Button deleteProf = root.findViewById(R.id.del_button);
        Button changePass = root.findViewById(R.id.change_pass_button);
        Button changePic = root.findViewById(R.id.change_pic_button);
        Button changeDesc = root.findViewById(R.id.change_desc_button);
        deleteProf.setOnClickListener(this);
        changeDesc.setOnClickListener(this);
        changePass.setOnClickListener(this);
        changePic.setOnClickListener(this);

        profilePic = root.findViewById(R.id.imageView);
        userNameText = root.findViewById(R.id.text_notifications);
        userDesc = root.findViewById(R.id.userDesc);

        fd = FirebaseDatabase.getInstance();
        getFirebaseConnection();
        return root;
    }

    private void getFirebaseConnection() {
        connection = fd.getReference().child(".info/connected");
        connection.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.getValue(Boolean.class)) {
                    loadUser();
                    connectedToDb = true;
                } else {
                    if (connectedToDb) {
                        Toast.makeText(getContext(),"No connection to the database", Toast.LENGTH_SHORT).show();
                        connectedToDb = false;
                    }

                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getContext(),"No connection to the database", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onClick(View v) {
        String defaultUsername = "Error";
        String username = sharedPref.getString(getString(R.string.saved_username_key), defaultUsername);
        DatabaseReference fdRef = fd.getReference();
        if (v.getId() == R.id.del_button) {
            Intent toLogin = new Intent(v.getContext(), LoginActivity.class);
            fdRef.child("users").child(username).removeValue();
            startActivity(toLogin);
        } else if (v.getId() == R.id.change_pass_button) {
            DialogFragment dialogFragment = new NotificationDialogPassword(username);
            dialogFragment.show(getActivity().getSupportFragmentManager(), "Notification Fragment Pass");
        } else if (v.getId() == R.id.change_desc_button) {
            DialogFragment dialogFragment = new NotificationDialogDesc(username);
            dialogFragment.show(getActivity().getSupportFragmentManager(), "Notification Fragment Desc");
        } else if (v.getId() == R.id.change_pic_button) {
            try {
                mGetContent.launch("image/*");
            }
            catch (ActivityNotFoundException e) {
                Log.e("Notification Fragment", "Error in picture");
            }

        } else {
            Log.e("Login Fragment", "Bad button input");
        }

    }

    public void loadUser() {
        sharedPref = getActivity().getSharedPreferences(getString(R.string.preference_file_key), Context.MODE_PRIVATE);
        String defaultUsername = "Error";
        String username = sharedPref.getString(getString(R.string.saved_username_key), defaultUsername);
        Log.v("Notification Fragment", username);
        DatabaseReference getImage = fd.getReference();
        Context context = getContext();
        getImage.child("users").child(username).addValueEventListener(
                new ValueEventListener() {
                    @Override
                    public void onDataChange(
                            @NonNull DataSnapshot dataSnapshot)
                    {
//                        Log.v("Notification Fragment", dataSnapshot.toString());
                        try {
                            user = dataSnapshot.getValue(User.class);
//                            Log.v("Notification Fragment", user.toString());
                            if (user.getImage() == null) {
                                Glide.with(context).load(getString(R.string.profile_uri_default)).into(profilePic);
                            }
                            else {
                                Glide.with(context).load(user.getImage()).into(profilePic);
                            }
                            if (user.getUsername() == null) {
                                userNameText.setText(getString(R.string.error_loading_data));
                            } else {
                                userNameText.setText(username);
                            }
                            if (user.getDescription() == null) {
                                userDesc.setText(getString(R.string.no_description));
                            } else {
                                userDesc.setText(user.getDescription());
                            }
                            Log.v("Notification Fragment", "Picture Loaded");
                        }
                        catch (Exception e) {
                            Log.e("Notification Fragment", e.toString());
                        }
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Log.e("Notification Fragment", "Picture Database error");
                    }
                });

    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }


}