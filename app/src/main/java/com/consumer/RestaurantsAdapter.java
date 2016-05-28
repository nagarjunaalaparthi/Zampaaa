package com.consumer;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by naresh poola on 15/4/16.
 */
public class RestaurantsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>  implements AppConstants{

    private Activity mContext;
    private ArrayList<Restaurant> restaurantsList = new ArrayList<>();

    public RestaurantsAdapter(Activity context) {
        this.mContext = context;
    }


    public class RestaurantViewHolder extends RecyclerView.ViewHolder {
        public TextView mTitle;
        public TextView mAddr;
        public ImageView mImage;
        public CardView mCardView;

        public RestaurantViewHolder(View view) {
            super(view);
            mTitle = (TextView) view.findViewById(R.id.rest_name);
            mAddr = (TextView) view.findViewById(R.id.rest_addr);
            mImage = (ImageView) view.findViewById(R.id.rest_image);
            mCardView = (CardView) view.findViewById(R.id.movie_card_view);
            mCardView.setOnClickListener(onClickListener);
            mCardView.setTag(R.id.rest_name, mImage);
        }
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Restaurant item = (Restaurant) v.getTag();
            String transitionName = mContext.getString(R.string.transition_string);
            ImageView viewStart = null;
            if(v!=null) {
                viewStart = (ImageView) v.getTag(R.id.rest_name);
            }
            Intent intent = new Intent(mContext, RestaurantDetailsActivity.class);
            intent.putExtra(IBundleParams.RESULT_OBJ, (Serializable) item);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                ActivityOptionsCompat options =

                        ActivityOptionsCompat.makeSceneTransitionAnimation(mContext,
                                viewStart,
                                transitionName
                        );
                ActivityCompat.startActivity(mContext, intent, options.toBundle());
            } else {
                mContext.startActivity(intent);
            }
        }
    };


    public void updateData(ArrayList<Restaurant> restaurantsList) {
        this.restaurantsList = restaurantsList;
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
        return 0;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_movie_item, parent, false);
        return new RestaurantViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final RestaurantViewHolder restaurantViewHolder = (RestaurantViewHolder) holder;
        Restaurant restaurant = restaurantsList.get(position);
        restaurantViewHolder.mTitle.setText(restaurant.getName());
        restaurantViewHolder.mAddr.setText(restaurant.getAddress());
        restaurantViewHolder.mImage.setImageResource(restaurant.getImageDrawbale());
        restaurantViewHolder.mCardView.setTag(restaurant);
    }

    @Override
    public int getItemCount() {
        return restaurantsList.size();
    }

}
