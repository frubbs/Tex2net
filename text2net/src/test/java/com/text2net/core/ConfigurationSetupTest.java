package com.text2net.core;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;



public class ConfigurationSetupTest {

	//String randomGaneratedName = "ertgyhujikol";
	ConfigurationSetup cs;
	@Before
	public void setup() {
	 cs = new ConfigurationSetup("ertgyhujikol");
	}
	
	@Test
	public void testCreateDirectory(){
		
		
		File dir = cs.createDir();
		
		if (dir.exists()) {
			Assert.assertEquals("temp\\" + dir.getName(), cs.getRandomGaneratedName());
		}else{
			Assert.fail();
		}
	}
	
	@Test 
	public void testCopyResourcesToTempDir() throws IOException {
		
		
		String[] resourceNames = cs.getResourceNames();
		
		cs.copyResourcesToTemp("@@DOUBLE_NW@@");

		for (String resourceName : resourceNames) {
			//File resource = new File(this.getClass().getClassLoader().getResource(resourceName).getPath());
			File resourceCopy = new File(cs.getRandomGaneratedName() + "\\" + resourceName);
			System.out.println("File:" + resourceCopy.getAbsoluteFile());
			if (!resourceCopy.exists())
				Assert.fail();
		}
		
	}
	/*
	@Test
	@Ignore
	public void testChangeXappTempFileReferences() throws IOException {
		  String xappFile = "Nomes_Trecho.xapp";
		  
			
		  cs.changeXappTempGazeterReference(xappFile);
		  
		  byte[] xappArray = Files.readAllBytes(Paths.get(cs.getRandomGaneratedName() + "\\" + xappFile));

	      String xappString = new String(xappArray, "UTF-8");
	      
	      Assert.assertTrue(xappString.indexOf("$relpath$Gazeter") <= 0);
	}
	*/
	@Test 
	public void testAlteraRecursoGazzeter() throws IOException {
		
		String lineBreak = "TESTE2";

		cs.updateLineBreak(lineBreak, new File(cs.getRandomGaneratedName() + "\\Gazeter\\quebra.lst"));
		

		byte[] lstArray = Files.readAllBytes(Paths.get(cs.getRandomGaneratedName() + "\\Gazeter\\quebra.lst"));

	      String lstString = new String(lstArray, "UTF-8");
	      
	                     
	   
        Assert.assertEquals(lstString, lineBreak);

	         			
	}
	
	@Test
	@Ignore
	public void testDeletaTudo() throws IOException {
		cs.deleteAllTempFiles();
		
		Assert.assertFalse(new File(cs.getRandomGaneratedName()).exists());
			
		
	}
	
	
}
