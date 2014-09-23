package com.liukai.server;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class Server extends Thread{
	private Socket socket = null;

	public Server(Socket socket) {
		this.socket = socket;
	}

	static HashMap map = new HashMap();
	static {
		map.put("lisi", "8888");
		map.put("zs", "8888");
		map.put("zl", "8888");
		map.put("ww", "8888");
		map.put("xh", "8888");
		map.put("qq", "8888");

	}

	static HashMap pools = new HashMap();

	public static Set<String> set = new HashSet();

	public void run() {

		try {

			InputStream in = socket.getInputStream();
			OutputStream out = socket.getOutputStream();
			byte[] b = new byte[100];
			in.read(b);

			String cmd = new String(b).trim();

			if (cmd.startsWith("login")) {// 登录

				String[] str = cmd.split(",");// 分割 ,
				String username = str[1];
				String password = str[2];
				try {
					if (map.get(username).equals(password)) {
						out.write("ok".getBytes());
						out.flush();
						set.add(socket.getInetAddress().getHostAddress());
						return;
					}
					throw new Exception();
				} catch (Exception e) {
					out.write("error".getBytes());
					out.flush();
				}

			} else if (cmd.startsWith("get")) {// 下载
				// get,123456789.amr
				try {

					String filename = cmd.split(",")[1];

					File f = new File("D:\\amr", filename);
					if (f.exists()) {
						out.write(("ok," + f.length()).getBytes());
						out.flush();
						byte[] b1 = new byte[10];
						in.read(b1);
						FileInputStream fin = new FileInputStream(f);
						int len = 0;
						byte[] b2 = new byte[1024];
						while ((len = fin.read(b2)) != -1) {
							out.write(b2, 0, len);
							out.flush();
						}
						fin.close();
						return;
					}
					throw new Exception();
				} catch (Exception e) {
					out.write("error".getBytes());
					out.flush();
				}

			} else if (cmd.startsWith("upload")) {// 上传

				// upload,156441,lisi
				String s = cmd.split(",")[1];
				String username = cmd.split(",")[2];
				long size = Long.parseLong(s);
				out.write("ok".getBytes());
				out.flush();
				File ff = new File("D:\\amr", (new Date().getTime()) + "R"
						+ ((int) (Math.random() * 100000)) + ".amr");
				FileOutputStream fout = new FileOutputStream(ff);
				byte[] b1 = new byte[1024];
				int len = 0;
				long length = 0;
				while ((len = in.read(b1)) != -1) {
					length += len;
					fout.write(b1, 0, len);
					if (length >= size) {
						break;
					}
				}
				fout.close();

				Pool p = new Pool();

				pools.put(p.id, p);

				p.message = username + "," + ff.getName();

				for (String ip : set) {
					if (ip.equalsIgnoreCase(socket.getInetAddress()
							.getHostAddress()))
						continue;
					p.ips.add(ip);
				}

				p.start();

			}

		} catch (Exception e) {

		} finally {
			try {
				socket.close();
			} catch (Exception e1) {
			}
		}

	}

	public static void openServer() throws Exception {
		ServerSocket server = new ServerSocket(5555);
		while (true) {
			new Server(server.accept()).start();
			System.out.println("有客到!");
		}

	}

}
