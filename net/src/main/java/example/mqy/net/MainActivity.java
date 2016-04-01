package example.mqy.net;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static final int SUCCESS = 0;
    private static final int NETWORK_ERROR = 1;
    private static final int ERROR = 2;
    Button button;
    ImageView iv;
    EditText editText;
    EditText editText2;
    String path = "http://img2.imgtn.bdimg.com/it/u=1457437487,655486635&fm=11&gp=0.jpg";
    private Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SUCCESS:
                    Bitmap bitmap = (Bitmap) msg.obj;
                    iv.setImageBitmap(bitmap);
                    break;
                case NETWORK_ERROR:
                    Toast.makeText(MainActivity.this, "Connect error", Toast.LENGTH_SHORT).show();
                    break;
                case ERROR:
                    Toast.makeText(MainActivity.this, "No resource", Toast.LENGTH_SHORT).show();
                    break;
                default:
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = (Button) findViewById(R.id.button);
        editText = (EditText) findViewById(R.id.editText);
        iv = (ImageView) findViewById(R.id.iv);
        button.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button:

                Log.e("xyz","a");
                new Thread() {
                    public void run() {
                        Log.e("xyz","b");
                        try {
                            Log.e("xyz","b1");
                            URL url = new URL(path);
                            Log.e("xyz","b2");
                            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                            Log.e("xyz","b3");
                            connection.setRequestMethod("GET");
                            Log.e("xyz", "b4");
                            int code = connection.getResponseCode();
                            Log.e("xyz",code+"b5");
                            if (code == 200) {
                                InputStream in = connection.getInputStream();
                                Bitmap bitmap = BitmapFactory.decodeStream(in);
                                Message msg = Message.obtain();
                                msg.what = SUCCESS;
                                msg.obj = bitmap;
                                mHandler.sendMessage(msg);
                                in.close();
                            }
                            else {
                                Message msg = Message.obtain();
                                msg.what = NETWORK_ERROR;
                                mHandler.sendMessage(msg);
                            }
                        }
                        catch (Exception e) {
                            e.printStackTrace();
                            Message msg = Message.obtain();
                            msg.what = ERROR;
                            mHandler.sendMessage(msg);
                        }

                    }
                }.start();
            break;
        }
    }
}