package tw.fili.hey20171030;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {

    SQLiteDatabase mDB;

    List<Sample> mList;
    ArrayAdapter<Sample> mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SampleDBHelper mDBH;
        mDBH = new SampleDBHelper(this);
        mDB = mDBH.getWritableDatabase();

        mList = new ArrayList<>();
//        mList.add( new Sample("鉛筆",10,"相關說明") );
//        mList.add( new Sample("課本",100,"其他沒甚麼好寫的*2") );

        Cursor rr = mDB.query("sample",null,null,null,null,null,null,null);
        while ( rr.moveToNext() ){

            String s1, s3;
            int i2;
            s1 = rr.getString(0);
            i2 = rr.getInt(1);
            s3 = rr.getString(2);

//            Sample a = new Sample(s1,i2,s3);
            Sample a = new Sample();
            a.setName( s1 );
            a.setPrice( i2 );
            a.setDetail( s3 );
            mList.add( a );
//            mList.add( new Sample(s1,i2,s3) );
        }
        rr.close();


        mAdapter = new MyArrayAdapter(this, R.layout.mylist_sample_item_layout, mList );

        ListView lv;
        lv = (ListView)findViewById(R.id.cc1);
        lv.setAdapter( mAdapter );
        lv.requestFocus();

        Button bt = (Button)findViewById(R.id.bb1);
        bt.setOnClickListener(mAdd);
    }



    private class MyArrayAdapter extends ArrayAdapter<Sample> {

        MyArrayAdapter(Context context, int resource, List<Sample> objects) {
            super(context, resource, objects);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            if( convertView==null ){
                convertView = LayoutInflater
                        .from(getContext())
                        .inflate( R.layout.mylist_sample_item_layout, parent, false );
            }

            Sample x = mList.get( position );

            TextView tv1, tv2, tv3;
            tv1 = convertView.findViewById(R.id.dd1);
            tv2 = convertView.findViewById(R.id.dd2);
            tv3 = convertView.findViewById(R.id.dd3);
            tv1.setText( x.getName() );
            tv2.setText( String.valueOf(x.getPrice()) );
            tv3.setText( x.getDetail() );
            return convertView;
        }
    }



    private View.OnClickListener mAdd = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            EditText et1, et2, et3;
            et1 = (EditText)findViewById(R.id.aa1);
            et2 = (EditText)findViewById(R.id.aa2);
            et3 = (EditText)findViewById(R.id.aa3);

            String s1, s2, s3;
            s1 = et1.getText().toString();
            s2 = et2.getText().toString();
            s3 = et3.getText().toString();

            int i2;
            i2 = Integer.parseInt(s2);

            ContentValues cv = new ContentValues();
            cv.put("name",s1);
            cv.put("price",i2);
            cv.put("detail",s3);
            mDB.insert("sample",null,cv);

            Sample a = new Sample(s1,i2,s3);
            mAdapter.add(a);

            
        }
    };
}
