package edu.upc.eetac.dsa.okupainfo.client.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Guillermo on 24/05/2016.
 */
public class Comments_EventsCollection {
    private List<Link> links;
    private long newestTimestamp;
    private long oldestTimestamp;
    private List<Comments_Events> comments_eventsCollection = new ArrayList<>();

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

    public List<Comments_Events> getComments_eventsCollection() {
        return comments_eventsCollection;
    }

    public void setComments_eventsCollection(List<Comments_Events> comments_eventsCollection) {
        this.comments_eventsCollection = comments_eventsCollection;
    }
}
