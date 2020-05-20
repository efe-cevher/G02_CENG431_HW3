package tube;

public class LoginController {

    LoginView loginView;

    public LoginController() {
        this.loginView = new LoginView(this);
    }

    public void checkCredentials(String username, char[] password) {
        //get data from somewhere
        //if(alright)
            //User user = new User()
            //go somewhere
        //else
            loginView.securityCheckFailed();
        System.out.println(username);
        System.out.println(password);

    }

}
