package webservice.blog.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.*;
import webservice.blog.blog.Blog;
import webservice.blog.repository.BlogRepository;
import java.util.*;

@RestController
@RequestMapping(path = "/blog")
public class BlogController {

    private final BlogRepository blogRepository;


    @Autowired
    private RedisTemplate<String, Object> redisTemplate;


    @Autowired
    public BlogController(BlogRepository blogRepository) {
        this.blogRepository = blogRepository;
    }


    @GetMapping(path = "/all")
    @ResponseBody
    List<Blog> getAllBlog() {
        // This returns a JSON or XML with the users


        List<Blog> blogList = new ArrayList<>();

        for (Blog blog : blogRepository.findAll()) {

            blogList.add(blog);
        }


        return blogList;
    }


    @GetMapping(path = "/{id}")
    @ResponseBody
    Blog one(@PathVariable Integer id) {


        Boolean exist = redisTemplate.hasKey(id.toString());


        if (exist != null && exist) {



            return (Blog) redisTemplate.opsForValue().get(id.toString());


        } else {

            ValueOperations<String, Object> operations = redisTemplate.opsForValue();

            Blog blog = blogRepository.findById(id).isPresent() ? blogRepository.findById(id).get() : null;


            operations.set(id.toString(), blog);

            return blog;

        }

    }

    @GetMapping(path = "/recent")
    @ResponseBody
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
