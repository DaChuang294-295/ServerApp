package linServer;

import java.io.*;
import java.net.*;
import java.util.*;

import javax.swing.*;

import java.awt.*;

public class FinalServer extends JFrame{
	private JTextArea display; 
	int counter = 1;
	int tempnumber=0;
	float besttemp;

	public FinalServer() throws IOException{
		super("FinalServer"); // 引用父类的超类
		Container c = getContentPane();
		display = new JTextArea(); // 创建文本域对象
		c.add(new JScrollPane(display), BorderLayout.CENTER);
		setSize(300, 300);
		setVisible(true);
		
		display.append("等待客户端的请求\n");
		
		/*ServerSocket server=new ServerSocket(4700);
		while(true){
			Socket socket=server.accept();
			new DealSocket(socket).start();
		}*/
	}
	
	/*public static void main(String[] args) {  
		try{
			new FinalServer();
		}catch(IOException e){
			e.printStackTrace();  
		}
	}*/

class DealSocket extends Thread{
	private Socket socket;
	private DataInputStream in;
	private PrintStream out;
	
	public DealSocket(Socket s) throws IOException {  
        this.socket = s;  
        this.in = new DataInputStream(socket.getInputStream());  
        this.out =new PrintStream(new BufferedOutputStream(socket.getOutputStream()));  
    } 
	
	public void run(){
		try{
			String option,code,family_id,family_password,user_id,user_password,operation,date;
			float temp;
			Database db=new Database();
			option=in.readLine();
			switch(option){
				case "familyExit":{
					family_id=in.readLine();
					db.exitFamily(family_id);
					display.append("来自:"+ socket.getInetAddress().getHostName()+"的家庭"+family_id+"登出\n");
					display.setCaretPosition(display.getText().length());
					break;
				}
				case "userExit":{
					family_id=in.readLine();
					user_id=in.readLine();
					int i=db.exitUser(family_id,user_id);
					if(i==1){
						out.println("true");
						out.flush();
					}else{
						out.println("false");
						out.flush();
					}
					display.append("来自:"+family_id+"家庭的用户"+user_id+"登出\n");
					display.setCaretPosition(display.getText().length());
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
						display.append("来自:"+ socket.getInetAddress().getHostName()+"的家庭"+family_id+"登录成功\n");
						display.setCaretPosition(display.getText().length());
					}else{
						out.println("false");
						out.flush();
						display.append("来自:"+ socket.getInetAddress().getHostName()+"的家庭"+family_id+"登录失败\n");
						display.setCaretPosition(display.getText().length());
					}
					break;
				}
				case "userLog":{
					family_id=in.readLine();
					user_id=in.readLine();
					user_password=in.readLine();
					int i=db.checkUser(family_id,user_id,user_password);
					if(i==1){
						out.println("true");
						out.flush();
						display.append("来自:"+family_id+"家庭的用户"+user_id+"登录成功\n");
						display.setCaretPosition(display.getText().length());
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
					int i=db.familyReg(code,family_id,family_password);
					if(i==1){
						out.println("true");
						out.flush();
						display.append("来自:"+ socket.getInetAddress().getHostName()+"成功注册新家庭"+family_id+"\n");
						display.setCaretPosition(display.getText().length());
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
					int i=db.userReg(family_id,user_id,user_password);
					if(i==1){
						out.println("true");
						out.flush();
						display.append("来自:"+family_id+"的家庭注册成功新用户，id="+user_id+"\n");
						display.setCaretPosition(display.getText().length());
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
					int i=db.insertIPC_diary(family_id,user_id,operation);
					if(i==1){
						out.println("true");
						out.flush();
						display.append("来自"+family_id+"家庭的用户"+user_id+"插入一条ipc记录\n");
						display.setCaretPosition(display.getText().length());
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
					int i=db.insertZigBee_diary(family_id,user_id,operation);
					if(i==1){
						out.println("true");
						out.flush();
						display.append("来自"+family_id+"家庭的用户"+user_id+"插入一条ZigBee记录\n");
						display.setCaretPosition(display.getText().length());
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
					temp=Float.parseFloat(operation);
					int i=db.insertTemperature_diary(family_id,user_id,temp);
					if(i==1){
						out.println("true");
						out.flush();
						display.append("来自"+family_id+"家庭的用户"+user_id+"传来了一个温度\n");
						display.setCaretPosition(display.getText().length());
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
					db.selectIPC_diary(family_id,user_id,date);
					out.println(db.ar_description.size());
					out.flush();
					for(int i=0;i<db.ar_description.size();i++){
						out.println(db.ar_description.get(i));
					}
					out.flush();
					display.append("来自"+family_id+"家庭的用户"+user_id+"调取了日期为"+date+"的ipc记录\n");
					display.setCaretPosition(display.getText().length());
					break;
				}
				case "ZigBeeSelectOption":{
					family_id=in.readLine();
					user_id=in.readLine();
					date=in.readLine();
					db.selectZigBee_diary(family_id,user_id,date);
					out.println(db.ar_description.size());
					out.flush();
					for(int i=0;i<db.ar_description.size();i++){
						out.println(db.ar_description.get(i));
					}
					out.flush();
					display.append("来自"+family_id+"家庭的用户"+user_id+"调取了日期为"+date+"的ZigBee记录\n");
					display.setCaretPosition(display.getText().length());
					break;
				}
				case "TempSelectOption":{
					family_id=in.readLine();
					user_id=in.readLine();
					date=in.readLine();
					db.selectTemp_record(family_id,user_id,date);
					out.println(db.ar_description.size());
					out.flush();
					for(int i=0;i<db.ar_description.size();i++){
						out.println(db.ar_description.get(i));
					}
					out.flush();
					display.append("来自"+family_id+"家庭的用户"+user_id+"调取了日期为"+date+"的温度记录\n");
					display.setCaretPosition(display.getText().length());
					break;
				}
				case "getBestTemp":{
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
				}
				default: break;
			}
			switch(AndroidRunable.option){
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
	}
}
}
/*public static String getRandomString(int length) { //length表示生成字符串的长度
String base = "abcdefghijklmnopqrstuvwxyz0123456789";   
Random random = new Random();   
StringBuffer sb = new StringBuffer();   
for (int i = 0; i < length; i++) {   
    int number = random.nextInt(base.length());   
    sb.append(base.charAt(number));   
}   
return sb.toString();   
} */

