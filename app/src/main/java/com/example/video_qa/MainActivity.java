package com.example.video_qa;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView recl;
    myAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recl = (RecyclerView)findViewById(R.id.rcl_view);
        //recl.setLayoutManager(new LinearLayoutManager(this));

        adapter = new myAdapter(dataqueue(),getApplicationContext());
        recl.setAdapter(adapter);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,2);
        recl.setLayoutManager(gridLayoutManager);
    }

    public ArrayList<Model> dataqueue(){
        ArrayList<Model> holder = new ArrayList<>();
        Model obj1  = new Model();
        obj1.setHeader("Java");
        obj1.setDesc("Space for Java");
        obj1.setImgName(R.drawable.java);
        holder.add(obj1);

        Model obj2  = new Model();
        obj2.setHeader("JavaScript");
        obj2.setDesc("Space for JavaScript");
        obj2.setImgName(R.drawable.js);
        holder.add(obj2);

        Model obj3  = new Model();
        obj3.setHeader("C++");
        obj3.setDesc("Space for C++");
        obj3.setImgName(R.drawable.cpp);
        holder.add(obj3);

        Model obj4  = new Model();
        obj4.setHeader("Python");
        obj4.setDesc("Space for Python");
        obj4.setImgName(R.drawable.python);
        holder.add(obj4);

        Model obj5  = new Model();
        obj5.setHeader("Competitive Programing");
        obj5.setDesc("Space for CP");
        obj5.setImgName(R.drawable.programing);
        holder.add(obj5);

        Model obj6  = new Model();
        obj6.setHeader("Data Science");
        obj6.setDesc("Space for Data Science");
        obj6.setImgName(R.drawable.statistics);
        holder.add(obj6);

        Model obj7  = new Model();
        obj7.setHeader("Blockchain");
        obj7.setDesc("Space for Blockchain");
        obj7.setImgName(R.drawable.blockchain);
        holder.add(obj7);

        Model obj8  = new Model();
        obj8.setHeader("DevOps");
        obj8.setDesc("Space for Devops");
        obj8.setImgName(R.drawable.agile);
        holder.add(obj8);

        Model obj9  = new Model();
        obj9.setHeader("Web Dev");
        obj9.setDesc("Space for Web Development");
        obj9.setImgName(R.drawable.web);
        holder.add(obj9);

        Model obj10  = new Model();
        obj10.setHeader("App Dev");
        obj10.setDesc("Space for App Development");
        obj10.setImgName(R.drawable.app);
        holder.add(obj10);

        Model obj11  = new Model();
        obj11.setHeader("Big Data");
        obj11.setDesc("Space for Big Data");
        obj11.setImgName(R.drawable.big_data);
        holder.add(obj11);
        return holder;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.main_menu,menu);
        MenuItem item = menu.findItem(R.id.search_item);
        SearchView searchView = (SearchView)item.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }
}