package com.wing.mainApp.util.sql;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.wing.mainApp.util.StringUtil;

public class DbInfoMap {
  private HashMap map = new HashMap();
  
  public DbInfoMap() {
	  // 
	  LoadInfo();	  
  }

  
  private void LoadInfo()
  {	  	  
	  String filename="";
	  String dir = getInitDirectory();
	  filename = dir +File.separator +"dbinfo.xml";	  	  
	  

	  
	  ParseXml(filename);
	  
  }
  
  
	public  String getInitDirectory() 
	{

		String sPath="";

		sPath = DbInfoMap.class.getProtectionDomain()
				.getCodeSource().getLocation().getPath();
		// WEB-INF 위치

		int nFindInx = sPath.indexOf("WEB-INF");

		if (nFindInx > 0)
			sPath = sPath.substring(0, nFindInx + 7);


		String sConfPath="";
		sConfPath = sPath + File.separator + "Conf"+File.separator +"DB";				

		return sConfPath;		
	}
  
  
  public void ParseXml(String filename) 
  {
         
  	try {
  	 DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
  	  DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
  	  Document doc = dBuilder.parse(filename);
  	  doc.getDocumentElement().normalize();
  	  
  	  NodeList descNodes = doc.getElementsByTagName("DB");
  	  

  		 
        for(int i=0; i<descNodes.getLength();i++){

   
        String dbid = "";
      	 String dbname = "";
      	 String url = "";
      	 
      	 String    porttemp = "";
      	 String    drivename ="";
      	
      	  
            for(Node node = descNodes.item(i).getFirstChild(); node!=null; node=node.getNextSibling()){ //첫번째 자식을 시작으로 마지막까지 다음 형제를 실행
          	  
                 if(node.getNodeName().equals("dbname")){
                  	dbname = node.getTextContent().trim();                                       	
                }else if(node.getNodeName().equals("url")){
                  url =  node.getTextContent().trim();
                } else if(node.getNodeName().equals("defaultport")){             
                  porttemp =  node.getTextContent().trim();
                } else if(node.getNodeName().equals("drivename")){             
                	drivename =  node.getTextContent().trim();
                  } else if(node.getNodeName().equals("dbid")){             
                	  dbid =  node.getTextContent().trim();
                  }

            }
            
          
            if (StringUtil.isNull(dbid)) 
          	    continue;
             
            if (StringUtil.isNull(dbname)) 
          	    continue;
             
             if (StringUtil.isNull(drivename)) 
          	    continue;
             
             DbInfo info = new DbInfo();
             info.setDbname(dbname);
             info.setUrl(url);
             info.setDbid(dbid);
             info.setDrivename(drivename);

             int port = 0;
             try {
            	 port = Integer.parseInt(porttemp);             
             } catch (Exception ex) {

             }
             info.setDefaultport(port);             
             map.put(dbid, info);
             
        }

  	  
  	} catch (Exception ex) {
  		
  		
  	}
  	
  }

  
  public DbInfo GetDbInfo(String dbid)
  {
	  return (DbInfo)map.get(dbid);	  
  }
  
  public HashMap GetMap()
  {	  
	  return map;
  }
  

}
