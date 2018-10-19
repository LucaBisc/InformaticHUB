package it.unibas.biscione.informaticHUB.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import it.unibas.biscione.informaticHUB.R;
import it.unibas.biscione.informaticHUB.model.Post;

public class ForumAdapter extends RecyclerView.Adapter<ForumAdapter.ForumViewHolder> {

    private LayoutInflater inflater;
    private ArrayList<Post> posts;

    public ForumAdapter(Context context, ArrayList<Post> posts) {
        inflater = LayoutInflater.from(context);
        this.posts = posts;
    }

    @Override
    public ForumAdapter.ForumViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_post, parent, false);
        ForumAdapter.ForumViewHolder forumViewHolder = new ForumAdapter.ForumViewHolder(view);
        return forumViewHolder;
    }

    @Override
    public void onBindViewHolder(ForumAdapter.ForumViewHolder holder, final int position) {
        Post post = posts.get(position);
        holder.name.setText(post.getName());
        holder.author.setText(post.getUserfullname()); // + " - " + post.getCreationDate());
        if (post.getMessageformat() == 1){
            holder.message.setText(Html.fromHtml(post.getMessage()));
            holder.message.setMovementMethod(LinkMovementMethod.getInstance());
        } else {
            holder.message.setText(post.getMessage());
        }
    }

    @Override
    public int getItemCount() {
        return posts.size();
    }

    public class ForumViewHolder extends RecyclerView.ViewHolder{

        TextView name;
        TextView author;
        TextView message;


        public ForumViewHolder(View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.txtPost);
            author = itemView.findViewById(R.id.txtAuthor);
            message = itemView.findViewById(R.id.txtMessage);
        }
    }
}
