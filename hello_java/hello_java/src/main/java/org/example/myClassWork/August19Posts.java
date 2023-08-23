package org.example.myClassWork;

import org.example.entities.posts.Category;
import org.example.entities.posts.Tag;

import java.util.ArrayList;

public class August19Posts implements Runnable{
    @Override
    public void run() {
        System.out.println("Work with posts");
    }

    ArrayList<Tag>tags=new ArrayList<Tag>();
    ArrayList<Category>categories=new ArrayList<Category>();

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
    }
}
