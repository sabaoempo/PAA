
import java.io.EOFException;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

class PesquisaObjetos {

	public static void main(String[] args) throws IOException, ClassNotFoundException {

		// Define o nome do arquivo a ser trabalhado
		System.out.print("Informe o nome do Arquivo a ser pesquisado: ");
		String nomeArquivo = Teclado.readLine();

		int numRegPesquisados = 0;

		try {

			// Objetos utilizados na manipulação do arquivo e seus dados
			FileInputStream arqEntrada = new FileInputStream(nomeArquivo);
			ObjectInputStream entrada = new ObjectInputStream(arqEntrada);

			System.out.print("Digite o nome da Empresa a ser pesquisada: ");
			String nome = Teclado.readLine();

			// Realiza a pesquisa no arquivo
			while (entrada.available() != 0) {

				Empresa empr = (Empresa) entrada.readObject();

				// empr.getClass().getMethod("get" + "Nome", );

				numRegPesquisados++;
				if (empr.getNome().startsWith(nome)) {
					System.out.println(empr);
					break;
				}
				entrada.close();
				arqEntrada.close();
				System.out.println("Digite o nome do novo arquivo: ");
				String novo = Teclado.readLine();
				SalvaComo(nome, novo);
			}
			System.out.println("Registro encontrado!\n");

		} catch (FileNotFoundException e) {
			System.out.println("O Arquivo informado não existe.\n" + e.toString());
		} catch (EOFException e) {
			System.out.println("Registro nao encontrado!\n");
		}

		System.out.println("Num. Registros Pesquisados: " + numRegPesquisados);
		

	}

	public static void SalvaComo(String atual, String novo) {
		try {
			FileInputStream nomeArquivo = new FileInputStream(atual);
			ObjectInputStream entrada = new ObjectInputStream(nomeArquivo);
			while(entrada.available() != 0) {
				Empresa empr = (Empresa) entrada.readObject();
				if(empr.getNome().startsWith(atual)) {
					FileOutputStream novoArquivo = new FileOutputStream(novo);
					ObjectOutputStream arquivo = new ObjectOutputStream(novoArquivo);
				}
			}
			
			nomeArquivo.close();
			entrada.close();
		} catch (Exception e) {
			System.out.println("Erro ao copiar o arquivo " + atual + " para o arquivo " + novo + ".");
		}
	}
}