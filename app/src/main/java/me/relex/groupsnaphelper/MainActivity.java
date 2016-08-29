package me.relex.groupsnaphelper;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SnapHelper;

public class MainActivity extends AppCompatActivity {

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SampleAdapter adapter = new SampleAdapter();

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(
                new GridLayoutManager(getApplicationContext(), 1, LinearLayoutManager.HORIZONTAL,
                        false));
        recyclerView.setAdapter(adapter);
        SnapHelper snapHelper = new GroupSnapHelper(SampleAdapter.GROUP_COUNT);
        snapHelper.attachToRecyclerView(recyclerView);
        recyclerView.setOnFlingListener(snapHelper);

        adapter.setItems(40);
    }
}
