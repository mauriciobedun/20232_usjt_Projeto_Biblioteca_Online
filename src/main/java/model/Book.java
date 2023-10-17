package main.java.model;

public class Book {
    private int id;
    private String title;
    private String author;
    private int year;

    // Construtor
    public Book() {
        // Construtor vazio
    }

    // Getters e setters para o campo 'id'
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    // Getters e setters para o campo 'title'
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    // Getters e setters para o campo 'author'
    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    // Getters e setters para o campo 'year'
    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
}

