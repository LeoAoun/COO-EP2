package main;

import java.io.IOException;

import enums.CRIT;
import enums.FILTER;
import enums.SORT;

public class Main {

	public static final int FORMATO_PADRAO = 0b0000;
	public static final int FORMATO_NEGRITO = 0b0001;
	public static final int FORMATO_ITALICO = 0b0010;

	public static void main(String[] args) {
		if (args.length < 3) {

			System.out.println("Uso:");
			System.out.println("\tjava " + GeradorDeRelatorios.class.getName()
					+ " <algoritmo> <critério de ordenação> <critério de filtragem> <parâmetro de filtragem (se houver)>");
			System.out.println("Onde:");
			System.out.println("\talgoritmo: 'quick' ou 'insertion'");
			System.out.println(
					"\tcriterio de ordenação: 'preco_c' ou 'descricao_c' ou 'estoque_c' ou 'preco_d' ou 'descricao_d' ou 'estoque_d' ");
			System.out.println(
					"\tcriterio de filtragem: 'todos' ou 'estoque_menor_igual' ou 'categoria_igual' ou 'preco_no_intervalo' ou 'descricao_contem_substring' ");
			System.out.println("\tparâmetro de filtragem: argumentos adicionais necessários para a filtragem");
			System.out.println();
			System.exit(1);
		}

		SORT opcao_algoritmo = SORT.valueOf(args[0]);
		CRIT opcao_criterio_ord = CRIT.valueOf(args[1]);
		FILTER opcao_criterio_filtro = FILTER.valueOf(args[2]);

		String opcao_parametro_filtro = null;

		if (!opcao_criterio_filtro.equals(FILTER.todos))
			opcao_parametro_filtro = args[3];

		GeradorDeRelatorios gdr = new GeradorDeRelatorios(MetodosAuxiliaresGerador.carregaProdutos(), opcao_algoritmo,
				opcao_criterio_ord, opcao_criterio_filtro, opcao_parametro_filtro);

		try {
			gdr.geraRelatorio("saida.html");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
