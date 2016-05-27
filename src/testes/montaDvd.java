package testes;
/**
 * INSTRUÇÕES:
 * 
 * O software busca pela melhor combinação de arquivos, ou seja aquela que aproveita melhor o espaço de um DVD de dados. 
 * E move os arquivos selecionados (assim como suas respectivas pastas) para o diretório destino fornecido pelo usuário.
 * 
 * Passo 1: Forneça o diretório FONTE. 
 * Passo 2: Forneça o diretório DESTINO.
 * 
 * OBS IMPORTANTE! LEIA:
 * 
 * Para o correto funcionamento deste software: 
 * 
 * - é necessário que a pasta FONTE contenha apenas 1 nível de subpastas.
 * - é necessário que cada subpasta contenha apenas 1 arquivo grande (relativamente grande...). 
 * 		Se uma mesma subpasta contém por exemplo alli-wc-xvid.cd1.avi e alli-wc-xvid.cd2.avi, uma solução seria 
 * 		zipar os dois arquivos em apenas um usando winrar, winzip, 7zip, etc. 
 * 		Caso contrário pode acontecer de o programa selecionar apenas um dos dois arquivos mas ao copiar a pasta que contém ambos... os dois estarão presentes,
 * 		resultando em um conjunto de dados provavelmente maior que um DVD 4.37GB.
 * 
 * Author: fmr3@cin.ufpe.br
 */

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class montaDvd {

	final static String diretorio = "G:/TUDO/Videos/Filmes";
	final static String pastaDestino = "G:/GRAVAR/DVD1";

	final static long tamanhoMaxDVDBytes = 4700000000L; //4.37 GB OU SEJA 4.700.000.000 bytes 
	static long tamanhoMinimoDeArquivo = 50*1000000L; //50 MB //usado para filtrar arquivos pequenos retirando os mesmos da busca, agilizando assim o processo.
	static long margemDeErro = 25*1000000L;//25MB usado para não chegar excessivamente próximo à capacidade do DVD, e também para armazenar legendas e imagens...

	public static List<File> listaDeArquivosFile=null;//listagem de TODOS os arquivos encontrados
	public static List<File[]> listaDeDVDs=null; //listagem final dos dvds com tamanho otimizado
	public static List<List<File>> listaArquivosDVD=null;

	public static boolean finalizaPrograma=false;

	public static void main(String[] args) {
		while(!finalizaPrograma){

			inicializaListas();

			//TODO: retirar comentários das próximas duas linhas quando quiser ler a url da pasta a partir do console
			System.out.println("Insira Diretório Fonte: (ex.: C:/suaPasta/)");
			String pastaFonte = lerConsole();
			File fonte = new File(pastaFonte);
			
			//File pasta = new File(diretorio);

			if(fonte.exists())
			{
				System.out.println("Diretório encontrado, iniciando busca por arquivos...");

				listFilesForFolder(fonte);

				System.out.println("Busca realizada com sucesso!");
				System.out.println("Início da fase de comparação...");

				if(listaDeArquivosFile.size()>1)
				{
					manipulaListaArquivos2();
				}
				else
				{
					System.out.println("ERRO: apenas um arquivo foi encontrado.");
				}

				System.out.println("Comparação realizada com sucesso!");

				//aqui já temos uma lista de arquivos otimizada, ou seja o mais próximo possível do tamanho máximo de um dvd.
				printLista(listaArquivosDVD); //joga a lista na tela


				System.out.println("Insira Diretório Destino: (ex.: C:/suaOutraPasta/)");
				String pastaDestino = lerConsole();
				File destino = new File(pastaDestino);

				System.out.println("Copiando arquivos...");
				copiaArquivos(destino);
				System.out.println("Cópia de arquivos realizada com sucesso.");
				JOptionPane.showMessageDialog(null, "Seu DVD está pronto !\n"+pastaDestino);

			}
			else
				System.out.println("ERRO: diretório não encontrado.");

			
			
		}

	}

	public static void criaDiretorio(File diretorio){
		if(!diretorio.exists()) //se a pasta inexiste
			diretorio.mkdir();//cria a pasta
	}

	private static void copiaArquivos(File diretorioDestino) {
		//if(!diretorioDestino.exists()) diretorioDestino.mkdir();//cria a pasta raíz fornecida como destino
		criaDiretorio(diretorioDestino);
		for (List<File> list : listaArquivosDVD) {
			for (File arquivoFonte : list) {
				//montando nome:
				//System.out.println("montando nome");
				File diretorioFonte = new File(arquivoFonte.getParent());
				//System.out.println("nome montado.");
				//String nomeArquivoDestino = diretorioDestino.getName()+arquivoFonte.getName();// de acordo com a pasta fornecida como destino, e o nome do arquivo-fonte
				//File arquivoDestino = new File(nomeArquivoDestino);

				try {
					//System.out.println(	   "diretorioFonte: " + diretorioFonte.getName() + 
					//				" diretorioDestino: " + diretorioDestino.getName());
					if(diretorioFonte.isDirectory() && diretorioDestino.isDirectory())
					{
						//montando pasta destino de acordo com o nome da pasta fonte
						String urlDestino = diretorioDestino.getPath()+"\\"+diretorioFonte.getName();
						File destino = new File(urlDestino);
						System.out.println("urlDestino: "+urlDestino);
						//						if(!destino.exists()) destino.mkdir(); 
						criaDiretorio(destino);
						System.out.println("copiando diretorioFonte: "+diretorioFonte.getPath()+" para "+destino.getPath());
						copyDirectory(diretorioFonte,destino);//diretorio-fonte, diretorio-destino
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		//		for (List<File> list : listaArquivosDVD) {
		//			for (File arquivoFonte : list) {
		//				//montando nome do arquivo destino:
		//				String nomeArquivoDestino = pastaDestino.getName()+arquivoFonte.getName();// de acordo com a pasta fornecida como destino, e o nome do arquivo-fonte
		//				File arquivoDestino = new File(nomeArquivoDestino);
		//				
		//				try {
		//					copyFile(arquivoFonte,arquivoDestino);//arquivo-fonte, arquivo-destino
		//				} catch (IOException e) {
		//					e.printStackTrace();
		//				}
		//			}
		//		}


	}

	public static void copyDirectory(File sourceDir, File destDir) throws IOException{
		if(!destDir.exists()){
			destDir.mkdir();
		}
		File[] children = sourceDir.listFiles();
		for(File sourceChild : children){
			String name = sourceChild.getName();
			File destChild = new File(destDir, name);
			if(sourceChild.isDirectory()){
				copyDirectory(sourceChild, destChild);
			}
			else{
				copyFile(sourceChild, destChild);
			}
		}
	}

	private static void copyFile(File source, File dest) throws IOException {
		if(!dest.exists()){
			dest.createNewFile();
		}
		InputStream in = null;
		OutputStream out = null;
		try{
			System.out.println("Copiando arquivo... "+source.getName());
			//long tamanhoTotal = source.length();
			in = new FileInputStream(source);
			out = new FileOutputStream(dest);
			byte[] buf = new byte[1024];
			int len;
			
			while((len = in.read(buf)) > 0){
				//int valor = (int) tamanhoTotal/len;
				out.write(buf, 0, len);
				//System.out.println("["+ len/valor +"%]");
			}
		}
		finally{
			in.close();
			out.close();
		}
	}

	private static void printLista(List<List<File>> listaArquivosDVD2) {
		int index=1;
		long tamanho=0L;
		for (List<File> list : listaArquivosDVD2) {
			System.out.println("~~~~~~~~~ dvd ["+index+"] ~~~~~~~~~");
			for (File file : list) {
				System.out.println(file.getName());
				tamanho+=file.length();
			}
			System.out.println("---- ["+ tamanho +"] bytes.----");
			index++;
		}
	}

	public static void listFilesForFolder(File folder) {
		for (File fileEntry : folder.listFiles()) {
			if (fileEntry.isDirectory()) {
				listFilesForFolder(fileEntry);
			} else {
				//System.out.println(fileEntry.getName());
				if(fileEntry.length()>tamanhoMinimoDeArquivo)
					listaDeArquivosFile.add(fileEntry);				
			}
		}
	}

	public static void inicializaListas(){
		listaDeArquivosFile = new ArrayList<File>();
		listaDeDVDs = new ArrayList<File[]>();
		listaArquivosDVD = new ArrayList<List<File>>();
	}
	
	public static void manipulaListaArquivos2(){
		int indice1=0;
		long tamanhoAtualDVD=0L;
		long tamanhoOtimoDVD=0L;
		long tamanhoDesejadoDVD = tamanhoMaxDVDBytes - margemDeErro;

		List<File> listaArquivos=null;//contém todos os arquivos adicionados ao dvd-temp atual
		List<File> listaOtimaArquivos=null;//contém todos os arquivos adicionados ao dvd-temp OTIMIZADO
		listaArquivos = new ArrayList<File>();
		listaOtimaArquivos = new ArrayList<File>();

		for (File arquivo1 : listaDeArquivosFile) {
			//printProgressoComparacao(indice1);//apenas joga na tela o progresso das comparações

			tamanhoAtualDVD=adiciona(arquivo1, listaArquivos, tamanhoAtualDVD, tamanhoDesejadoDVD);
			
			for (File arquivo2 : listaDeArquivosFile) {
				if(arquivo2!=arquivo1){
					tamanhoAtualDVD=adiciona(arquivo2, listaArquivos, tamanhoAtualDVD, tamanhoDesejadoDVD);
				}
			}//fim-segundo-FOR

			if(tamanhoAtualDVD>tamanhoOtimoDVD && tamanhoAtualDVD<=tamanhoDesejadoDVD){
				//System.out.println("[1]listaArquivos.size(): " + listaArquivos.size());
				//System.out.println("[1]listaOtimaArquivos.size(): " + listaOtimaArquivos.size());
				listaOtimaArquivos = new ArrayList<File>();
				tamanhoOtimoDVD=updateListaOtima(tamanhoAtualDVD, listaOtimaArquivos, listaArquivos);
				//System.out.println("[2]listaArquivos.size(): " + listaArquivos.size());
				//System.out.println("[2]listaOtimaArquivos.size(): " + listaOtimaArquivos.size());
			}//fim-IF
			//System.out.println("tamanhoAtualDVD: " + tamanhoAtualDVD);
			listaArquivos = new ArrayList<File>();//contém todos os arquivos adicionados ao dvd temp atual
			tamanhoAtualDVD=0;
			indice1++;
		}//fim-primeiro-FOR

		listaArquivosDVD.add(listaOtimaArquivos);

//		if(listaOtimaArquivos!=null){
//			System.out.println("Tamanho do dvd: " + tamanhoOtimoDVD+" bytes. Num total de "+listaOtimaArquivos.size()+ " arquivos.");
//			System.out.println("listaOtimaArquivos.size(): " + listaOtimaArquivos.size());
//			System.out.println("listaArquivos.size(): " + listaArquivos.size());
//			System.out.println("tamanhoAtualDVD: " + tamanhoAtualDVD);
//			System.out.println("tamanhoOtimoDVD: " + tamanhoOtimoDVD);
//		}
	}

	private static long updateListaOtima(long tamanhoAtualDVD, List<File> listaOtimaArquivos, List<File> listaArquivos) {
		//tamanhoOtimoDVD=tamanhoAtualDVD;
		copiaLista(listaOtimaArquivos, listaArquivos);
//		if(listaOtimaArquivos!=null){
//			System.out.println("tamanhoOtimoDVD: "+tamanhoOtimoDVD+" listaOtimaArquivos.size(): "+listaOtimaArquivos.size());
//			System.out.println("---");
//			long teste = 0L;
//			for (File file : listaArquivos) {
//				System.out.println(file.getName());
//				teste+=file.length();
//			}
//			System.out.println("TaMaNhO(bytes): "+teste);
//		}
		return tamanhoAtualDVD;
	}

	/**
	 * adiciona o arquivo à lista temp atual
	 * @param tamanhoAtualDVD
	 * @param tamanhoDesejadoDVD
	 * @param listaArquivos
	 * @param arquivo
	 */

	private static long adiciona(File arquivo, List<File> listaArquivos, long tamanhoAtualDVD, long tamanhoDesejadoDVD) {
		if((tamanhoAtualDVD + arquivo.length()) <= tamanhoDesejadoDVD)
		{
			tamanhoAtualDVD += arquivo.length();
			adicionaArquivoALista(arquivo,listaArquivos);
		}
		return tamanhoAtualDVD;
	}

	/**
	 * lista1 recebe todos os elementos da lista 2
	 * @param lista1
	 * @param lista2
	 */
	private static void copiaLista(List<File> lista1, List<File> lista2) {
		//lista1 = new ArrayList<File>(lista2);//copia o conteúdo de uma lista para a outra !
		//lista1 = new ArrayList<File>();
		
		for (File file : lista2) {
			lista1.add(file);
		}		
	}

	private static void printProgressoComparacao(int indice1) {
		if(indice1%5==0)
			System.out.println("Arquivo ["+indice1+"] de um total de ["+listaDeArquivosFile.size()+"] arquivos.");
	}

	private static void adicionaArquivoALista(File arquivo, List<File> listaArquivos) {
		if(arquivo.length()>tamanhoMinimoDeArquivo)
			listaArquivos.add(arquivo);
	}

	/**
	 * Retorna a string digitada pelo usuário no console.
	 * @return null se não foi capturada a string fornecida
	 */
	public static String lerConsole(){
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));  
		try {
			return in.readLine();
		} catch (IOException e) {
		}
		return null;
	}
	

//	public static String lerConsole2(){
//
//
//		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));  
//		try {
//			printLista(listaArquivosDVD); //joga a lista na tela
//			System.out.println("Continuar a busca? (s/n):");
//			return in.readLine();
//		} catch (IOException e) {
//		}
//		return null;
//	}

//	public static boolean continuarBusca(){
//		boolean respostaValida=false;
//		boolean fimDaBusca=false;
//		while(!respostaValida){
//			if(lerConsole2().equals("s")){
//				respostaValida=true;
//				return true;
//			}
//			else if(lerConsole2().equals("n")){
//				fimDaBusca=true;
//				return false;
//			}
//		}
//		return true;
//	}
}