package pz.project;

import javax.net.ssl.SSLServerSocketFactory;
import java.io.*;
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


            serverSocket = new ServerSocket(2500);

            while(true) {



                Socket clientSocket = serverSocket.accept();
                ClientConnection client = new ClientConnection(clientSocket);
                executor.submit(client);
            }


        }catch(IOException e)
        {
            System.out.print("Nie udalo sie utworzyc polaczenia");
            System.out.print(e.getMessage());
        }


    }

}
class ClientConnection implements Runnable
{

    private Socket clientSocket;
    private PrintWriter writer;
    private BufferedReader bufferedReader;

    public ClientConnection(Socket clientSocket) throws IOException {

        this.clientSocket = clientSocket;
        bufferedReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        writer = new PrintWriter(clientSocket.getOutputStream(),true);
    }

    @Override
    public void run() {

        try {
            StringBuilder message = new StringBuilder();
            String readedLine = bufferedReader.readLine();


            switch(readedLine)
            {
                case("LOGIN"):
                    String login = bufferedReader.readLine();
                    String password = bufferedReader.readLine();
                    login(login,password);
                    break;
            }

        }catch (Exception e)
        {
            // to do
        }
    }

    public void login(String login,String password)
    {
        if(login.equals("Antoni") && password.equals("Dzik"))
        {
            writer.println("OK");
        }else
        {
            writer.println("NOK");
        }
    }
}