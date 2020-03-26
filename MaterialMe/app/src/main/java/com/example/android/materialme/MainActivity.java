

package com.example.android.materialme;

import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collections;


public class MainActivity extends AppCompatActivity {

    // Member variables.
    private RecyclerView mRecyclerView;
    private ArrayList<Sport> mSportsData;
    private SportsAdapter mAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize the RecyclerView.
        mRecyclerView = findViewById(R.id.recyclerView);

        // Set the Layout Manager.
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 1));

        // Initialize the ArrayList that will contain the data.
        mSportsData = new ArrayList<>();

        // Initialize the adapter and set it to the RecyclerView.
        mAdapter = new SportsAdapter(this, mSportsData);
        mRecyclerView.setAdapter(mAdapter);

        int dragDirs = ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT | ItemTouchHelper.DOWN | ItemTouchHelper.UP;
        int swapDirs = ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT;
        ItemTouchHelper helper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(dragDirs, swapDirs) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                int from = viewHolder.getAdapterPosition();
                int to   = target.getAdapterPosition();
                Collections.swap(mSportsData, from, to);
                mAdapter.notifyItemMoved(from, to);
                return true;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder,
                                 int direction) {
                mSportsData.remove(viewHolder.getAdapterPosition());
                mAdapter.notifyItemRemoved(viewHolder.getAdapterPosition());

            }
        });
        helper.attachToRecyclerView(mRecyclerView);
        // Get the data.
        initializeData();
    }

    /**
     * Initialize the sports data from resources.
     */
    private void initializeData() {
        // Get the resources from the XML file.
        String[] sportsList = getResources()
                .getStringArray(R.array.sports_titles);
        String[] sportsInfo = getResources()
                .getStringArray(R.array.sports_info);
        TypedArray sportsImageResources =
                getResources().obtainTypedArray(R.array.sports_images);
        // Clear the existing data (to avoid duplication).
        mSportsData.clear();

        // Create the ArrayList of Sports objects with titles and
        // information about each sport.
        for(int i=0;i<sportsList.length;i++){
            mSportsData.add(new Sport(sportsList[i],sportsInfo[i],
                    sportsImageResources.getResourceId(i,0)));
        }
        sportsImageResources.recycle();

        // Notify the adapter of the change.
        mAdapter.notifyDataSetChanged();
    }

    public void resetSports(View view) {
        initializeData();
    }
}
