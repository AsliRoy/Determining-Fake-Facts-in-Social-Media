package com.example.asliroy.pdlab;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.app.DownloadManager;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.icu.text.IDNA;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.app.LoaderManager.LoaderCallbacks;

import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;

import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;
import java.util.List;

import static android.Manifest.permission.READ_CONTACTS;

/**
 * A main app screen that takes the text you want to verify as input and displays the output on a pop-up screen.
 */
public class LoginActivity extends AppCompatActivity  {

    // UI references.
    private AutoCompleteTextView mEmailView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        // Set up the login form.
        mEmailView = (AutoCompleteTextView) findViewById(R.id.email);
        mEmailView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                if (id == EditorInfo.IME_ACTION_DONE || id == EditorInfo.IME_NULL) {
                    attemptSubmit();
                    return true;
                }
                return false;
            }
        });

//Code segment that deals with the SUBMIT button...
        Button mEmailSignInButton = (Button) findViewById(R.id.email_sign_in_button);
        mEmailSignInButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptSubmit();
            }
        });

//Code segment that deals with the RESET button...
        Button mResetButton = (Button) findViewById(R.id.reset);
        mResetButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                final TextView mTextView = (TextView) findViewById(R.id.email);
                mTextView.setText("");
            }
        });
    }


    private void attemptSubmit() {


        final TextView mTextView = (TextView) findViewById(R.id.email);
// ...

// Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this);
        String url =("http://192.168.43.189/pdlab/main.php?username="+mTextView.getText());

// Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest( com.android.volley.Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Intent myintent=new Intent(LoginActivity.this, PopUp.class).putExtra("mnls", response.substring(0));
                        startActivity(myintent);
                     //   mTextView.setText("Response is: "+ response.substring(0));

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Intent myintent=new Intent(LoginActivity.this, PopUp.class).putExtra("mnls","That didn't work!"+error.toString() );
                startActivity(myintent);
               // mTextView.setText("That didn't work!"+error.toString());
            }
        });

// Add the request to the RequestQueue.
        queue.add(stringRequest);


    }

}

