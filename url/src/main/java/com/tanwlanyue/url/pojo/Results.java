package com.tanwlanyue.url.pojo;

import java.util.List;
import java.util.Date;

public class Results {

    private int id;
    private List<TagList> tagList;
    private User user;
    private boolean status;
    private String title;
    private String content;
    private int category;
    private int comment_num;
    private int like_num;
    private int read_num;
    private Date time;
    private Date comment_time;
    private boolean is_delete;

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setTagList(List<TagList> tagList) {
        this.tagList = tagList;
    }

    public List<TagList> getTagList() {
        return tagList;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public boolean getStatus() {
        return status;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public int getCategory() {
        return category;
    }

    public void setComment_num(int comment_num) {
        this.comment_num = comment_num;
    }

    public int getComment_num() {
        return comment_num;
    }

    public void setLike_num(int like_num) {
        this.like_num = like_num;
    }

    public int getLike_num() {
        return like_num;
    }

    public void setRead_num(int read_num) {
        this.read_num = read_num;
    }

    public int getRead_num() {
        return read_num;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public Date getTime() {
        return time;
    }

    public void setComment_time(Date comment_time) {
        this.comment_time = comment_time;
    }

    public Date getComment_time() {
        return comment_time;
    }

    public void setIs_delete(boolean is_delete) {
        this.is_delete = is_delete;
    }

    public boolean getIs_delete() {
        return is_delete;
    }

}