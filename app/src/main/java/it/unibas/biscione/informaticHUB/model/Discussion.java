package it.unibas.biscione.informaticHUB.model;

import java.util.ArrayList;

public class Discussion {

    private ArrayList<Post> discussions;

    private int discussionid = 999;

    public ArrayList<Post> getDiscussions() {
        return discussions;
    }

    public void setDiscussions(ArrayList<Post> discussions) {
        this.discussions = discussions;
    }

    public int getDiscussionid() {
        return discussionid;
    }
}
