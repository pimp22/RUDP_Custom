package client;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Arrays;

class ClientMain
{

	
    public static void main(String args[]) throws Exception
    {
       BufferControlClient bufferControl = new BufferControlClient();
       BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
       DatagramSocket clientSocket = new DatagramSocket();
       InetAddress IPAddress = InetAddress.getByName("localhost");
       
       byte[] sendData = new byte[bufferControl.packetSize];
       byte[] receiveData = new byte[bufferControl.packetSize];
       String sentence = inFromUser.readLine();
       
      
       bufferControl.addData(sentence);
       
       while (bufferControl.seq < bufferControl.packetsToSend){
       
       bufferControl.sendDataOnSocket(clientSocket);
       
       
     
       
       
  DatagramPacket receivePacket =  new DatagramPacket(receiveData, receiveData.length);
       
       clientSocket.receive(receivePacket);
       String modifiedSentence = new String(receivePacket.getData());
       System.out.println("FROM SERVER:" + modifiedSentence);
      // System.out.println(bufferControl.packetList.get(1));
       
    }
       clientSocket.close();
    }
}