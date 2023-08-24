package org.example.myClassWork;

import org.example.entities.posts.Category;
import org.example.entities.posts.Post;
import org.example.entities.posts.Tag;

import java.util.ArrayDeque;
import java.util.ArrayList;

public class August19Posts implements Runnable{
    @Override
    public void run() {
        System.out.println("Work with posts");
        seed();
    }

    ArrayList<Tag>tags=new ArrayList<Tag>();
    ArrayList<Category>categories=new ArrayList<Category>();

    ArrayDeque<Post> posts=new ArrayDeque<>();


    private void seed(){
        Category cNews=new Category();
        cNews.setTitle("News");
        cNews.setSlug("/news");

        Category cPromo=new Category();
        cPromo.setTitle("Promo");
        cPromo.setSlug("/promo");

        categories.add(cNews);
        categories.add(cPromo);

        Tag tOdesa=new Tag();
        tOdesa.setTitle("Odesa");
        tOdesa.setSlug("/tag/odesa");

        Tag tMykolaiv=new Tag();
        tMykolaiv.setTitle("Mykolaiv");
        tMykolaiv.setSlug("/tag/mykolaiv");

        tags.add(tOdesa);
        tags.add(tMykolaiv);

        System.out.println(categories);
        System.out.println(tags);

        Post p1=new Post();
        p1.setTitle("Post about news in Odessa");
        p1.setCategory(cNews);
        cNews.getPosts().add(p1);
        p1.getTags().add(tOdesa);
        tOdesa.getPosts().add(p1);

        Post p2=new Post();
        p2.setTitle("Post about promo in Mykolaiv");
        p2.setCategory(cPromo);
        cPromo.getPosts().add(p2);
        p2.getTags().add(tMykolaiv);
        tMykolaiv.getPosts().add(p2);

        posts.addFirst(p1);
        posts.addFirst(p2);

        //Get all posts(new for the first)
        System.out.println(posts);

        //Get posts by categories
        System.out.println("Category" + cNews.getTitle());
        for(Post p:cNews.getPosts()){
            System.out.println(p);
        }

        System.out.println("Category" + cPromo.getTitle());
        for(Post p:cPromo.getPosts()){
            System.out.println(p);
        }

        //Get posts by tags
        System.out.println("Tag" + tOdesa.getTitle());
        for(Post p:tOdesa.getPosts()){
            System.out.println(p);
        }

        System.out.println("Tag" + tMykolaiv.getTitle());
        for(Post p:tMykolaiv.getPosts()){
            System.out.println(p);
        }

        //delete
        System.out.println("\n\n---------\n Lets delete one post");
        posts.remove(p1);
        p1.getCategory().getPosts().remove(p1);
        for(Tag t: p1.getTags()){
            t.getPosts().remove(p1);
        }

        //Get all posts(new for the first)
        System.out.println(posts);

        //Get posts by categories
        System.out.println("Category" + cNews.getTitle());
        for(Post p:cNews.getPosts()){
            System.out.println(p);
        }

        System.out.println("Category" + cPromo.getTitle());
        for(Post p:cPromo.getPosts()){
            System.out.println(p);
        }

        //Get posts by tags
        System.out.println("Tag" + tOdesa.getTitle());
        for(Post p:tOdesa.getPosts()){
            System.out.println(p);
        }

        System.out.println("Tag" + tMykolaiv.getTitle());
        for(Post p:tMykolaiv.getPosts()){
            System.out.println(p);
        }
    }
}
