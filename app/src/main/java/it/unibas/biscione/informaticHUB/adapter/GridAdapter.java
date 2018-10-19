package it.unibas.biscione.informaticHUB.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import it.unibas.biscione.informaticHUB.Application;
import it.unibas.biscione.informaticHUB.R;

public class GridAdapter extends BaseAdapter {

    private Context context;
    private String[] strings;

    public GridAdapter(Context context, ArrayList<String> strings){
        this.context = context;
        this.strings = strings.toArray(new String[0]);
    }

    @Override
    public int getCount() {
        return strings.length;
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    public View getView(int position, View convertView, ViewGroup parent) {

        View gridView;
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            gridView = new View(context);
            gridView = inflater.inflate(R.layout.item_grid, null);
        } else {
            gridView = convertView;
        }
        TextView textView = (TextView) gridView.findViewById(R.id.txtHour);
        textView.setText(strings[position]);

        if (position % 6 == 0 || position < 6) {
            textView.setBackground(Application.getInstance().getDrawable(R.drawable.squared_item_colored));
            textView.setTextColor(Color.WHITE);
        } else {
            textView.setBackground(Application.getInstance().getDrawable(R.drawable.squared_item));
            textView.setTextColor(Color.BLACK);
        }
        return gridView;
    }
}
