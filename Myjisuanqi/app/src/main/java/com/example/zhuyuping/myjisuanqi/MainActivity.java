package com.example.zhuyuping.myjisuanqi;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    private StringBuffer strBuf=new StringBuffer();

    @BindView(R.id.edit_text)
    EditText show_Text;
    @BindView(R.id.btn_cos)
    Button btnCos;
    @BindView(R.id.btn_tan)
    Button btnTan;
    @BindView(R.id.btn_sin)
    Button btnSin;
    @BindView(R.id.btn_clear)
    Button btnClear;
    @BindView(R.id.btn_seven)
    Button btnSeven;
    @BindView(R.id.btn_eight)
    Button btnEight;
    @BindView(R.id.btn_night)
    Button btnNight;
    @BindView(R.id.btn_tuige)
    Button btnTuige;
    @BindView(R.id.btn_four)
    Button btnFour;
    @BindView(R.id.btn_five)
    Button btnFive;
    @BindView(R.id.btn_six)
    Button btnSix;
    @BindView(R.id.btn_one)
    Button btnOne;
    @BindView(R.id.btn_two)
    Button btnTwo;
    @BindView(R.id.btn_three)
    Button btnThree;
    @BindView(R.id.btn_equal)
    Button btnEqual;
    @BindView(R.id.btn_zero)
    Button btnZero;
    @BindView(R.id.btn_point)
    Button btnPoint;
    @BindView(R.id.btn_rigitbr)
    Button btnRigitbr;
    @BindView(R.id.btn_mul)
    Button btnMul;
    @BindView(R.id.btn_eli)
    Button btnEli;
    @BindView(R.id.btn_sum)
    Button btnSum;
    @BindView(R.id.btn_jian)
    Button btnJian;
    @BindView(R.id.btn_leftbr)
    Button btnLeftbr;

    @BindView(R.id.btn_pow)
    Button btnPow;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        show_Text.setMovementMethod(ScrollingMovementMethod.getInstance());
    }

    @OnClick({R.id.btn_pow,R.id.edit_text, R.id.btn_cos, R.id.btn_tan, R.id.btn_sin, R.id.btn_clear, R.id.btn_seven, R.id.btn_eight, R.id.btn_night, R.id.btn_tuige, R.id.btn_four, R.id.btn_five, R.id.btn_six, R.id.btn_one, R.id.btn_two, R.id.btn_three, R.id.btn_equal, R.id.btn_zero, R.id.btn_point, R.id.btn_rigitbr, R.id.btn_mul, R.id.btn_eli, R.id.btn_sum, R.id.btn_jian, R.id.btn_leftbr})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_cos:
                setAndShowEdit("c");
                break;
            case R.id.btn_tan:
                setAndShowEdit("t");
                break;
            case R.id.btn_sin:
                setAndShowEdit("s");
                break;
            case R.id.btn_clear:
                strBuf.delete(0,strBuf.length());
                setAndShowEdit("");
                break;
            case R.id.btn_seven://7
                setAndShowEdit("7");
                break;
            case R.id.btn_eight://8
                setAndShowEdit("8");
                break;
            case R.id.btn_night://9
                setAndShowEdit("9");
                break;
            case R.id.btn_tuige:
                if(strBuf.length()!=0)
                strBuf.deleteCharAt(strBuf.length()-1);//退格
                setAndShowEdit("");
                break;
            case R.id.btn_four:
                setAndShowEdit("4");
                break;
            case R.id.btn_five:
                setAndShowEdit("5");
                break;
            case R.id.btn_six:
                setAndShowEdit("6");
                break;
            case R.id.btn_one:
                setAndShowEdit("1");
                break;
            case R.id.btn_two:
                setAndShowEdit("2");
                break;
            case R.id.btn_three:
                setAndShowEdit("3");
                break;
            case R.id.btn_equal:
                ExpresEavl();//表达式求值
                break;
            case R.id.btn_zero:
                setAndShowEdit("0");
                break;
            case R.id.btn_point:
                setAndShowEdit(".");
                break;
            case R.id.btn_rigitbr:
                setAndShowEdit(")");
                break;
            case R.id.btn_mul:
                setAndShowEdit("*");
                break;
            case R.id.btn_eli:
                strBuf.append("/");
                break;
            case R.id.btn_sum:
                setAndShowEdit("+");
                break;
            case R.id.btn_jian:
                setAndShowEdit("-");
                break;
            case R.id.btn_leftbr:
                setAndShowEdit("(");
                break;
            case R.id.btn_pow:
                setAndShowEdit("^");
                break;
        }
    }

    /**
     * 求值函数
     */
    private void ExpresEavl() {
        try {
            String result = Double.toString(Calculator.toReversePolishNotation(strBuf.toString()));
            strBuf.append("=");
            setAndShowEdit(result);
            strBuf.delete(0, strBuf.length());
        }catch (Exception e){


            Toast toast=Toast.makeText(this,e.toString() , Toast.LENGTH_LONG);//创建Toast
            toast.show();//显示消息
        }
    }

    /**
     *添加并显示
     */
    public void setAndShowEdit(String number){
        strBuf.append(number);
        show_Text.setText(strBuf.toString());
        show_Text.setSelection(show_Text.length());

    }

}
