package ru.bkmz.lib.table;

public class Table {
    private int id;
    private String word;
    private String definition;
    private String url;

    public Table(int id, String word, String definition, String url) {
        this.id = id;
        this.word = word;
        this.definition = definition;
        this.url = url;

    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getDefinition() {
        return definition;
    }

    public void setDefinition(String definition) {
        this.definition = definition;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
