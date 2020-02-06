import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args)  {
        Account logedUser = null;
        AccountController aControl = new AccountController();


        Scanner inStr = new Scanner(System.in);
        Scanner inInt = new Scanner(System.in);
        boolean running = true;

        while (running) {
            boolean entered = false;
            System.out.println("1-Login\n2-Sign up");
            System.out.print("Enter choice: ");
            switch (inStr.nextLine()) {
                case "1":
                    System.out.print("Enter username: ");
                    String uname = inStr.nextLine();
                    System.out.print("Enter password: ");
                    String pass = inStr.nextLine();
                    logedUser = aControl.login(uname, pass);
                    if(logedUser == null)
                        continue;
                    entered = true;
                    break;

                case "2":
                    System.out.print("Enter fullname: ");
                    String fname = inStr.nextLine();
                    System.out.print("Enter username: ");
                    uname = inStr.nextLine();
                    System.out.print("Enter password: ");
                    pass = inStr.nextLine();
                    System.out.print("Enter phone number: ");
                    String phone = inStr.nextLine();
                    System.out.print("Enter age: ");
                    int age = inInt.nextInt();
                    System.out.print("Enter type 1-Premium  2-Normal: ");
                    int t = inInt.nextInt();
                    boolean type = (t == 1) ? true : false;
                    logedUser = aControl.signUp(fname, uname, pass, phone, age, type);
                    if (logedUser == null)
                        continue;
                    entered = true;
                    break;

                default:
                    System.out.println("Not an option !");
                    continue;
            }

            while (entered) {
                System.out.print("1-Send Friend Request\n2-Accept Friend Request\n");
                if(!logedUser.isPremium())
                    System.out.println("3-Upgrade to premium\n4-Logout");
                else{
                    System.out.println("3-Logout");
                }
                System.out.print("Enter choice: ");
                switch (inStr.nextLine()) {
                    case "1":
                        System.out.print("Enter name of user: ");
                        String fname = inStr.nextLine();
                        aControl.searchUsers(fname);
                        System.out.print("Enter username of user to send request: ");
                        String uname = inStr.nextLine();
                        boolean check = aControl.sendFriendRequest(logedUser, uname);
                        if (check == false)
                            continue;
                        break;

                    case "2":
                        ArrayList<String> tmp = logedUser.getFriendRequests();
                        if (tmp.isEmpty()) {
                            System.out.println("You have no friend Requests");
                            continue;
                        }
                        for (String s : tmp)
                            System.out.println(s + " sent to you a friend request");
                        System.out.print("Enter username of user to accept request: ");
                        uname = inStr.nextLine();
                        check = aControl.acceptFriendRequest(logedUser, uname);
                        if (check == false)
                            continue;
                        break;

                    case "3":
                        if(logedUser.isPremium()) {
                            entered = false;
                            break;
                        }
                        else {
                            logedUser = ((NormalAccount) logedUser).upgradeToPremium();
                            break;
                        }

                    case "4":
                        if(!logedUser.isPremium()) {
                            entered = false;
                            break;
                        }


                    default:
                        System.out.println("Not an option !");
                        continue;
                }
            }
        }
    }
}



