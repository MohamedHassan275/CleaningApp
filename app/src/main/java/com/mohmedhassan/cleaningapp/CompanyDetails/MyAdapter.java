package com.mohmedhassan.cleaningapp.CompanyDetails;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.mohmedhassan.cleaningapp.R;

import java.util.ArrayList;

public class MyAdapter extends ArrayAdapter<CompanyDetails_item_Model> {

    ArrayList<CompanyDetails_item_Model> listdata;
    Context context;
    int resource;


    public MyAdapter(@NonNull Context context, int resource,@NonNull ArrayList<CompanyDetails_item_Model> companyDetails_item_models) {
        super(context, resource, companyDetails_item_models);
        this.listdata = companyDetails_item_models;
        this.context = context;
        this.resource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView==null){
            LayoutInflater layoutInflater=(LayoutInflater)getContext().getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            convertView=layoutInflater.inflate(R.layout.company_details_recycleview_item,null,true);
        }
         CompanyDetails_item_Model companyDetails_item_model =getItem(position);
       /* ImageView imageViewCompany = (ImageView) convertView.findViewById(R.id.image_company_details_recycle);
        TextView textViewCarWish = (TextView) convertView.findViewById(R.id.tv_name_companyDetails_recycle);
        TextView textViewInternal_cleaning = (TextView) convertView.findViewById(R.id.tv_carWish_companyDetails_recycle);
        TextView textViewMinimum_Order = (TextView) convertView.findViewById(R.id.tv_packages_companyDetails_recycle);
        TextView textViewMinimum_Order_Number = (TextView) convertView.findViewById(R.id.tv_offers_companyDetails_recycle);
*/

        return convertView;
    }

}
