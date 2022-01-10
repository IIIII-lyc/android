package com.fzu.campusnews;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterMainActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText etNewAccount;
    private EditText etNewPassword1;
    private EditText etNewPassword2;
    private Button newRegister;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_layout);

        initView();
    }
    private void initView()
    {
        etNewAccount=(EditText) findViewById(R.id.newAccount);
        etNewPassword1=(EditText) findViewById(R.id.newPassword1);
        etNewPassword2=(EditText) findViewById(R.id.newPassword2);
        newRegister=(Button) findViewById(R.id.newRegister);
        newRegister.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.newRegister:
                String newAccount=etNewAccount.getText().toString();
                String newPassword1=etNewPassword1.getText().toString();
                String newPassword2=etNewPassword2.getText().toString();
                if(newPassword1.equals(newPassword2))
                {
                    Intent intent=new Intent(RegisterMainActivity.this,LoginMainActivity.class);
                    startActivity(intent);
                    Toast.makeText(RegisterMainActivity.this,"register successfully",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(RegisterMainActivity.this,"重复输入错误",Toast.LENGTH_SHORT);
                }
                finish();
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + view.getId());
        }
    }
}