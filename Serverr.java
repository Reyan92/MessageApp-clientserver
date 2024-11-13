
import java.net.Socket;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.net.ServerSocket;
import java.io.*;
import java.util.Scanner;
import java.util.Collections;

public class Serverr {
       ArrayList<ServerTxt> list = new ArrayList<ServerTxt>();
       ServerSocket server;
       Socket socket;
       private boolean status=true;
       Serverr(ServerSocket server,Socket socket) {
        this.server = server;
        this.socket = socket;
       }

        public void startChat(){
    
            String message="";
            String response="";

                    
                try{
                    
                    while (true){ 
                    // Read message from the client
                    if(message.equals("bye")||response.equals("bye"))
                        break;
                    BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                     message = reader.readLine();
                    list.add(new ServerTxt(message, LocalDateTime.now()));
                    System.out.println("Message from Client: " + message);

                    // Send response to the client
                    BufferedReader reader2 = new BufferedReader(new InputStreamReader(System.in));
                    System.out.println("Enter message to send to client:");
                     response = reader2.readLine();
                    PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
                    writer.println(response);
                    list.add(new ServerTxt(response, LocalDateTime.now()));
                    
                }} catch (Exception e) {
                    e.printStackTrace();
                }
            }
        
    
    public void display(){
        for(int i=0; i<list.size(); i++){
            System.out.println(list.get(i).toString()); }//
            status=false;
    }
    public void checkStatus(){
        if(status==true){
            System.out.println("Messages are not seen");}
            else if(status==false){
                System.out.println("Messages are seen");
            }
        }
    
    @SuppressWarnings("unlikely-arg-type")
    public void delmsg(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter 1 to delete specific message");
        System.out.println("Enter 2 to delete chat");
        int choice=scanner.nextInt();
        switch (choice) {
            case 1:
            System.out.println("Enter message index to delete:");  
            int messageToDelete = scanner.nextInt();
            if(messageToDelete >= 0 && messageToDelete < list.size()){
            list.remove(messageToDelete);
            System.out.println("Message deleted");}
            else{
            System.out.println("Message not found");}
            
                break;
        case 2:
        list.clear();
        System.out.println("Chat deleted");
        break;
            default:
        }
      

    }
    
    public static void main(String[] args) {
    
        Scanner scanner = new Scanner(System.in);
       try{ ServerSocket ss = new ServerSocket(9806);
        System.out.println("Server started and listening on port 9806");
            Socket s = ss.accept();
            System.out.println("Client connected");
        Serverr server = new Serverr(ss,s);
           
        
        while(true) {
            System.out.println("Enter 1 for start chat");
            System.out.println("Enter 2 for displaying messages");
            System.out.println("Enter 3 for deleting messages");
            System.out.println("Enter 4 for sorting messages");
            System.out.println("Enter 5 for checking status");
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    server.startChat();
                    break;
                case 2:
                    server.display();
                    break;
                    case 3:
                    server.delmsg();
                    break;
                    case 4:
                    Collections.sort(server.list.reversed());
                    System.out.println("Messages have been sorted.");
                    break;
                    case 5:
                    server.checkStatus();
                    break;
                    
                default:
                    System.out.println("Invalid choice");
        
    }
    }} catch(IOException e){
        e.printStackTrace();
    }
       }
       
    }


