package com.example.recyclerview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;

import java.util.Collections;
import java.util.LinkedList;

public class MainActivity extends AppCompatActivity {

    private final LinkedList<String> mWordList = new LinkedList<>();
    private final LinkedList<String> mWordList2 = new LinkedList<>();
    private RecyclerView mRecyclerView;
    private WordListAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        int gridColumnCount =
                getResources().getInteger(R.integer.grid_column_count);

        // Put initial data into the word list.
        // Put initial data into the word list.
        mWordList.addLast("Salad Caesar" );
        mWordList2.addLast("Salad recipe Caesar is elementary. To prepare a refreshing and at the same time," +
                " a wholesome snack is not difficult even for inexperienced housewife. " +
                "We offer you to make Caesar by the classical recipe." );
        mWordList.addLast("Recipe of dumplings");
        mWordList2.addLast("Homemade dumplings – the Acme of perfection. " +
                "They do not go to any comparison with store-bought convenience foods. " +
                "Attach to the process of making dumplings for the whole family.");
        mWordList.addLast("Classic pizza recipe");
        mWordList2.addLast("Pizza I love to eat everything, and that's a recipe for some remains a mystery." +
                "A classic recipe will help to understand all the intricacies of the process.\n" +
                "According to the Italians, the main secret of any pizza is a yeast dough that is pulled with hands " +
                "to the size of the shape, not rolled. Also of great importance while roasting, the faster the dish is " +
                "cooked, the better it tastes. The most traditional recipe is a classic Italian sausage pizza.");
        mWordList.addLast("Fried chicken");
        mWordList2.addLast("Recipe is the secret mix of ingredients that fast food restaurant chain KFC" +
                " uses to produce fried chicken. Sanders' Original Recipe of \"11 herbs and spices\" is one of the most famous " +
                "trade secrets in the catering industry.");
        mWordList.addLast("A classic kebab recipe");
        mWordList2.addLast("Beginners in cooking barbecue can use a classic recipe that will allow you to understand the main basics and secrets of this process.\n" +
                "Flavorful and tender, the skewers will not leave anyone indifferent, and friendly gatherings on the nature can not be compared with anything." +
                " Recipes for this wonderful dishes there is a huge number, but there is the traditional option. Usually it is prepared from meat of mutton or pork. " +
                "A simple procedure, it would seem, does not promise amazing results, but in the skilled hands miracles happen.");


        // Create recycler view.
        mRecyclerView = findViewById(R.id.recyclerView);
        // Create an adapter and supply the data to be displayed.
        mAdapter = new WordListAdapter(this, mWordList, mWordList2);
        // Connect the adapter with the recycler view.
        mRecyclerView.setAdapter(mAdapter);
        // Give the recycler view a default layout manager.
        mRecyclerView.setLayoutManager(new
                GridLayoutManager(this, gridColumnCount));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this,
                DividerItemDecoration.VERTICAL));

        int swipeDirs;
        if(gridColumnCount > 1){
            swipeDirs = 0;
        } else {
            swipeDirs = ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT;
        }

        ItemTouchHelper helper = new ItemTouchHelper(new ItemTouchHelper
                .SimpleCallback(
                ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT |
                        ItemTouchHelper.DOWN | ItemTouchHelper.UP,
                swipeDirs) {

            @Override
            public boolean onMove(RecyclerView recyclerView,
                                  RecyclerView.ViewHolder viewHolder,
                                  RecyclerView.ViewHolder target) {
                // Get the from and to positions.
                int from = viewHolder.getAdapterPosition();
                int to = target.getAdapterPosition();

                // Swap the items and notify the adapter.
                Collections.swap(mWordList, from, to);
                mAdapter.notifyItemMoved(from, to);
                return true;
            }


            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder,
                                 int direction) {
                // Remove the item from the dataset.
                mWordList.remove(viewHolder.getAdapterPosition());
                // Notify the adapter.
                mAdapter.notifyItemRemoved(viewHolder.getAdapterPosition());
            }
        });

    }
}