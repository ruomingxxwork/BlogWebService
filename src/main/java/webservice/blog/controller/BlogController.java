package webservice.blog.controller;

import excepition.BlogNotFoundException;
import org.hibernate.mapping.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import webservice.blog.blog.Blog;
import webservice.blog.repository.BlogRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping(path = "/blog")
public class BlogController {

    private final BlogRepository blogRepository;

    @Autowired
    public BlogController(BlogRepository blogRepository) {
        this.blogRepository = blogRepository;
    }


    @GetMapping(path = "/all")
    List<Blog> getAllBlog() {
        // This returns a JSON or XML with the users


        List<Blog> blogList = new ArrayList<>();

        for (Blog blog : blogRepository.findAll()) {

            blogList.add(blog);
        }


        return blogList;
    }


    @GetMapping(path = "/{id}")
    Blog one(@PathVariable Integer id) {

        return blogRepository.findById(id).orElseThrow(() -> new BlogNotFoundException(id));

    }

    @GetMapping(path = "/recent")
    List<Blog> getRecentBlog() {
        // This returns a JSON or XML with the users


        List<Blog> blogList = new ArrayList<>();


        for (Blog blog : blogRepository.findAll()) {

            blogList.add(blog);
        }

        blogList.sort(((o1, o2) -> (int) (o2.getModifiedTime().getTime() - o1.getModifiedTime().getTime())));


        return blogList.subList(0, Math.min(blogList.size(), 10));
    }


}
