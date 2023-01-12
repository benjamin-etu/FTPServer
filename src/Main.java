import java.io.IOException;

public class Main {
    public static void main(String[] args){
        int port = 4242;
        try{
            FTPServer server = new FTPServer(port);
            server.run();
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
