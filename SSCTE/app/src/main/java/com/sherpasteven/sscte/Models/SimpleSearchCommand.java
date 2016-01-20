package com.sherpasteven.sscte.Models;

/**
 * SimpleSearchCommand from CMPUT 301 Lab
 * This class is used for constructing a search
 * command that is sent to elasticsearch. It
 * holds the query to search and the fields to
 * search on.
 */
public class SimpleSearchCommand {
    private SimpleSearchQuery query;

    public SimpleSearchCommand(String query) {
        super();
        this.query = new SimpleSearchQuery(query);
    }

    public SimpleSearchCommand(String query, String[] fields) {
        super();
        throw new UnsupportedOperationException("Fields not yet implemented.");
    }

    public SimpleSearchQuery getQuery() {
        return query;
    }

    public void setQuery(SimpleSearchQuery query) {
        this.query = query;
    }

    static class SimpleSearchQuery {
        private SimpleSearchQueryString queryString;

        public SimpleSearchQuery(String query) {
            super();
            this.queryString = new SimpleSearchQueryString(query);
        }

        public SimpleSearchQueryString getQueryString() {
            return queryString;
        }

        public void setQueryString(SimpleSearchQueryString queryString) {
            this.queryString = queryString;
        }

        static class SimpleSearchQueryString {
            private String query;

            public SimpleSearchQueryString(String query) {
                super();
                this.query = query;
            }

            public String getQuery() {
                return query;
            }

            public void setQuery(String query) {
                this.query = query;
            }
        }
    }
}
