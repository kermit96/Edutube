package com.iedu.sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashSet;

import org.apache.commons.dbcp.BasicDataSource;

import com.iedu.config.Globalconfig;
import com.iedu.util.DbInfo;
import com.iedu.util.DbInfoMap;


public class BaseJDBCDao {
	protected BasicDataSource ds = new BasicDataSource();
	private  Connection con = null;
	
	private  String DatabaseProductName = "";

	protected HashSet<Statement> statements = new HashSet<Statement>();
	protected HashSet<PreparedStatement>  prestatements = new HashSet<PreparedStatement>();
	protected HashSet<ResultSet>  resultsets = new HashSet<ResultSet>();

	 public BaseJDBCDao(String drivename,String  url,String userid,String password )  throws  Exception
	{
		this(drivename,url,userid,password,4);	
	}
	 
	 

	
	public BaseJDBCDao(String drivename,String  url,String userid,String password,int initnum )  throws  Exception
	{
		try {					
			ds.setDriverClassName(drivename);
			ds.setUrl(url);
			ds.setUsername(userid);
			ds.setPassword(password);			
			ds.setInitialSize(initnum);

		} catch (Exception ex) {
			// TODO Auto-generated catch block
			ex.printStackTrace();
			throw ex;
		}

		try { 
			con =   getcon();
			DatabaseProductName = con.getMetaData().getDatabaseProductName();
		} catch (Exception ex) {
			throw ex;		
		}
	}
	
	
	public String getDatabaseProductName() {
		return DatabaseProductName;
	}




	public void setAutoCommit(boolean autoCommit)
	{
	     	
		try {
			getcon().setAutoCommit(autoCommit);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void Commit()
	{
	     	
		try {
			getcon().commit();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	public ResultSet GetResultStat(String sql) throws Exception
	{

		Statement  stat  =   getSTMT();

		ResultSet result= stat.executeQuery(sql);

		close(stat);

		resultsets.add(result);

		return result;
		
	}

	// result  
	

	/// Connection 을 얻어 온다.
	public Connection getcon() throws Exception   {

		if (con == null) {

			try { 

				con = ds.getConnection();
			} catch (Exception ex) {
				throw ex;		
			}

		}

		return con;
	}

	/// Statement 을 얻어 온다.
	public Statement getSTMT() throws Exception {

		Statement smt = null;
		try {
			smt = con.createStatement();			
			statements.add(smt);						
		} catch(Exception ex) {
			throw ex;					
		}

		return smt;
	}


	public Statement getSTMTForUpdate() throws Exception {

		Statement smt = null;
		try {
			smt = con.createStatement(ResultSet.TYPE_FORWARD_ONLY,ResultSet.CONCUR_UPDATABLE );			
			statements.add(smt);						
		} catch(Exception ex) {
			throw ex;					
		}

		return smt;
	}



	

/// PreparedStatement 을 얻어 온다.
	public PreparedStatement getPrepare(String sql) throws Exception {

		PreparedStatement smt = null;

		try {
			smt = con.prepareStatement(sql);
			this.prestatements.add(smt);			
		} catch(Exception ex) {
			throw ex;					
		}

		return smt;

	}
	

	public PreparedStatement getPrepareForUpdate(String sql) throws Exception {

		
		PreparedStatement smt = null;
		
		try {
			smt = con.prepareStatement(sql,ResultSet.TYPE_FORWARD_ONLY,ResultSet.CONCUR_UPDATABLE );
			this.prestatements.add(smt);			
		} catch(Exception ex) {
			throw ex;					
		}

		return smt;

	}
	
	
	

	public  void closeAll()
	{

		for(ResultSet set  :this.resultsets) {
			try { 
				set.close();
			} catch (Exception ex) {
				
			}
		}

		resultsets.clear();

		for(Statement stat: statements) {
			try {
				stat.close();
			} catch(Exception ex) {


			}
		}
		statements.clear();


		for(PreparedStatement stat: this.prestatements) {
			try {
				stat.close();
			} catch(Exception ex) {


			}
		}

		prestatements.clear();

		close(con);
		con = null;

	}




	public  void close(Object obj) {		
		try {
			if (obj instanceof ResultSet ) {

				((ResultSet)(obj)).close();
				resultsets.remove(obj);							
			}

			if (obj instanceof Connection ) {

				((Connection)(obj)).close();
				if (obj== this.con) 
					con = null;
			}


			if (obj instanceof PreparedStatement ) {
				((PreparedStatement)(obj)).close();
				this.prestatements.remove(obj);
			}

			if (obj instanceof Statement ) {

				((Statement)(obj)).close();
				this.statements.remove(obj);
			}

		} catch (Exception ex) {

			
		}

	}

	
	public static  BaseJDBCDao GetjdbcDao(String dbid,String host,  int port, String  dbname,String userid,String password) throws Exception
	{
		
		String drivename="";
		String url = "";
		
		DbInfoMap map = new DbInfoMap();
		
		DbInfo info = map.GetDbInfo(dbid);
		
		if (info == null)
			return null;
		
	    drivename = info.getDrivename();
	    url = info.getUrl(host,port,dbname);
   
		BaseJDBCDao dao = new BaseJDBCDao(drivename,url,userid,password);
	
		return dao;
	}
		
}