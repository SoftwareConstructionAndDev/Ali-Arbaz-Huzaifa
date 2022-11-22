import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class Data_Fetch {

	
	public void SendData(File file) {
		
		ArrayList<String> Array2=new ArrayList<String>();
		ArrayList<String> Array3=new ArrayList<String>();
		ArrayList<String> Array1=new ArrayList<String>();
		
		try {
   
	DocumentBuilderFactory Al = DocumentBuilderFactory.newInstance();  
	  
	DocumentBuilder Builder = Al.newDocumentBuilder();  
	Document Dc = Builder.parse(file);  
	Dc.getDocumentElement().normalize();  
	NodeList S = Dc.getElementsByTagName("document");  
		Node n = S.item(0);  
	
	if (n.getNodeType() == Node.ELEMENT_NODE)   
	{  
	Element wor= (Element) n;  
	
	
	
	
	
   Array1.add(wor.getElementsByTagName("title").item(0).getTextContent());
   
	Array2.add(wor.getElementsByTagName("author").item(0).getTextContent());
    
	Array3.add(wor.getElementsByTagName("section").item(0).getTextContent());
	}
	}
	catch(Exception e) {
		
	}
     
	
		String Str="jdbc:mysql://localhost:3306/spell_checker";
		   String Pass="root";
		   String User="";
	try {
    	Connection con = DriverManager.getConnection(Str,Pass,User);
    	
    	for(int i=0;i<Array2.size();i++) {
    		try {
    		
	        PreparedStatement Que = con.prepareStatement("insert into paragraph values("+null+",'"+Array1.get(i)+"','"+Array2.get(i)+"','"+Array3.get(i)+"')");
	        Que.execute();
    		
    		
    		}
	        catch(SQLException e) {
		    	
		    }
    	}
    	con.close();
    	System.out.println("FIle Uploadeed");
    }
    catch(SQLException e) {
    	System.out.println(e);
    }

	}
}
