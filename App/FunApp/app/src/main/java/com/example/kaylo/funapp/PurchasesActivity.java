package com.example.kaylo.funapp;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.LinkedHashMap;

/**
 * Created by kaylo on 3/25/2017.
 */
public class PurchasesActivity extends AsyncTask<String, Void, String> {
    //private TextView statusField, roleField;
    private Context context;
    //private LinearLayout linearBox;
    public NextActivity activity;
    private String username;
    private int quantity;
    private String ISBN;
    // Flag 0 means GET and 1 means POST. (By default it is GET.)
    public PurchasesActivity(Context context, String ISBN, int quantity, String Username) {
        this.context     = context;
        this.username = Username;
        this.quantity = quantity;
        this.ISBN = ISBN;
        this.activity = activity;
    }

    protected void onPreExecute( ) { }

    @Override
    protected String doInBackground( String... arg0 ) {
        try {
            //String username = (String) arg0[0];
            //String password = (String) arg0[1];
            //String keywordyo = (String) arg0[0];
            String link     = "http://people.aero.und.edu/~klommen/457/BookStore/purchase.php";

            // Connect to the server.
            String newquant = quantity + "";

            System.out.println(username);
            System.out.println(ISBN);
            System.out.println(newquant);
            // Send the arguments via standard output for the POST method.

            String data  = URLEncoder.encode( "username", "UTF-8" ) + "=";
            data += URLEncoder.encode( username,   "UTF-8" ) + "&";
            data += URLEncoder.encode( "ISBN", "UTF-8" ) + "=";
            data += URLEncoder.encode( ISBN,   "UTF-8" )+ "&";
            data += URLEncoder.encode( "quantity", "UTF-8" ) + "=";
            data += URLEncoder.encode( newquant,   "UTF-8" );


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

        //this.statusField.setText( "Login Successful" );
        //this.roleField.setText  ( result );
        //activity.parseresults(result);
        //this.linearBox.setText(result);

    }
}