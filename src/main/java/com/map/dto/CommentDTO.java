package com.map.dto;

import com.map.entity.User;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.sql.Date;

/**
 * @author Yevhenii Semenov
 */
public class CommentDTO {

    private long id;

    @NotNull
    private User user;

    @NotNull
    @Min(5)
    private String text;

    @NotNull
    private Date date;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
