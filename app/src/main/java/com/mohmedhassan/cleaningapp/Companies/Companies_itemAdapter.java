package com.mohmedhassan.cleaningapp.Companies;

import android.content.Context;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import com.mohmedhassan.cleaningapp.R;

import java.util.ArrayList;

public class Companies_itemAdapter extends RecyclerView.Adapter<Companies_itemAdapter.CustomViewHolder> {

    private Context context;
    private ArrayList<DataModelCompanies_item> dataModelCompanyDeatils;


    public Companies_itemAdapter(Context context, ArrayList<DataModelCompanies_item> dataModels) {
        this.context = context;
        this.dataModelCompanyDeatils = dataModels;
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder {


        public LinearLayout linearLayout;
        public ImageView imageView;
        public TextView NameCompany, ReViews_number,Comments_Number, Minimum_Oreder_Number,Comment,Reviews,Minimum_Order,car_Gps,Delivery_Gps;
        public RatingBar RatingBarCompany;


        public CustomViewHolder(View view) {
            super(view);

            linearLayout = (LinearLayout) view.findViewById(R.id.linearlayout_companies_item);
            imageView = (ImageView) view.findViewById(R.id.image_item_company);
            NameCompany = (TextView) view.findViewById(R.id.tv_item_company_name);
            ReViews_number = (TextView) view.findViewById(R.id.tv_item_reviews_number_company);
            Comments_Number = (TextView) view.findViewById(R.id.tv_item_comments_number_company);
            Minimum_Oreder_Number = (TextView) view.findViewById(R.id.tv_item_minimum_number_order_comapny);
            Minimum_Order = (TextView) view.findViewById(R.id.tv_item_minimum_order_comapny);
            car_Gps = (TextView) view.findViewById(R.id.tv_item_carOrder_comapny);
            Delivery_Gps = (TextView) view.findViewById(R.id.tv_item_DeliveryGps_comapny);
            RatingBarCompany = (RatingBar) view.findViewById(R.id.ratingBar_company);
            Comment = (TextView) view.findViewById(R.id.tv_item_Comments_company);
            Reviews = (TextView) view.findViewById(R.id.tv_item_reviews_company);

        }
    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recycle_view_company_item, viewGroup, false);
        return new Companies_itemAdapter.CustomViewHolder(view);

    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder myViewHolder, int position) {

        DataModelCompanies_item model = dataModelCompanyDeatils.get(position);

      myViewHolder.NameCompany.setText(model.getCompanyName());
      myViewHolder.imageView.setImageResource(model.getPhoto());
      myViewHolder.ReViews_number.setText(model.getReviewsNumber());
      myViewHolder.Comments_Number.setText(model.getCommentsNumber());
      myViewHolder.Minimum_Oreder_Number.setText(model.getMunimum_Order_Number());
      myViewHolder.Comment.setText(model.getComment());
      myViewHolder.Reviews.setText(model.getReviews());
      myViewHolder.Minimum_Order.setText(model.getMinimum_Order());
      myViewHolder.car_Gps.setText(model.getCar_Gps());
      myViewHolder.Delivery_Gps.setText(model.getDelivery_Gps());
      myViewHolder.RatingBarCompany.setRating((float) model.getRating());

    }


    @Override
    public int getItemCount() {
        return dataModelCompanyDeatils.size();
    }
}
