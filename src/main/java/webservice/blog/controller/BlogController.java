package webservice.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import webservice.blog.blog.Blog;
import webservice.blog.repository.BlogRepository;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(path = "/blog")
public class BlogController {

    private final BlogRepository blogRepository;

    @Autowired
    public BlogController(BlogRepository blogRepository) {
        this.blogRepository = blogRepository;
    }

    @GetMapping(path = "/all")
    public @ResponseBody
    ResponseEntity<List<Blog>> getAllBlog() {
        // This returns a JSON or XML with the users


        List<Blog> blogList = new ArrayList<>();

        for (Blog blog : blogRepository.findAll()) {

            blogList.add(blog);
        }


        return new ResponseEntity<>(blogList, HttpStatus.OK);
    }
}
