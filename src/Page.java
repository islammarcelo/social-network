import java.util.ArrayList;

public class Page {

    private String name;
    private ArrayList<Account> likers;

    public void createPage() { DatabaseHandler db;}
    public void getPage() {}
    public void updatePage() {}
    public void deletePage() {}
}


class PageController {

    public Page makePage(Account admin) { return new Page(); }
    public Page likePage(Account user) { return new Page(); }
    public Page sharePage(Account user) { return new Page(); }
}
