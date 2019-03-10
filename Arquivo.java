
import java.io.*;
import javax.swing.JOptionPane;

public class Arquivo {

	String nomeArquivo;

	Arquivo(String nomeArquivo) {
		this.nomeArquivo = nomeArquivo;
	}

	Arquivo() {
		this.nomeArquivo = "";
	}

	public void salvarRegistro(Registro r) throws IOException {
		RandomAccessFile file = new RandomAccessFile(nomeArquivo, "rw");
		file.seek(file.length());
		file.writeInt(r.getByteArray().length);
		file.write(r.getByteArray());
		file.close();
	}

	public void listarArquivo() throws IOException {
		RandomAccessFile file = new RandomAccessFile(nomeArquivo, "r");

		int cont = 0;
		while (cont < file.length()) {

			int size = file.readInt();
			byte b[] = new byte[size];
			file.read(b);

			Aluno a = new Aluno();
			a.loadData(b);
			a.print();

			cont = cont + 4 + size;

		}
		file.close();
	}

	public Aluno pesquisaBinaria(int codigo) throws IOException {
		// looks like searching for a number from 0-1024 in 10 tries;
		// System.out.println("Pesquisando...");

		RandomAccessFile file = new RandomAccessFile(nomeArquivo, "rw");
		file.seek(0);
		int tam_registro_bytes = 4 + 4 + Aluno.TAM_NOME;
		// int numRegistros = (int) file.length() / tam_registro_bytes;
		long begin = 0, end = file.length() - tam_registro_bytes, half;

		// determines the middle of a file
		// int pos = (int) (numRegistros / 2) * tam_registro_bytes;

		do {
			half = (begin + end) / 2;

			// if "half" is not divisible by file length
			// avoids the cursor from falling in the middle of a record
			if (half % tam_registro_bytes != 0)
				half += (half % tam_registro_bytes);
			file.seek(half);

			// Ler o registro na posiÃ§Ã£o definida
			int size = file.readInt();
			byte b[] = new byte[size];
			file.read(b);

			// Carrega o objeto da classe aluno e imprime o conteÃºdo
			Aluno a = new Aluno();
			a.loadData(b);// old setByteArray
			if (codigo == a.getCodigo()) {
				JOptionPane.showMessageDialog(null,
						"Registro encontrado.\nCódigo: " + a.getCodigo() + "\nNome: " + a.getNome());
				end = -1;
			}
			else if (codigo > a.getCodigo()) {
				begin = half + tam_registro_bytes;
			}
			else if (codigo < a.getCodigo()) {
				end = half - tam_registro_bytes;
			}
			

		} while (begin <= end);

		// System.out.println("Fim Pesquisa");

		file.close();
		return null;

	}

	public Aluno pesquisaNome(String nome) throws IOException {
		RandomAccessFile file = new RandomAccessFile(nomeArquivo, "r");
		int cont = 0;
		while(cont < file.length()) {
			int size = file.readInt();
			byte b[] = new byte[size];
			file.read(b);
			Aluno a = new Aluno();
			a.loadData(b);
			if(a.getNome().startsWith(nome)) {
				JOptionPane.showMessageDialog(null, "Encontrado: " + a.getNome());
				break;
			}
			cont += 4 + size;
		}
		
		file.close();
		return null;
	}

}
