package com.mohmedhassan.cleaningapp.Companies;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import org.json.JSONException;

public class RecyclerTouchListener  implements RecyclerView.OnItemTouchListener {

    Context context;
    private ClickListener clickListener;
    private RecyclerView recyclerView;
    private GestureDetector gestureDetector;

    public RecyclerTouchListener(Context context, final ClickListener clickListener, final RecyclerView recyclerView) {
        this.context = context;
        this.clickListener = clickListener;
        this.recyclerView = recyclerView;

        gestureDetector = new GestureDetector(context,new GestureDetector.SimpleOnGestureListener(){

            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                return true;
            }

            @Override
            public void onLongPress(MotionEvent e) {
                View child = recyclerView.findChildViewUnder(e.getX(), e.getY());
                if (child != null && clickListener != null) {
                    clickListener.onLongClick(child, recyclerView.getChildPosition(child));
                }
            }
        });
    }



    @Override
    public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {

        View child = rv.findChildViewUnder(e.getX(), e.getY());
        if (child != null && clickListener != null && gestureDetector.onTouchEvent(e)) {

            try {
                clickListener.onClick(child, rv.getChildPosition(child));
            } catch (JSONException e1) {
                e1.printStackTrace();
            }

        }
        return false;
    }

    @Override
    public void onTouchEvent(RecyclerView rv, MotionEvent e) {
    }

    @Override
    public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

    }

    public interface ClickListener {
        void onClick(View view, int position) throws JSONException;

        void onLongClick(View view, int position);
    }
}
