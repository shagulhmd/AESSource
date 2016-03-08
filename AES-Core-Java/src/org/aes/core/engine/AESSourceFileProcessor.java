package org.aes.core.engine;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class AESSourceFileProcessor {
	
	
	public static List<File> processSourceFiles (String sourcePath,  AESConfig configs){
		
		List<File> files = new ArrayList<File>();
		
		File source = new File(sourcePath);
		
		if(source.exists()){
			
			addFiles(source, files, configs);
		}
		
		return files;
		
	}

	private static void addFiles(File source, List<File> files, AESConfig configs) {
		
		if(source.isDirectory()){
			
			File[] subFiles = source.listFiles();
			for(File f: subFiles){
				addFiles(f, files, configs);
			}
		}
		else
		{
			String fileName = source.getName();
			if(source.exists() && fileName.lastIndexOf(".") != -1){
				String ext = fileName.substring(fileName.lastIndexOf("."));
				if(!configs.getIgnoreFileExts().contains(ext)){
					files.add(source);
				}
			}

		}
		
	}

}
