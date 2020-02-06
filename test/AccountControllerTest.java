import org.junit.Before;

import static org.junit.Assert.*;

public class AccountControllerTest {
    Account logedUser = null;
    AccountController aControl = new AccountController();
    @org.junit.Test
    public void t1signUp() {
        //Arrange
        aControl = new AccountController();

        //Act
        logedUser =  aControl.signUp("islam adel", "marcel", "12345678", "01117595229", 16, false);

        //Assert
        assertEquals("islam adel",logedUser.getFullName());
        assertEquals("marcel",logedUser.getUsername());
        assertEquals("12345678",logedUser.getPassword());
        assertEquals("01117595229",logedUser.getPhoneNo());
        assertEquals(16,logedUser.getAge());
        assertEquals(false,logedUser.isPremium());


    }
    @org.junit.Test
    public void t2signUp() {

        //Arrange
        aControl = new AccountController();

        //Act
        logedUser =  aControl.signUp("islam adel", "marcelo", "1234567", "01117595229", 16, true);

        //Assert

        assertEquals(null,logedUser);

    }
    @org.junit.Test
    public void t3signUp() {

        //Arrange
        aControl = new AccountController();

        //Act
        logedUser =  aControl.signUp("islam adel", "marcelo", "12345678", "01117595229", 15, true);

        //Assert

        assertEquals(null,logedUser);

    }
    @org.junit.Test
    public void t4signUp() {

        //Arrange
        aControl = new AccountController();

        //Act
        logedUser =  aControl.signUp("islam adel", "marcelo", "12345678", "01117595229", 16, false);
        logedUser =  aControl.signUp("islam adel", "marcelo", "12345678", "01117595229", 16, false);

        //Assert

        assertEquals(null,logedUser);


    }
    @org.junit.Test
    public void t1sendFriendRequest() {
        //Arrange
        aControl = new AccountController();
        //Act
        logedUser = aControl.signUp("islam adel", "soso", "12345678", "01117595229", 16, false);
        boolean test = aControl.sendFriendRequest(logedUser, "soso");
        //Assert
        assertEquals(false,test);
    }
    @org.junit.Test
    public void t2sendFriendRequest() {
        //Arrange
        aControl = new AccountController();
        //Act
        aControl.signUp("islam adel", "sharkawy", "12345678", "01117595229", 16, false);
        aControl.signUp("islam adel", "marcelo", "12345678", "01117595229", 16, false);

        logedUser = aControl.login("sharkawy", "12345678");
        boolean test = aControl.sendFriendRequest(logedUser, "marcelo");
        //Assert
        assertEquals(true,test);
    }

    @org.junit.Test
    public void t3sendFriendRequest() {
        //Arrange
        aControl = new AccountController();
        //Act
        aControl.signUp("islam adel", "sharkawy", "12345678", "01117595229", 16, false);

        logedUser = aControl.login("sharkawy", "12345678");
        boolean test = aControl.sendFriendRequest(logedUser, "aya");
        //Assert
        assertEquals(false,test);
    }
    @org.junit.Test
    public void t4sendFriendRequest() {
        //Arrange
        aControl = new AccountController();
        //Act
        Account account = aControl.signUp("alaa saeed", "alaa", "12345678", "01117595229", 16, false);

        logedUser = aControl.login("sharkawy", "12345678");
        logedUser.getMyFriends().add(account);
        boolean test = aControl.sendFriendRequest(logedUser, "alaa");
        //Assert
        assertEquals(false,test);
    }

    @org.junit.Test
    public void t1acceptFriendRequest() {
        //Arrange
        aControl = new AccountController();

        //Act
        aControl.signUp("islam adel", "sharkawy", "12345678", "01117595229", 16, false);
        aControl.signUp("islam adel", "marcelo", "12345678", "01117595229", 16, false);
        logedUser = aControl.login("sharkawy", "12345678");

        //Assert
        assertEquals(false,aControl.acceptFriendRequest(logedUser,"marcelo"));
    }

    @org.junit.Test
         public void t2acceptFriendRequest() {
        //Arrange
        aControl = new AccountController();

        //Act
        aControl.signUp("islam adel", "sharkawy", "12345678", "01117595229", 16, false);
        Account account = aControl.signUp("islam adel", "aya", "12345678", "01117595229", 16, false);

        logedUser = aControl.login("sharkawy", "12345678");
        aControl.sendFriendRequest(account,"sharkawy");
        //Assert
        assertEquals(true,aControl.acceptFriendRequest(logedUser,"aya"));
    }

    @org.junit.Test
         public void t3acceptFriendRequest() {
        //Arrange
        aControl = new AccountController();
        Account  a=  aControl.signUp("islam adel", "marcelo", "12345678", "01117595229", 16, false);

        aControl.signUp("islam adel", "sharkawy", "12345678", "01117595229", 16, false);

        //Act
        logedUser = aControl.login("sharkawy", "12345678");
//        aControl.sendFriendRequest(a,"sharkawy");


        //Assert
        assertEquals(false,aControl.acceptFriendRequest(logedUser,"aya"));
    }

}