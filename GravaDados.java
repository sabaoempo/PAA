
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.StringTokenizer;

public class GravaDados {

	public static String getStringTamanhoFixo(String texto, int tamanho) {
		StringBuffer s1 = new StringBuffer(texto);
		s1.setLength(tamanho);
		return s1.toString();
	}

	public static void main(String[] args) {

		try {

			// Define o nome do arquivo a ser trabalhado
			System.out.print("Informe o nome do Arquivo a ser gravado: ");
			String nomeArquivo = Teclado.readLine();

			// Objetos utilizados na manipulação do arquivo e seus dados
			FileOutputStream arqSaida = new FileOutputStream(nomeArquivo);
			PrintStream saida = new PrintStream(arqSaida);

			System.out.print("Informe o número de registros a serem gravados: ");
			int numRegistros = Teclado.readInt();

			// Adiciona os registros desejados
			for (int i = 0; i < numRegistros; i++) {
				System.out.print("Informe o código do Veículo: ");
				int codigo = Teclado.readInt();

				System.out.print("Nome Veiculo: ");
				String nomeVeiculo = Teclado.readLine();

				String linha = codigo + " " + nomeVeiculo;
				saida.println(linha);

				// >>> E se a duas linhas a seguir substituissem as 2 linhas anteriores? O que
				// aconteceria com os registros?
				// >>> String linha2 = getStringTamanhoFixo(codigo+"", 5) + " " +
				// getStringTamanhoFixo(nomeVeiculo, 15);
				// >>> saida.println(linha2);

			}

			// Finalização da manipulação dos dados no arquivo
			saida.flush();
			saida.close();
			arqSaida.close();

			System.out.println("Dados Gravados com sucesso");
			System.out.println("Caso deseje fazer uma cópia do arquivo, digite 1.");
			int resposta = Teclado.readInt();
			if (resposta == 1) {
				System.out.println("Digite outro nome para o arquivo: ");
				String novoNome = Teclado.readLine();
				SalvarComo(nomeArquivo, novoNome);
			}

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
			while (entrada.available() != 0) {
				String linha = entrada.readLine();//tentar refazer com bufferedreader
				StringTokenizer listaPalavras = new StringTokenizer(linha);//pesquisar sobre
				String id = listaPalavras.nextToken();
				String nome = listaPalavras.nextToken();
				String escrever = id + " " + nome;
				saida.println(escrever);
				System.out.println("Arquivo gravado com sucesso!");
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
