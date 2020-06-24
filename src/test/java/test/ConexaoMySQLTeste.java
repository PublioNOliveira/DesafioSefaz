package test;

import java.sql.Connection;

import org.junit.Test;

import junit.framework.TestCase;
import util.ConexaoMySQL;

public class ConexaoMySQLTeste extends TestCase {

	protected void setUp() throws Exception {
		super.setUp();
	}
	@Test
	public void testConexaoMySQL() {
		Connection conexao = ConexaoMySQL.getConexaoMySQL();
		if(conexao == null) {
			System.out.println("Não conectou");
		}
		else {
			System.out.println("Conectou");
		}
	}
	
}
