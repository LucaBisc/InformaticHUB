package it.unibas.biscione.informaticHUB.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import it.unibas.biscione.informaticHUB.model.Exam;
import it.unibas.biscione.informaticHUB.R;

public class ExamAdapter extends RecyclerView.Adapter<ExamAdapter.ExamViewHolder> {

    private LayoutInflater inflater;
    private ArrayList<Exam> exams;

    public ExamAdapter (Context context, ArrayList<Exam> exams) {
        this.exams = exams;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public ExamAdapter.ExamViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_exam, parent, false);
        ExamAdapter.ExamViewHolder examViewHolder = new ExamAdapter.ExamViewHolder(view);
        return examViewHolder;
    }


    public void onBindViewHolder(ExamAdapter.ExamViewHolder holder, final int position) {
        holder.name.setText(exams.get(position).getName());
        holder.cfuDate.setText(exams.get(position).getStringCFUDate());
        holder.vote.setText(exams.get(position).getVote());
    }

    @Override
    public int getItemCount() {
        return exams.size();
    }


    public class ExamViewHolder extends RecyclerView.ViewHolder{

        TextView name;
        TextView cfuDate;
        TextView vote;


        public ExamViewHolder(View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.txtName);
            cfuDate = itemView.findViewById(R.id.txtDateCFU);
            vote = itemView.findViewById(R.id.txtVote);
        }
    }
}
