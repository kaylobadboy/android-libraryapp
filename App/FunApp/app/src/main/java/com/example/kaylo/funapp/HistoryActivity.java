package com.example.kaylo.funapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.URLSpan;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class HistoryActivity extends Activity {
    public String username;
    public double Totalspent;
    @Override
    public void onCreate( Bundle savedInstanceState ) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);


        Intent intent = getIntent( );
        username = intent.getStringExtra( "name" );


        new HistoryResultsActivity(this,this).execute(username);

        final TextView head = (TextView) findViewById( R.id.Hist_Header );


        //String keys = intent.getStringExtra( "keywords" );
        //textyolo.setText("Searched: " +1 keys);
        head.setText( username +"'s Purchases");

    }
    public void parseresults(String keys) {
        Totalspent = 0;
        if (keys.contains("No Book"))
        {
            }else {

            TableLayout purchasetable = (TableLayout) findViewById(R.id.purchasetable);

            purchasetable.removeAllViews();

            TextView HEADquant = new TextView(this);

            HEADquant.setText("  Quantity");
            //TextView HEADisbn = new TextView(this);
            //HEADisbn.setText("ISBN");
            TextView HEADtitle = new TextView(this);
            HEADtitle.setText("Title");
            TextView HEADprice = new TextView(this);
            HEADprice.setText("Price");

            TableRow headrow = new TableRow(this);
            headrow.setOrientation(LinearLayout.VERTICAL);


            //headrow.addView(HEADisbn);
            headrow.addView(HEADtitle);
            headrow.addView(HEADprice );
            headrow.addView(HEADquant);

            purchasetable.addView(headrow);
            //purchasetable.setColumnStretchable(4,true);

            String[] lines = keys.split("\\|");
            int i = 0;
            int length = lines.length;
                for (String s : lines) {


                    final String[] book = s.split(",");

                    i++;

                    TextView tv = new TextView(this);


                    SpannableStringBuilder ssb = new SpannableStringBuilder();
                    ssb.append(book[1]);
                    ssb.setSpan(new URLSpan("#"), 0, ssb.length(),
                            Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                    tv.setText(ssb, TextView.BufferType.SPANNABLE);

                    tv.setOnClickListener(new View.OnClickListener() {
                        //tv.setOrientation(LinearLayout.HORIZONTAL);
                        @Override

                        public void onClick(View v) {
                            Intent intent = new Intent(HistoryActivity.this, BookInfoActivity.class);

                            String trimmed = book[0].replaceAll("\\p{C}", "");
                            intent.putExtra("ISBN", trimmed);
                            startActivity(intent);
                        }

                    });
                    tv.setSingleLine();

                    //final TextView textyolo = new TextView(this);
                    //textyolo.setText("hello");
                    //row.addView(textyolo);

                    //purchasetable.addView(row);

                    purchasetable.setOrientation(TableLayout.VERTICAL);


                    TableRow row = new TableRow(this);
                    row.setOrientation(LinearLayout.HORIZONTAL);

                    TextView isbntext = new TextView(this);
                    //isbntext.setPadding(4,0,0,0);
                    isbntext.setText(book[0] + "");

                    TextView pricetext = new TextView(this);
                    pricetext.setText("$" + book[2]);

                    TextView quanttext = new TextView(this);
                    quanttext.setText("  " + book[3]);
                    System.out.println("price:" + Double.valueOf((book[2])));
                    System.out.println("quant:" + Double.valueOf(book[3]));
                    Totalspent += Double.valueOf((book[2])) * Double.valueOf(book[3]);

                    pricetext.setPadding(0, 0, 0, 0);


                    //row.addView(isbntext);
                    row.addView(tv);
                    row.addView(pricetext);
                    row.addView(quanttext);
                    //l.addView(et);

                    //l.set
                    purchasetable.addView(row);

                }


                final TextView mad = (TextView) findViewById( R.id.TotalSpent );
                String rounded = String.format("%.2f", Totalspent);
                mad.setText("Total spent:  $"+rounded);

                //rowRESULT.addView(total);
                //purchasetable.addView(rowRESULT);


            }
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
        else if ( id == R.id.search ) {
            Intent i = new Intent( HistoryActivity.this, MainActivity.class );
            startActivity( i );
        }
        return super.onOptionsItemSelected( item );
    }
}
