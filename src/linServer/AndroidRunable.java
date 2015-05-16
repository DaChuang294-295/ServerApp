package linServer;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

public class AndroidRunable implements Runnable{
	static Socket socket;
	ServerSocket server=null;
    static String family_id;
    static String option="nothing";
    String a;
    static int i;
    Boolean get=false;
	public AndroidRunable(ServerSocket se) {
		
		this.server=se;
	}

	@Override
	public void run() {
		while (true) {
			
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
			String code,family_password,user_id,user_password,operation,date;
			float temp;//向客户端发送信息
			option="nothing";
			output = socket.getOutputStream();
			input = socket.getInputStream();
			BufferedReader bff = new BufferedReader(
					new InputStreamReader(input));
			if(get==true){
				String s="12%12";
				
				output = socket.getOutputStream();
				System.out.println(s);
				output.write(s.getBytes("gbk"));
				output.flush();
				get=false;//半关闭socket  
				socket.shutdownOutput();
				
			}
			//半关闭socket  
			socket.shutdownOutput();
			//获取客户端的信息
			Database db=new Database();
			option=bff.readLine();
			
			switch(option){
			case "nothing":{
				break;
			}
			case "familyLog":{
				//if(ClientRunable.socket!=null){
				System.out.println("我发了"+ClientRunable.socket.getInetAddress());	
				PrintStream out =new PrintStream(new BufferedOutputStream(ClientRunable.socket.getOutputStream()));
				out.println(option);
				out.flush();
				//}
				family_id=bff.readLine();
				System.out.println(family_id);
				family_password=bff.readLine();
				System.out.println(family_password);
				i=db.checkFamily(family_id,family_password);
				if(i==1){
					//output.write("true".getBytes("gbk"));
					//output.flush();
					System.out.println("来自:"+ socket.getInetAddress().getHostName()+"的家庭"+family_id+"登录成功\n");
					/*server.display.append("来自:"+ socket.getInetAddress().getHostName()+"的家庭"+family_id+"登录成功\n");
					server.display.setCaretPosition(display.getText().length());*/
			}else{
				//display.append("hi");  
  			}
				break;
				}
			
		    case "familyExit":{
			family_id=bff.readLine();
			db.exitFamily(family_id);
			System.out.println("来自:"+ socket.getInetAddress().getHostName()+"的家庭"+family_id+"登出\n");
			//display.append("来自:"+ socket.getInetAddress().getHostName()+"的家庭"+family_id+"登出\n");
			//display.setCaretPosition(display.getText().length());
			break;
		   }
		    case "OpenAir":{
		    	//if(ClientRunable.socket!=null){
					System.out.println("我发了"+ClientRunable.socket.getInetAddress());	
					PrintStream out =new PrintStream(new BufferedOutputStream(ClientRunable.socket.getOutputStream()));
					out.println("P");
					out.flush();
					//}
				//String ins=bff.readLine();
				System.out.println(option);
				//display.append("来自:"+ socket.getInetAddress().getHostName()+"的家庭"+family_id+"登出\n");
				//display.setCaretPosition(display.getText().length());
				break;
			   }
			
			case "Temp":{
				//if(ClientRunable.socket!=null){
					System.out.println("我发了"+ClientRunable.socket.getInetAddress());	
					PrintStream out =new PrintStream(new BufferedOutputStream(ClientRunable.socket.getOutputStream()));
					out.println("A");
					out.flush();
					//}
				a=bff.readLine();
				System.out.println(a);
				break;
			}
			case "CloseAir":{
				//if(ClientRunable.socket!=null){
					System.out.println("我发了"+ClientRunable.socket.getInetAddress());	
					PrintStream out =new PrintStream(new BufferedOutputStream(ClientRunable.socket.getOutputStream()));
					out.println("P");
					out.flush();
					//}
				System.out.println(option);
				break;
			}
			case "GetTemp":{
				
				get=true;
				
				System.out.println(option);
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
		}// TODO 自动生成的方法存根
		
	}
			
		
	}
	
}
