package testes;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.commons.io.FileUtils;

public class Principal {
	public static void main(String[] args) {
		
		// Arquivo arquivo = new Arquivo("C:/Users/felipe.de.rodrigues/dirteste/copiavel/");
			//		arquivo.copiarPara("C:/Users/felipe.de.rodrigues/dirteste/diretoriodestino/");
		
					
					
					
		File source = new File("C:/Users/felipe.de.rodrigues/dirteste/copiavel");
		File dest = new File("C:/Users/felipe.de.rodrigues/dirteste/diretoriodestino");
		try {
		    FileUtils.copyDirectory(source, dest);
		} catch (IOException e) {
		    e.printStackTrace();
		}
	}
	
}
