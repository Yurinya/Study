package example.mqy.studentinfo;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.List;

import example.mqy.studentinfo.domain.Student;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    Button button;
    ListView lv;
    EditText editText;
    RadioGroup rdg;
    RadioButton male;
    RadioButton female;
    StudentDbOpenHelper dbOpenHelper;
    AnimationDrawable animationDrawable;
    private StudentDao sdao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageView iv = (ImageView) findViewById(R.id.iv);
        iv.setBackgroundResource(R.drawable.logo);
        animationDrawable = (AnimationDrawable) iv.getBackground();
        animationDrawable.start();


        dbOpenHelper = new StudentDbOpenHelper(this,"information.db",null,1);
        dbOpenHelper.getWritableDatabase();
        button = (Button) findViewById(R.id.button);
        editText = (EditText) findViewById(R.id.editText);
        rdg = (RadioGroup) findViewById(R.id.rdg);
        male = (RadioButton) findViewById(R.id.male);
        female = (RadioButton) findViewById(R.id.female);
        button.setOnClickListener(this);
        sdao = new StudentDao(this);
        lv = (ListView) findViewById(R.id.lv);
        refreshView();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button :
                String name = editText.getText().toString().trim();
                String sex = "male";
                int id = rdg.getCheckedRadioButtonId();
                if(id == R.id.male){
                    sex = "male";
                }
                else {
                    sex= "female";
                }
                Student st = new Student();
                st.setName(name);
                st.setSex(sex);
                sdao.add(st);
                refreshView();




        }

    }
    List<Student> students;
    private void refreshView() {


        students = sdao.getAll();


        lv.setAdapter(new Myadapter());

    }

    class Myadapter extends BaseAdapter{

        @Override
        public int getCount() {
            return students.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            Student st = students.get(position);
            View view = View.inflate(MainActivity.this, R.layout.item, null);
//            View view = LayoutInflater.from(MainActivity.this).inflate(R.layout.item,null);
            TextView textView = (TextView) view.findViewById(R.id.tvinlv);
            textView.setText(st.toString());
            return view;
        }
    }

}
