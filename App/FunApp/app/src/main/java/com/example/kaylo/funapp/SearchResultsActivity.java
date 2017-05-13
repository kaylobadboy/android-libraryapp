package com.example.kaylo.funapp;


        import java.util.Iterator;
        import java.util.LinkedHashMap;
        import java.util.Map;
        import java.util.Set;
        import android.widget.Button;
        import android.widget.CheckBox;
        import android.app.Activity;
        import android.content.Intent;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.Button;
        import android.widget.EditText;
        import android.support.v7.app.AppCompatActivity;
        import android.view.Menu;
        import android.widget.LinearLayout;
        import android.view.MenuItem;
        import android.widget.TextView;
        import android.graphics.Color;
import android.content.Context;
import android.graphics.Color;
import android.os.AsyncTask;
        import android.view.View;
import android.widget.CheckBox;
import android.widget.LinearLayout;
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


public class SearchResultsActivity extends AsyncTask<String, Void, String> {
    private TextView statusField, roleField;
    private Context context;
    private LinearLayout linearBox;
    public NextActivity activity;


    // Flag 0 means GET and 1 means POST. (By default it is GET.)
    public SearchResultsActivity(Context context, LinearLayout linearBox, TextView roleField, NextActivity activity ) {
        this.context     = context;
        this.statusField = statusField;
        this.roleField   = roleField;
        this.linearBox =  linearBox;
        this.activity = activity;
    }

    protected void onPreExecute( ) { }

    @Override
    protected String doInBackground( String... arg0 ) {
        try {
            //String username = (String) arg0[0];
            //String password = (String) arg0[1];
            String keywordyo = (String) arg0[0];
            String link     = "http://people.aero.und.edu/~klommen/457/BookStore/search.php";

            // Connect to the server.


            // Send the arguments via standard output for the POST method.

            String data  = URLEncoder.encode( "keywords", "UTF-8" ) + "=";
            data += URLEncoder.encode( keywordyo,   "UTF-8" );

            URL url = new URL( link );
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
    protected void onPostExecute( String result )  {
System.out.println("search result" + result);
        System.out.println("search result" + result);
        System.out.println("search result" + result);
        System.out.println("search result" + result);
        //this.statusField.setText( "Login Successful" );
        //this.roleField.setText  ( result );
        activity.parseresults(result);
        //this.linearBox.setText(result);

    }
}