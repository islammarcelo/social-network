import java.util.ArrayList;
import java.util.Scanner;

public class Account {

    private String fullName;
    private String username;
    private String password;
    private String phoneNo;
    private int age;
    private boolean isPremium = false;

    private static ArrayList<Account> registered = new ArrayList<>(); // database

    private ArrayList<String> friendRequests = new ArrayList<>();
    private ArrayList<Account> myFriends = new ArrayList<>();
    private ArrayList<Post> myPosts;
    private ArrayList<Page> myPages;
    private ArrayList<Group> myGroups;
    private ArrayList<Message> myMessages;



    public ArrayList<String> getFriendRequests() {
        return friendRequests;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public int getAge() {
        return age;
    }

    public ArrayList<Account> getMyFriends() {
        return myFriends;
    }

    public void setFriendRequests(ArrayList<String> friendRequests) {
        this.friendRequests = friendRequests;
    }

    public void setMyFriends(ArrayList<Account> myFriends) {
        this.myFriends = myFriends;
    }

    public Account() {
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public boolean isPremium() {
        return isPremium;
    }

    public static ArrayList<Account> getRegistered() {
        return registered;
    }

    public String getFullName() {
        return fullName;
    }


    public Account(String fullName, String username, String password, String phoneNo, int age, boolean isPremium) {
        this.fullName = fullName;
        this.username = username;
        this.password = password;
        this.phoneNo = phoneNo;
        this.age = age;
        this.isPremium = isPremium;
    }


    public void createAccount(Account acc) {
        registered.add(acc);
    }

    public void updateAccount() {
    }

    public void deleteAccount() {
    }

    public void addFriendNotification(String username) {
        this.friendRequests.add(username);
    }

    public void addFriend(Account account) {
        myFriends.add(account);
    }
}


class PremiumAccount extends Account {
    private CreditCard myCard;
    private ArrayList<Ad> myAds;

    public PremiumAccount() {

    }

    public PremiumAccount(String fullName, String username, String password, String phoneNo, int age, boolean isPremium) {
        super(fullName, username, password, phoneNo, age, isPremium);
    }

    public void makeAd(Ad ad) {}

    public boolean payAnnualFees() {
        System.out.print("Enter credit card-number: ");
        long no = new Scanner(System.in).nextLong();
        CreditCard card = new CreditCard(no);
        this.myCard = card;
        if (this.myCard.getBalance() >= 99) {
            this.myCard.setBalance(this.myCard.getBalance() - 99);
            return true;
        } else {
            System.out.println("No enough money !");
            return false;
        }
    }
    public void payForAdditionalAd() {}
}


class NormalAccount extends Account {
    public NormalAccount(){}
    public NormalAccount(String fullName, String username, String password, String phoneNo, int age, boolean isPremium) {
        super(fullName, username, password, phoneNo, age, isPremium);
    }

    public Account upgradeToPremium() {
        AccountController controller = new AccountController();
        Account.getRegistered().remove(this);
        Account premium = controller.signUp(this.getFullName(), this.getUsername(), this.getPassword(), this.getPhoneNo(), this.getAge(), true);

        if (premium == null)
            return this;
        else {
            premium.setMyFriends(this.getMyFriends());
            premium.setFriendRequests(this.getFriendRequests());
            return premium;
        }
    }
}


class AccountController {
    private Account obj = new Account();

    private boolean validUsername(String username) {
        ArrayList<Account> tmp = obj.getRegistered();
        if(tmp.isEmpty())
            return true;

        for (Account a : tmp) {
            if(a.getUsername().equals(username))
                return false;
        }

        return true;
    }

    private boolean validPassword(String password) {
        if(password.length() < 8 || password.contains("/"))
            return false;

        return true;
    }

    private boolean validAge(int age) {
        if (age < 16)
            return false;

        return true;
    }

    public Account signUp(String fullname, String username, String password, String phoneNo, int age, boolean type) {
        if (!validUsername(username)) {
            System.out.println("Username is taken !");
            return  null;
        }else if (!validPassword(password)) {
            System.out.println("Password is invalid!");
            return  null;
        } else if (!validAge(age)) {
            System.out.println("Age less than 16 !");
            return  null;
        }

        if (type == true) { //premium
            PremiumAccount premium =  new PremiumAccount(fullname, username, password, phoneNo, age, true);;
            boolean check = premium.payAnnualFees();
            if (check) {
                obj.createAccount(premium);
                return premium;
            } else {
                return null;
            }
        } else { // normal
            NormalAccount normal = new NormalAccount(fullname, username, password, phoneNo, age, false);
            obj.createAccount(normal);
            return normal;
        }
    }



    public Account login(String username, String password) {
        ArrayList<Account> tmp = obj.getRegistered();
        if(tmp.isEmpty()) {
            System.out.println("Account not found !");
            return null;
        }

        for (Account a : tmp) {
            if(a.getUsername().equals(username))
                if(a.getPassword().equals(password)) {
                    System.out.println("Successful log in :D");
                    return a;
                }
        }

        System.out.println("Invalid Username or Password :(");
        return null;
    }


    public void searchUsers(String fullname) {
        ArrayList<Account> registered = obj.getRegistered();
        ArrayList<Account> tmp = new ArrayList<>();
        for (Account a : registered) {
            if (a.getFullName().contains(fullname))
                tmp.add(a);
        }

        System.out.println("All users with this name: ");
        for (Account a : tmp) {
            System.out.println("\t" + a.getFullName() + " with username : " + a.getUsername());
        }
    }

    public boolean sendFriendRequest(Account sender, String username) {
        if (sender.getUsername().equals(username)) {
            System.out.println("htb3t le nfsk ya ahbl");
            return false ;
        } else {
            ArrayList<Account> tmp = obj.getRegistered();
            for (Account a : tmp) {

                if (a.getUsername().equals(username)) {
                    if (sender.getMyFriends().contains(a)) {
                        System.out.println("ana henaa ya habibi");
                        return false;
                    }
                    System.out.println(sender.getUsername());
                    a.addFriendNotification(sender.getUsername());
                    System.out.println("Friend request send to " + a.getUsername());
                    return true;
                }
            }
        }

        System.out.println("User not found !");
        return false;
    }


    public boolean acceptFriendRequest(Account receiver, String username) {

        boolean sentToME = false; // lazm ykon b3tly add abl keda
        ArrayList<String> notifications = receiver.getFriendRequests();

        boolean registered = false;
        for (Account a : Account.getRegistered())
            if (a.getUsername() == username)
                registered = true;

        for (String n : notifications) {
            if (n.contains(username))
                sentToME = true;
        }

        if(!sentToME && registered) {
            System.out.println("Account with username: " + username
                    + " didn't sent a request to you");
            return false;
        }

        ArrayList<Account> tmp = obj.getRegistered();
        for (Account a : tmp) {
            if (a.getUsername().equals(username)) {
                receiver.addFriend(a);
                receiver.getFriendRequests().remove(receiver.getFriendRequests().indexOf(username));
                a.addFriend(receiver);
                System.out.println("Friend request accepted: " + username + " is a friend nows");
                return true;
            }
        }

        System.out.println("User not found !");
        return false;
    }
}
