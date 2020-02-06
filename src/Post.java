import java.util.ArrayList;

public class Post {

    private String content;
    private boolean isPrivate = false;
    private ArrayList<Hashtag> myHashtags;
    private ArrayList<Account> likers;

    public void createPost() { DatabaseHandler db;}
    public void getPost() {}
    public void updatePost() {}
    public void deletePost() {}
}


class Hashtag {

    private String hashtagName;
}


class PostController {

    public Post writePost(Account admin) { return new Post(); }
    public Post sharePost(Account user) { return new Post();}
    public Post likePost(Account user) { return new Post();}
    public ArrayList<String> searchHashtags() { return new ArrayList<>(); }
}