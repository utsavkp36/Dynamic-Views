package in.bitcode.sumdiffapp1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private TextView txtResult;
    private Button btnAddView,btnDeleteView;
    private LinearLayout layoutEditText;
    private Button btnSum;
    private Button btnDiff;
    private ArrayList<EditText> editTextArrayList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextArrayList=new ArrayList<>();

        initViews();
        setOnClickListeners();
    }
    private void initViews(){
        txtResult=findViewById(R.id.result);
        btnAddView=findViewById(R.id.add);
        btnDeleteView=findViewById(R.id.delete);
        layoutEditText=findViewById(R.id.layoutEditText);
        btnSum=findViewById(R.id.btnSum);
        btnDiff=findViewById(R.id.btnDiff);
    }

    private void setOnClickListeners(){

        BtnAddDeleteViewSetOnClickListener btnAddDeleteViewSetOnClickListener=
                new BtnAddDeleteViewSetOnClickListener();

        btnAddView.setOnClickListener(btnAddDeleteViewSetOnClickListener);
        btnDeleteView.setOnClickListener(btnAddDeleteViewSetOnClickListener);

        btnSum.setOnClickListener(new BtnSumSetOnClickListener());

        btnDiff.setOnClickListener(new BtnDiffSetOnClickListener());
    }

    private class BtnDiffSetOnClickListener implements View.OnClickListener{
        @Override
        public void onClick(View view) {
            double diff=0.0;
            for(int i=0;i<editTextArrayList.size();i++){
                String value=editTextArrayList.get(i).getText().toString();
                double num=Double.parseDouble(value);
                if(i==0){
                    diff+=num;
                }
                else {
                    diff -= num;
                }
            }
            txtResult.setText("Result: "+diff);
        }
    }

    private class BtnSumSetOnClickListener implements View.OnClickListener{
        @Override
        public void onClick(View view) {
            double sum=0.0;
            for(EditText editText:editTextArrayList){
                String value=editText.getText().toString();
                sum+=Double.parseDouble(value);
            }
            txtResult.setText("Result: "+sum);
        }
    }

    private class BtnAddDeleteViewSetOnClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {

            switch (view.getId()) {
                case R.id.add:
                    EditText editText=new EditText(MainActivity.this);
                    layoutEditText.addView(editText);
                    editTextArrayList.add(editText);
                    break;
                case R.id.delete:
                    if (editTextArrayList.size()>0){
                        EditText editText1=editTextArrayList.get(editTextArrayList.size()-1);
                        layoutEditText.removeView(editText1);
                        editTextArrayList.remove(editText1);
                    }
                    break;
            }
        }

    }

}