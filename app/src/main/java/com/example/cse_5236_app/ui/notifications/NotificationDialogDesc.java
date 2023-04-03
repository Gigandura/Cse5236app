package com.example.cse_5236_app.ui.notifications;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

import com.example.cse_5236_app.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class NotificationDialogDesc extends DialogFragment {

    private String username;
    public NotificationDialogDesc(String username) {
        this.username = username;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = requireActivity().getLayoutInflater();

        View inflated = inflater.inflate(R.layout.dialog_change_desc, null);
        EditText newDesc = inflated.findViewById(R.id.new_pass_text);

        builder.setView(inflated)
                .setPositiveButton(R.string.dialog_ok_button, (dialog, id) -> {
                    FirebaseDatabase fd = FirebaseDatabase.getInstance();
                    DatabaseReference fdRef = fd.getReference();
                    Log.v("NotificationDialogPassword", "New desc: " + newDesc.getText());
                    fdRef.child("users").child(username).child("description").setValue(newDesc.getText().toString());
                }).setNegativeButton(R.string.dialog_cancel_button, (dialog, id) -> {
                    NotificationDialogDesc.this.getDialog().cancel();
                });
        // Create the AlertDialog object and return it
        return builder.create();
    }
}
