package com.fzu.campusnews;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ViewPager1 extends AppCompatActivity {
    private RecyclerView rv_News;
    private MyAdapter adapter;

    private String [] titles={"防控疫情，戴好口罩","食堂门口漏油！","爱护草坪，带走垃圾","井盖碎裂"};
    private String [] contents={"防控疫情，戴好口罩/防控疫情，戴好口罩/防控疫情，戴好口罩/防控疫情，戴好口罩/防控疫情，戴好口罩",
            "食堂门口漏油！/食堂门口漏油！/食堂门口漏油！/食堂门口漏油！/食堂门口漏油！/食堂门口漏油！/食堂门口漏油！/食堂门口漏油！",
            "爱护草坪，带走垃圾/爱护草坪，带走垃圾/爱护草坪，带走垃圾/爱护草坪，带走垃圾/爱护草坪，带走垃圾/爱护草坪，带走垃圾",
            "井盖碎裂/井盖碎裂/井盖碎裂/井盖碎裂/井盖碎裂/井盖碎裂/井盖碎裂/井盖碎裂/井盖碎裂/井盖碎裂/井盖碎裂/井盖碎裂/井盖碎裂"
    };
    private int []icons={R.drawable.list_image,R.drawable.list_image,R.drawable.list_image,R.drawable.list_image};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.viewpage1_layout);
        rv_News=(RecyclerView) findViewById(R.id.rvNews);
        rv_News.setLayoutManager(new LinearLayoutManager(this));
        adapter=new MyAdapter();
        rv_News.setAdapter(adapter);
    }

    //RecyclerViewView适配器
    class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder>
    {

        @NonNull
        @Override
        public MyAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            MyViewHolder holder=new MyViewHolder(LayoutInflater.from(ViewPager1.this).inflate(R.layout.list_item,parent,false));
            return holder;
        }

        @Override
        public void onBindViewHolder(@NonNull MyAdapter.MyViewHolder holder, int position) {
            holder.title.setText(titles[position]);
            holder.content.setText(contents[position]);
            @SuppressLint("SimpleDateFormat") SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            Date date=new Date();
            holder.dateTime.setText(dateFormat.format(date));
            holder.image.setBackgroundResource(icons[position]);
        }

        @Override
        public int getItemCount() {
            return titles.length;
        }

        class  MyViewHolder extends RecyclerView.ViewHolder
        {
            TextView title,content,dateTime;
            ImageView image;
            public MyViewHolder(@NonNull View itemView) {
                super(itemView);
                title=(TextView) itemView.findViewById(R.id.tu_Title);
                content=(TextView) itemView.findViewById(R.id.tu_Content);
                dateTime=(TextView) itemView.findViewById(R.id.tu_DateTime);
                image=(ImageView) itemView.findViewById(R.id.iv_Image);
            }
        }

    }

}