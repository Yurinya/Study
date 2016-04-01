package example.mqy.json;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private static final  int SUCCESS = 0;
    private EditText editText;
    private TextView tv;
    private Button button;
    private String city;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText = (EditText) findViewById(R.id.editText);
        tv = (TextView) findViewById(R.id.tv);
        button = (Button) findViewById(R.id.button);
        button.setOnClickListener(this);


    }
    private Handler mHandler = new Handler(){
        public void handleMessage(Message msg){
            switch (msg.what){
                case SUCCESS:
                    JSONObject obj = (JSONObject) msg.obj;
                    String info = obj.toString();
                    tv.setText(info);
            }

        }
    };

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button:
                city = editText.getText().toString().trim();
                if(TextUtils.isEmpty(city)){
                    Toast.makeText(this,"City couldn`t be empty",Toast.LENGTH_SHORT).show();
                    return;
                }
                new Thread(){
                    public void run(){
                        try {
                            String path = "http://wthrcdn.etouch.cn/weather_mini?city=" + URLEncoder.encode(city,"UTF-8");
                            URL url = new URL(path);
                            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                            connection.setConnectTimeout(5000);
                            connection.setRequestMethod("GET");
                            int code = connection.getResponseCode();
                            if (code == 200){
                                InputStream in = connection.getInputStream();
                                BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                                StringBuilder sb = new StringBuilder();
                                String line = null;
                                while((line = reader.readLine()) != null){
                                    sb.append(line);
                                }
                                String data = sb.toString();
                                Log.e("xyz",data);
                                JSONObject jsonObject = new JSONObject(data);
                                JSONObject dataobj = jsonObject.getJSONObject("data");
                                JSONArray forecast = dataobj.getJSONArray("forecast");
                                JSONObject day01 = forecast.getJSONObject(0);
                                Message message = Message.obtain();
                                message.what = SUCCESS;
                                message.obj = day01;
                                mHandler.sendMessage(message);




                            }





                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }.start();






                break;
        }
    }
}





