package decorator;

import main.Produto;

public class StandardProduct implements Produto {

	private ProdutoFormatado component;

	public StandardProduct(Produto component) {
        this.component = (ProdutoFormatado)component;
    }

	@Override
	public void setQtdEstoque(int qtdEstoque) {
		component.setQtdEstoque(qtdEstoque);
	}

	@Override
	public void setPreco(double preco) {
		component.setPreco(preco);
	}

	@Override
	public int getId() {
		return component.getId();
	}

	@Override
	public String getDescricao() {
		return component.getDescricao();
	}

	@Override
	public String getCategoria() {
		return component.getCategoria();
	}

	@Override
	public int getQtdEstoque() {
		return component.getQtdEstoque();
	}

	@Override
	public double getPreco() {
		return component.getPreco();
	}

	@Override
	public String formataParaImpressao() {
		return "<span style=\"color: " + this.component.getCor() + "\">" + this.component.formataParaImpressao() + "</span>";
	}

}
