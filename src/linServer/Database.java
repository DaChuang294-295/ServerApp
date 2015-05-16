package linServer;

import java.sql.*;
import java.util.*;
public class Database {
	String driver = "com.mysql.jdbc.Driver";
	String user="root";
	String passwd="";
	String url="jdbc:mysql://localhost:3306/Intel_family";
	List<String> ar_description=new ArrayList<String>();
	List<Float> ar_TempRecord=new ArrayList<Float>();
	int tempNo;
	public void exitFamily(String family_id){
		System.out.println("exit");
		try {
			// 加载驱动程序
			Class.forName(driver);
			// 连续数据库
			Connection conn = DriverManager.getConnection(url, user, passwd);
			Statement statement = conn.createStatement();
			String sql = "update families set online=0 where family_id='"+family_id+"'";
			statement.executeUpdate(sql);
		} catch(ClassNotFoundException e) {
			System.out.println("Sorry,can`t find the Driver!");
			e.printStackTrace();
		} catch(SQLException e) {
			System.out.println("Sorry,can`t find the Driver!sql exception");
			e.printStackTrace();
		} catch(Exception e) {
			System.out.println("Sorry,can`t find the Driver!other");
			e.printStackTrace();
		}
	}
	public int exitUser(String family_id,String user_id){
		try {
			Class.forName(driver);
			Connection conn = DriverManager.getConnection(url, user, passwd);
			Statement statement = conn.createStatement();
			String sql = "update users set online=0 where family_id='"+family_id+"'"+" and user_id='"+user_id+"'";
			statement.executeUpdate(sql);
			return 1;
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
			return 0;
		} catch(SQLException e) {
			e.printStackTrace();
			return 0;
		} catch(Exception e) {
			e.printStackTrace();
			return 0;
		}
	}
	int checkUser(String family_id,String user_id,String user_password){
		try {
			Class.forName(driver);
			Connection conn = DriverManager.getConnection(url, user, passwd);
			Statement statement = conn.createStatement();
			String sql = "select online from families where family_id='"+family_id+"'";
			ResultSet rs = statement.executeQuery(sql);
			int check=3;
			while(rs.next()){
				check = rs.getInt("online");
			}
			if(check==1){
				sql = "select user_password from users where family_id='"+family_id+"' and user_id='"+user_id+"'";
				ResultSet rs1 = statement.executeQuery(sql);
				String password="";
				while(rs1.next()){
					password = rs1.getString("user_password");
				}
				if(!user_password.equals(password)){
					conn.close();
					return 0;
				}else{
					sql = "update users set online=1 where family_id='"+family_id+"'"+" and user_id='"+user_id+"'";
					statement.executeUpdate(sql);
					conn.close();
					return 1;
				}
			}else{
				return 0;
			}
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
			return 0;
		} catch(SQLException e) {
			e.printStackTrace();
			return 0;
		} catch(Exception e) {
			e.printStackTrace();
			return 0;
		}
	}
	int checkFamily(String family_id,String family_password){
		try {
			Class.forName(driver);
			Connection conn = DriverManager.getConnection(url, user, passwd);
			Statement statement = conn.createStatement();
			String sql = "select* from families where family_id='"+family_id+"'";
			ResultSet rs = statement.executeQuery(sql);
			int check=3;
			String password="";
			while(rs.next()){
				
				check = rs.getInt("online");
				password=rs.getString("family_password");
			}
			System.out.println(check);
			if(check==0){
				if(password.equals(family_password)){
					sql = "update families set online=1 where family_id='"+family_id+"'";
					statement.executeUpdate(sql);
					return 1;
				}else{
					return 0;
				}
			}else{
				return 0;
			}
		} catch(ClassNotFoundException e) {
			System.out.println("Sorry,can`t find the Driver!");
			e.printStackTrace();
			return 0;
		} catch(SQLException e) {
			System.out.println("Sorry,can`t find the Driver!sql");
			e.printStackTrace();
			return 0;
		} catch(Exception e) {
			System.out.println("Sorry,can`t find the Driver!other");
			e.printStackTrace();
			return 0;
		}
	}
	public int familyReg(String code,String family_id,String family_password){
		try {
			Class.forName(driver);
			Connection conn = DriverManager.getConnection(url, user, passwd);
			Statement statement = conn.createStatement();
			String sql = "select code from registration where code='"+code+"'";
			ResultSet rs = statement.executeQuery(sql);
			String key="";
			while(rs.next()){
				key = rs.getString("code");
			}
			if(key.equals(code)){
				sql = "insert into families values('"+family_id+"','"+family_password+"',0)";
				statement.executeUpdate(sql);
				sql = "delete from registration where code='"+code+"'";
				statement.executeUpdate(sql);
				return 1;
			}else{
				return 0;
			}
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
			return 0;
		} catch(SQLException e) {
			e.printStackTrace();
			return 0;
		} catch(Exception e) {
			e.printStackTrace();
			return 0;
		}
	}
	public int userReg(String family_id,String user_id,String user_password){
		try {
			Class.forName(driver);
			Connection conn = DriverManager.getConnection(url, user, passwd);
			Statement statement = conn.createStatement();
			String sql = "select online from families where family_id='"+family_id+"'";
			ResultSet rs = statement.executeQuery(sql);
			int check=3;
			while(rs.next()){
				check = rs.getInt("online");
			}
			if(check==1){
				sql="select user_id from users where user_id='"+user_id+"'";
				ResultSet rs1 = statement.executeQuery(sql);
				if(!rs1.next()){
					sql = "insert into users values('"+family_id+"','"+user_id+"','"+user_password+"',0,0)";
					statement.executeUpdate(sql);
					return 1;
				}else{
					return 0;
				}
			}else{
				return 0;
			}
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
			return 0;
		} catch(SQLException e) {
			e.printStackTrace();
			return 0;
		} catch(Exception e) {
			e.printStackTrace();
			return 0;
		}
	}
	public int insertIPC_diary(String family_id,String user_id,String operation){
		try {
			Class.forName(driver);
			Connection conn = DriverManager.getConnection(url, user, passwd);
			Statement statement = conn.createStatement();
			/*String sql0 = "use Intel_family";
			ResultSet rs0 = statement.executeQuery(sql0);*/
			String sql = "insert into IPC_diary values('"+family_id+"','"+user_id+"',curdate(),curtime(),'"+operation+"')";
			statement.executeUpdate(sql);
			return 1;
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
			return 0;
		} catch(SQLException e) {
			e.printStackTrace();
			return 0;
		} catch(Exception e) {
			e.printStackTrace();
			return 0;
		}
	}
	public int insertZigBee_diary(String family_id,String user_id,String operation){
		try {
			Class.forName(driver);
			Connection conn = DriverManager.getConnection(url, user, passwd);
			Statement statement = conn.createStatement();
			/*String sql0 = "use Intel_family";
			ResultSet rs0 = statement.executeQuery(sql0);*/
			String sql = "insert into ZigBee_diary values('"+family_id+"','"+user_id+"',curdate(),curtime(),'"+operation+"')";
			statement.executeUpdate(sql);
			return 1;
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
			return 0;
		} catch(SQLException e) {
			e.printStackTrace();
			return 0;
		} catch(Exception e) {
			e.printStackTrace();
			return 0;
		}
	}
	public int insertTemperature_diary(String family_id,String user_id,float temp){
		try {
			Class.forName(driver);
			Connection conn = DriverManager.getConnection(url, user, passwd);
			Statement statement = conn.createStatement();
			/*String sql0 = "use Intel_family";
			ResultSet rs0 = statement.executeQuery(sql0);*/
			String sql = "insert into temp_record values('"+family_id+"','"+user_id+"','"+temp+"',curdate(),curtime())";
			statement.executeUpdate(sql);
			return 1;
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
			return 0;
		} catch(SQLException e) {
			e.printStackTrace();
			return 0;
		} catch(Exception e) {
			e.printStackTrace();
			return 0;
		}
	}
	public void selectIPC_diary(String family_id,String user_id,String date){
		ar_description=new ArrayList<String>();
		try {
			Class.forName(driver);
			Connection conn = DriverManager.getConnection(url, user, passwd);
			Statement statement = conn.createStatement();
			/*String sql0 = "use Intel_family";
			ResultSet rs0 = statement.executeQuery(sql0);*/
			String sql = "select* from IPC_diary where family_id='"+family_id+"' and user_id='"+user_id+"' and date='"+date+"'";
			ResultSet rs = statement.executeQuery(sql);
			while(rs.next()) {
				/*ar_fid.add(rs.getString("family_id"));
				ar_uid.add(rs.getString("user_id"));
				ar_date.add(rs.getString("date"));
				ar_time.add(rs.getString("time"));
				ar_diary.add(rs.getString("diary"));*/
				ar_description.add(rs.getString("family_id")+"  "+rs.getString("user_id")+"  "+rs.getString("date")+"  "+rs.getString("time")+"  "+rs.getString("diary"));
			}
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
		} catch(SQLException e) {
			e.printStackTrace();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	public void selectZigBee_diary(String family_id,String user_id,String date){
		ar_description=new ArrayList<String>();
		try {
			Class.forName(driver);
			Connection conn = DriverManager.getConnection(url, user, passwd);
			Statement statement = conn.createStatement();
			/*String sql0 = "use Intel_family";
			ResultSet rs0 = statement.executeQuery(sql0);*/
			String sql = "select* from IPC_diary where family_id='"+family_id+"' and user_id='"+user_id+"' and date='"+date+"'";
			ResultSet rs = statement.executeQuery(sql);
			while(rs.next()) {
				/*ar_fid.add(rs.getString("family_id"));
				ar_uid.add(rs.getString("user_id"));
				ar_date.add(rs.getString("date"));
				ar_time.add(rs.getString("time"));
				ar_diary.add(rs.getString("diary"));*/
				ar_description.add(rs.getString("family_id")+rs.getString("user_id")+rs.getString("date")+rs.getString("time")+rs.getString("diary"));
			}
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
		} catch(SQLException e) {
			e.printStackTrace();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	public void selectTemp_record(String family_id,String user_id, String date){
		ar_description=new ArrayList<String>();
		try {
			Class.forName(driver);
			Connection conn = DriverManager.getConnection(url, user, passwd);
			Statement statement = conn.createStatement();
			/*String sql0 = "use Intel_family";
			ResultSet rs0 = statement.executeQuery(sql0);*/
			String sql = "select* from temp_record where family_id='"+family_id+"' and user_id='"+user_id+"' and date='"+date+"'";
			ResultSet rs = statement.executeQuery(sql);
			while(rs.next()) {
				/*ar_fid.add(rs.getString("family_id"));
				ar_uid.add(rs.getString("user_id"));
				ar_date.add(rs.getString("date"));
				ar_time.add(rs.getString("time"));
				ar_diary.add(rs.getString("diary"));*/
				ar_description.add(rs.getString("time")+rs.getFloat("temp"));
			}
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
		} catch(SQLException e) {
			e.printStackTrace();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	public float selectBestTemp(String family_id,String user_id){
		try {
			Class.forName(driver);
			Connection conn = DriverManager.getConnection(url, user, passwd);
			Statement statement = conn.createStatement();
			/*String sql0 = "use Intel_family";
			ResultSet rs0 = statement.executeQuery(sql0);*/
			String sql = "select* from temp_best where family_id='"+family_id+"' and user_id='"+user_id+"'";
			ResultSet rs = statement.executeQuery(sql);
			float temp=0;
			while(rs.next()){
				temp = rs.getFloat("expect");
			}
			return temp;
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
			return 0;
		} catch(SQLException e) {
			e.printStackTrace();
			return 0;
		} catch(Exception e) {
			e.printStackTrace();
			return 0;
		}
	}
}
