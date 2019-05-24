import java.io.*;
import java.net.*;

class Cliente
{

	private static int portaServidor = 3333;
	private static byte[] sendData = new byte[1024];
	private static byte[] receiveData = new byte[1024];

	public static byte[] lerString () throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		return in.readLine().getBytes();
	}

	public static void main(String args[]) throws Exception
	{
		/*DatagramSocket clientSocket = new DatagramSocket();
      InetAddress ipServidor = InetAddress.getByName("192.168.43.59");
      sendData = lerString();

      DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, ipServidor, portaServidor);
      clientSocket.send(sendPacket);

      DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
      clientSocket.receive(receivePacket);
      clientSocket.close();

      System.out.println("FROM SERVER:" + new String(receivePacket.getData()));*/

		DatagramSocket clientSocket = new DatagramSocket();
		InetAddress ipServidor = InetAddress.getByName("192.168.43.188");
		sendData = lerString();

		DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, ipServidor, portaServidor);
		clientSocket.send(sendPacket);

		DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
		clientSocket.receive(receivePacket);
		clientSocket.close();
		
		String aux = new String(receivePacket.getData().toString());
		
		System.out.println("Esse é o aux: " + aux);

		//ABRIR, ESCREVER E FECHAR ARQUIVO TEXTO
		Arq.openWrite("cliente.txt");
		Arq.println(new String(receivePacket.getData().toString()));
		Arq.close();

		System.out.println("FROM SERVER:" + new String(receivePacket.getData().toString()));

	}
}
