package com.example.weatherappttiktok.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.weatherappttiktok.MainActivity;
import com.example.weatherappttiktok.Model.Forecast.Forecast;
import com.example.weatherappttiktok.R;
import com.example.weatherappttiktok.Utils.MyUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ForecastAdapter extends RecyclerView.Adapter<ForecastAdapter.ForecastViewHolder> implements Filterable {

    private List<Forecast> twoHoursForecastList;
    private List<Forecast> twoHoursForecastListAll;
    private MyClickListener myClickListener;
    private Context mContext;

//    public void setData(List<Forecast> list){
//        this.twoHoursForecastList = list;
//    }

    public ForecastAdapter(Context context, MyClickListener myClickListener, List<Forecast> forecasts){
        this.twoHoursForecastList = forecasts;
        this.mContext = context;
        this.myClickListener = myClickListener;
        this.twoHoursForecastListAll = new ArrayList<>(twoHoursForecastList);

    }

    @NonNull
    @Override
    public ForecastViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.two_hour_forecast, parent,
                false);

        return new ForecastViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ForecastViewHolder holder, int position) {
        Forecast twoHoursForecast = twoHoursForecastList.get(position);

        if (twoHoursForecast == null){
            return;
        }

        String forecast = twoHoursForecast.getForecast();

        holder.area.setText(twoHoursForecast.getArea());
        holder.weatherIcon.setImageResource(new MyUtils().getImg(forecast));
        holder.weather.setText(twoHoursForecast.getForecast());

        holder.itemView.setOnClickListener(view -> {
            myClickListener.onClickGetViewPos(position, twoHoursForecastList);
        });
    }

    @Override
    public int getItemCount() {
        if (twoHoursForecastList != null){
            return twoHoursForecastList.size();
        }
        return 0;
    }

    @Override
    public Filter getFilter() {
        return filter;
    }

    Filter filter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            List<Forecast> filteredForecast = new ArrayList<>();
            if (charSequence.toString().isEmpty()){
                filteredForecast.addAll(twoHoursForecastListAll);
            }else {
                for (Forecast forecast : twoHoursForecastListAll){
                    if (forecast.getArea().toLowerCase().contains(charSequence.toString().toLowerCase())){
                        filteredForecast.add(forecast);
                    }
                }
            }

            FilterResults filterResults = new FilterResults();
            filterResults.values = filteredForecast;
            return filterResults;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            twoHoursForecastList.clear();
            twoHoursForecastList.addAll((Collection<? extends Forecast>) filterResults.values);
            notifyDataSetChanged();
        }
    };


    public class ForecastViewHolder extends RecyclerView.ViewHolder{

        private TextView area;
        private ImageView weatherIcon;
        private TextView weather;

        public ForecastViewHolder(@NonNull View itemView) {
            super(itemView);

             area = itemView.findViewById(R.id.area);
             weatherIcon = itemView.findViewById(R.id.weatherIcon);
             weather = itemView.findViewById(R.id.weather);
        }

    }


}
