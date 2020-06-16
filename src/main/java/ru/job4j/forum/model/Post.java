package ru.job4j.forum.model;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Objects;

public class Post {

    private Integer id;
    private User author;
    private String name;
    private String desc;
    private Calendar created = new GregorianCalendar();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Calendar getCreated() {
        return created;
    }

    public void setCreated(Calendar created) {
        this.created = created;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Post post = (Post) o;
        return id == post.id &&
                Objects.equals(author, post.author) &&
                Objects.equals(name, post.name) &&
                Objects.equals(desc, post.desc) &&
                Objects.equals(created, post.created);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, author, name, desc, created);
    }
}