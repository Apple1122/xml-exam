/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xmlFinalExam;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Scanner;

/**
 *
 * @author Jason
 */
public class Client {
    public static void main(String[] args) {
        Socket s;
        Scanner scanner = new Scanner(System.in);
        String Item = "";
        String Address = "";
        DataOutputStream output;
        try {
            s = new Socket();
            s.connect(new InetSocketAddress("127.0.0.1", 5010));
            output = new DataOutputStream(s.getOutputStream());
            while(true){
                System.out.print("Item:");
                Item = scanner.nextLine();
                System.out.print("Address:");
                Address = scanner.nextLine();
                output.writeUTF(Item);
                output.writeUTF(Address);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
