package com.text2net.console;

import java.io.File;
import java.io.IOException;
import java.util.Properties;

import gate.Gate;
import gate.util.GateException;

public class GATEExtractor {
	private static GATEExtractor instance = null;
	private static Gate gate = null;
	
	protected GATEExtractor() throws GateException, IOException{
		
		configureGateProps();
		gate = new Gate();

		//gate.runInSandbox(true);
		gate.init();
		File gateHome = gate.getGateHome();
		File pluginsHome = new File(gateHome, "plugins");
		gate.getCreoleRegister().registerDirectories(new File(pluginsHome, "ANNIE").toURL());
	}
	
	public static GATEExtractor getInstance() throws GateException, IOException
	{
		if(instance==null){
		//	System.out.println("########### INICIANDO GATE ############");
			instance = new GATEExtractor();
		}/*
		else {
			System.out.println("########### GATE JA INICIADO ############");
		}
		*/
		return instance;
	}
	
	private void configureGateProps()
	{
		String home = this.getClass().getClassLoader().getResource("com/text2net/gate_brnames/gatehome").getPath();
		System.setProperty("gate.home","C:\\Users\\rafa\\Documents\\Projects\\Text2Net\\console-processor");
		System.setProperty("gate.plugins.home","C:\\Users\\rafa\\Documents\\Projects\\Text2Net\\console-processor");
		String gateHomeStr = System.getProperty(Gate.GATE_HOME_PROPERTY_NAME);
		//System.out.println("*********** GAte home: " + gateHomeStr);

		gateHomeStr = System.getProperty(Gate.PLUGINS_HOME_PROPERTY_NAME);
		//System.out.println("*********** GAte plug home: " + gateHomeStr);

	}

	
}
