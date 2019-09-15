package com.example.jersey;

import android.content.Context;

import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by TayyabIsmail on 01-Jun-17.
 */




    public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.CustomViewHolder> implements Filterable {
        private List<PersonData> feedItemList;
        private Context mContext;
    List<PersonData> mFilteredList;
    private LayoutInflater mInflater;
        public MyRecyclerViewAdapter(Context context, List<PersonData> feedItemList) {
            mInflater = LayoutInflater.from(context);
            this.feedItemList = feedItemList;
            this.mFilteredList = feedItemList;
            this.mContext = context;

        }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recycler_row, viewGroup,false);
        CustomViewHolder viewHolder = new CustomViewHolder(view);

        //View itemView = LayoutInflater.from(viewGroup.getContext())
          //      .inflate(R.layout.recycler_row, viewGroup, false);

        //return new CustomViewHolder(itemView);
        return viewHolder;
    }

        @Override
        public void onBindViewHolder(CustomViewHolder customViewHolder, int i) {
            PersonData feedItem = feedItemList.get(i);

            customViewHolder.txtname.setText(feedItem.getName());
            customViewHolder.txtid.setText((feedItem.getid()));
            //Render image using Picasso library

            //Setting text view title
            //customViewHolder.textView.setText(Html.fromHtml(feedItem.getTitle()));
        }

        @Override
        public int getItemCount() {
            return (null != mFilteredList ? mFilteredList.size() : 0);
        }

        class CustomViewHolder extends RecyclerView.ViewHolder {

            protected TextView txtid;
            protected TextView txtname;

            public CustomViewHolder(View view) {
                super(view);
                this.txtid = (TextView) view.findViewById(R.id.txtid);
                this.txtname = (TextView) view.findViewById(R.id.txtname);


            }
        }

    @Override
    public Filter getFilter() {

        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {

                String charString = charSequence.toString();

                if (charString.isEmpty()) {

                    mFilteredList = feedItemList;
                } else {

                    List<PersonData> filteredList = new ArrayList<>();

                    for (PersonData  item : feedItemList) {

                        if (item.getName().toLowerCase().contains(charString) ){//|| androidVersion.getid().contains(charString) || androidVersion.getVer().toLowerCase().contains(charString)) {

                            filteredList.add(item);
                        }
                    }

                    mFilteredList = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = mFilteredList;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                //mFilteredList = (List<UsersRows>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }
    }
