package rikmuld.core.helper;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;

import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.src.ModLoader;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class CloakHelper extends RenderPlayer{
	
	static URL url;
	static URLConnection conn;
	static DocumentBuilderFactory factory;
	static DocumentBuilder builder;
	static Document doc;
	static Transformer xform;

	public static String[] developers;
	public static String[] codinghelp;
	public static String[] help;
	
	public static void GetXmlFile()
	{
		try 
		{
			url = new URL("http://rikmuld.com/cloaks.xml");
		} 	
		catch (MalformedURLException e) 
		{
			e.printStackTrace();
		}
		
		try 
		{
			conn = url.openConnection();
		} 	
		catch (IOException e) 
		{
			e.printStackTrace();
		}

		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		
		try
		{
			builder = factory.newDocumentBuilder();
		}
		catch (ParserConfigurationException e) 
		{	
			e.printStackTrace();
		}
		
		try 
		{
			doc = builder.parse(conn.getInputStream());
		} 
		catch (SAXException e1)
		{	
			e1.printStackTrace();
		} 
		catch (IOException e1)
		{
			e1.printStackTrace();
		}
	}
	
	public static void GetUsers()
	{
		GetXmlFile();
		if(doc!=null)
		{	
			NodeList developer = doc.getElementsByTagName("developer");
			NodeList codinghelpers = doc.getElementsByTagName("codinghelpers");
			NodeList helper = doc.getElementsByTagName("helpers");
			
			Node dev = developer.item(0);	
			
			int manyCoders = codinghelpers.getLength();			
			Node[] coders = new Node[manyCoders+1];
			
			int manyHelpers = helper.getLength();			
			Node[] helpers = new Node[manyHelpers+1];
			
			for (int x = 0; x<manyCoders; x++)
			{
				coders[x] = codinghelpers.item(x);
			}
			
			for (int x = 0; x<manyHelpers; x++)
			{
				helpers[x] = helper.item(x);
			}
			
			developers = new String[1];
			codinghelp = new String[manyCoders];
			help = new String[manyHelpers];
			
			developers[0] = dev.getTextContent();
			
			for (int x = 0; x<manyCoders; x++)
			{
				codinghelp[x] = coders[x].getTextContent();
			}
			
			for (int x = 0; x<manyHelpers; x++)
			{
				help[x] = helpers[x].getTextContent();	
			}
		}
	}
	
	 public static void init()
	 {
		 GetUsers();
		 EntityPlayer entityPlayer = ModLoader.getMinecraftInstance().thePlayer;
		 
		 if(ModLoader.getMinecraftInstance().thePlayer!=null)
		 {
			 for(int x = 0; x<developers.length; x++)
			 {
				 if(entityPlayer.username.equals(developers[x]))
				 {
					 entityPlayer.cloakUrl = "rikmuld.com/cloakDev.png";
				 }
			 }
			 
			 for(int x = 0; x<codinghelp.length; x++)
			 {
				 if(entityPlayer.username.equals(codinghelp[x]))
				 {
					 entityPlayer.cloakUrl = "rikmuld.com/cloakCoder.png";
				 }
			 }
			 
			 for(int x = 0; x<help.length; x++)
			 {
				 if(entityPlayer.username.equals(help[x]))
				 {
					 entityPlayer.cloakUrl = "rikmuld.com/cloakHelper.png";
				 }
			 }
	 	}
	 }
}
