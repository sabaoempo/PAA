import javax.swing.JOptionPane;

public class CMain {

	public static Arquivo arq = new Arquivo("lista.txt");
	public static int opcao;

	public static void main(String[] args) {

		do {
			opcao = Integer.parseInt(JOptionPane.showInputDialog(
					"Digite o número da opção desejada: \n1 - Inserir aluno\n2 - Listar registros\n3 - Pesquisa (nome)\n4 - Pesquisa (código)\n5 - Sair"));

			switch (opcao) {
			case 1:
				int codigo = Integer.parseInt(JOptionPane.showInputDialog("Digite o código do aluno: "));
				String nome = JOptionPane.showInputDialog("Digite o nome do aluno: ");
				inserir(codigo, nome);
				break;

			case 2:
				try {
					arq.listarArquivo();
				} catch (Exception e) {
					System.out.println(e);
				}

				break;

			case 3:
				String nomeAluno = JOptionPane.showInputDialog("Digite o nome do aluno: ");
				try {
					arq.pesquisaNome(nomeAluno);
				} catch (Exception e) {
					System.out.println(e);
				}

				break;

			case 4:
				int codigoAluno = Integer.parseInt(JOptionPane.showInputDialog("Digite o código do aluno: "));
				try {
					arq.pesquisaBinaria(codigoAluno);
				} catch (Exception e) {
					System.out.println(e);
				}
				break;

			case 5:
				break;

			default:
				JOptionPane.showMessageDialog(null, "Por favor, digite uma entrada válida.");

			}
		} while (opcao != 5);

	}

	public static void inserir(int codigo, String nome) {
		try {
			Aluno aluno = new Aluno();
			aluno.setCodigo(codigo);
			aluno.setNome(nome);
			arq.salvarRegistro(aluno);
		} catch (Exception e) {
			System.out.println("Exceção E/S");
		}

	}

}
