package com.mohmedhassan.cleaningapp.Companies;

import android.content.Context;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mohmedhassan.cleaningapp.R;

import java.util.ArrayList;

public class Companies_item_OrederAdapter extends RecyclerView.Adapter<Companies_item_OrederAdapter.CustomViewHolder> {

    private Context context;
    private ArrayList<DataModel_itemOreder> dataModels;
    private ArrayList<DataModelCompanies_item> dataModelCompanies_items;
    private int selectedPosition = -1;

    public Companies_item_OrederAdapter(Context context, ArrayList<DataModel_itemOreder> dataModels) {
        this.context = context;
        this.dataModels = dataModels;
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder {


        public LinearLayout linearLayout;
        public TextView Name;


        public CustomViewHolder(View view) {
            super(view);

            linearLayout = (LinearLayout) view.findViewById(R.id.linearlayout_item);
            Name = (TextView) view.findViewById(R.id.tv_item_recycleview);


        }
    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_list, viewGroup, false);
        return new Companies_item_OrederAdapter.CustomViewHolder(view);

    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder myViewHolder, int position) {

        DataModel_itemOreder model = dataModels.get(position);

         myViewHolder.Name.setText(model.getName());

      myViewHolder.Name.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {



          }
      });
      /*  if (selectedPosition == position) {
            myViewHolder.itemView.setSelected(true); //using selector drawable



         myViewHolder.linearLayout.setBackground(ContextCompat.getDrawable(myViewHolder.linearLayout.getContext(),R.drawable.deign_recycleview));
           // myViewHolder.linearLayout.setBackgroundColor(ContextCompat.getColor(myViewHolder.linearLayout.getContext(),R.color.colorPrimary));
        } else {
            myViewHolder.itemView.setSelected(false);
            myViewHolder.linearLayout.setBackground(ContextCompat.getDrawable(myViewHolder.linearLayout.getContext(),R.drawable.image_item));

            //   myViewHolder.linearLayout.setBackgroundColor(Color.BLACK);
           // myViewHolder.linearLayout.setBackgroundColor(ContextCompat.getColor(myViewHolder.linearLayout.getContext(),R.color.colorAccent));
        }

        myViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (selectedPosition >= 0)
                    notifyItemChanged(selectedPosition);
                selectedPosition = myViewHolder.getAdapterPosition();
                notifyItemChanged(selectedPosition);
            }
        });
*/

    }

    @Override
    public int getItemCount() {
        return dataModels.size();
    }
}
