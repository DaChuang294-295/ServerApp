package linServer;

import java.awt.BorderLayout;
import java.awt.Container;
import java.io.*;
import java.net.*;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import linServer.FinalServer.DealSocket;
public class server extends JFrame {
	static JTextArea display = null;
	public server() throws IOException{
		super("FinalServer"); // 引用父类的超类
		
	
	}
	
	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		 
		int counter = 1;
		int tempnumber=0;
		float besttemp;
		// TODO 自动生成的方法存根
		JFrame c = new JFrame();
		display = new JTextArea(); // 创建文本域对象
		c.add(new JScrollPane(display), BorderLayout.CENTER);
		c.setSize(300, 300);
		c.setVisible(true);
		
		display.append("等待客户端的请求\n");
		
		
		
		/*try{
			new FinalServer();
		}catch(IOException e){
			System.out.println("wrong");
			e.printStackTrace();  
		}*/
		ServerSocket service = new ServerSocket(30000);
		ServerSocket server=new ServerSocket(4700);
		
		    new Thread(new ClientRunable(server)).start();
			new Thread(new AndroidRunable(service)).start();
			
			}
		
		/*ServerSocket server = null;
		try {
			server = new ServerSocket(30000);
		} catch (IOException e2) {
			// TODO 自动生成的 catch 块
			e2.printStackTrace();
		}
		Socket socket = null;
		try {
			socket = server.accept();
		} catch (IOException e1) {
			// TODO 自动生成的 catch 块
			e1.printStackTrace();
		}
		String line = null;
		InputStream input;
		OutputStream output;
		String str = "已连接";
		try {
			String option,code,family_id,family_password,user_id,user_password,operation,date;
			float temp;//向客户端发送信息
			output = socket.getOutputStream();
			input = socket.getInputStream();
			BufferedReader bff = new BufferedReader(
					new InputStreamReader(input));
			//半关闭socket  
			socket.shutdownOutput();
			//获取客户端的信息
			Database db=new Database();
			option=bff.readLine();
			switch(option){
			case "familyLog":{
				family_id=bff.readLine();
				System.out.println(family_id);
				family_password=bff.readLine();
				System.out.println(family_password);
				int i=db.checkFamily(family_id,family_password);
				if(i==1){
					//output.write("true".getBytes("gbk"));
					//output.flush();
					display.append("来自:"+ socket.getInetAddress().getHostName()+"的家庭"+family_id+"登录成功\n");
					display.setCaretPosition(display.getText().length());
			}else{
				display.append("hi");  
  			}
				break;
				}
			
		    case "familyExit":{
			family_id=bff.readLine();
			db.exitFamily(family_id);
			display.append("来自:"+ socket.getInetAddress().getHostName()+"的家庭"+family_id+"登出\n");
			display.setCaretPosition(display.getText().length());
			break;
		   }
			}
			//关闭输入输出流
			output.close();
			bff.close();
			input.close();
			socket.close();

		} catch (IOException e) {
			e.printStackTrace();
		}*/
	}
	

	
