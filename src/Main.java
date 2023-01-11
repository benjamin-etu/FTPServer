import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Main{

    public static void main(String[] args) throws IOException{
    
        ServerSocket s = new ServerSocket( 4242);
        System.out.println("FTP Server listening");

        // Boucle infinie pour accepter les connexions clientes
        while (true) {

            // Attente d'une connexion cliente
            Socket clientSocket = s.accept();
            System.out.println("Connexion cliente reçue");

            // Création des flux d'entrée et sortie
            BufferedReader inFromClient = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            DataOutputStream outToClient = new DataOutputStream(clientSocket.getOutputStream());

            // Envoi de la réponse au client
            outToClient.writeBytes("220 Server ready\n");

            // Lecture des commandes du client
            String str = inFromClient.readLine();
            System.out.println(str);
            
            // Envoi de la réponse au client
            outToClient.writeBytes("331 User name ok, need password\n");

            // Lecture des commandes du client
            str = inFromClient.readLine();
            System.out.println(str);

            // Envoi de la réponse au client
            outToClient.writeBytes("230 User logged in\n");

        }

    }

}