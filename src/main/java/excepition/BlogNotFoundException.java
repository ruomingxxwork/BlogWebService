package excepition;

public class BlogNotFoundException extends RuntimeException {


    public BlogNotFoundException(Integer id) {
        super("Could not find blog " + id);
    }
}
