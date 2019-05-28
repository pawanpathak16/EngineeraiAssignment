package com.assignment.engineeraiassignment.model;

import java.util.Date;

public class Post
{
    private String title;
    private Long created_at_i;
    private boolean isEnable;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getCreated_at() {
        return created_at_i;
    }

    public void setCreated_at(Long created_at) {
        this.created_at_i = created_at;
    }

    public boolean isEnable() {
        return isEnable;
    }

    public void setEnable(boolean enable) {
        isEnable = enable;
    }
}
