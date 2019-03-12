package webservice.blog.repository;

import org.springframework.data.repository.CrudRepository;
import webservice.blog.blog.Blog;

public interface BlogRepository extends CrudRepository<Blog, Integer> {
}
