package decorator;

import java.text.NumberFormat;

import main.ProdutoPadrao;

public class ProdutoFormatado extends ProdutoPadrao{

	private static final String SEPARADOR = ", ";
	boolean negrito;
	boolean italico;
	String cor;
	
	public ProdutoFormatado(int id, String descricao, String categoria, int qtdEstoque,
			double preco, boolean negrito, boolean italico, String cor) {
		
		super(id, descricao, categoria, qtdEstoque, preco);
		this.negrito = negrito;
		this.italico = italico;
		this.cor = cor;
	
	}

	public boolean isNegrito() {
		return negrito;
	}

	public boolean isItalico() {
		return italico;
	}

	public String getCor() {
		return cor;
	}

	@Override
	public String formataParaImpressao() {

		NumberFormat fmt = NumberFormat.getCurrencyInstance();

		return getDescricao() + SEPARADOR + getCategoria() + SEPARADOR + fmt.format(getPreco()) + SEPARADOR + getQtdEstoque() + " unidade(s) em estoque";
	}

}
