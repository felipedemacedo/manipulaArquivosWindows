package testes;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.nio.file.FileSystem;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Arquivo {

	private File f;
	private String nome;

	Arquivo(){//Capturar o diretório home do usuário: C:\Users\felipe.de.rodrigues
		this.f = new File(System.getProperty("user.home"));
		setNome(this.f.getName());
	}

	Arquivo(File arquivo){
		setF(arquivo);
		setNome(this.f.getName());
	}

	Arquivo(String caminho){
		this.f = new File(caminho);
		setNome(this.f.getName());
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public boolean isDiretorio(){
		return getF().isDirectory();
	}

	public File getF() {
		return f;
	}

	public void setF(File f) {
		this.f = f;
	}

	public List<String> getConteudo(){
		return ler();
	}

	/**
	 * Verifica existência de um arquivo
	 * @param f
	 */
	public boolean existe(){
		return this.f.exists()?true:false;
	}

	/**
	 * Verifica existência de um arquivo
	 * @param f
	 */
	public boolean existe(File f){
		return f.exists()?true:false;
	}

	/**
	 * Cria arquivo
	 */
	public void criar(){
		if(existe(this.f)){
			System.out.println("Arquivo " + this.f.getPath() + " já existente.");
			return;
		}

		FileOutputStream out;
		PrintStream prt;
		try {
			out = new FileOutputStream(this.f);
			prt = new PrintStream(out);

			//texto a ser escrito:
				//prt.println(texto);
			prt.close();
		} catch(Exception e) {
			System.out.println("Write error");
		}
	}

	/**
	 * Cria arquivo
	 * @param f
	 */
	public void criar(File f){
		if(existe(f)){
			System.out.println("Arquivo já existente.");
			return;
		}




		FileOutputStream out;
		PrintStream prt;
		try {
			out = new FileOutputStream(f);
			prt = new PrintStream(out);

			//texto a ser escrito:
				//prt.println(texto);
			prt.close();
		} catch(Exception e) {
			System.out.println("Write error");
		}
	}

	/**
	 * Cria arquivo com o texto fornecido
	 * @param texto
	 */
	public void criar(String texto){
		if(existe(this.f)){
			System.out.println("Arquivo já existente.");
			return;
		}

		FileOutputStream out;
		PrintStream prt;
		try {
			out = new FileOutputStream(this.f);
			prt = new PrintStream(out);

			//texto a ser escrito:
				prt.println(texto);
				prt.close();
		} catch(Exception e) {
			System.out.println("Write error");
		}
	}

	public void criar(List<String> texto){
		if(existe(this.f)){
			System.out.println("Arquivo já existente.");
			return;
		}

		FileOutputStream out;
		PrintStream prt;
		try {
			out = new FileOutputStream(this.f);
			prt = new PrintStream(out);

			//texto a ser escrito:
				for (String linha : texto) {
					prt.println(linha);
				}
				prt.close();
		} catch(Exception e) {
			System.out.println("Write error");
		}
	}

	/**
	 * Cria arquivo com o texto fornecido
	 * @param f
	 */
	public void criar(File f, String texto){
		if(existe(f)){
			System.out.println("Arquivo já existente.");
			return;
		}

		FileOutputStream out;
		PrintStream prt;
		try {
			out = new FileOutputStream(f);
			prt = new PrintStream(out);

			//texto a ser escrito:
				prt.println(texto);
				prt.close();
		} catch(Exception e) {
			System.out.println("Write error");
		}
	}

	/**
	 * Cria arquivo com o texto fornecido
	 * @param f
	 */
	public void criar(File f, List<String> texto){
		if(existe(f)){
			System.out.println("Arquivo já existente.");
			return;
		}

		FileOutputStream out;
		PrintStream prt;
		try {
			out = new FileOutputStream(f);
			prt = new PrintStream(out);

			//texto a ser escrito:
				for (String linha : texto) {
					prt.println(linha);
				}
				prt.close();
		} catch(Exception e) {
			System.out.println("Write error");
		}
	}

	/**
	 * Cria arquivo
	 */
	public void criarSobrescrevendo(){
		if(existe(this.f)){
			System.out.println("Arquivo já existente.");
			return;
		}

		FileOutputStream out;
		PrintStream prt;
		try {
			out = new FileOutputStream(this.f);
			prt = new PrintStream(out);

			//texto a ser escrito:
				//prt.println(texto);
			prt.close();
		} catch(Exception e) {
			System.out.println("Write error");
		}
	}

	/**
	 * Cria arquivo
	 * @param f
	 */
	public void criarSobrescrevendo(File f){
		if(existe(f)){
			System.out.println("Arquivo já existente.");
			return;
		}

		FileOutputStream out;
		PrintStream prt;
		try {
			out = new FileOutputStream(f);
			prt = new PrintStream(out);

			//texto a ser escrito:
				//prt.println(texto);
			prt.close();
		} catch(Exception e) {
			System.out.println("Write error");
		}
	}

	/**
	 * Cria arquivo com o texto fornecido
	 * @param texto
	 */
	public void criarSobrescrevendo(String texto){
		if(existe(this.f)){
			System.out.println("Arquivo já existente.");
			return;
		}

		FileOutputStream out;
		PrintStream prt;
		try {
			out = new FileOutputStream(this.f);
			prt = new PrintStream(out);

			//texto a ser escrito:
				prt.println(texto);
				prt.close();
		} catch(Exception e) {
			System.out.println("Write error");
		}
	}

	public void criarSobrescrevendo(List<String> texto){
		if(existe(this.f)){
			System.out.println("Arquivo já existente.");
			return;
		}

		FileOutputStream out;
		PrintStream prt;
		try {
			out = new FileOutputStream(this.f);
			prt = new PrintStream(out);

			//texto a ser escrito:
				for (String linha : texto) {
					prt.println(linha);
				}
				prt.close();
		} catch(Exception e) {
			System.out.println("Write error");
		}
	}

	/**
	 * Cria arquivo com o texto fornecido
	 * @param f
	 */
	public void criarSobrescrevendo(File f, String texto){
		if(existe(f)){
			System.out.println("Arquivo já existente.");
			return;
		}

		FileOutputStream out;
		PrintStream prt;
		try {
			out = new FileOutputStream(f);
			prt = new PrintStream(out);

			//texto a ser escrito:
				prt.println(texto);
				prt.close();
		} catch(Exception e) {
			System.out.println("Write error");
		}
	}

	/**
	 * Cria arquivo com o texto fornecido
	 * @param f
	 */
	public void criarSobrescrevendo(File f, List<String> texto){
		if(existe(f)){
			System.out.println("Arquivo já existente.");
			return;
		}

		FileOutputStream out;
		PrintStream prt;
		try {
			out = new FileOutputStream(f);
			prt = new PrintStream(out);

			//texto a ser escrito:
				for (String linha : texto) {
					prt.println(linha);
				}
				prt.close();
		} catch(Exception e) {
			System.out.println("Write error");
		}
	}

	/**
	 * Lë as linhas de um arquivo
	 * Retorna lista onde cada elemento é uma linha
	 * @param f
	 */
	public List<String> ler(){
		List<String> linhas = new ArrayList<String>();
		try {
			FileInputStream fStream = new FileInputStream(this.f);
			BufferedReader in = new BufferedReader(new InputStreamReader(fStream));
			while (in.ready()) {
				linhas.add(in.readLine());
			}
			in.close();
		} catch (IOException e) {
			System.out.println("File input error");
		}
		return linhas;
	}

	/**
	 * Lë as linhas de um arquivo
	 * Retorna lista onde cada elemento é uma linha
	 * @param f
	 */
	public List<String> ler(File f){
		List<String> linhas = new ArrayList<String>();
		try {
			FileInputStream fStream = new FileInputStream(f);
			BufferedReader in = new BufferedReader(new InputStreamReader(fStream));
			while (in.ready()) {
				linhas.add(in.readLine());
			}
			in.close();

		} catch (IOException e) {
			System.out.println("File input error");
		}
		return linhas;
	}

	/**
	 * Adiciona ao arquivo o texto fornecido
	 * @param f
	 * @param textoAserAdicionado
	 */
	public void append(File f, String textoAserAdicionado){
		FileWriter out;
		try {
			out = new FileWriter(f, true);
			out.write(textoAserAdicionado);
			out.close();
		} catch(IOException e) {
			System.out.println("Error appending to file " + f);
		}
	}

	/**
	 * Adiciona ao arquivo o texto fornecido
	 * @param f
	 * @param textoAserAdicionado
	 */
	public void append(File f, List<String> textoAserAdicionado){
		FileWriter out;
		try {
			out = new FileWriter(f, true);
			for (String linha : textoAserAdicionado) {
				out.write(linha);				
			}
			out.close();
		} catch(IOException e) {
			System.out.println("Error appending to file " + f);
		}
	}

	/**
	 * Adiciona ao arquivo o texto fornecido
	 * @param f
	 * @param textoAserAdicionado
	 */
	public void append(Arquivo arquivo, String textoAserAdicionado){
		this.append(arquivo.getF(), textoAserAdicionado);
	}

	/**
	 * Adiciona ao arquivo o texto fornecido
	 * @param f
	 * @param textoAserAdicionado
	 */
	public void append(Arquivo arquivo, List<String> textoAserAdicionado){
		this.append(arquivo.getF(), textoAserAdicionado);
	}

	/**
	 * Copia arquivo
	 * @param origem
	 * @param destino
	 */
	public static void copiarArquivo(Arquivo origem, Arquivo destino){
		copiarArquivo(origem.getF(), destino.getF());
	}

	/**
	 * Copia arquivo
	 * @param origem
	 * @param destino
	 */
	public static void copiarArquivo(File origem, File destino){
		System.out.println("Copiar " + origem.toString() + " para " + destino.toString());

		if(!origem.isDirectory() && destino.isDirectory()){
			Arquivo arquivoDestino = new Arquivo(destino.getPath() + "/" + origem.getName());
			copiarArquivo(origem, arquivoDestino.getF());
			return;
		}

		try {
			FileInputStream in = new FileInputStream(origem);
			FileOutputStream out = new FileOutputStream(destino);
			byte[] buf = new byte[1024];
			int i = 0;
			while ((i = in.read(buf)) != -1) {
				out.write(buf, 0, i);
			}
			in.close();
			out.close();
		} catch(IOException e) {
			System.out.println("Error copying file");
		}
	}

	/**
	 * Copiar arquivo
	 * @param conteudo
	 * @param destino
	 */
	public static void copiarArquivo(List<String> conteudo, File destino){
		Arquivo a = new Arquivo();
		a.criar(destino);
		a.append(destino, conteudo);
	}

	//DELETE:

	public void delete(){
		File f = getF();
		delete(f);
	}

	public void delete(File f){
		if (f.exists()) {
			f.delete(); 
		}
	}

	public void delete(Arquivo arquivo){
		arquivo.delete();
	}

	//DEBUG:

	public void print_console(){
		List<String> conteudo = ler();
		System.out.println("-----------------");
		for (String linha : conteudo) {
			System.out.println(linha);
		}
		System.out.println("-----------------");
	}

	public void print_console(File f){
		List<String> conteudo = ler(f);
		System.out.println("-----------------");
		for (String linha : conteudo) {
			System.out.println(linha);
		}
		System.out.println("-----------------");
	}

	public void print_console(Arquivo f){
		List<String> conteudo = ler(f.getF());
		System.out.println("-----------------");
		for (String linha : conteudo) {
			System.out.println(linha);
		}
		System.out.println("-----------------");
	}


	public List<File> listarTudo() {
		return listarTudo(getF());
	}

	public List<File> listarTudo(File path) {
		List<File> listaVolta = new ArrayList<File>();
		File[] files = path.listFiles();

		for (int i = 0; i < files.length; i++) {
			File arq = files[i];

			listaVolta.add(arq);

			if (arq.isDirectory()) {
				Collection<File> lista = listarTudo(arq);
				if (lista.size() > 0) listaVolta.addAll(lista);
			}
		}
		return listaVolta;
	}
	
	public List<File> listarTudo(Arquivo path) {
		return listarTudo(path.getF());
	}

	public void copiarPara(String localDestino) {

		if(!existe()) return; //não se pode copiar um arquivo que não existe
		System.out.println("new file localDestino: "+localDestino);
		
		if(isDiretorio()) 		//cópia de sub-diretórios e arquivos para diretório
		{
			new File(localDestino+"/"+getNome()).mkdirs();//cria diretorio destino e todos os diretorios no caminho			
			List<File> listaDeTudo = listarTudo();
			for (File file : listaDeTudo) {
				Arquivo temp = new Arquivo(file);
				temp.copiarPara(localDestino+"/"+getNome());
			}
		}
		else					//cópia de arquivo para diretório
		{
			
			new File(localDestino).mkdirs();//cria diretorio destino e todos os diretorios no caminho 
			copiarArquivo(this, new Arquivo(localDestino + "/" + getNome()));
		}



	}


	//	public void copiarPara(String local) {
	//		Arquivo origem = this;
	//		
	//		if(origem.isDiretorio())
	//		{
	//			new File (local).mkdirs()
	//			
	//			List<File> listaArquivos = listarTudo(origem.getF());
	//			System.out.println(listaArquivos.toString());
	//			for (File arq : listaArquivos) {
	//				Arquivo temp = new Arquivo(arq);
	//				temp.copiarPara(local);
	//			}
	//			Arquivo diretorioCopiado = new Arquivo(local + origem.getNome());
	//			criar(diretorioCopiado.getF());
	//		}
	//		else
	//		{
	//			//a string que representa o local deve terminar com barra. Senão ela será corrigida.
	//			if(!local.endsWith("/") && local.contains("/")){
	//				local = local + "/";
	//			}else if(!local.endsWith("\\\\") && local.contains("\\\\")){
	//				local = local + "\\\\";
	//			}
	//			
	//			Arquivo destino = new Arquivo(local);
	//			copiarArquivo(origem, destino);
	//			
	//			
	//		}
	//		
	//	}










}
