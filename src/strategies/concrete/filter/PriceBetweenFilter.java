package strategies.concrete.filter;

import main.Produto;
import strategies.FilterStrategy;

public class PriceBetweenFilter implements FilterStrategy {

	@Override
	public boolean selecionarProduto(Produto p, String argFiltro) {
		
		// Verificar se o argumento argFiltro é válido (dois números separados por
		// vírgula)
		String[] limites = argFiltro.split(",");
		if (limites.length != 2) {
			throw new IllegalArgumentException("O argumento de filtro deve conter dois números separados por vírgula.");
		}

		// Converter os limites do intervalo para valores double
		double limiteInferior = Double.parseDouble(limites[0]);
		double limiteSuperior = Double.parseDouble(limites[1]);

		// Obter o preço do produto
		double precoProduto = p.getPreco();

		// Verificar se o preço do produto está dentro do intervalo
		return (precoProduto >= limiteInferior && precoProduto <= limiteSuperior);
	}

}
