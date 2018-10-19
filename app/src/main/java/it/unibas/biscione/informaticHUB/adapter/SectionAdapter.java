package it.unibas.biscione.informaticHUB.adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import it.unibas.biscione.informaticHUB.Application;
import it.unibas.biscione.informaticHUB.R;
import it.unibas.biscione.informaticHUB.model.Module;
import it.unibas.biscione.informaticHUB.model.Section;

public class SectionAdapter extends RecyclerView.Adapter<SectionAdapter.SectionViewHolder> {

    private LayoutInflater inflater;
    private ArrayList<Section> sections;

    public SectionAdapter(Context context, ArrayList<Section> sections) {
        inflater = LayoutInflater.from(context);
        this.sections = sections;

    }

    @Override
    public SectionViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_section, parent, false);
        SectionViewHolder sectionViewHolder = new SectionViewHolder(view);
        return sectionViewHolder;
    }

    @Override
    public void onBindViewHolder(SectionViewHolder holder, final int position) {
        holder.name.setText(sections.get(position).getName());
        ArrayList<Module> modules = sections.get(position).getModules();
        holder.rvModule.setAdapter(new ModuleAdapter(Application.getInstance().getCurrentActivity(), modules));
        holder.rvModule.setNestedScrollingEnabled(false);
    }

    @Override
    public int getItemCount() {
        return sections.size();
    }

    public class SectionViewHolder extends RecyclerView.ViewHolder{

        TextView name;
        RecyclerView rvModule;


        public SectionViewHolder(View itemView) {
            super(itemView);
            rvModule = itemView.findViewById(R.id.rvModule);
            LinearLayoutManager llmModule = new LinearLayoutManager(Application.getInstance().getCurrentActivity());
            rvModule.setLayoutManager(llmModule);
            name = itemView.findViewById(R.id.txtSection);
        }
    }
}
