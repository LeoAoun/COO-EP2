package strategies.concrete.filter;

import main.Produto;
import strategies.FilterStrategy;

public class DescriptionContainsSubstringFilter implements FilterStrategy {

	@Override
	public boolean selecionarProduto(Produto p, String argFiltro) {
		
        // Verifica se a descrição contém a substring fornecida pelo filtro
		return p.getDescricao().contains(argFiltro);
	}

}
