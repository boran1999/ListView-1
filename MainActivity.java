package com.example.customadapterdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.AssetManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class MainActivity extends AppCompatActivity {
    UserListAdapter adapter;
    ListView listView;
    ArrayList<User> users = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = findViewById(R.id.list);
        AssetManager asst = getResources().getAssets();
        try(InputStreamReader istream = new InputStreamReader(asst.open("js.json"))){
            BufferedReader br = new BufferedReader(istream);
            String js = "", tmp = "";
            while((tmp = br.readLine()) != null){
                js += tmp;
            }
            Gson proc = new Gson();
            String mass[] = js.split("\"user\":");
            int kl=0;
            for (String c: mass){
                if(kl>0) {
                    String nc = c.trim();
                    nc = nc.substring(0, nc.length() - 1);
                    User t = proc.fromJson(nc, User.class);
                    User user = new User(t.name.toString(), t.phoneNumber.toString(), t.sex.toString());
                    users.add(user);
                }
                kl+=1;
            }

        }
        catch (IOException ex) {}
        adapter = new UserListAdapter(this, users);
        listView.setAdapter(adapter);
    }
    public void onClick(View view)
    {
        if (view.getId() == R.id.b1) {
            Collections.sort(users, new Comparator<User>() {
                public int compare(User o1, User o2) {
                    return o1.name.toString().compareTo(o2.name.toString());
                }
            });
            adapter.notifyDataSetChanged();
        }
        if (view.getId() == R.id.b2) {
            Collections.sort(users, new Comparator<User>() {
                public int compare(User o1, User o2) {
                    return o1.phoneNumber.toString().compareTo(o2.phoneNumber.toString());
                }
            });
            adapter.notifyDataSetChanged();
        }
        if (view.getId() == R.id.b3) {
            Collections.sort(users, new Comparator<User>() {
                public int compare(User o1, User o2) {
                    return o1.sex.toString().compareTo(o2.sex.toString());
                }
            });
            adapter.notifyDataSetChanged();
        }
    }
}
