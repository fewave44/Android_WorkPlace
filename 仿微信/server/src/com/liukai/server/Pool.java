package com.liukai.server;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class Pool extends Thread {

	public String id;

	public String message = "";
	public Set<String> ips = new HashSet();

	public void remove(String ip) {
		ips.remove(ip);
	}

	public boolean b = false;

	public void run() {
		try {
			System.out.println("AAAAAAAAAAAAAAAA");
			DatagramSocket scoket = new DatagramSocket();
			for (int i = 0; i < 3; i++) {

				Iterator<String> it = ips.iterator();
				while (it.hasNext()) {
					String ip = it.next();
					byte[] b = (message + "," + id).getBytes();
					DatagramPacket data = new DatagramPacket(b, b.length,
							InetAddress.getByName(ip), 7777);
					scoket.send(data);
				}
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
				}

			}
			b = true;

		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	public Pool() {
		id = new Date().getTime() + "";
	}

}
