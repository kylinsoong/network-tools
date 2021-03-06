package com.example;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class HTTPTrafficDebugClient implements CommandLineRunner {


	public static void main(String[] args) {
		SpringApplication.run(HTTPTrafficDebugClient.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		if(args.length != 2) {
			System.out.println("Run with paramters: java -jar http-client.jar <IP> <Port> <Times>");
			System.out.println("                    <IP> - Server IP address");
			System.out.println("                    <Port> - Service Port, like 80, 443, 8080, 8443, etc");
			System.exit(0);
		}
		
		String ip = args[0];
		int port = Integer.parseInt(args[1]);
		
		System.out.println("Send 3 http request without GET");
		
		List<Socket> list = new ArrayList<>();
		
		for (int i = 1 ; i <= 3 ; i ++) {
			
			SocketAddress address = new InetSocketAddress(ip, port);
			Socket socket = new Socket();
			socket.connect(address, 2000);
			
			list.add(socket);
			
			System.out.println("    http request " + i + ", " + socket.toString());
		}
		
		System.out.println("\nPress \"ENTER\" to continue...");
		Scanner scanner = new Scanner(System.in);
		scanner.nextLine();
		scanner.close();
		
		list.forEach(s -> {
			try {
				s.close();
			} catch (IOException e) {
				System.err.println(e.getMessage());
			}
		});
		
	}

	
}
