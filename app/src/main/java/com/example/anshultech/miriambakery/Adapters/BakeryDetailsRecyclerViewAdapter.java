package com.example.anshultech.miriambakery.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.anshultech.miriambakery.Bean.BakeryIngridentsListBean;
import com.example.anshultech.miriambakery.Bean.BakeryStepsListBean;
import com.example.anshultech.miriambakery.R;

import java.util.ArrayList;

public class BakeryDetailsRecyclerViewAdapter extends RecyclerView.Adapter<BakeryDetailsRecyclerViewAdapter.BakeryDetailsHolder> {

    private Context mContext;
    private ArrayList<BakeryStepsListBean> mBakeryStepsListBeans;
    private ArrayList<BakeryIngridentsListBean> mBakeryIngridentsListBeans;
    private BakeryDetailsIngredientsOnClickListener mBakeryDetailsIngredientsOnClickListener;
    private BakeryDetailsStepsOnClickListener mBakeryDetailsStepsOnClickListener;
    private String mListType;


    public BakeryDetailsRecyclerViewAdapter(Context lContext, ArrayList<BakeryIngridentsListBean> bakeryIngridentsListBeans,
                                            BakeryDetailsIngredientsOnClickListener bakeryDetailsIngredientsOnClickListener, String listType) {
        this.mContext = lContext;
        this.mBakeryIngridentsListBeans = bakeryIngridentsListBeans;
        this.mBakeryDetailsIngredientsOnClickListener = bakeryDetailsIngredientsOnClickListener;
        this.mListType = listType;
    }

    public BakeryDetailsRecyclerViewAdapter(Context lContext, ArrayList<BakeryStepsListBean> bakeryStepsListBeans,
                                            BakeryDetailsStepsOnClickListener bakeryDetailsStepsOnClickListener, String listType) {
        this.mContext = lContext;
        this.mBakeryStepsListBeans = bakeryStepsListBeans;
        this.mBakeryDetailsStepsOnClickListener = bakeryDetailsStepsOnClickListener;
        this.mListType = listType;
    }


    @NonNull
    @Override
    public BakeryDetailsHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.recipiedetailslistlayout, viewGroup, false);
        return new BakeryDetailsHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BakeryDetailsHolder bakeryDetailsHolder, int position) {

        String recipeDesciption = new String();

        if (mListType.equalsIgnoreCase("Steps")) {
            if (mBakeryStepsListBeans.size() - 1 == position) {
                bakeryDetailsHolder.recipieDetailsHorizontalBar.setVisibility(View.GONE);
            }
            recipeDesciption = mBakeryStepsListBeans.get(position).getShortDescription();
        } else {
            if (mBakeryIngridentsListBeans.size() - 1 == position) {
                bakeryDetailsHolder.recipieDetailsHorizontalBar.setVisibility(View.GONE);
            }
            recipeDesciption = mBakeryIngridentsListBeans.get(position).getIngredient();
        }


        bakeryDetailsHolder.recipieDetailsDesciptionTextView.setText(recipeDesciption);


    }

    @Override
    public int getItemCount() {
        if (mListType.equalsIgnoreCase("Steps")) {
            return mBakeryStepsListBeans.size();
        } else {
            return mBakeryIngridentsListBeans.size();
        }
    }

    public class BakeryDetailsHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView recipieDetailsDesciptionTextView;
        View recipieDetailsHorizontalBar;

        public BakeryDetailsHolder(@NonNull View itemView) {
            super(itemView);
            recipieDetailsDesciptionTextView = (TextView) itemView.findViewById(R.id.recipieDetailsDesciptionTextView);
            recipieDetailsHorizontalBar = (View) itemView.findViewById(R.id.recipieDetailsHorizontalBar);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (mListType.equalsIgnoreCase("Steps")) {
                mBakeryDetailsStepsOnClickListener.onBakeryDetailsStepsCliCkListenerr(getAdapterPosition(), mBakeryStepsListBeans);
            }
            else {
                mBakeryDetailsIngredientsOnClickListener.onBakeryDetailsIngredientsCliCkListenerr(getAdapterPosition(), mBakeryIngridentsListBeans);
            }


        }
    }

    public interface BakeryDetailsIngredientsOnClickListener {
        public void onBakeryDetailsIngredientsCliCkListenerr(int position, ArrayList<BakeryIngridentsListBean> bakeryIngridentsListBeans);
    }

    public interface BakeryDetailsStepsOnClickListener {
        public void onBakeryDetailsStepsCliCkListenerr(int position, ArrayList<BakeryStepsListBean> bakeryStepsListBeans);

    }

//    public void updateIndridentsAdapterView(ArrayList<BakeryIngridentsListBean> bakeryIngridentsListBeans){
//        mBakeryIngridentsListBeans=bakeryIngridentsListBeans;
//        notifyDataSetChanged();
//    }


}
