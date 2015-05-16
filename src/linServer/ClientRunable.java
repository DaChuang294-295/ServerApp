package linServer;

import java.awt.List;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class ClientRunable implements Runnable {
	static Socket socket;
	private ServerSocket server;
	private DataInputStream in;
	private PrintStream out;
	static String tempre="0";
	Boolean get=false;
	ArrayList<Socket> sockets;
	
	public ClientRunable(ServerSocket s) throws IOException {  
        this.server = s;   
    } 
	@Override
	public void run() {
		while(true){
		try{
			String option,code,family_id,family_password,user_id,user_password,operation,date;
			float temp;
			System.out.println("client "+AndroidRunable.option);
			socket=server.accept();
			ArrayList<Socket> sockets = new ArrayList();
			sockets.add(socket);
			 this.in = new DataInputStream(socket.getInputStream());  
		        this.out =new PrintStream(new BufferedOutputStream(socket.getOutputStream()));  
			Database db=new Database();
			option=in.readLine();
			switch(option){
				case "familyExit":{
					family_id=in.readLine();
					db.exitFamily(family_id);
					System.out.println(option);
					//display.append("����:"+ socket.getInetAddress().getHostName()+"�ļ�ͥ"+family_id+"�ǳ�\n");
					//display.setCaretPosition(display.getText().length());
					break;
				}
				case "userExit":{
					family_id=in.readLine();
					user_id=in.readLine();
					System.out.println(option);
					int i=db.exitUser(family_id,user_id);
					if(i==1){
						out.println("true");
						out.flush();
					}else{
						out.println("false");
						out.flush();
					}
					//display.append("����:"+family_id+"��ͥ���û�"+user_id+"�ǳ�\n");
					//display.setCaretPosition(display.getText().length());
					break;
				}
				case "familyLog":{
					family_id=in.readLine();
					System.out.println(family_id);
					family_password=in.readLine();
					System.out.println(family_password);
					int i=db.checkFamily(family_id,family_password);
					if(i==1){
						out.println("true");
						out.flush();
						/*display.append("����:"+ socket.getInetAddress().getHostName()+"�ļ�ͥ"+family_id+"��¼�ɹ�\n");
						display.setCaretPosition(display.getText().length());*/
					}else{
						out.println("false");
						out.flush();
						/*display.append("����:"+ socket.getInetAddress().getHostName()+"�ļ�ͥ"+family_id+"��¼ʧ��\n");
						display.setCaretPosition(display.getText().length());
*/					}
					break;
				}
				case "userLog":{
					family_id=in.readLine();
					user_id=in.readLine();
					user_password=in.readLine();
					System.out.println(option);
					int i=db.checkUser(family_id,user_id,user_password);
					if(i==1){
						out.println("true");
						out.flush();
						/*display.append("����:"+family_id+"��ͥ���û�"+user_id+"��¼�ɹ�\n");
						display.setCaretPosition(display.getText().length());*/
					}else{
						out.println("false");
						out.flush();
					}
					break;
				}
				case "familyReg":{
					code=in.readLine();
					family_id=in.readLine();
					family_password=in.readLine();
					System.out.println(option);
					int i=db.familyReg(code,family_id,family_password);
					if(i==1){
						out.println("true");
						out.flush();
						/*display.append("����:"+ socket.getInetAddress().getHostName()+"�ɹ�ע���¼�ͥ"+family_id+"\n");
						display.setCaretPosition(display.getText().length());*/
					}else{
						out.println("false");
						out.flush();
					}
					break;
				}
				case "userReg":{
					family_id=in.readLine();
					user_id=in.readLine();
					user_password=in.readLine();
					System.out.println(option);
					int i=db.userReg(family_id,user_id,user_password);
					if(i==1){
						out.println("true");
						out.flush();
						/*display.append("����:"+family_id+"�ļ�ͥע��ɹ����û���id="+user_id+"\n");
						display.setCaretPosition(display.getText().length());*/
					}else{
						out.println("false");
						out.flush();
					}
					break;
				}
				case "IPCInsertOption":{
					family_id=in.readLine();
					user_id=in.readLine();
					operation=in.readLine();
					System.out.println(option);
					int i=db.insertIPC_diary(family_id,user_id,operation);
					if(i==1){
						out.println("true");
						out.flush();
						/*display.append("����"+family_id+"��ͥ���û�"+user_id+"����һ��ipc��¼\n");
						display.setCaretPosition(display.getText().length());*/
					}else{
						out.println("false");
						out.flush();
					}
					break;
				}
				case "ZigBeeInsertOption":{
					family_id=in.readLine();
					user_id=in.readLine();
					operation=in.readLine();
					System.out.println(option);
					int i=db.insertZigBee_diary(family_id,user_id,operation);
					if(i==1){
						out.println("true");
						out.flush();
						/*display.append("����"+family_id+"��ͥ���û�"+user_id+"����һ��ZigBee��¼\n");
						display.setCaretPosition(display.getText().length());*/
					}else{
						out.println("false");
						out.flush();
					}
					break;
				}
				case "TempInsertOption":{
					family_id=in.readLine();
					user_id=in.readLine();
					operation=in.readLine();
					System.out.println(option);
					temp=Float.parseFloat(operation);
					int i=db.insertTemperature_diary(family_id,user_id,temp);
					if(i==1){
						out.println("true");
						out.flush();
						/*display.append("����"+family_id+"��ͥ���û�"+user_id+"������һ���¶�\n");
						display.setCaretPosition(display.getText().length());*/
					}else{
						out.println("false");
						out.flush();
					}
					break;
				}
				case "IPCSelectOption":{
					family_id=in.readLine();
					user_id=in.readLine();
					date=in.readLine();
					System.out.println(option);
					db.selectIPC_diary(family_id,user_id,date);
					out.println(db.ar_description.size());
					out.flush();
					for(int i=0;i<db.ar_description.size();i++){
						out.println(db.ar_description.get(i));
					}
					out.flush();
					/*display.append("����"+family_id+"��ͥ���û�"+user_id+"��ȡ������Ϊ"+date+"��ipc��¼\n");
					display.setCaretPosition(display.getText().length());*/
					break;
				}
				case "ZigBeeSelectOption":{
					family_id=in.readLine();
					user_id=in.readLine();
					date=in.readLine();
					System.out.println(option);
					db.selectZigBee_diary(family_id,user_id,date);
					out.println(db.ar_description.size());
					out.flush();
					for(int i=0;i<db.ar_description.size();i++){
						out.println(db.ar_description.get(i));
					}
					out.flush();
					/*display.append("����"+family_id+"��ͥ���û�"+user_id+"��ȡ������Ϊ"+date+"��ZigBee��¼\n");
					display.setCaretPosition(display.getText().length());*/
					break;
				}
				case "TempSelectOption":{
					family_id=in.readLine();
					user_id=in.readLine();
					date=in.readLine();
					System.out.println(option);
					db.selectTemp_record(family_id,user_id,date);
					out.println(db.ar_description.size());
					out.flush();
					for(int i=0;i<db.ar_description.size();i++){
						out.println(db.ar_description.get(i));
					}
					out.flush();
					/*display.append("����"+family_id+"��ͥ���û�"+user_id+"��ȡ������Ϊ"+date+"���¶ȼ�¼\n");
					display.setCaretPosition(display.getText().length());*/
					break;
				}
				case "Sensor":{
					get=true;
					tempre=in.readLine();
					
					break;
				}
				/*case "getBestTemp":{
					family_id=in.readLine();
					user_id=in.readLine();
					besttemp=db.selectBestTemp(family_id,user_id);
					if(besttemp!=0)
					{
						out.println("true");
						out.flush();
						out.println(besttemp);
						out.flush();
					}else{
						out.println("false");
						out.flush();
					}
					break;
				}*/
				default: break;
			}
			switch(AndroidRunable.option){
			case "nothing":{
				break;
			}
			case "familyLog":{
				out=new PrintStream(new BufferedOutputStream(socket.getOutputStream()));
				System.out.println("client"+option);
				out.println(AndroidRunable.option);
				out.flush();
				break;
				}
			case "familyExit":{
				out=new PrintStream(new BufferedOutputStream(socket.getOutputStream()));
				System.out.println("client"+option);
				out.println(AndroidRunable.option);
				out.flush();
				break;
				}
			case "OpenAir":{
				out=new PrintStream(new BufferedOutputStream(socket.getOutputStream()));
				System.out.println("client"+option);
				out.println(AndroidRunable.option);
				out.flush();
				break;
			}
			case "CloseAir":{
				out=new PrintStream(new BufferedOutputStream(socket.getOutputStream()));
				System.out.println("client"+option);
				out.println(AndroidRunable.option);
				out.flush();
				break;
			}
			case "Temp":{
				out=new PrintStream(new BufferedOutputStream(socket.getOutputStream()));
				System.out.println("client"+option);
				out.println(AndroidRunable.option);
				out.flush();
				break;
			}
			case "GetTemp":{
				out=new PrintStream(new BufferedOutputStream(socket.getOutputStream()));
				System.out.println("client "+option);
				out.println(AndroidRunable.option);
				out.flush();
				break;
			}
			default: break;
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}	// TODO �Զ����ɵķ������
	}	
	}


