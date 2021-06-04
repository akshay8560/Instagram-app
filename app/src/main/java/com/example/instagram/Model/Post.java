package com.example.instagram.Model;

public class Post {
    private String description;
    private String postimageurl;
    private String postid;
    private String publisher;


    public Post() {

    }

    public Post(String description, String postimageurl, String postid, String publisher) {
        this.description = description;
        this.postimageurl = postimageurl;
        this.postid = postid;
        this.publisher = publisher;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPostimageurl() {
        return postimageurl;
    }

    public void setPostimageurl(String postimageurl) {
        this.postimageurl = postimageurl;
    }

    public String getPostid() {
        return postid;
    }

    public void setPostid(String postid) {
        this.postid = postid;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }
}
