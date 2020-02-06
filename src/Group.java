import java.util.ArrayList;

public class Group {

    private String name;
    private String description;
    private boolean isPrivate;
    private ArrayList<Account> members;


    public void createGroup() { DatabaseHandler db;}
    public void getGroup() {}
    public void updateGroup() {}
    public void deleteGroup() {}
}


class GroupController {

    public Group makeGroup(Account admin) { return new Group();}
    public Group joinGroup(Account user) { return new Group(); }
    public void addGroupMember(Account admin, Account user) {}
    public void removeGroupMember(Account admin, Account user) {}
    public ArrayList<String> searchGroups() { return new ArrayList<>(); }
}
