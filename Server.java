package pz.project;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {


    static ServerSocket serverSocket;


    public static void main(String[] args)
    {
        try {
            ExecutorService executor = Executors.newCachedThreadPool();
            while(true) {
                serverSocket = new ServerSocket(2500);
                Socket clientSocket = serverSocket.accept();
                ClientConnection client = new ClientConnection(clientSocket);
                executor.submit(client);
            }



        }catch(IOException e)
        {
            System.out.print("Nie udalo sie utworzyc polaczenia");
        }


    }

}
class ClientConnection implements Runnable
{

    Socket clientSocket;
    OutputStreamWriter socketWriter;
    InputStreamReader sockerReader;
    public ClientConnection(Socket clientSocket) throws IOException {
        this.clientSocket = clientSocket;
        this.socketWriter = new OutputStreamWriter(clientSocket.getOutputStream(), "UTF-8");
        this.sockerReader = new InputStreamReader(clientSocket.getInputStream(), "UTF-8");
    }

    @Override
    public void run() {

    }
}