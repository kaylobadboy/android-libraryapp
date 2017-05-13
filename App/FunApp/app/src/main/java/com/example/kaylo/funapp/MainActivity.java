package com.example.kaylo.funapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import org.w3c.dom.Text;


public class MainActivity extends AppCompatActivity {
    private EditText name,pass;
    private TextView statusfield, role;
    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );
        name = (EditText) findViewById( R.id.name );
        pass = (EditText) findViewById( R.id.pass );
        final Button button = (Button)   findViewById( R.id.next );
        role = (TextView) findViewById(R.id.role);
        statusfield = (TextView) findViewById(R.id.statusField);

       // wrongpass.setVisibility(View.INVISIBLE);
       // wrongpass.setText("fag");


    }
    public void loginPost( View view ) {

        String username = name.getText( ).toString( );
        String password = pass.getText( ).toString( );

        //method.setText( "Post Method" );

        new SigninActivity( this, statusfield, role, 1,this ).execute( username, password );








    }
    public void Successlogin(){

        Intent i = new Intent( MainActivity.this, NextActivity.class );
        i.putExtra( "name", name.getText( ).toString( ) );
        i.putExtra("pass", pass.getText( ).toString( ));

        startActivity( i );


    }
    public void signupPost( View view ) {

        String username = name.getText( ).toString( );

        String password = pass.getText( ).toString( );

        //method.setText( "Post Method" );

        new SignUpActivity( this, statusfield, role, 1 ).execute( username, password );

    }



    @Override
    public boolean onCreateOptionsMenu( Menu menu ) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater( ).inflate( R.menu.menu_main, menu );
        return true;
    }

    @Override
    public boolean onOptionsItemSelected( MenuItem item ) {
        // Handle action bar item clicks here.  The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId( );

        // noinspection SimplifiableIfStatement
        if ( id == R.id.action_settings ) {
            return true;
        }
        else if ( id == R.id.next ) {
            Intent i = new Intent( MainActivity.this, NextActivity.class );
            startActivity( i );
        }
        else if ( id == R.id.search ) {
            //Intent i = new Intent( MainActivity.this, SearchActivity.class );
            //startActivity( i );
        }
        return super.onOptionsItemSelected( item );
    }
}