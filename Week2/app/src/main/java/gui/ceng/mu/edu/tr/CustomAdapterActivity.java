package gui.ceng.mu.edu.tr;


import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class CustomAdapterActivity extends AppCompatActivity {

    final List<Animal> animals=new ArrayList<Animal>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_custom_adapter);
        animals.add(new Animal("Ant", R.mipmap.other));
        animals.add(new Animal("Bear", R.mipmap.other));
        animals.add(new Animal("Bird", R.mipmap.other));
        animals.add(new Animal("Cat", R.mipmap.cat));
        animals.add(new Animal("Dog", R.mipmap.other));

        final ListView listView= (ListView) findViewById(R.id.listview);
        AnimalAdapter adapter= new AnimalAdapter(this, animals);
        listView.setAdapter(adapter);

    }
}
