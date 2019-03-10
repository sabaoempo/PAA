import java.io.*;

public class Aluno implements Registro {
	protected int codigo;
	protected String nome;
	
	public final static int TAM_NOME = 20;

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public int getCodigo() {
		return this.codigo;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getNome() {
		return nome;
	}

	public static String getStringTamanhoFixo(String texto, int tamanho) {
		StringBuffer s1 = new StringBuffer(texto);
		s1.setLength(tamanho);
		return s1.toString();
	}
	
	public byte[] getByteArray() throws IOException {
		ByteArrayOutputStream registro = new ByteArrayOutputStream();
		DataOutputStream saida = new DataOutputStream(registro);
		saida.writeInt(codigo);
		
		//Escreve Tamanho Fixo
		saida.write((getStringTamanhoFixo(nome,TAM_NOME)).getBytes());
		
		return registro.toByteArray();
	}

	public void loadData(byte[] b) throws IOException {
		ByteArrayInputStream registro = new ByteArrayInputStream(b);
		DataInputStream entrada = new DataInputStream(registro);
		codigo = entrada.readInt();
		//nome = entrada.readUTF();
		nome = entrada.readLine();
	}

	public int compareTo(Object b) {
		return codigo - ((Aluno) b).getCodigo();
	}

	public Aluno clone() throws CloneNotSupportedException {
		return ((Aluno) super.clone());
	}

	public void print() {
		System.out.println(this.codigo + " " + this.nome);
	}
}
