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


    public class BookInfoActivity extends Activity {
        public String ISBN;
        //public double Totalspent;
        @Override
        public void onCreate( Bundle savedInstanceState ) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_bookinfo);


            Intent intent = getIntent( );
            ISBN = intent.getStringExtra( "ISBN" );
            new BookInfoResultsActivity(this,this).execute(ISBN);



            final TextView head = (TextView) findViewById( R.id.Hist_Header );


            //String keys = intent.getStringExtra( "keywords" );
            //textyolo.setText("Searched: " + keys);
            head.setText( ISBN +" BOOK INFO");
            //System.out.println(ISBN);
        }
        public void parseresults(String keys) {
            //Totalspent = 0;


            TableLayout bookinfotable = (TableLayout) findViewById(R.id.BookInfoTable);

            bookinfotable.removeAllViews();

            TextView HEADquant = new TextView(this);

            //HEADquant.setText("Quantity");
            TextView HEADisbn = new TextView(this);
            HEADisbn.setText("ISBN");
            TextView HEADtitle = new TextView(this);
            HEADtitle.setText("Title");
            TextView HEADprice = new TextView(this);
            HEADprice.setText("Price");

            TableRow headrow = new TableRow(this);
            headrow.setOrientation(LinearLayout.HORIZONTAL);


            headrow.addView(HEADisbn);
            headrow.addView(HEADtitle);
            headrow.addView(HEADprice );
            //headrow.addView(HEADquant);


            bookinfotable.addView(headrow);
            bookinfotable.setColumnStretchable(4,true);


            System.out.println("string is " + keys);
            if ( keys.equals("No Book found") ) return;

            String[] book = keys.split(",");



            //System.out.println("LENGTH IS "+ length);








                TextView tv = new TextView(this);
                tv.setText(book[1]);



                bookinfotable.setOrientation(TableLayout.VERTICAL);






                TableRow row = new TableRow(this);
                row.setOrientation(LinearLayout.HORIZONTAL);

                TextView isbntext = new TextView(this);
                //isbntext.setPadding(4,0,0,0);
                isbntext.setText(book[0] + "   ");
                TextView pricetext = new TextView(this);
                pricetext.setText("   $" + book[2]);




                pricetext.setPadding(0,0,0,0);


                row.addView(isbntext);
                row.addView(tv);
                row.addView(pricetext);


                bookinfotable.addView(row);


           /* TableRow rowRESULT = new TableRow(this);
            rowRESULT.setOrientation(LinearLayout.HORIZONTAL);
            TextView total = new TextView(this);
            total.setText("Total spent:" + " $"+ Totalspent);
            rowRESULT.addView(total);
            purchasetable.addView(rowRESULT);


*/

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
                Intent i = new Intent( BookInfoActivity.this, MainActivity.class );
                startActivity( i );
            }
            return super.onOptionsItemSelected( item );
        }
    }
