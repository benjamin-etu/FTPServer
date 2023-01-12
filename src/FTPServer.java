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
    private void receiveConnections() throws IOException{
        while (true) {
            this.clientSocket = serverSocket.accept();
            System.out.println("Connexion cliente reçue");

            // Création des flux d'entrée et sortie
            this.inFromClient = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            this.outToClient = new DataOutputStream(clientSocket.getOutputStream());

            // Envoi de la réponse au client
            outToClient.writeBytes("220 Server ready\n");

            this.readCommands();
        }
    }

    /*
     * Read ftp commands and use the correct behavior 
     */
    private void readCommands() throws IOException{
        while (true){
            String command = inFromClient.readLine();
            System.out.println(command);

            if (command.startsWith("USER")){
                outToClient.writeBytes("331 User name ok, need password\n");
            }
            else if (command.startsWith("PASS")){
                outToClient.writeBytes("230 User logged in\n");
            }
            else if (command.startsWith("QUIT")){
                outToClient.writeBytes("Server closes all connections\n"); 
                clientSocket.close();
            }
            else if (command.startsWith("SYST")){
                outToClient.writeBytes("500\n");
            }
            else if(command.startsWith("FEAT")){
                outToClient.writeBytes("211\n");
            }
        }
    }

}