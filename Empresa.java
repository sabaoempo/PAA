

import java.io.Serializable;

class Empresa implements Serializable {
	private int id;
	private String nome;
	public Empresa(int id, String nome) {
		this.id = id;
		this.nome = nome;
	}
	public String toString() {
		return "Codigo : " + String.valueOf(id) + "\tEmpresa: " + nome;
	}
	public int getId() {
		return id;
	}
	public String getNome(){
		return nome;
	}
}