package com.wing.mainApp.util;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.File;
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
