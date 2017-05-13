package com.example.kaylo.funapp;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.ArrayList;
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
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.graphics.Color;
import android.widget.GridLayout.LayoutParams;
import android.text.TextWatcher;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.URLSpan;

public class NextActivity extends Activity {
    /** Called when the activity is first created. */
    CheckBox     checkBox, checkBox1;
    LinearLayout linearBox;
    EditText keywordlist;
    TextView tvView;
    ArrayList<Integer> selectcheck = new ArrayList<>();
    ArrayList<EditText> quantitycheck = new ArrayList<>();
    ArrayList<String> checkboxISBN = new ArrayList<>();
    public String username;
    String pass;
    @Override
   protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_next);

        linearBox = (LinearLayout) findViewById(R.id.linearBox);
        //linearBox.setGravity(1);
        keywordlist = (EditText) findViewById(R.id.bookkeyword);
        tvView = (TextView) findViewById(R.id.tvView);
        final TextView results = (TextView) findViewById(R.id.results);
        //final EditText number = (EditText) findViewById( R.id.number );
        //final EditText title  = (EditText) findViewById( R.id.title );

        Intent intent = getIntent( );
        username = intent.getStringExtra( "name" );
        String pass = intent.getStringExtra( "pass" );

        /**
         * Connect to server and see if they are valid
         *
         *
         */

        tvView.setText( "Welcome " + username );


        final Button button = (Button) findViewById( R.id.home );
        button.setOnClickListener(
                new View.OnClickListener( ) {
                    public void onClick( View v ) {
                        Intent i = new Intent( NextActivity.this, MainActivity.class );
                        startActivity( i );
                    }
                }
        );
        final Button histbutton = (Button) findViewById( R.id.viewuserinfo );
        histbutton.setOnClickListener(
                new View.OnClickListener( ) {
                    public void onClick( View v ) {
                        Intent i = new Intent( NextActivity.this, HistoryActivity.class );
                        i.putExtra( "name", username );
                        startActivity( i );
                    }
                }
        );





    }


    @Override
    public boolean onCreateOptionsMenu( Menu menu ) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater( ).inflate( R.menu.menu_next, menu );
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
        else if ( id == R.id.home ) {
            Intent i = new Intent( NextActivity.this, MainActivity.class );
            startActivity( i );
        }
        return super.onOptionsItemSelected( item );
    }




    public void searchPost( View view ) {
        keywordlist = (EditText) findViewById( R.id.bookkeyword );
        String keywords = keywordlist.getText( ).toString( ) ;
        final TextView tvView = (TextView) findViewById( R.id.results );

        //method.setText( "Post Method" );

        selectcheck = new ArrayList<>();
        quantitycheck = new ArrayList<>();
        checkboxISBN = new ArrayList<>();

        new SearchResultsActivity( this, linearBox, tvView ,this ).execute(keywords);


    }
    public int quantityget(int checkboxindex)
    {



                String quantity = quantitycheck.get(checkboxindex).getText().toString();
                return Integer.parseInt(quantity);


    }
    public void purchasePost(View view){
        final TextView tvView = (TextView) findViewById(R.id.tvView);
        final LinkedHashMap<String, Integer>
         alphabet = new LinkedHashMap<String, Integer>();



        for (int i = 0; i < selectcheck.size(); i++) {
            int quantity = quantityget(selectcheck.get(i));
            String ISBN = checkboxISBN.get((selectcheck.get(i)));
            alphabet.put(ISBN, quantity);
            //tvView.setText(quantity + " " + ISBN);
            new PurchasesActivity(this,ISBN,quantity,username).execute();

                 }






    }
    public void parseresults(String keys) {
        final TextView tvView = (TextView) findViewById(R.id.results);
        TableLayout purchasetable = (TableLayout) findViewById(R.id.purchasetable);

        purchasetable.removeAllViews();

        TextView HEADquant = new TextView(this);

        TextView empty = new TextView(this);

        HEADquant.setText("Quantity");
        //TextView HEADisbn = new TextView(this);
        //HEADisbn.setText("ISBN");
        TextView HEADtitle = new TextView(this);
        HEADtitle.setText("Title");
        TextView HEADprice = new TextView(this);
        HEADprice.setText("Price");

        TableRow headrow = new TableRow(this);
        headrow.setOrientation(LinearLayout.HORIZONTAL);

        headrow.addView(empty);
        //headrow.addView(HEADisbn);
        headrow.addView(HEADtitle);
        headrow.addView(HEADprice );
        headrow.addView(HEADquant);

        purchasetable.addView(headrow);
        purchasetable.setColumnStretchable(5,true);


        //tvView.append(keys);
        if( keys.contains("No Results")){
            //linearBox.removeAllViews();


        }else{
        String[] lines = keys.split("\\|");




        //tvView.append(lines.length + "");
        //tvView.append(lines.toString());
        int i = 0;
        int length = lines.length;



        int width = 16;
       // linearBox.removeAllViews();
        for (String s : lines) {


            final String[] book = s.split(",");
            //tvView.append(book[0]);
            //tvView.append(book[1]);
            //tvView.append(book[2]);
            //tvView.append("\n\n");

            //final EditText input = new EditText(this);
            //EditText.setId(i);
            checkBox = new CheckBox(this);
            checkBox.setId(i);
            //checkBox.setText(book[0]+ " " + book[1] + " $" + book[2]);
            checkBox.setOnClickListener(getOnClickDoSomething(checkBox));

            checkboxISBN.add(book[0]);
            i++;

            TextView tv = new TextView(this);
            SpannableStringBuilder ssb = new SpannableStringBuilder( );
            ssb.append( book[1] );
            ssb.setSpan( new URLSpan("#"), 0, ssb.length(),
                    Spanned.SPAN_EXCLUSIVE_EXCLUSIVE );
            tv.setText( ssb, TextView.BufferType.SPANNABLE );

            tv.setOnClickListener( new View.OnClickListener( ) {
                //tv.setOrientation(LinearLayout.HORIZONTAL);
                @Override

                public void onClick( View v ) {
                    //to go book product page with the book details
                    Intent intent = new Intent( NextActivity.this, BookInfoActivity.class );
                    intent.putExtra( "ISBN", book[0] );
                    startActivity( intent );
                }

            } );

            //linearBox.setOrientation(LinearLayout.VERTICAL);

            //linearBox.setShowDividers(LinearLayout.SHOW_DIVIDER_END);
            //LinearLayout l = new LinearLayout(this);

            TableRow row = new TableRow(this);
            row.setOrientation(LinearLayout.HORIZONTAL);


            //l.setGravity(gravity.CENTER);

            //l.setWeightSum(1);
            //l.setOrientation(LinearLayout.HORIZONTAL);

            //LayoutParams lp = new LayoutParams(width,LayoutParams.WRAP_CONTENT);
            //l.addView(et,lp);
            //TextView isbntext = new TextView(this);
            //isbntext.setPadding(4,0,0,0);
            //isbntext.setText(book[0]+"   ");

            TextView pricetext = new TextView(this);
            pricetext.setText("   $"+book[2]);
            //pricetext.setPadding(0,0,0,0);

            EditText et = new EditText(this);
           // et.setPadding(4,0,0,0);
            //tv.setPadding(4,0,0,0);
            et.setId(i);
            et.setText("1");
            et.setEms(2);
            //et.setRight(1);
            quantitycheck.add(et);

            row.addView(checkBox);
            //row.addView(isbntext);
            row.addView(tv);
            row.addView(pricetext);
            row.addView(et);

            //l.set
            purchasetable.addView(row);
            purchasetable.isShrinkAllColumns();

        }







    }
    }

        View.OnClickListener getOnClickDoSomething( final Button button ){
            return new View.OnClickListener() {
                public void onClick(View v) {
                    CheckBox button = (CheckBox)v;
                    if (button.isChecked())
                    {

                        selectcheck.add(button.getId());
                        //tvView.setText(selectcheck.toString());
                    }
                    else
                    {
                        selectcheck.remove(selectcheck.indexOf(button.getId()));
                        //tvView.setText(selectcheck.toString());
                    }
                    //slctcheck[ slctcheck.length ]= button.getId();

                    //for(int arg: slctcheck) {
                       // tvView.setText(arg + "");
                        //System.out.print(arg + " ");
                    //}

                    //final TextView tvView = (TextView) findViewById( R.id.textView3 );
                    //tvView.setText( button.getId( ) + ": " + button.getText( ).toString( ) );
                }
            };










        //tvView.append(output[i]);




        //final LinkedHashMap<String, String>
          //      alphabet = new LinkedHashMap<String, String>();


            //String next = output[i+1];
            //String ISBN = output[i];

            //alphabet.put((i + ""), output[i+1]);


        //tvView.append(keys);
    /*


        //alphabet.put("370", "Computer Architecture");

        Set<?> set = alphabet.entrySet();
        // Get an iterator.
        Iterator<?> j = set.iterator();
        // Display elements.
        while (j.hasNext()) {
            @SuppressWarnings("rawtypes")
            Map.Entry me = (Map.Entry) j.next();
            checkBox = new CheckBox(this);
            checkBox.setId(Integer.parseInt(me.getKey().toString()));
            checkBox.setText(me.getValue().toString());
            checkBox.setOnClickListener(getOnClickDoSomething(checkBox));
            linearBox.addView(checkBox);
            //results.addView(checkBox);
        }


    }
    View.OnClickListener getOnClickDoSomething( final Button button ) {
        return new View.OnClickListener( ) {
            public void onClick( View v ) {
                //final TextView tvView = (TextView) findViewById( R.id.textView3 );
                //tvView.setText( button.getId( ) + ": " + button.getText( ).toString( ) );
            }
        };



    }
    */
    }



}