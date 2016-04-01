package example.mqy.listview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {
    public ListView lv;
    private String[] data = {"a","b","c","d","e","f","g","i","j","k","l","m","n","o","p","q","r","s"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,data);
        lv = (ListView) findViewById(R.id.lv);
        lv.setAdapter(adapter);

    }

}

