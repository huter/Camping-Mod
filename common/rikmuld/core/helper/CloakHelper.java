package rikmuld.core.helper;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import rikmuld.core.handlers.PlayerRenderingHandler;
import rikmuld.core.lib.Config;

public class CloakHelper implements Runnable{

	private static CloakHelper instance = new CloakHelper();
	static PlayerRenderingHandler playerRenderer;
	
	public boolean gotXml = false;
	
	public static String[] developers;
	public static String[] codinghelp;
	public static String[] help;
	
	static URL url;
	static URLConnection conn;
	static DocumentBuilderFactory factory;
	static DocumentBuilder builder;
	static Document doc;
	static Transformer xform;
	
	private int check= 0;
			
	@Override
	public void run() 
	{
		if(Config.GENERAL_XML_INTERACTION)
		{
			while(gotXml==false||check>3)
			{
				GetUsers();
		        check++;
				if(gotXml==false)
				{
					try 
					{
						Thread.sleep(500000);
					} 
					catch (InterruptedException e) 
					{
						e.printStackTrace();
					}
				}
			}
		}
			
		PlayerRenderingHandler.developers = developers;
		PlayerRenderingHandler.codinghelp = codinghelp;
		PlayerRenderingHandler.help = help;
	}
	
	public static void getCloakUsers(PlayerRenderingHandler askerClass)
	{
		 playerRenderer = askerClass;
		 new Thread(instance).start();
	}
	
	public void GetXmlFile()
	{
		try 
		{
			url = new URL("http://rikmuld.mdn52.creeperhost.net/cloaks.xml");
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
	
	public void GetUsers()
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
		this.gotXml = true;
		PlayerRenderingHandler.hasUsers = true;
	}
}
