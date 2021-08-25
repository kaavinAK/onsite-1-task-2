package com.example.task2b;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> {

  //  private String[] localDataSet;
    private box[] boxes;
   private  List<Integer> localDataSet;


    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {
        //private final TextView textView;
        private View views;

        public ViewHolder(View view) {
            super(view);
            // Define click listener for the ViewHolder's View
              views=view.findViewById(R.id.view);
           // textView = (TextView) view.findViewById(R.id.textView);
        }

        public View getViews() {
            return views;
        }
    }

    /**
     * Initialize the dataset of the Adapter.
     *
     * @param dataSet String[] containing the data to populate views to be used
     * by RecyclerView.
     */
    public CustomAdapter(List<Integer> dataSet,box[] boxeslocal) {
        localDataSet = dataSet;
        boxes=boxeslocal;
        for(int i=0;i<boxeslocal.length;i++)
        {
            boxes[i].color=dataSet.get(i);
        }
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view, which defines the UI of the list item
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.customadapter, viewGroup, false);

        return new ViewHolder(view);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
      //  boxes[position]=new box(localDataSet.get(position),viewHolder.getAdapterPosition());
         boxes[position].positiony=viewHolder.getAdapterPosition();
        // boxes[position].color=localDataSet.get(position);
        viewHolder.getViews().setBackgroundColor(boxes[position].color);
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return localDataSet.size();
    }
}

