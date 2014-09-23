package com.liukai.server;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class UDPServer extends Thread {

	DatagramPacket data = null;

	public UDPServer(DatagramPacket data) {

		this.data = data;
	}

	public void run() {
		// 12312312312312
		String s = new String(data.getData()).trim();
		System.out.println(s);
		Pool p = (Pool) Server.pools.get(s);
		p.ips.remove(data.getAddress().getHostAddress());
		if (p.b) {
			Server.pools.remove(s);
		}

	}

	public static void openServer() throws Exception {

		DatagramSocket socket = new DatagramSocket(8888);
		while (true) {
			byte[] b = new byte[1000];
			DatagramPacket data = new DatagramPacket(b, b.length);
			socket.receive(data);
			new UDPServer(data).start();

		}
	}

}
