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
    private HashMap kolangmap = new HashMap(); // 한국어 map 	
    private HashMap japanlangmap = new HashMap();  // 일본어 map
    private HashMap enlangmap = new HashMap();  // 영어 map
    private HashMap chlangmap = new HashMap();  // 중국어 번체
    private HashMap ch_cnlangmap = new HashMap(); // 중국어 간체 
    
    static public Langxml getinstance() {       
    	if (xml == null) {
    		synchronized(xml) {
    			if (xml == null) {
    				xml = new Langxml();    				
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
    	
    	
    }
    
    public void LoadLang()
    {
    	String dir = getInitDirectory();
    	String files[] = GetXmlList(dir);
    	
    	this.ch_cnlangmap.clear();
    	this.chlangmap.clear();
    	this.enlangmap.clear();
    	this.japanlangmap.clear();
    	this.kolangmap.clear();
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
		sConfPath = sPath + File.separator + "conf"+File.separator +"lang";				
		
		
		return sConfPath;
	
		
	}

    
    
    
	private Langxml()
	{
		
		
	}
	
	public HashMap getMap(String lang)
	{
		if (lang.toLowerCase() == "korea") {
			
			 return kolangmap;
		}
		
		if (lang.toLowerCase() == "japan") {
			
			 return kolangmap;
		}
		

		if (lang.toLowerCase() == "english") {
			
			 return enlangmap;
		}

		
		if (lang.toLowerCase() == "ch") {
			
			 return chlangmap;
		}
		
		
		if (lang.toLowerCase() == "ch_cn") {
			
			 return ch_cnlangmap;
		}
		
		
		return kolangmap;
	}
	
	static public  HashMap load(String lang) {		
		Langxml xml =  getinstance();				
		return  xml.getMap(lang); 
	}
	
}
