package com.fzu.campusnews;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import java.time.chrono.MinguoChronology;
import java.util.Map;

public class LoginMainActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText etAccount;
    private EditText etPassword;
    private ImageButton ibLogin;
    private Button bRegister;
    private MyHelper myHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout);

        //初始化控件
        initView();

        //获取访问存储器权限
        ActivityCompat.requestPermissions(LoginMainActivity.this,new String[]{"android.permission.WRITE_EXTERNAL_STORAGE"},1);

        /*
        *SharedPerferences实现自动登录
        Map<String,String> userInfo=SPsave.getUserInfo(LoginMainActivity.this);
        if(userInfo.containsKey("account")&&userInfo.containsKey("password"))
        {
            etAccount.setText(userInfo.get("account"));
            etPassword.setText(userInfo.get("password"));
            onClick(ibLogin);
        }
        */

        //SQLite实现自动等录
        myHelper=new MyHelper(LoginMainActivity.this);
        ContentValues values=find();
        if(values!=null) {
            etAccount.setText((String) values.get("account"));
            etPassword.setText((String) values.get("password"));
            onClick(ibLogin);
        }
    }

    private void initView()
    {
        etAccount=(EditText) findViewById(R.id.account);
        etPassword=(EditText) findViewById(R.id.password);
        ibLogin=(ImageButton) findViewById(R.id.login);
        bRegister=(Button) findViewById(R.id.register);
        ibLogin.setOnClickListener(this);
        bRegister.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.login:
            String account=etAccount.getText().toString();
            String password=etPassword.getText().toString();
            if(account.equals("admin"))
            {
                if(password.equals("123456"))
                {
                    Intent intent=new Intent(LoginMainActivity.this, MainActivity3.class);
                    startActivity(intent);
                    Toast.makeText(LoginMainActivity.this,"login successfully",Toast.LENGTH_SHORT).show();
                    /*
                    *SharedPerferences实现
                    boolean isSaveSuccess =SPsave.saveUserInfo(LoginMainActivity.this,account,password);
                    if(isSaveSuccess)
                    {
                        Toast.makeText(LoginMainActivity.this,"sava successfully",Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        Toast.makeText(LoginMainActivity.this,"save unsuccessfully",Toast.LENGTH_SHORT).show();
                    }
                    */

                    //SQLite实现
                    Delete(account);
                    Insert(account,password);
                    finish();
                }
                else
                {
                    Toast.makeText(LoginMainActivity.this,"the password is error",Toast.LENGTH_SHORT).show();
                }
            }
            else
            {
                Toast.makeText(LoginMainActivity.this,"the account is no exist",Toast.LENGTH_SHORT).show();
            }
            break;
            case R.id.register:
                Intent intent=new Intent(LoginMainActivity.this, RegisterMainActivity.class);
                startActivity(intent);
                finish();
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + view.getId());
        }
    }

    //请求权限
    @Override
    public void onRequestPermissionsResult(int requestCode,String[] permissions,int []grantResults)
    {
        super.onRequestPermissionsResult(requestCode,permissions,grantResults);
        if(requestCode==1)
        {
            for(int i=0;i<permissions.length;i++)
            {
                if(permissions[i].equals("android.permission.WRITE_EXTERNAL_STORAGE")&&grantResults[i]== PackageManager.PERMISSION_GRANTED)
                {
                    Toast.makeText(this,"权限"+permissions[i]+"申请成功",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(this,"权限"+permissions[i]+"申请失败",Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    //对SQLite增删改查
    public void Insert(String account,String password)
    {
        SQLiteDatabase db=myHelper.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put("account",account);
        values.put("password",password);
        db.insert("information",null,values);
        db.close();
    }

    public int Delete(String account)
    {
        SQLiteDatabase db=myHelper.getWritableDatabase();
        int number=db.delete("information","account=?",new String []{account+" "});
        db.close();
        return number;
    }

    public int Update(String account,String password)
    {
        SQLiteDatabase db=myHelper.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put("password",password);
        int number=db.update("information",values,"account=?",new String[]{account});
        db.close();
        return number;
    }

    @SuppressLint("Range")
    public ContentValues find()
    {
        SQLiteDatabase db=myHelper.getReadableDatabase();
        Cursor cursor=db.query("information",null,null,null,null,null,null);
        ContentValues values=new ContentValues();
        if(cursor.getCount()>0)
        {
            cursor.moveToFirst();
            values.put("account",cursor.getString(cursor.getColumnIndex("account")));
            values.put("password",cursor.getString(cursor.getColumnIndex("password")));
        }
        cursor.close();
        db.close();
        return values;
    }


}