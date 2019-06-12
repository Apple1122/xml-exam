/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xmlFinalExam;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;



/**
 *
 * @author Jason
 */
public class Server implements Runnable {
	
	private ServerSocket serverSocket;
	private OrderRW orderRW;
	private List<Order> orders;
	private boolean isStopped = false;
	
    public Server() {
    	orders = new ArrayList<>();
    	orderRW = new OrderRW(); 
    	
       	try {
    			serverSocket = new ServerSocket(5010);
    		} catch (IOException e) {
    			e.printStackTrace();
    		}
        	
    		
    		while(!isStopped())
    		{
    			
    			Socket clientSocket = null;
    			try 
    			{
    				this.addClient(serverSocket.accept());
    			} 
    			catch(IOException exc) 
    			{
    				exc.printStackTrace();
    			}
    			
    		}
    		System.out.println("Server Stopped.") ;
    	
    }

    @Override
    public void run() {

    	try {
			serverSocket = new ServerSocket(5010);
		} catch (IOException e) {
			e.printStackTrace();
		}
    	
		
		while(!isStopped())
		{
			
			Socket clientSocket = null;
			try 
			{
				this.addClient(serverSocket.accept());
			} 
			catch(IOException exc) 
			{
				exc.printStackTrace();
			}
			
		}
		System.out.println("Server Stopped.") ;
	
    }
    
	private synchronized boolean isStopped()
	{
		return this.isStopped;
	}
	
	private void addClient(Socket clientSocket)
	{

		new Thread()
		{
			public void run()
			{
				try {
					DataInputStream input = new DataInputStream(clientSocket.getInputStream());
					
					while(true)
					{
						String item = input.readUTF();
						String address  = input.readUTF();
						
						addOrder(new Order(item, address));
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
				

			
			}
		}.start();
		
	}
	
	private synchronized void addOrder(Order order)
	{
		// add into List
		orders.add(order);
		orderRW.write(orders);
	}
    
    public static void main(String[] args) {
//    	new Thread(new Server()).start();
        new Server();
    }
    
    
}
