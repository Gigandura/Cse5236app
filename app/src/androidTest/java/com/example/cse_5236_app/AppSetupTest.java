package com.example.cse_5236_app;

import android.content.Context;

import androidx.test.core.app.ApplicationProvider;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;



/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class AppSetupTest {

    private Context context;
    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        assertEquals("com.example.cse_5236_app", appContext.getPackageName());
    }

    @Test
    public void testDatabase() {
        context = ApplicationProvider.getApplicationContext();
        FirebaseOptions options = new FirebaseOptions.Builder()
                .setApplicationId("1:827233743741:android:e06712247f67db0bee5214")
                .setApiKey("AIzaSyAkjuLtVoYZNV0C5hIemjJvKGZDUqpKAUA")
                .setDatabaseUrl("https://cse5236app-default-rtdb.firebaseio.com")
                .build();
        FirebaseApp testDatabase = FirebaseApp.initializeApp(context, options, "testDatabase");
    }
}