package com.example.recyclerview;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.LinkedList;

public class WordListAdapter extends
        RecyclerView.Adapter<WordListAdapter.WordViewHolder> {

    private final LinkedList<String> mWordList2;
    private final LinkedList<String> mWordList;

    private final LayoutInflater mInflater;

    class WordViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener {
        public final TextView wordItemView;
        public final TextView wordItemView2;

        final WordListAdapter mAdapter;



        public WordViewHolder(View itemView, WordListAdapter adapter) {
            super(itemView);
            wordItemView = itemView.findViewById(R.id.text1);
            wordItemView2 = itemView.findViewById(R.id.text_main);

            this.mAdapter = adapter;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {

        }
    }

    public WordListAdapter(Context context, LinkedList<String> mWordList, LinkedList<String> wordList) {
        mInflater = LayoutInflater.from(context);
        this.mWordList = wordList;
        this.mWordList2 = mWordList;
    }


    @Override
    public WordListAdapter.WordViewHolder onCreateViewHolder(ViewGroup parent,
                                                             int viewType) {
        // Inflate an item view.
        View mItemView = mInflater.inflate(
                R.layout.wordlist_item, parent, false);
        return new WordViewHolder(mItemView, this);

    }


    @Override
    public void onBindViewHolder(WordListAdapter.WordViewHolder holder,
                                 final int position) {
        // Retrieve the data for that position.
        String mCurrent = mWordList.get(position);
        String mCurrent2 = mWordList2.get(position);

        // Add the data to the view holder.
        holder.wordItemView.setText(mCurrent);
        //Zagolovok
        holder.wordItemView2.setText(mCurrent2);

        final Context context = holder.wordItemView.getContext();
        ((View)holder.wordItemView.getParent()).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent;
                switch (position) {
                    case 0:
                        intent = new Intent(context, ActivityZero.class);
                        break;
                    case 1:
                        intent = new Intent(context, ActivityOne.class);
                        break;
                    case 2:
                        intent = new Intent(context, ActivityTwo.class);
                        break;
                    case 3:
                        intent = new Intent(context, ActivityThree.class);
                        break;
                    case 4:
                        intent = new Intent(context, ActivityFour.class);
                        break;
                    default:
                        intent = new Intent(context, Activity4Empty.class);
                        break;
                }
                context.startActivity(intent);
            }
        });
    }

    /**
     * Returns the total number of items in the data set held by the adapter.
     *
     * @return The total number of items in this adapter.
     */
    @Override
    public int getItemCount() {

        return mWordList.size();
    }
}