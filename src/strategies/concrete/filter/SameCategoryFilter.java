package strategies.concrete.filter;
import main.Produto;
import strategies.FilterStrategy;

public class SameCategoryFilter implements FilterStrategy{
	@Override
	public boolean selecionarProduto(Produto p, String argFiltro) {
		return p.getCategoria().equalsIgnoreCase(argFiltro);
	}
}
