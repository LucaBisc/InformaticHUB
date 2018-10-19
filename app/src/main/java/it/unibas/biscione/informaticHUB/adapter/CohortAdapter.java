package it.unibas.biscione.informaticHUB.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import it.unibas.biscione.informaticHUB.activity.CohortActivity;
import it.unibas.biscione.informaticHUB.Application;
import it.unibas.biscione.informaticHUB.Costants;
import it.unibas.biscione.informaticHUB.R;
import it.unibas.biscione.informaticHUB.model.Cohort;

public class CohortAdapter extends RecyclerView.Adapter<CohortAdapter.CohortViewHolder> {


    private LayoutInflater inflater;
    private ArrayList<Cohort> cohorts;

    public CohortAdapter (Context context, ArrayList<Cohort> cohorts) {
        this.cohorts = cohorts;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public CohortAdapter.CohortViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_cohort, parent, false);
        CohortAdapter.CohortViewHolder categoryViewHolder = new CohortAdapter.CohortViewHolder(view);
        return categoryViewHolder;
    }


    public void onBindViewHolder(CohortAdapter.CohortViewHolder holder, final int position) {
        holder.name.setText(cohorts.get(position).getNome());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cohort c = cohorts.get(position);
                Application.getInstance().getModel().putBean(Costants.COHORT, c);
                ((CohortActivity) Application.getInstance().getCurrentActivity()).showTimetableActivity();
            }
        });
    }

    @Override
    public int getItemCount() {
        return cohorts.size();
    }


    public class CohortViewHolder extends RecyclerView.ViewHolder{

        TextView name;


        public CohortViewHolder(View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.txtCohort);
        }
    }
}
