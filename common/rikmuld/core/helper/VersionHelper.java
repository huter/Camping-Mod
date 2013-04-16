package rikmuld.core.helper;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.logging.Level;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;

import net.minecraft.server.MinecraftServer;
import net.minecraft.src.ModLoader;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import rikmuld.core.lib.Colors;
import rikmuld.core.lib.ModInfo;
import rikmuld.core.register.ModLogger;

public class VersionHelper implements Runnable{
	
	private static VersionHelper instance = new VersionHelper();
	
	public static final String MOD_MESSAGE_VERSION_NEW = "There is an Updated version of the camping mod";
	public static final String MOD_MESSAGE_VERSION_VERSION = "Version: ";
	public static final String MOD_MESSAGE_VERSION_DATE = "The version came out at: ";
	public static final String MOD_MESSAGE_VERSION_WHATSNEW = "The new version mainly includes: ";
	public static final String MOD_MESSAGE_VERSION_CURR = "Your version is: ";
	
	static URL url;
	static URLConnection conn;
	static DocumentBuilderFactory factory;
	static DocumentBuilder builder;
	static Document doc;
	static Transformer xform;
	
	static boolean checked = false;

	public static void GetXmlFile()
	{
		try 
		{
			url = new URL("http://rikmuld.com/version.xml");
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
	
	public static void CheckVersion()
	{
		GetXmlFile();
		if(doc!=null)
		{
			NodeList Version = doc.getElementsByTagName("VersionNumber");
			NodeList VersionDate = doc.getElementsByTagName("VersionDate");
			NodeList VersionNew = doc.getElementsByTagName("NewInVersion");
			
			Node NewestVersion = Version.item(0);
			Node NewestVersionDate = VersionDate.item(0);
			Node NewestVersionNew = VersionNew.item(0);
			
			String NewVersion = NewestVersion.getTextContent();
			String NewVersionDate = NewestVersionDate.getTextContent();
			String NewVersionNew = NewestVersionNew.getTextContent();
			
			if(!NewVersion.equals(ModInfo.MOD_VERSION))
			{	
				if(MinecraftServer.getServer()!=null)
				{
					if(!MinecraftServer.getServer().isDedicatedServer()&&ModLoader.getMinecraftInstance().thePlayer!=null)
					{
						ModLoader.getMinecraftInstance().thePlayer.addChatMessage(Colors.COLOR_RED+MOD_MESSAGE_VERSION_NEW);
						ModLoader.getMinecraftInstance().thePlayer.addChatMessage(Colors.COLOR_RED+MOD_MESSAGE_VERSION_VERSION + NewVersion); 
						ModLoader.getMinecraftInstance().thePlayer.addChatMessage(Colors.COLOR_RED+MOD_MESSAGE_VERSION_CURR + ModInfo.MOD_VERSION);
						ModLoader.getMinecraftInstance().thePlayer.addChatMessage(Colors.COLOR_RED+MOD_MESSAGE_VERSION_WHATSNEW + NewVersionNew);
						ModLoader.getMinecraftInstance().thePlayer.addChatMessage(Colors.COLOR_RED+MOD_MESSAGE_VERSION_DATE + NewVersionDate);
						checked = true;
					}
				}
				else if(ModLoader.getMinecraftInstance().thePlayer!=null)
				{
						ModLoader.getMinecraftInstance().thePlayer.addChatMessage(Colors.COLOR_RED+MOD_MESSAGE_VERSION_NEW);
						ModLoader.getMinecraftInstance().thePlayer.addChatMessage(Colors.COLOR_RED+MOD_MESSAGE_VERSION_VERSION + NewVersion); 
						ModLoader.getMinecraftInstance().thePlayer.addChatMessage(Colors.COLOR_RED+MOD_MESSAGE_VERSION_CURR + ModInfo.MOD_VERSION);
						ModLoader.getMinecraftInstance().thePlayer.addChatMessage(Colors.COLOR_RED+MOD_MESSAGE_VERSION_WHATSNEW + NewVersionNew);
						ModLoader.getMinecraftInstance().thePlayer.addChatMessage(Colors.COLOR_RED+MOD_MESSAGE_VERSION_DATE + NewVersionDate);	
						checked = true;
				}
			}
		}
	}

	@Override
	public void run() 
	{
		while(checked==false)
		{
			try 
			{
				Thread.sleep(4000);
			} 
			catch (InterruptedException e) 
			{
				e.printStackTrace();
			}
			
			CheckVersion();
	        
			if(checked==false)
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
	
	 public static void execute() 
	 {
	     new Thread(instance).start();
	 }
}
