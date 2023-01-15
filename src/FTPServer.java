import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.Inet6Address;
import java.net.InetAddress;

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

            this.handleCommand();
        }
    }

    /*
     * Read a command and return a list of two elements.
     * First element is the command name. Second element is the argument given with this command.
     */
    public String[] readCommand() throws IOException{
        String command = inFromClient.readLine();
        System.out.println(command);
        return command.split(" ");
    }

    /*
     * Handles ftp commands
     */
    public void handleCommand() throws IOException{
        boolean stop = false;
        while (stop == false){
            String[] command = this.readCommand();
            String commandName = command[0];
            //phase de login
            if (commandName.startsWith("USER")){
                this.handleUser();
                String username = command[1];
                command = this.readCommand();
                commandName = command[0];
                String password = command[1];
                if (commandName.startsWith("PASS")){
                    if (username.equals(this.USERNAME) && password.equals(this.PASSWORD)){
                        this.handlePass();
                    }else{
                        writeToClient("ERROR login\n");
                    }
                }
            }
    
            else if (commandName.startsWith("QUIT")){
                stop = this.handleQuit();
            }
            else if (commandName.startsWith("SYST")){
                this.handleSyst();
            }
            else if(commandName.startsWith("FEAT")){
                this.handleFeat();
            }
            else if(commandName.startsWith("EPSV")){
                this.handleEpsv();
            }
            else if(commandName.startsWith("OPTS")){
                this.handleOpts();
            }
            else if(commandName.startsWith("EPRT")){
                String arg = command[1];
                this.handleEprt(arg);
            }
        }
    }

    public void handleEprt(String eprtArgument) throws IOException{
        //creation d'une connexion vers le client TODO: à externaliser
        String[] parts = eprtArgument.split("\\|");
        String clientHostName = parts[2];
        InetAddress address = Inet6Address.getByName(clientHostName);
        int clientPort = Integer.parseInt(parts[3]);
        Socket dataSocket = new Socket(address, clientPort);
        this.writeToClient("200 Ready to transfer data\n");
    }
    public void handleOpts(){
        writeToClient("200 SP opts good\n");
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