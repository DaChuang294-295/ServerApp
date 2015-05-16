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
					//display.append("来自:"+ socket.getInetAddress().getHostName()+"的家庭"+family_id+"登出\n");
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
					//display.append("来自:"+family_id+"家庭的用户"+user_id+"登出\n");
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
						/*display.append("来自:"+ socket.getInetAddress().getHostName()+"的家庭"+family_id+"登录成功\n");
						display.setCaretPosition(display.getText().length());*/
					}else{
						out.println("false");
						out.flush();
						/*display.append("来自:"+ socket.getInetAddress().getHostName()+"的家庭"+family_id+"登录失败\n");
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
						/*display.append("来自:"+family_id+"家庭的用户"+user_id+"登录成功\n");
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
						/*display.append("来自:"+ socket.getInetAddress().getHostName()+"成功注册新家庭"+family_id+"\n");
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
						/*display.append("来自:"+family_id+"的家庭注册成功新用户，id="+user_id+"\n");
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
						/*display.append("来自"+family_id+"家庭的用户"+user_id+"插入一条ipc记录\n");
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
						/*display.append("来自"+family_id+"家庭的用户"+user_id+"插入一条ZigBee记录\n");
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
						/*display.append("来自"+family_id+"家庭的用户"+user_id+"传来了一个温度\n");
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
					/*display.append("来自"+family_id+"家庭的用户"+user_id+"调取了日期为"+date+"的ipc记录\n");
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
					/*display.append("来自"+family_id+"家庭的用户"+user_id+"调取了日期为"+date+"的ZigBee记录\n");
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
					/*display.append("来自"+family_id+"家庭的用户"+user_id+"调取了日期为"+date+"的温度记录\n");
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
	}	// TODO 自动生成的方法存根
	}	
	}


