package edu.upc.eetac.dsa.okupainfo.client.entity;

import java.util.List;

/**
 * Created by Guillermo on 24/05/2016.
 */
public class Comments_Casals {
    private List<Link> links;
    private String id;
    private String creatorid;
    private String casalid;
    private String Content;
    private long creationTimestamp;
    private long lastModified;

    public List<Link> getLinks() {
        return links;
    }

    public void setLinks(List<Link> links) {
        this.links = links;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCreatorid() {
        return creatorid;
    }

    public void setCreatorid(String creatorid) {
        this.creatorid = creatorid;
    }

    public String getCasalid() {
        return casalid;
    }

    public void setCasalid(String casalid) {
        this.casalid = casalid;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }

    public long getCreationTimestamp() {
        return creationTimestamp;
    }

    public void setCreationTimestamp(long creationTimestamp) {
        this.creationTimestamp = creationTimestamp;
    }

    public long getLastModified() {
        return lastModified;
    }

    public void setLastModified(long lastModified) {
        this.lastModified = lastModified;
    }
}
