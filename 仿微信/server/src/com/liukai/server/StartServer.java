 package com.liukai.server;

public class StartServer {
	public static void main(String[] args) {

		new Thread() {

			public void run() {

				try {
					Server.openServer();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			};

		}.start();

		new Thread() {

			public void run() {

				try {
					UDPServer.openServer();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			};

		}.start();

	}
}
