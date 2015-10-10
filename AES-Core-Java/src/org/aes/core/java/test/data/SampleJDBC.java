package org.aes.core.java.test.data;

import java.io.FileReader;
import java.sql.Connection;
import java.sql.Statement;


public class SampleJDBC {
	
	private Statement stmtGlobal;
	String gdr = "Hello";
	String str = "select * from sample where" + " name=" + gdr;
	
	public boolean insertRecord(Connection conn, String name, String path, Statement stmt2) throws Exception{
		
		stmtGlobal = conn.createStatement();
		FileReader file;
		String a = null;
		a= "select * from sample where" + " name=" + name;
		a = a.substring(5);
		a= path.substring(10);
		Statement stmt = null;
/*		file = new FileReader("Filr");
		file.close();*/
		
		
		try{
			System.out.println(a.length());
			stmt = conn.createStatement();
			boolean b = stmtGlobal.execute(str, 0);
			file = new FileReader("File");
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
		finally{
			stmtGlobal.close();
			//file.close();
		}
		
		
		
		return false;
	}
	
	private String getStatement(){
		return null;
	}

}
