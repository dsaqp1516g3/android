package edu.upc.eetac.dsa.okupainfo.client.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Guillermo on 24/05/2016.
 */
public class Comments_CasalsCollection {
    private List<Link> links;
    private long newestTimestamp;
    private long oldestTimestamp;
    private List<Comments_Casals> comments_casalsCollection = new ArrayList<>();

    public List<Link> getLinks() {
        return links;
    }

    public void setLinks(List<Link> links) {
        this.links = links;
    }

    public long getNewestTimestamp() {
        return newestTimestamp;
    }

    public void setNewestTimestamp(long newestTimestamp) {
        this.newestTimestamp = newestTimestamp;
    }

    public long getOldestTimestamp() {
        return oldestTimestamp;
    }

    public void setOldestTimestamp(long oldestTimestamp) {
        this.oldestTimestamp = oldestTimestamp;
    }

    public List<Comments_Casals> getComments_casalsCollection() {
        return comments_casalsCollection;
    }

    public void setComments_casalsCollection(List<Comments_Casals> comments_casalsCollection) {
        this.comments_casalsCollection = comments_casalsCollection;
    }
}
