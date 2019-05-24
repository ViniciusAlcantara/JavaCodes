import java.io.*;
import java.net.*;

class Servidor
{
	private static int portaServidor = 5555;
	private static byte[] receiveData = new byte[1024];
	private static byte[] sendData = new byte[1024];

	public static void main(String args[]) throws Exception
	{
		/*DatagramSocket serverSocket = new DatagramSocket(portaServidor);

      while(true) 
      {
         DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
         serverSocket.receive(receivePacket);

         System.out.println("RECEIVED: " + new String(receivePacket.getData()));

         InetAddress ipCliente = receivePacket.getAddress();A
         int portaCliente = receivePacket.getPort();
         sendData = (new String(receivePacket.getData())).toUpperCase().getBytes();

         DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, ipCliente, portaCliente);
         serverSocket.send(sendPacket);
      }*/
		
		DatagramSocket serverSocket = new DatagramSocket(portaServidor);
		
		String str;

		while(true){
			DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
			serverSocket.receive(receivePacket);

			System.out.println("RECEIVED: " + new String(receivePacket.getData()));

			Arq.openRead(new String(receivePacket.getData()));
			str = Arq.readString();
			Arq.close();

			InetAddress ipCliente = receivePacket.getAddress();
			int portaCliente = receivePacket.getPort();
			sendData = str.getBytes();

			DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, ipCliente, portaCliente);
			serverSocket.send(sendPacket);
		}

	}
}
