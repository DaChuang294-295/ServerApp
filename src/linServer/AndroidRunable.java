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
			// TODO �Զ����ɵ� catch ��
			e1.printStackTrace();
		}
		String line = null;
		InputStream input;
		OutputStream output;
		String str = "������";
		try {
			String code,family_password,user_id,user_password,operation,date;
			float temp;//��ͻ��˷�����Ϣ
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
				get=false;//��ر�socket  
				socket.shutdownOutput();
				
			}
			//��ر�socket  
			socket.shutdownOutput();
			//��ȡ�ͻ��˵���Ϣ
			Database db=new Database();
			option=bff.readLine();
			
			switch(option){
			case "nothing":{
				break;
			}
			case "familyLog":{
				//if(ClientRunable.socket!=null){
				System.out.println("�ҷ���"+ClientRunable.socket.getInetAddress());	
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
					System.out.println("����:"+ socket.getInetAddress().getHostName()+"�ļ�ͥ"+family_id+"��¼�ɹ�\n");
					/*server.display.append("����:"+ socket.getInetAddress().getHostName()+"�ļ�ͥ"+family_id+"��¼�ɹ�\n");
					server.display.setCaretPosition(display.getText().length());*/
			}else{
				//display.append("hi");  
  			}
				break;
				}
			
		    case "familyExit":{
			family_id=bff.readLine();
			db.exitFamily(family_id);
			System.out.println("����:"+ socket.getInetAddress().getHostName()+"�ļ�ͥ"+family_id+"�ǳ�\n");
			//display.append("����:"+ socket.getInetAddress().getHostName()+"�ļ�ͥ"+family_id+"�ǳ�\n");
			//display.setCaretPosition(display.getText().length());
			break;
		   }
		    case "OpenAir":{
		    	//if(ClientRunable.socket!=null){
					System.out.println("�ҷ���"+ClientRunable.socket.getInetAddress());	
					PrintStream out =new PrintStream(new BufferedOutputStream(ClientRunable.socket.getOutputStream()));
					out.println("P");
					out.flush();
					//}
				//String ins=bff.readLine();
				System.out.println(option);
				//display.append("����:"+ socket.getInetAddress().getHostName()+"�ļ�ͥ"+family_id+"�ǳ�\n");
				//display.setCaretPosition(display.getText().length());
				break;
			   }
			
			case "Temp":{
				//if(ClientRunable.socket!=null){
					System.out.println("�ҷ���"+ClientRunable.socket.getInetAddress());	
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
					System.out.println("�ҷ���"+ClientRunable.socket.getInetAddress());	
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
			//�ر����������
			output.close();
			bff.close();
			input.close();
			socket.close();

		} catch (IOException e) {
			e.printStackTrace();
		}// TODO �Զ����ɵķ������
		
	}
			
		
	}
	
}
