package it.unibas.biscione.informaticHUB.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import it.unibas.biscione.informaticHUB.activity.SectionActivity;
import it.unibas.biscione.informaticHUB.control.AsyncTaskDownload;
import it.unibas.biscione.informaticHUB.control.AsyncTaskForum;
import it.unibas.biscione.informaticHUB.control.AsyncTaskPage;
import it.unibas.biscione.informaticHUB.Application;
import it.unibas.biscione.informaticHUB.Costants;
import it.unibas.biscione.informaticHUB.R;
import it.unibas.biscione.informaticHUB.model.Content;
import it.unibas.biscione.informaticHUB.model.Module;

public class ModuleAdapter extends RecyclerView.Adapter<ModuleAdapter.ModuleViewHolder> {

    private LayoutInflater inflater;
    private ArrayList<Module> modules;

    public ModuleAdapter(Context context, ArrayList<Module> modules) {
        inflater = LayoutInflater.from(context);
        this.modules = modules;
    }

    @Override
    public ModuleAdapter.ModuleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_module, parent, false);
        ModuleAdapter.ModuleViewHolder moduleViewHolder = new ModuleAdapter.ModuleViewHolder(view);
        return moduleViewHolder;
    }

    @Override
    public void onBindViewHolder(ModuleAdapter.ModuleViewHolder holder, final int position) {
        holder.name.setText(modules.get(position).getName());
        final Module m = modules.get(position);
        if (m.getModname().trim().equals("forum")){
            holder.image.setImageResource(R.drawable.ic_forum);
        }
        if (m.getModname().trim().equals("page")){
            if (m.getContents().get(0).getMimetype() != null){
                holder.image.setImageResource(R.drawable.ic_file);
            } else {
                holder.image.setImageResource(R.drawable.ic_page);
            }
        }
        if (m.getModname().trim().equals("reservation")) {
            holder.image.setImageResource(R.drawable.ic_plugin);
        }
        if (m.getModname().trim().equals("folder")) {
            holder.image.setImageResource(R.drawable.ic_folder);
        }
        if (m.getModname().trim().equals("resource")){
            Content c = m.getContents().get(0);
            if (c.getType().trim().equals("file")) {
                if (m.getContents().get(0).getMimetype().contains("pdf")) {
                    holder.image.setImageResource(R.drawable.ic_pdf);
                } else {
                    holder.image.setImageResource(R.drawable.ic_file);
                }
            } else {
                holder.image.setImageResource(R.drawable.ic_url);
            }
        }
        if (m.getModname().trim().equals("url")){
            holder.image.setImageResource(R.drawable.ic_url);
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Application.getInstance().getModel().putBean(Costants.MODULE, m);
                    if (m.getModname().trim().equals("forum")) {
                        AsyncTaskForum asyncTaskForum = new AsyncTaskForum(true);
                        asyncTaskForum.execute(m.getId());
                    }
                    if (m.getModname().trim().equals("page")) {
                        if (m.getContents().get(0).getMimetype() == null) {
                            AsyncTaskPage asyncTaskPage = new AsyncTaskPage();
                            asyncTaskPage.execute(m.getContents().get(0).getFileurl());
                        } else {
                            AsyncTaskDownload asyncTaskDownload = new AsyncTaskDownload(false);
                            asyncTaskDownload.execute(m.getContents().get(0));
                        }
                    }
                    if (m.getModname().trim().equals("reservation")) {
                        SectionActivity sectionActivity = (SectionActivity) Application.getInstance().getCurrentActivity();
                        sectionActivity.showMessageDialog(sectionActivity.getString(R.string.warning), sectionActivity.getString(R.string.reservation_not_available));
                    }
                    if (m.getModname().trim().equals("folder")){
                        SectionActivity sectionActivity = (SectionActivity) Application.getInstance().getCurrentActivity();
                        sectionActivity.showContentActivity();
                    }
                    if (m.getModname().trim().equals("resource")){
                        Content c = m.getContents().get(0);
                        if (c.getType().trim().equals("file")){
                            SectionActivity sectionActivity = (SectionActivity) Application.getInstance().getCurrentActivity();
                            AsyncTaskDownload asyncTaskDownload = new AsyncTaskDownload(false);
                            asyncTaskDownload.execute(c);
                        } else {
                            SectionActivity sectionActivity = (SectionActivity) Application.getInstance().getCurrentActivity();
                            Intent extLink = new Intent("android.intent.action.VIEW", Uri.parse(c.getFileurl()));
                            sectionActivity.startActivity(extLink);
                        }
                    }
                    if (m.getModname().trim().equals("url")){
                        Content c = m.getContents().get(0);
                        SectionActivity sectionActivity = (SectionActivity) Application.getInstance().getCurrentActivity();
                        Intent extLink = new Intent("android.intent.action.VIEW", Uri.parse(c.getFileurl()));
                        sectionActivity.startActivity(extLink);
                    }
                }
            });
    }

    @Override
    public int getItemCount() {
        return modules.size();
    }

    public class ModuleViewHolder extends RecyclerView.ViewHolder{

        TextView name;
        ImageView image;


        public ModuleViewHolder(View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.txtModule);
            image = itemView.findViewById(R.id.imgModule);
        }
    }
}
