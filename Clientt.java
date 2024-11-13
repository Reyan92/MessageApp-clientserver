
import java.net.Socket;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.io.*;

public class Clientt {
    ArrayList<ClientTxt> list = new ArrayList<>();
    Socket socket;
    public boolean status=true;
    Clientt(Socket socket) {
        this.socket = socket;
    }
    public void startchat() {
        try {
          String message="";
          String response="";

            // Get message from user
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            while (true) {
                try{ if(message.equals("bye")||response.equals("bye"))
                break;

                    System.out.println("Enter message to send to server:");
                    message = reader.readLine();

                    // Send message to the server
                    PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
                    writer.println(message);
                    list.add(new ClientTxt(message, LocalDateTime.now()));

                    // Receive response from server
                    BufferedReader reader2 = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    response = reader2.readLine();
                    System.out.println("Message from server: " + response);
                    list.add(new ClientTxt(response, LocalDateTime.now()));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
       
}
public void display(){
    for(int i=0; i<list.size(); i++){
        System.out.println(list.get(i).toString());
    }
    status=false;
    
}
public void chechStatus(){
    if(status==true){
        System.out.println("Messages are not seen");}
        else if(status==false){
            System.out.println("Messages are seen");
        }
    }
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
            System.out.println("Invalid index");
            }
            break;
    case 2:
    list.clear();
    System.out.println("Chat deleted");
    break;
        default:
    }
  

}

public static void main(String[] args) {
    try{Socket socket = new Socket("localhost", 9806);
            System.out.println("Client started");
    
    Clientt client = new Clientt(socket);
    Scanner scanner = new Scanner(System.in);
        while(true) {
            System.out.println("Enter 1 for start chat");
            System.out.println("Enter 2 for displaying messages");
            System.out.println("Enter 3 for deleting messages");
            System.out.println("Enter 4 for sorting messages");
            System.out.println("Enter 5 for checking status");
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    client.startchat();
                    break;
                case 2:
                    client.display();
                    break;
                case 3:
                    client.delmsg();
                    break;
                case 4:
                    Collections.sort(client.list.reversed());
                    System.out.println("Messages have been soted");
                    break;
                case 5:
                    client.chechStatus();
                    break;
                default:
                    System.out.println("Invalid choice");
        
    }
    }}catch(Exception e){
         e.printStackTrace();
    }

}
    }
  


