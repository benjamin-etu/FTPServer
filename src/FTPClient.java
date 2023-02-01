import java.io.*;
import java.net.Socket;

public class FTPClient {
    private Socket socket;
    private BufferedReader reader;
    private BufferedWriter writer;

    public FTPClient(String host, int port) throws IOException {
        socket = new Socket(host, port);
        reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
    }

    public void login(String username, String password) throws IOException {
        String response = reader.readLine();
        System.out.println(response);

        writer.write("USER " + username + "\r\n");
        writer.flush();
        response = reader.readLine();
        System.out.println(response);

        writer.write("PASS " + password + "\r\n");
        writer.flush();
        response = reader.readLine();
        System.out.println(response);
    }

    public void downloadFile(String filename) throws IOException {
        writer.write("RETR " + filename + "\r\n");
        writer.flush();

        try {
            int port = 4243;
            Socket socket = new Socket("localhost", port);
            InputStream inputStream = socket.getInputStream();
    
            FileOutputStream fileOutputStream = new FileOutputStream(filename);
            byte[] buffer = new byte[4096];
            int bytesRead;
    
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                fileOutputStream.write(buffer, 0, bytesRead);
            }
    
            fileOutputStream.close();
            inputStream.close();
            socket.close();
        } catch (IOException e) {
            System.err.println("Error while downloading file: " + e.getMessage());
        }
    }

    public void getListOfFiles() throws IOException{
        writer.write("LIST \r\n");
        writer.flush();
    }

    public void closeSocket() throws IOException {
        socket.close();
    }
}