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
		super("FinalServer"); // ���ø���ĳ���
		
	
	}
	
	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		 
		int counter = 1;
		int tempnumber=0;
		float besttemp;
		// TODO �Զ����ɵķ������
		JFrame c = new JFrame();
		display = new JTextArea(); // �����ı������
		c.add(new JScrollPane(display), BorderLayout.CENTER);
		c.setSize(300, 300);
		c.setVisible(true);
		
		display.append("�ȴ��ͻ��˵�����\n");
		
		
		
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
			// TODO �Զ����ɵ� catch ��
			e2.printStackTrace();
		}
		Socket socket = null;
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
			String option,code,family_id,family_password,user_id,user_password,operation,date;
			float temp;//��ͻ��˷�����Ϣ
			output = socket.getOutputStream();
			input = socket.getInputStream();
			BufferedReader bff = new BufferedReader(
					new InputStreamReader(input));
			//��ر�socket  
			socket.shutdownOutput();
			//��ȡ�ͻ��˵���Ϣ
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
					display.append("����:"+ socket.getInetAddress().getHostName()+"�ļ�ͥ"+family_id+"��¼�ɹ�\n");
					display.setCaretPosition(display.getText().length());
			}else{
				display.append("hi");  
  			}
				break;
				}
			
		    case "familyExit":{
			family_id=bff.readLine();
			db.exitFamily(family_id);
			display.append("����:"+ socket.getInetAddress().getHostName()+"�ļ�ͥ"+family_id+"�ǳ�\n");
			display.setCaretPosition(display.getText().length());
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
		}*/
	}
	

	
