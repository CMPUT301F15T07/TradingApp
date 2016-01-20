package com.sherpasteven.sscte.Models;


import java.util.List;

/**
 * Hits class from CMPUT 301 Lab
 * This class is a collections of SearchHits
 * that is returned after an http request.
 * @param <T> Type of data source of the hit (i.e Friend)
 */
public class Hits<T> {
    private int total;
    private float max_score;
    private List<SearchHit<T>> hits;

    public Hits() {}

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public float getMax_score() {
        return max_score;
    }

    public void setMax_score(float max_score) {
        this.max_score = max_score;
    }

    public List<SearchHit<T>> getHits() {
        return hits;
    }

    public void setHits(List<SearchHit<T>> hits) {
        this.hits = hits;
    }

    @Override
    public String toString() {
        return "Hits [total=" + total + ", max_score=" + max_score + ", hits="
                + hits + "]";
    }
}
