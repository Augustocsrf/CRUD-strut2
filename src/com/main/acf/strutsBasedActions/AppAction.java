package com.main.acf.strutsBasedActions;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.opensymphony.xwork2.ActionSupport;

public class AppAction extends ActionSupport {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	static String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static String JDBC_URL = "jdbc:mysql://localhost:3306/db";
	static String USR = "root";
	static String PASS = "";
	static Connection con; 
	
	private String id;
	private String nome;
	private String exame1;
	private String exame2;

	private String r = "";
	private String newLine = "";
	private String originalLine = "";
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getExame1() {
		return exame1;
	}

	public void setExame1(String exame1) {
		this.exame1 = exame1;
	}

	public String getExame2() {
		return exame2;
	}

	public void setExame2(String exame2) {
		this.exame2 = exame2;
	}
	
	public String getR() {
		return r;
	}

	public void setR(String r) {
		this.r = r;
	}

	public String getNewLine() {
		return newLine;
	}

	public void setNewLine(String newLine) {
		this.newLine = newLine;
	}

	public String getOriginalLine() {
		return originalLine;
	}

	public void setOriginalLine(String originalLine) {
		this.originalLine = originalLine;
	}
	
	public String addElement() throws ClassNotFoundException, SQLException {
		//Definir commando que acontecerá
		String command = "INSERT INTO middle(Nome, Nota_do_Exame_1, Nota_do_Exame_2) VALUES ('" + getNome() + "'," + getExame1() 
			+ "," + getExame2() + ")";
		
		Statement st = connectToDatabase(); 
		st.executeUpdate(command);
		
		setR("Added values " + "('" +  getNome() + "'," + getExame1() + "," + getExame2() + ")");
			
		con.close();
		return SUCCESS;
	}

	public String findById() throws ClassNotFoundException, SQLException {
		//Apagar valor de R
		setR("");
		
		Statement st = connectToDatabase(); 
		ResultSet rSet = st.executeQuery("SELECT * FROM middle WHERE Matricula=" + id);
		
		//Pegar os valores relacionados com aquela matricula
		while (rSet.next()) {
			setR("{matricula: " +  rSet.getInt(1) + ", nome: " + rSet.getString(2) + ", nota do Exame 1: " + rSet.getInt(3)
				+ ", nota do Exame 2: " + rSet.getInt(4) + "};");
		}
			
		if(getR().equals("")) {
			setR("Matricula não encontrada");
		}
		con.close();
		
		
		return SUCCESS;
	}
	
	public String findAll() throws ClassNotFoundException, SQLException {
		Statement st = connectToDatabase(); 
		ResultSet rSet = st.executeQuery("SELECT * FROM middle");
		
		//Pegar todos os valores no banco de dados
		while (rSet.next()) {
			setR(getR() + "{matricula: " +  rSet.getInt(1) + ", nome: " + rSet.getString(2) +
					", nota do Exame 1: " + rSet.getInt(3) + ", nota do Exame 2: " + rSet.getInt(4) + "};\n");
		}
		
		if(getR().equals("")) {
			setR("O Banco de dados está vazio");
		}
		
		con.close();
		return SUCCESS;
	}
	
	public String alterById() throws ClassNotFoundException, SQLException{
		String getValuesCommand = "SELECT * FROM middle WHERE Matricula=" + getId();
		
		//Limpar valores de variaveis		
		setOriginalLine("");
		setNewLine("");
		setR("");
		
		Statement st = connectToDatabase(); 
		ResultSet rSet = st.executeQuery(getValuesCommand);
		
		//Pegar os valores ligados aquela matricula. Ajustar caso os valores estejam vazios.
		while(rSet.next()) {
			setOriginalLine("{matricula: " + rSet.getInt(1) + ", nome: " + rSet.getString(2) + ", nota do Exame 1: "
				+ rSet.getInt(3) + ", nota do Exame 2: " + rSet.getInt(4) + "};\n");
				
			if(getNome().equals("")){
				setNome(rSet.getString(2));
			}
				
			if(getExame1().equals("")) {
				setExame1(Integer.toString(rSet.getInt(3)));
			}
			
			if(getExame2().equals("")) {
				setExame2(Integer.toString(rSet.getInt(4)));
			}
				
		}
			
		//Executar Update
		st.executeUpdate("UPDATE middle SET Nome='" + getNome() + "', Nota_do_Exame_1=" + getExame1() + 
					", Nota_do_Exame_2=" + getExame2() + " WHERE Matricula=" + getId());
		
		//Pegar valor novo relacionado a matricula
		rSet = st.executeQuery(getValuesCommand);
			
		while(rSet.next()) {
			setNewLine("{matricula: " + rSet.getInt(1) + ", nome: " + rSet.getString(2) + ", nota do Exame 1: "
						+ rSet.getInt(3) + ", nota do Exame 2: " + rSet.getInt(4) + "};\n");
		}
		
		setR("Linha com informações " + originalLine + " alterada para os valores " + newLine);
		
		con.close();
		
		return SUCCESS;
	}
	
	public String deleteById() throws Exception {
		String deleteCommand = "DELETE FROM middle WHERE Matricula = " + getId();
		String deletedInformation = "";
		setR("");
		
		//Pegar o valor anterior
		Statement st = connectToDatabase();
		ResultSet rSet = st.executeQuery("SELECT * FROM middle WHERE Matricula=" + getId());
		
		while (rSet.next()) {
			deletedInformation += "{matricula: " +  rSet.getInt(1) + ", nome: " + rSet.getString(2) + ", nota do Exame 1: " + rSet.getInt(3)
				+ ", nota do Exame 2: " + rSet.getInt(4) + "};";
		}
		
		setR("Deletado informações da linha de matricula " + getId() + "\n Informações deletadas: " + deletedInformation);
		
		//Deletar o elemento com aquela matricula
		st.executeUpdate(deleteCommand);
		
		return SUCCESS;
	}
	
	public Statement connectToDatabase() throws ClassNotFoundException, SQLException {
		Statement st = null;
		
		//Criar Statement e conecar com o banco de dados
		Class.forName(JDBC_DRIVER);
		con = DriverManager.getConnection(JDBC_URL, USR, PASS);
		st = con.createStatement();
		
		return st;
	}
}
