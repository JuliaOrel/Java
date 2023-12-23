package com.example.myapplication.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.models.State;

import java.util.ArrayList;
import java.util.List;



public class StateAdapter extends ArrayAdapter<State>
{
    Context context;
    private LayoutInflater inflater;
    private int layout;
    private List<State> states;



    // resource - можно писать так, что бы не передавать ресурс - а назначать его уже внутри адаптера
    // но это наружит единообразие адаптеров
    public StateAdapter(Context context, int resource, List<State> states) {
        super(context, resource, states);
        this.context = context;
        this.states = states;
        this.layout = resource;
        this.inflater = LayoutInflater.from(context);
    }

    // Но такой стиль нарушает едиообразие
    public StateAdapter(Context context, List<State> states) {
        super(context, R.layout.my_list_state_item, states);

        this.states = states;
        this.layout = R.layout.my_list_state_item;
        this.inflater = LayoutInflater.from(context);
    }

    public void loadDataFromServer() {
//        states.add(new State("England From Server", "London", R.drawable.en));
//        states.add(new State("Ukraine From Server", "Kyiv", R.drawable.ua));
//        ((Activity) context).runOnUiThread(new Runnable() {
//            @Override
//            public void run() {
//                notifyDataSetChanged();
//            }
//        });
    }


    public View getView(int position, View convertView, ViewGroup parent) {

        View view=inflater.inflate(this.layout, parent, false);

        ImageView flagView = view.findViewById(R.id.flag);
        TextView nameView = view.findViewById(R.id.name);
        TextView capitalView = view.findViewById(R.id.capital);

        State state = states.get(position);

        flagView.setImageResource(state.getFlagResource());
        nameView.setText(state.getName());
        capitalView.setText(state.getCapital());

        return view;
    }


}