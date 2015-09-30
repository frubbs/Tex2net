package com.text2net.core.gateUtil;

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
		gate.init();
		File gateHome = gate.getGateHome();
		File pluginsHome = new File(gateHome, "plugins");
		gate.getCreoleRegister().registerDirectories(new File(pluginsHome, "ANNIE").toURL());
/*		gate.getCreoleRegister().registerDirectories(new File(pluginsHome, "Tools").toURL());
		gate.getCreoleRegister().registerDirectories(new File(pluginsHome, "Annotation_Merging").toURL());
		*/
	}
	
	public static GATEExtractor getInstance() throws GateException, IOException
	{
		if(instance==null){
			instance = new GATEExtractor();
		}
		return instance;
	}
	
	private void configureGateProps()
	{
		Properties props = System.getProperties();
		String home = this.getClass().getClassLoader().getResource("com/text2net/gate_brnames/gatehome").getPath();
		props.setProperty("gate.home",home);
		//String gateHomeStr = System.getProperty(Gate.GATE_HOME_PROPERTY_NAME);
	}

	
}
