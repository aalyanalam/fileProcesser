package com.mycompany.fileprocesser;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONObject;

public class ControlSequence {
	
	public static List<File> applyProcesses(Instructions instructions, List<File> files) throws IOException {
		//Copy list of files passed
		List<File> finalFiles = files;
		
		//For each processing element call the funcCall method and update finalList
		for (int i = 0; i < instructions.processingElements.size(); i++) {
			JSONObject process = (JSONObject) instructions.processingElements.get(i);
			List<File> temp = funcCall(getMode(process), process, finalFiles);
			finalFiles = temp;
		}
		
		return finalFiles;
	}
	
	
	public static String getMode(JSONObject process) {
		 return (String) process.get("type");
	}
	
	private static List<File> funcCall(String mode, JSONObject process, List<File> files) throws IOException {
		
		List<File> returnFiles = new ArrayList<File>();
		
		switch (mode) {
		case ("NameFilter"):	
			returnFiles = filterNameMethodCall(process, files);
		break;
		
		case ("Rename"):
			returnFiles =  renameMethodCall(process, files);
		break;
		
		case ("LengthFilter"):	
			returnFiles = filterLengthMethodCall(process, files);
		break;
		
		case ("ContentFilter"):	
			returnFiles = filterContentMethodCall(process, files);
		break;
		
		case ("CountFilter"):	
			returnFiles = filterCountMethodCall(process, files);
		break;
		
		case ("Split"):	
			returnFiles = fileSplitMethodCall(process, files);
		break;
		
		case ("List"):	
			returnFiles = listMethodCall(process, files);
		break;
		
		case ("Print"):	
			returnFiles = Processes.FilePrint(files);
		break;
		
		default: 
			System.out.println("Something went wrong with process selection");
			returnFiles = files;
		}
		
		return returnFiles;
	}
	
	//**********************************LOADER METHODS******************************************
	//Loads list method with process parameters to be called
	private static List<File> listMethodCall(JSONObject process, List<File> files) throws IOException{
		String maxStr = Instructions.processToParamValue(process);
		int max = Integer.parseInt(maxStr);
		return Processes.ListMethod(files, max);
	}
		
	//Loads rename method with process parameters to be called
	private static List<File> renameMethodCall(JSONObject process, List<File> files) throws IOException{
		String suffix = Instructions.processToParamValue(process);
		return Processes.renameMethod(files, suffix);
	}
	
	//Loads content filter method with process parameters to be called
	private static List<File> filterContentMethodCall(JSONObject process, List<File> files) throws IOException{
		String key = Instructions.processToParamValue(process);
		return Processes.filterContent(files, key);
	}
			
	//Loads filter by name method with process parameters to be called
	private static List<File> filterNameMethodCall(JSONObject process, List<File> files) throws IOException{
		String key = Instructions.processToParamValue(process);
		return Processes.filterName(files, key);
	}
	
	//Loads split method with process parameters to be called
	private static List<File> fileSplitMethodCall(JSONObject process, List<File> files) throws IOException{
		String linesStr = Instructions.processToParamValue(process);
		int lines = Integer.parseInt(linesStr);
		return Processes.FileSplit(files, lines);
	}
		
	//Loads count filter method with process parameters to be called
	private static List<File> filterCountMethodCall(JSONObject process, List<File> files) throws IOException{
		String key = Instructions.processToParamValue(process);
		int value = Integer.parseInt(Instructions.processToParamValue(process, 1));
		return Processes.filterCount(files, key, value);
	}
				
	
	//Loads length content filter method with process parameters to be called
	private static List<File> filterLengthMethodCall(JSONObject process, List<File> files) throws IOException{
		Long length = Long.parseLong(Instructions.processToParamValue(process));
		String operator = Instructions.processToParamValue(process, 1);
		return Processes.filterLength(files, length, operator);
	}
	
	
	
	
	
	
	
}
