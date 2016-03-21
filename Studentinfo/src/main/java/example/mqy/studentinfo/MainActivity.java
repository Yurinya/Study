package example.mqy.studentinfo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.List;

import example.mqy.studentinfo.domain.Student;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    Button button;
    LinearLayout ll;
    EditText editText;
    RadioGroup rdg;
    RadioButton male;
    RadioButton female;
    StudentDbOpenHelper dbOpenHelper;
    private StudentDao sdao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dbOpenHelper = new StudentDbOpenHelper(this,"information.db",null,1);
        dbOpenHelper.getWritableDatabase();
        button = (Button) findViewById(R.id.button);
        editText = (EditText) findViewById(R.id.editText);
        rdg = (RadioGroup) findViewById(R.id.rdg);
        ll = (LinearLayout) findViewById(R.id.ll);
        male = (RadioButton) findViewById(R.id.male);
        female = (RadioButton) findViewById(R.id.female);
        button.setOnClickListener(this);
        sdao = new StudentDao(this);
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
    public void refreshView(){
        ll.removeAllViews();
        List<Student> students = sdao.getAll();
        for (Student student : students) {
            TextView tv = new TextView(this);
            tv.setText(student.toString());
            ll.addView(tv);


        }
    }
}
