package org.example.entities.posts;

import lombok.Data;

import java.util.ArrayList;

@Data
public class Category {
    private String title;
    private String slug;
    ArrayList<Post> posts=new ArrayList<>();
}
