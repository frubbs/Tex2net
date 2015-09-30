package com.text2net.core;

import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.StandardOpenOption;
import java.nio.file.attribute.BasicFileAttributes;
import java.security.SecureRandom;

import org.apache.commons.io.FileUtils;
import org.junit.Assert;

public class ConfigurationSetup {
	
	private String randomGaneratedName;
	private String root = "temp/";
	
	
	protected ConfigurationSetup(String name) {
		randomGaneratedName = root + name;
	}
	
	public ConfigurationSetup() {
		SecureRandom random = new SecureRandom();

		   
		randomGaneratedName = root + new BigInteger(130, random).toString(32);
	}
	
	public File configure(String lineBreak, String namesList, String xappFile) throws IOException {
		//String resFolder = resourceFolder;
		
			
		createDir();
		copyResourcesToTemp(lineBreak, namesList);
		//changeXappTempGazeterReference(xappFile);
		
		return new File(randomGaneratedName + "/" + xappFile);
	}
	
	
	
	protected String getRandomGaneratedName() {
		return randomGaneratedName;
	}

	
	private String baseResourceFolder = "com/text2net/gate_{0}/";
	private String resourceFolder = "";
	
	private String resourceNames[] = {"Gazeter/quebra.lst",
			"Gazeter/conectores.lst",
			"Gazeter/cargos.lst",
			"Gazeter/names.lst",
			"Gazeter/lists.def",
			"Tokenizer/DefaultTokeniser.rules",
			"Tokenizer/principal.jape",
			"Tokenizer/nomes.jape",
			"Tokenizer/separadorSimples.jape",
			"Nomes_Trecho.xapp"};
	
	
	/*
	private String resourceNames[] = {"com/text2net/gate/Gazeter/quebra.lst",
			"com/text2net/gate/Gazeter/conectores.lst",
			"com/text2net/gate/Gazeter/names.lst",
			"com/text2net/gate/Gazeter/lists.def",
			"com/text2net/gate/Tokenizer/DefaultTokeniser.rules",
			"com/text2net/gate/Tokenizer/principal.jape",
			"com/text2net/gate/Tokenizer/nomes.jape",
			"com/text2net/gate/Tokenizer/separadorSimples.jape",
			"com/text2net/gate/Nomes_Trecho.xapp"};
			*/
	
	protected String[] getResourceNames() {
		String[] result = new String[10];
		
		for (int i = 0; i < resourceNames.length; i++) {
			result[i] = resourceFolder + resourceNames[i]; 
		}
		return resourceNames;
	}

	protected void updateLineBreak(String lineBreak, File lineBreakFile) throws IOException {
		//System.out.println("########### updateLineBreak INI ##############");
		//System.out.println("lineBreakFile.getAbsolutePath(): " + lineBreakFile.getAbsolutePath());
		//System.out.println("lineBreak: " + lineBreak);
		//try {
		

		
		Files.write(Paths.get(lineBreakFile.getAbsolutePath()), lineBreak.getBytes(), StandardOpenOption.TRUNCATE_EXISTING);
		/*}
		catch (Exception e) {
			//System.out.println("######### Erro updateLineBreak: " + e.getMessage());
			e.printStackTrace();
		}
		
		System.out.println("########### updateLineBreakFIM ##############");
		*/
	}

	protected File createDir() {
		File dir = new File(randomGaneratedName);
		dir.mkdirs();
		return dir;
	}

	protected void copyResourcesToTemp(String lineBreak, String namesList) throws IOException {
	//System.out.println("!!!!!!!!!!!!! copyResourcesToTemp INI !!!!!!!!");
		
		if (namesList != null && !namesList.equals(""))
			resourceFolder = baseResourceFolder.replace("{0}", "customlist");
		else
			resourceFolder = baseResourceFolder.replace("{0}", "brnames");
		
		for (String resourceName : resourceNames) {
			
			String resourceFullName = resourceFolder + resourceName;
			
			//System.out.println("resourceName: " + resourceFullName);
			
			File source = new File(this.getClass().getClassLoader().getResource(resourceFullName).getPath());
			
			
			//String folder = source.getParent().substring(source.getParent().lastIndexOf("\\")+1);
			
			//System.out.println("folder: " + folder);
			
			
			//try {
			File dest = new File(randomGaneratedName + "/" +  resourceName);
			FileUtils.copyFile(source, dest);
			/*}
			catch (Exception e){
				System.out.println("ssssssssss Erro : " + e.getMessage());
				e.printStackTrace();
			}*/
		}
		
		updateLineBreak(lineBreak, new File(randomGaneratedName + "/" +  "Gazeter/quebra.lst"));
		
		if (namesList != null && !namesList.equals(""))
			updateNamesList(namesList, new File(randomGaneratedName + "/" +  "Gazeter/names.lst"));
		
		
		//Check
		/*for (String resourceName : resourceNames) {
			File resourceCopy = new File(randomGaneratedName + "/" +  resourceName);
			System.out.println("Checking File:" + resourceCopy.getAbsoluteFile());
			if (resourceCopy.exists())
			{
				System.out.println("Exists File:" + resourceCopy.getAbsoluteFile());
					
			}
			else
			{
				System.out.println("NOT  File:" + resourceCopy.getAbsoluteFile());
				
			}
		}

		System.out.println("!!!!!!!!!!!!! copyResourcesToTemp FIM !!!!!!!!");*/
	}
/*
	protected void changeXappTempGazeterReference(String xappFile) throws IOException {
	    String filename = randomGaneratedName + "\\" + xappFile;  
		
		byte[] xappArray = Files.readAllBytes(Paths.get(filename));

	      String xappString = new String(xappArray, "UTF-8");
	      
	      String newXappString = xappString.replace("$relpath$Gazeter", new File(randomGaneratedName).getAbsolutePath());
	      newXappString = xappString.replace("$relpath$Tokenizer", new File(randomGaneratedName).getAbsolutePath());
	      
	      Files.write(Paths.get(filename), newXappString.getBytes(), StandardOpenOption.TRUNCATE_EXISTING);
	}
*/

	protected void deleteAllTempFiles() throws IOException {
		Path directory = Paths.get(randomGaneratedName);
		   Files.walkFileTree(directory, new SimpleFileVisitor<Path>() {
			   @Override
			   public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
				   Files.delete(file);
				   return FileVisitResult.CONTINUE;
			   }

			   @Override
			   public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
				   Files.delete(dir);
				   return FileVisitResult.CONTINUE;
			   }

		   });
		
	}
	
	
	protected void finalize () throws Throwable {
		//deleteAllTempFiles();
	}

	public void updateNamesList(String names, File file) throws IOException {
		
		
		String[] aux = names.split(",");
		String namesNormalized ="";
		for (String string : aux) {
			namesNormalized += string.trim() + '\n';
		}
		
		Files.write(Paths.get(file.getAbsolutePath()), namesNormalized.getBytes(), StandardOpenOption.TRUNCATE_EXISTING);
		
	}
	
}
