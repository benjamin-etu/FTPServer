import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class FTPServer{

    private int port;
    private ServerSocket serverSocket;
    private Socket clientSocket;
    private BufferedReader inFromClient;
    private DataOutputStream outToClient;
    private final String USERNAME = "ben";
    private final String PASSWORD = "ben";

    public FTPServer(int port) throws IOException{
        this.port = port;
        this.serverSocket = new ServerSocket(this.port);
    }

    /*
     * Run Server
     */
    public void run(){
        System.out.println("FTP Server listening");
        try{
            this.receiveConnections();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    /*
     * Receive clients connections 
     */
    public void receiveConnections() throws IOException{
        while (true) {
            this.clientSocket = serverSocket.accept();
            System.out.println(clientSocket.toString());

            // Création des flux d'entrée et sortie
            this.inFromClient = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            this.outToClient = new DataOutputStream(clientSocket.getOutputStream());

            // Envoi de la réponse au client
            writeToClient("220 Server ready\n");

            this.readCommands();
        }
    }

    /*
     * Read ftp commands and use the correct behavior 
     */
    public void readCommands() throws IOException{
        boolean stop = false;
        while (stop == false){
            String command = inFromClient.readLine();
            System.out.println(command);

            if (command.startsWith("USER")){
                this.handleUser();
            }
            else if (command.startsWith("PASS")){
                this.handlePass();
            }
            else if (command.startsWith("QUIT")){
                stop = this.handleQuit();
            }
            else if (command.startsWith("SYST")){
                this.handleSyst();
            }
            else if(command.startsWith("FEAT")){
                this.handleFeat();
            }
            else if(command.startsWith("EPSV")){
                this.handleEpsv();
            }
        }
    }

    public void handleUser(){
        writeToClient("331 User name ok, need password\n");
    }

    public void handlePass(){
        writeToClient("230 User logged in\n");
    }

    /*
     * Behavior for QUIT Command.
     * Return true if no error on close socket.
     * False otherwise.
     */
    public boolean handleQuit(){
        try{
            writeToClient("Server closes all connections\n");
            this.clientSocket.close();
            return true;
        }catch(IOException e){
            e.printStackTrace();
            return false;
        }
    }

    public void handleSyst(){
        writeToClient("500\n");
    }

    public void handleFeat(){
        writeToClient("211\n");
    }

    public void handleEpsv(){
        writeToClient(
            "229 Entering Extended Passive Mode (|||" + clientSocket.getPort() + "|)\n"
        );
    }

    public void writeToClient(String bytes){
        try{
            outToClient.writeBytes(bytes);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

}