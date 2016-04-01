package example.mqy.messagelayout;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private ListView lv;
    private EditText editText;
    private Button send;
    private Button receive;
    private MessageAdapter adapter;
    private List<Message> list = new ArrayList<Message>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        editText = (EditText) findViewById(R.id.editText);
        send = (Button) findViewById(R.id.send);
        receive = (Button) findViewById(R.id.receive);
        lv = (ListView) findViewById(R.id.lv);
        adapter = new MessageAdapter(this,R.layout.item,list);
        lv.setAdapter(adapter);
        send.setOnClickListener(this);
        receive.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.send :
                String content = editText.getText().toString();
                Message message = new Message(content,Message.SEND);
                list.add(message);
                adapter.notifyDataSetChanged();
                lv.setSelection(list.size());
                editText.setText("");
                break;
            case  R.id.receive :
                String content2 = editText.getText().toString();
                Message message2 = new Message(content2,Message.RECEIVE);
                list.add(message2);
                adapter.notifyDataSetChanged();
                lv.setSelection(list.size());
                editText.setText("");

                break;
        }
    }
}
