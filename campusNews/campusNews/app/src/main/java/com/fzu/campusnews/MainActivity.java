package com.fzu.campusnews;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    private String [] titles={"防控疫情，戴好口罩","食堂门口漏油！","爱护草坪，带走垃圾","井盖碎裂"};
    private String [] contexts={"防控疫情，戴好口罩/防控疫情，戴好口罩/防控疫情，戴好口罩/防控疫情，戴好口罩/防控疫情，戴好口罩",
            "食堂门口漏油！/食堂门口漏油！/食堂门口漏油！/食堂门口漏油！/食堂门口漏油！/食堂门口漏油！/食堂门口漏油！/食堂门口漏油！",
            "爱护草坪，带走垃圾/爱护草坪，带走垃圾/爱护草坪，带走垃圾/爱护草坪，带走垃圾/爱护草坪，带走垃圾/爱护草坪，带走垃圾",
            "井盖碎裂/井盖碎裂/井盖碎裂/井盖碎裂/井盖碎裂/井盖碎裂/井盖碎裂/井盖碎裂/井盖碎裂/井盖碎裂/井盖碎裂/井盖碎裂/井盖碎裂"
    };
    private int []icons={R.drawable.list_image,R.drawable.list_image,R.drawable.list_image,R.drawable.list_image};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MyBaseAdapter adapter=new MyBaseAdapter();
        ListView listView_News=(ListView) findViewById(R.id.lvNews);
        listView_News.setAdapter(adapter);
    }

    //listView适配器
    class MyBaseAdapter extends BaseAdapter
    {
        @Override
        public int getCount() {
            return titles.length;
        }

        @Override
        public Object getItem(int i) {
            return titles[i];
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            ViewHolder holder=new ViewHolder();;
            if(view == null)
            {
                view=View.inflate(MainActivity.this,R.layout.list_item,null);
                holder.title=(TextView) view.findViewById(R.id.tu_Title);
                holder.context=(TextView) view.findViewById(R.id.tu_Content);
                holder.dateTime=(TextView) view.findViewById(R.id.tu_DateTime);
                holder.image=(ImageView) view.findViewById(R.id.iv_Image);
                view.setTag(holder);
            }
            else
            {
                holder=(ViewHolder) view.getTag();
            }
            holder.title.setText(titles[i]);
            holder.context.setText(contexts[i]);
            Date date=new Date();
            SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            holder.dateTime.setText(dateFormat.format(date));
            holder.image.setBackgroundResource(icons[i]);
            return view;
        }
    }
    class ViewHolder
    {
        TextView title,context,dateTime;
        ImageView image;
    }

}