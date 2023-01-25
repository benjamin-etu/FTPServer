import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


public class FTPServer{

    private int port;
    private ServerSocket serverSocket;
    private Path serverPath;
    private Socket clientSocket;
    private BufferedReader inFromClient;
    private DataOutputStream outToClient;

    private Socket dataSocket;
    private DataOutputStream dataSocketOutputStream;
    private BufferedReader dataSocketBufferedReader; 

    private final String USERNAME = "ben";
    private final String PASSWORD = "ben";

    private User connectedUser;
    private Path currentDir;

    public FTPServer(int port) throws IOException{
        this.port = port;
        this.serverSocket = new ServerSocket(this.port);
        this.serverPath = Paths.get("C:","Users","Ben","Documents" );
       
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
     * First element is the command name. Second element is the argument given with this command if
     * exists.
     */
    public String[] readCommand() throws IOException{
        String command = inFromClient.readLine();
        System.out.println(command);
        //sépare le nom de la commande de l'argument
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
                    stop = !(this.handlePass(username, password));
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
            else if(commandName.equals("RETR")){
                String fileToRetrieve = command[1];
                this.handleRetr(fileToRetrieve, this.dataSocketOutputStream);
            }
            else if(commandName.equals("STOR")){
                String filenameToStore = command[1];
                this.handleStor(filenameToStore, this.dataSocketBufferedReader );
            }
            else if(commandName.equals("CWD")){
                String directoryName = command[1];
                this.handleCwd(directoryName);
            }
        }
    }

    private void handleCwd(String directoryName) throws IOException {
        //on regarde si le currentDir existe dans le user homedir
        String userHomeDir = this.connectedUser.getHomeDir().toString();
        Path p = Paths.get(this.serverPath.toString(), userHomeDir, directoryName);
        if (Files.exists(p) && Files.isDirectory(p)) {
            // Le dossier existe
            //on ajoute directoryName à currentDir
            this.currentDir = p;
        } else {
            // Le dossier n'existe pas
            //on le crée
            Files.createDirectory(p);
            //on ajoute directoryName à currentDir
            this.currentDir = p;
            
        }
        writeToClient("250 Okay.\n");
        System.out.print("current dir :"+this.currentDir);

    }

    public void openDataConnection(String address, int port) throws UnknownHostException, IOException{
        this.dataSocket = new Socket(address, port);
        this.dataSocketOutputStream = new DataOutputStream(this.dataSocket.getOutputStream());
        this.dataSocketBufferedReader = new BufferedReader(new InputStreamReader(this.dataSocket.getInputStream()));
    }

    public void handleStor(String filenameToStore, BufferedReader dataInputStream ){
        writeToClient("150 Start receiving data ...\n");
        //récupère les données du fichier 
        try{
            String fileContent = dataInputStream.readLine();
            //écrire le fichier sur le serveur 
            FileOutputStream fos = new FileOutputStream(filenameToStore);
            fos.write(fileContent.getBytes());
            fos.flush();
            fos.close();
            //envoi message que tout est ok
            writeToClient("226 File successfully stored.\n");
            dataInputStream.close();
            this.dataSocket.close();
        }catch(Exception e){
            e.printStackTrace();
            writeToClient("451 Error.\n ");
        }
    }

    public void handleRetr(String fileToRetrieve, DataOutputStream dataStream){
        try{
            // Ouvre un flux d'entrée pour lire le fichier
            // Le fichier d'entrée
            writeToClient("150 Sending...\n");
            File file = new File(fileToRetrieve);    
            // Envoi du fichier au client
            FileInputStream fileInputStream = new FileInputStream(file);
            byte[] buffer = new byte[4096];
            int bytesRead;
            while ((bytesRead = fileInputStream.read(buffer)) != -1) {
                dataStream.write(buffer, 0, bytesRead);
            }
            writeToClient("226 Successfully downloaded\n");
            dataStream.close();
            fileInputStream.close();
            this.dataSocket.close();
        }catch(Exception e){
            e.printStackTrace();
            writeToClient("451 Reading file throw an error\n");
        }
    }

    public void handleEprt(String eprtArgument) throws IOException{
        String[] parts = eprtArgument.split("\\|");
        try{
            String address = parts[2];
            int port = Integer.parseInt(parts[3]);
            this.openDataConnection(address, port);
            writeToClient("200 Ready to transfer data\n");
        }catch(Exception e){
            writeToClient("500 Error on creating socket to transfer data\n");
        }
    }

    public void handleOpts(){
        writeToClient("200 SP opts good\n");
    }

    public void handleUser(){
        writeToClient("331 User name ok, need password\n");
    }

    public boolean handlePass(String username, String password) throws IOException{
        if (username.equals(this.USERNAME) && password.equals(this.PASSWORD)){
            writeToClient("230 User logged in\n");
            this.connectedUser = new User(username);
            this.currentDir = this.connectedUser.getHomeDir();
            return true;
        }else{
            writeToClient("ERROR login\n");
            this.outToClient.close();
            this.inFromClient.close();
            this.clientSocket.close();
            return false;
        }
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