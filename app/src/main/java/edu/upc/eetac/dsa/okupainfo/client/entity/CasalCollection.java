package edu.upc.eetac.dsa.okupainfo.client.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Guillermo on 21/05/2016.
 */
public class CasalCollection {
    private List<Link> links;
    private List<Casal> casals = new ArrayList<>();

    public List<Link> getLinks() {
        return links;
    }

    public void setLinks(List<Link> links) {
        this.links = links;
    }

    public List<Casal> getCasals() {
        return casals;
    }

    public void setCasals(List<Casal> casals) {
        this.casals = casals;
    }
}
