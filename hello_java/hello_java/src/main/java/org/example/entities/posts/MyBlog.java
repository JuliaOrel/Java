package org.example.entities.posts;

import java.util.*;

public class MyBlog implements Collection<Post> {
    HashSet<Tag> tags= new HashSet<>();
    HashSet<Category>categories= new HashSet<>();

    ArrayDeque<Post> posts=new ArrayDeque<>();

    @Override
    public boolean add(Post post) {
        posts.add(post);
        categories.add(post.getCategory());
        post.getCategory().getPosts().add(post);
        for(Tag t: post.getTags()){
            tags.add(t);
            t.getPosts().add(post);
        }
        return true;
    }

    @Override
    public boolean remove(Object post) {
        if(post instanceof Post){
            posts.remove(post);
            ((Post)post).getCategory().getPosts().remove(post);
            for(Tag t: ((Post) post).getTags()){
                t.getPosts().remove(post);
            }
            return true;
        }
        return false;
    }
    @Override
    public int size() {
        return posts.size();
    }

    @Override
    public boolean isEmpty() {
        return posts.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return false;
    }

    @Override
    public Iterator<Post> iterator() {
        return null;
    }

    @Override
    public Object[] toArray() {
        return posts.toArray();
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return null;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends Post> c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return false;
    }

    @Override
    public void clear() {

    }
}
