
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintStream;

public class GravaObjetos {

	public static void main(String[] args) {

		try {

			// Define o nome do arquivo a ser trabalhado
			System.out.print("Informe o nome do Arquivo a ser gravado: ");
			String nomeArquivo = Teclado.readLine();

			// Objetos utilizados na manipulação do arquivo e seus dados
			FileOutputStream arqSaida = new FileOutputStream(nomeArquivo);
			ObjectOutputStream saida = new ObjectOutputStream(arqSaida);

			System.out.print("Informe o número de registros a serem gravados: ");
			int numRegistros = Teclado.readInt();

			// Adiciona os registros desejados
			for (int i = 0; i < numRegistros; i++) {
				System.out.print("Informe o código da Pessoa: ");
				int codigo = Teclado.readInt();

				System.out.print("Informe o nome da Pessoa: ");
				String nomeEmpresa = Teclado.readLine();

				Empresa e = new Empresa(codigo, nomeEmpresa);
				saida.writeObject(e);

			}

			// Finalização da manipulação dos dados no arquivo
			saida.flush();
			saida.close();
			arqSaida.close();

			System.out.println("Dados Gravados com sucesso");
			System.out.println("Deseja salvar como? 1 para sim");
			int opcao = Teclado.readInt();
			if(opcao == 1) {
				System.out.println("Informe o nome atual do arquivo: ");
				String nomeAtual = Teclado.readLine();
				System.out.println("Informe o nome do novo arquivo: ");
				String nomeNovo = Teclado.readLine();
				SalvarComo(nomeAtual, nomeNovo);
			}

		} catch (FileNotFoundException e) {
			System.out.println("O Arquivo informado não existe.\n" + e.toString());
		} catch (IOException e) {
			System.out.println("O seguinte erro de I/O ocorreu:\n" + e.toString());
		} catch (Exception e) { // Tratamento genérico da excessão ocorrida
			System.out.println("O seguinte erro ocorreu: " + e.toString());
		}
	}

	public static void SalvarComo(String atual, String novo) {
		try {
			FileInputStream arqEntrada = new FileInputStream(atual);
			DataInputStream entrada = new DataInputStream(arqEntrada);
			FileOutputStream arqSaida = new FileOutputStream(novo);
			PrintStream saida = new PrintStream(arqSaida);
			byte[] buffer = new byte[1024];
			int length;
			while ((length = arqEntrada.read(buffer)) > 0) {
				arqSaida.write(buffer, 0, length);
			}
			saida.flush();
			saida.close();
			arqSaida.close();
			entrada.close();
			arqEntrada.close();
		} catch (FileNotFoundException e) {
			System.out.println(e.toString() + " inexistente.");
		} catch (IOException e) {
			System.out.println("Erro IO: " + e.toString());
		} catch (Exception e) {
			System.out.println("Não foi possível fazer uma cópia do arquivo.");
		} finally {
			System.out.println("Chamou função salvar como.");
		}

	}
}
