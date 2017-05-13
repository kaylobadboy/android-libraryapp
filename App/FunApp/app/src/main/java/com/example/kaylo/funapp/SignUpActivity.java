package com.example.kaylo.funapp;

/**
 * Created by kaylo on 3/25/2017.
 */
import android.content.Context;
import android.os.AsyncTask;
import android.view.View;
import android.widget.TextView;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.TextView;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class SignUpActivity extends AsyncTask<String, Void, String> {
    private TextView statusField, roleField;
    private Context context;


    // Flag 0 means GET and 1 means POST. (By default it is GET.)
    public SignUpActivity( Context context, TextView statusField, TextView roleField, int flag ) {
        this.context     = context;
        this.statusField = statusField;
        this.roleField   = roleField;
    }

    protected void onPreExecute( ) { }

    @Override
    protected String doInBackground( String... arg0 ) {
        try {
            String username = (String) arg0[0];
            String password = (String) arg0[1];
            String link     = "http://people.aero.und.edu/~klommen/457/BookStore/sign_up.php";

            // Connect to the server.


            // Send the arguments via standard output for the POST method.

                String data  = URLEncoder.encode( "username", "UTF-8" ) + "=";
                data += URLEncoder.encode( username,   "UTF-8" ) + "&";
                data += URLEncoder.encode( "password", "UTF-8" ) + "=";
                data += URLEncoder.encode( password,   "UTF-8" );

            URL            url = new URL( link );
            URLConnection conn = url.openConnection( );
            conn.setDoOutput( true );
            OutputStreamWriter wr = new OutputStreamWriter(
                    conn.getOutputStream( ) );

                wr.write( data );
                wr.flush( );


            // Read server response.
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader( conn.getInputStream( ) ));
            StringBuilder sb = new StringBuilder( );
            String line = null;
            while (( line = reader.readLine( ) ) != null ) {
                sb.append( line );
                break;
            }
            return sb.toString( );
        }
        catch( Exception e ) {
            return new String( "Exception: " + e.getMessage( ) );
        }
    }

    @Override
    protected void onPostExecute( String result ) {
        //this.statusField.setText( "Login Successful" );
        this.roleField.setText  ( result );
    }
}