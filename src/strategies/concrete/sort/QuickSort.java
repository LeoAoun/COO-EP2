package strategies.concrete.sort;

import java.util.Collections;
import java.util.List;

import enums.CRIT;
import main.Produto;
import strategies.SortStrategy;

public class QuickSort implements SortStrategy {

	private int particiona(List<Produto> produtos, CRIT criterio, int ini, int fim) {

		// Produto x = produtos[ini];
		Produto x = produtos.get(ini);
		int i = (ini - 1);
		int j = (fim + 1);

		while (true) {

			if (criterio.equals(CRIT.descricao_c)) {

				do j--;
				while (produtos.get(j).getDescricao().compareToIgnoreCase(x.getDescricao()) > 0);
				// while(produtos[j].getDescricao().compareToIgnoreCase(x.getDescricao()) > 0);

				do i++;
				while (produtos.get(i).getDescricao().compareToIgnoreCase(x.getDescricao()) < 0);
				// while(produtos[i].getDescricao().compareToIgnoreCase(x.getDescricao()) < 0);
			
			} else if (criterio.equals(CRIT.preco_c)) {

				do j--;
				while (produtos.get(j).getPreco() > x.getPreco());
				// while(produtos[j].getPreco() > x.getPreco());

				do i++;
				while (produtos.get(i).getPreco() < x.getPreco());
				// while(produtos[i].getPreco() < x.getPreco());
			}

			else if (criterio.equals(CRIT.estoque_c)) {

				do j--;
				while (produtos.get(j).getQtdEstoque() > x.getQtdEstoque());
				// while(produtos[j].getQtdEstoque() > x.getQtdEstoque());

				do i++;
				while (produtos.get(i).getQtdEstoque() < x.getQtdEstoque());
				// while(produtos[i].getQtdEstoque() < x.getQtdEstoque());

			} else if (criterio.equals(CRIT.preco_d)) {

				do j--;
				while (produtos.get(j).getPreco() < x.getPreco()); 

				do i++;
				while (produtos.get(i).getPreco() > x.getPreco());
			
			} 
			else if (criterio.equals(CRIT.estoque_d)) {
				
				do j--;
				while (produtos.get(j).getQtdEstoque() < x.getQtdEstoque());

				do i++;
				while (produtos.get(i).getQtdEstoque() > x.getQtdEstoque()); 
			
			} 
			else if (criterio.equals(CRIT.descricao_d)) {
				
				do j--;
				while (produtos.get(j).getDescricao().compareToIgnoreCase(x.getDescricao()) < 0); 
																									

				do i++;
				while (produtos.get(i).getDescricao().compareToIgnoreCase(x.getDescricao()) > 0); 
																									
			}

			else {
				
				throw new RuntimeException("Criterio invalido!");
			}

			if (i < j) {
//				Produto temp = produtos[i];
//				produtos[i] = produtos[j]; 				
//				produtos[j] = temp;

				Produto temp = produtos.get(i);
				Collections.swap(produtos, i, j);
				produtos.set(j, temp);
			} else
				return j;
		}
	}

	@Override
	public void sort(List<Produto> produtos, CRIT criterio, int ini, int fim) {
		if (ini < fim) {

			int q = particiona(produtos, criterio, ini, fim);

			sort(produtos, criterio, ini, q);
			sort(produtos, criterio, q + 1, fim);
		}
	}

}
