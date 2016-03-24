package com.wing.mainApp.util;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import com.wing.mainApp.util.config.ConfigFileHandler;

import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.File;
import java.io.FilenameFilter;
import java.util.HashMap;
 
public class Langxml {
    static private Langxml xml;
    static private Object obj = new Object();
    static private Object obj2 = new Object();
    private HashMap kolangmap ; // 한국어 map 	
    private HashMap japanlangmap;   // 일본어 map
    private HashMap enlangmap ;  // 영어 map
    private HashMap chlangmap ;  // 중국어 번체
    private HashMap ch_cnlangmap ; // 중국어 간체 
    
    private String  lang = "ko";
    

    
    
    static public Langxml getinstance() {
    	
    	
	    	if (xml == null) {
	    		synchronized(obj) {
	    			if (xml == null) {
	    				xml = new Langxml();
	    				xml.LoadLang();
	    			}    			    		
	    		}    		    		
	    	}
	    	
	    	 
			    	
    	return xml;
    }
	
    
    public String []GetXmlList(String dir)  
    {
    	

    	File file = new File(dir);
        String[] list = file.list(new FilenameFilter()
        {
            @Override
            public boolean accept(File dir, String name) 
            {
                return name.toLowerCase().endsWith(".xml");
            }
        });
            	
    	return list;
    }
    
    public void ParseXml(String filename) 
    {
           
    	try {
    	 DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
    	  DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
    	  Document doc = dBuilder.parse(filename);
    	  doc.getDocumentElement().normalize();
    	  
    	  NodeList descNodes = doc.getElementsByTagName("Lang");
    	  

    		 
          for(int i=0; i<descNodes.getLength();i++){

        	 String id = "";
        	 String ko="";
        	 String  en="";
        	 String  ja = "";
        	 String  ch = "";
        	 String  ch_cn = "";
        	  
              for(Node node = descNodes.item(i).getFirstChild(); node!=null; node=node.getNextSibling()){ //첫번째 자식을 시작으로 마지막까지 다음 형제를 실행
            	  
                  if(node.getNodeName().equals("id")){
                	  id = node.getTextContent();                       	
                    }else if(node.getNodeName().equals("ko")){
                    	ko = node.getTextContent();                                       	
                  }else if(node.getNodeName().equals("ja")){
                      ja =  node.getTextContent();
                  }else if(node.getNodeName().equals("ch")){
                    ch =  node.getTextContent();
                  } else if(node.getNodeName().equals("ch_cn")){
                      ch_cn =  node.getTextContent();
                    } else if(node.getNodeName().equals("en")){
                        en =  node.getTextContent();
                    }   
  
              }
              
               if (StringUtil.isNull(id)) 
            	   continue;
               
               if (StringUtil.isNull(ko)) 
            	   continue;
               
               if (StringUtil.isNull(en)) 
            	   en = ko;
               
               if (StringUtil.isNull(ja)) 
            	   ja = ko;
               
               if (StringUtil.isNull(ch)) 
            	   ch = ko;
               
               if (StringUtil.isNull(ch_cn)) 
            	   ch_cn = ko;
                              
               this.kolangmap.put(id, ko);
               this.japanlangmap.put(id, ja);
               this.enlangmap.put(id, en);
               this.chlangmap.put(id, ch);
               this.ch_cnlangmap.put(id, ch_cn);
          }

    	  
    	} catch (Exception ex) {
    		
    		
    	}
    	
    }
    
    
    private void reload()
    {
    	synchronized(obj2) {
    	 LoadLang();
    	}
    }
    
    
    public void LoadLang()
    {
    	
    	
    	String dir = getInitDirectory();
    	String files[] = GetXmlList(dir);
    	
    	kolangmap = new HashMap(); // 한국어 map 	
        japanlangmap = new HashMap();  // 일본어 map
        enlangmap = new HashMap();  // 영어 map
        chlangmap = new HashMap();  // 중국어 번체
        ch_cnlangmap = new HashMap(); // 중국어 간체 
    	
    	
    	for(String file :files) {
    		String filename = dir + File.separator+ file;    		
    		ParseXml(filename);    		
    	}
    	    	
    }
    
	public  String getInitDirectory() 
	{

		String sPath="";

		sPath = Langxml.class.getProtectionDomain()
				.getCodeSource().getLocation().getPath();
		// WEB-INF 위치

		int nFindInx = sPath.indexOf("WEB-INF");

		if (nFindInx > 0)
			sPath = sPath.substring(0, nFindInx + 7);

  
		String sConfPath="";
		sConfPath = sPath + File.separator + "Conf"+File.separator +"Lang";				

		return sConfPath;		
	}
    
	private Langxml()
	{
		
		
	}
	
	static public  HashMap load() {		
	 	synchronized(obj2) { 
		Langxml xml =  getinstance();				
		return  xml.getMap();
	 	}
	}
	
	
	public HashMap getMap()
	{
		synchronized(obj2) {
		return getMap(this.lang);
		}
		
	}
	
	
	public HashMap getMap(String lang)
	{
		
		synchronized(obj2) { 
		
		this.lang = lang;
		
		if (lang.toLowerCase().equals("ko")) {
			
			 return kolangmap;
		}
		
		if (lang.toLowerCase().equals("ja")) {
			
			 return japanlangmap;
		}

		if (lang.toLowerCase().equals("en")) {		
			
			 return enlangmap;
		}

		if (lang.toLowerCase().equals("ch")) {	
			 return chlangmap;
		}
		
		
		if (lang.toLowerCase().equals("ch_cn")) {
			 return ch_cnlangmap;
		}
		
		
		return kolangmap;
		}
	}
	
	static public  HashMap load(String lang) {		
		Langxml xml =  getinstance();				
		return  xml.getMap(lang); 
	}
	
	static public void reloadxml( ) {
		 getinstance().reload();						
	}
		
}
