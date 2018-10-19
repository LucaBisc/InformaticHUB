package it.unibas.biscione.informaticHUB.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import it.unibas.biscione.informaticHUB.control.AsyncTaskDownload;
import it.unibas.biscione.informaticHUB.R;
import it.unibas.biscione.informaticHUB.model.Content;

public class ContentAdapter extends RecyclerView.Adapter<ContentAdapter.ContentViewHolder> {

    private static final String TAG = ContentAdapter.class.getName();

    private LayoutInflater inflater;
    private ArrayList<Content> contents;

    public ContentAdapter(Context context, ArrayList<Content> contents) {
        inflater = LayoutInflater.from(context);
        this.contents = contents;
    }

    @Override
    public ContentAdapter.ContentViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_content, parent, false);
        ContentAdapter.ContentViewHolder contentViewHolder = new ContentAdapter.ContentViewHolder(view);
        return contentViewHolder;
    }

    @Override
    public void onBindViewHolder(ContentAdapter.ContentViewHolder holder, final int position) {
        final Content c = contents.get(position);
        holder.name.setText(contents.get(position).getFilename());
        if (c.getMimetype().contains("pdf")){
            holder.image.setImageResource(R.drawable.ic_pdf);
        } else if (c.getMimetype().contains("zip")){
            holder.image.setImageResource(R.drawable.ic_zip);
        } else {
            holder.image.setImageResource(R.drawable.ic_file);
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println(c.getMimetype());
                Log.d(TAG ,c.getFilepath() + "" + c.getFileurl() + c.getType());
                AsyncTaskDownload asyncTaskDownload = new AsyncTaskDownload(true);
                asyncTaskDownload.execute(c);
            }
        });
    }

    @Override
    public int getItemCount() {
        return contents.size();
    }

    public class ContentViewHolder extends RecyclerView.ViewHolder{

        TextView name;
        ImageView image;


        public ContentViewHolder(View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.txtContent);
            image = itemView.findViewById(R.id.imageContent);
        }
    }
}
