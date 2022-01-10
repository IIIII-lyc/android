package com.fzu.campusnews;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.HashMap;
import java.util.Map;

public class SPsave {
    //将数据保存到data.xml
    public static boolean saveUserInfo(Context context,String account,String password)
    {
        SharedPreferences sp=context.getSharedPreferences("data",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor= sp.edit();
        editor.putString("account",account);
        editor.putString("password",password);
        editor.commit();
        return true;
    }
    //从data。xml获取数据
    public static Map<String,String> getUserInfo(Context context)
    {
        SharedPreferences sp=context.getSharedPreferences("data",Context.MODE_PRIVATE);
        String account=sp.getString("account",null);
        String password=sp.getString("password",null);
        Map<String,String> userMap=new HashMap<String, String>() ;
        userMap.put("account",account);
        userMap.put("password",password);
        return userMap;
    }


}
