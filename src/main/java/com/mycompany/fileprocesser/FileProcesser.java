/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.fileprocesser;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.json.simple.JSONObject;

/**
 *
 * @author tylerwarwick
 */
public class FileProcesser {
	
	
	
    public static void main(String[] args) throws IOException {       
    	String filePath = new String("/home/tyler/Desktop/Test_Scenario.txt");
    	Instructions instructions = (Instructions.getLocation(filePath).matches("local"))
    			? new LocalInstructions(filePath) : new RemoteInstructions(filePath);
    	
    	List<File> files = instructions.getInitialFiles();
    	
    	 //System.out.println(ControlSequence.getMode((JSONObject) (instructions.processingElements).get(1)));
    
		List<File> finalFiles = ControlSequence.applyProcesses(instructions, files);
		
		
		//System.out.println(Instructions.processToParamValue((JSONObject) (instructions.processingElements).get(0)));
		//System.out.println(Instructions.processToParamValue((JSONObject) (instructions.processingElements).get(1), 1));
    	
    
    	
    }
}

