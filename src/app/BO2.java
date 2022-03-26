package app;

import java.sql.Connection;
import java.sql.DriverManager;
import com.rabbitmq.client.*;

public class BO2 {
	/*
	private static Connection conn; 
	static{
		try{ 
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection ("jdbc:mysql://localhost:3306/HO","root","enafadeet");
		}
		catch(Exception e){ e.printStackTrace();
		}
		}
	public static Connection getConnection(){ return conn;
	}
	*/
	
	private final static String QUEUE_NAME = "BO1";

	  public static void main(String[] argv) throws Exception {
	    ConnectionFactory factory = new ConnectionFactory();
	    factory.setHost("localhost");
	    com.rabbitmq.client.Connection connection = factory.newConnection();
	    Channel channel = connection.createChannel();

	    channel.queueDeclare(QUEUE_NAME, false, false, false, null);
	    System.out.println(" [*] Waiting for messages. To exit press CTRL+C");
	    DeliverCallback deliverCallback = (consumerTag, delivery) -> {
	        String message = new String(delivery.getBody(), "UTF-8");
	        System.out.println(" [x] Received '" + message + "'");
	    };
	    channel.basicConsume(QUEUE_NAME, true, deliverCallback, consumerTag -> { });
	  }
}
