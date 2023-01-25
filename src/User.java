import java.nio.file.Path;
import java.nio.file.Paths;

public class User {

    private String username;

    public User(String username){
        this.username = username;
    }

    public String getUsername(){
        return this.username;
    }

    public Path getHomeDir(){
        return Paths.get(this.username);
    }
    

}
