package org.example.entities.posts;

import lombok.Data;

import java.util.ArrayList;

@Data
public class Post {
    private String title;
    private String slug;

    private Category category;
    ArrayList<Tag>tags=new ArrayList<>();

    @Override
    public String toString(){
        return "Post: {title="+this.title+ " }";
    }
}
