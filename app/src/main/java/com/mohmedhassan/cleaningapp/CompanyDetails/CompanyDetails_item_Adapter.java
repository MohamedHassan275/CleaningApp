package com.mohmedhassan.cleaningapp.CompanyDetails;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mohmedhassan.cleaningapp.R;

import java.util.ArrayList;

public class CompanyDetails_item_Adapter extends RecyclerView.Adapter<CompanyDetails_item_Adapter.CustomViewHolder> {

    Context context;
    ArrayList<CompanyDetails_item_Model> companyDetails_item_models;

    public CompanyDetails_item_Adapter(Context context, ArrayList<CompanyDetails_item_Model> companyDetails_item_models) {
        this.context = context;
        this.companyDetails_item_models = companyDetails_item_models;
    }



    public class CustomViewHolder extends RecyclerView.ViewHolder {


        public ImageView imageCompany;
        public TextView  CarWish, Clean, Price,Price_Number;

        public CustomViewHolder(View view) {
            super(view);

            imageCompany = (ImageView) view.findViewById(R.id.image_company_details_recycle);
            CarWish = (TextView) view.findViewById(R.id.tv_carWish_companyDetails_recycle);
            Clean = (TextView) view.findViewById(R.id.tv_Clean_companyDetails_recycle);
            Price = (TextView) view.findViewById(R.id.tv_item_Price_comapnyDetails);
            Price_Number = (TextView) view.findViewById(R.id.tv_price_Number_companyDetails_recycle);


        }
    }

    @NonNull
    @Override
    public CompanyDetails_item_Adapter.CustomViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.company_details_recycleview_item, viewGroup, false);
        return new CompanyDetails_item_Adapter.CustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CompanyDetails_item_Adapter.CustomViewHolder myViewHolder, int position) {

        CompanyDetails_item_Model model = companyDetails_item_models.get(position);

        myViewHolder.imageCompany.setImageResource(model.getImageCompany());
        myViewHolder.CarWish.setText(model.getCarwish());
        myViewHolder.Clean.setText(model.getClean());
        myViewHolder.Price.setText(model.getPrice());
        myViewHolder.Price_Number.setText(model.getPrice_Number());

    }

    @Override
    public int getItemCount() {
        return companyDetails_item_models.size();
    }
}
