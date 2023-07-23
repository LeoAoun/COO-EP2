package strategies.concrete.sort;

import java.util.List;

import enums.CRIT;
import main.Produto;
import strategies.SortStrategy;

public class InsertionSort implements SortStrategy {

	@Override
	public void sort(List<Produto> produtos, CRIT criterio, int ini, int fim) {
		for (int i = ini; i <= fim; i++) {

			// Produto x = produtos[i];
			Produto x = produtos.get(i);
			int j = (i - 1);

			while (j >= ini) {

				if (criterio.equals(CRIT.descricao_c)) {

					// if( x.getDescricao().compareToIgnoreCase(produtos[j].getDescricao()) < 0 ){
					if (x.getDescricao().compareToIgnoreCase(produtos.get(j).getDescricao()) < 0) {
						// produtos[j + 1] = produtos[j];
						produtos.set(j + 1, produtos.get(j));
						j--;
					} else
						break;
				} 
				else if (criterio.equals(CRIT.preco_c)) {

					if (x.getPreco() < produtos.get(j).getPreco()) {

						// produtos[j + 1] = produtos[j];
						produtos.set(j + 1, produtos.get(j));
						j--;
					} else
						break;
				} 
				else if (criterio.equals(CRIT.estoque_c)) {

					// if(x.getQtdEstoque() < produtos[j].getQtdEstoque()){
					if (x.getQtdEstoque() < produtos.get(j).getQtdEstoque()) {

						// produtos[j + 1] = produtos[j];
						produtos.set(j + 1, produtos.get(j));
						j--;
					} else
						break;
				} 
				else if (criterio.equals(CRIT.preco_d)) {
					if (x.getPreco() > produtos.get(j).getPreco()) { 
						produtos.set(j + 1, produtos.get(j));
						j--;
					} else
						break;
				} 
				else if (criterio.equals(CRIT.estoque_d)) {
					if (x.getQtdEstoque() > produtos.get(j).getQtdEstoque()) { 						
						produtos.set(j + 1, produtos.get(j));
						j--;
					} else
						break;
				} 
				else if (criterio.equals(CRIT.descricao_d)) {
					if (x.getDescricao().compareToIgnoreCase(produtos.get(j).getDescricao()) > 0) { 
																									
						produtos.set(j + 1, produtos.get(j));
						j--;
					} else
						break;
				} 
				else
					throw new RuntimeException("Criterio invalido!");
			}

			// produtos[j + 1] = x;
			produtos.set(j + 1, x);
		}
	}

}
