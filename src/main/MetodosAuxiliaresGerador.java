package main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import decorator.ProdutoFormatado;
import enums.CRIT;
import enums.FILTER;
import enums.SORT;
import strategies.FilterStrategy;
import strategies.SortStrategy;
import strategies.concrete.filter.SameCategoryFilter;
import strategies.concrete.filter.DescriptionContainsSubstringFilter;
import strategies.concrete.filter.LessThanOrEqualStockFilter;
import strategies.concrete.filter.PriceBetweenFilter;
import strategies.concrete.filter.EveryFilter;
import strategies.concrete.sort.InsertionSort;
import strategies.concrete.sort.QuickSort;

public class MetodosAuxiliaresGerador {

	public static void inicioHtml(PrintWriter out) {
		out.println("<!DOCTYPE html><html>");
		out.println("<head><title>Relatorio de produtos</title></head>");
		out.println("<body>");
		out.println("Relatorio de Produtos:");
		out.println("<ul>");
	}

	public static void fimHtml(List<Produto> produtos, PrintWriter out, int count) {
		out.println("</ul>");
		out.println(count + " produtos listados, de um total de " + produtos.size() + ".");
		out.println("</body>");
		out.println("</html>");
		out.close();
	}

	public static void debug(List<Produto> produtos, String argFiltro) {
		System.out.println("Gerando relatório para array contendo " + produtos.size() + " produto(s)");
		System.out.println("parametro filtro = '" + argFiltro + "'");
	}

	/*
	 * Carrega os produtos do arquivo produtos.csv para uma lista de produtos do
	 * tipo Produto
	 */
	public static List<Produto> carregaProdutos() {

		// Obtém a classe atual
		Class<?> classeAtual = GeradorDeRelatorios.class;

		// Obtém o diretório em que a classe está localizada
		String diretorioClasse = classeAtual.getProtectionDomain().getCodeSource().getLocation().getPath();

		// Constrói o caminho completo até a pasta "src"
		File pastaSrc = new File(diretorioClasse).getParentFile();

		// Constrói o caminho completo para o arquivo "produtos.csv"
		String caminhoArquivoCSV = pastaSrc.getAbsolutePath() + File.separator + "produtos.csv";

		System.out.println("Path utilizado do arquivo produtos.csv: " + caminhoArquivoCSV);

		List<Produto> produtos = new ArrayList<>();

		// Lê o arquivo produtos.csv e coloca todos os produtos em uma lista devidamente
		// formatados
		try (BufferedReader br = new BufferedReader(new FileReader(caminhoArquivoCSV))) {
			String headerLine = br.readLine();
			String[] headers = headerLine.split(",");

			int idIndex = MetodosAuxiliaresGerador.getIndex(headers, "ID");
			int descricaoIndex = MetodosAuxiliaresGerador.getIndex(headers, "DESCRIÇÃO");
			int categoriaIndex = MetodosAuxiliaresGerador.getIndex(headers, "CATEGORIA");
			int qtdEstoqueIndex = MetodosAuxiliaresGerador.getIndex(headers, "QUANTIDADE_ESTOQUE");
			int precoIndex = MetodosAuxiliaresGerador.getIndex(headers, "PREÇO");
			int negritoIndex = MetodosAuxiliaresGerador.getIndex(headers, "NEGRITO");
			int italicoIndex = MetodosAuxiliaresGerador.getIndex(headers, "ITALICO");
			int corIndex = MetodosAuxiliaresGerador.getIndex(headers, "COR");

			String line;
			while ((line = br.readLine()) != null) {

				String[] data = line.split(",");

				int id = Integer.parseInt(data[idIndex].trim());
				String descricao = data[descricaoIndex].trim();
				String categoria = data[categoriaIndex].trim();
				int qtdEstoque = Integer.parseInt(data[qtdEstoqueIndex].trim());
				double preco = Double.parseDouble(data[precoIndex].trim());

				boolean negrito = Boolean.parseBoolean(data[negritoIndex].trim());
				boolean italico = Boolean.parseBoolean(data[italicoIndex].trim());
				String cor = data[corIndex].trim();

				Produto produto;
				produto = new ProdutoFormatado(id, descricao, categoria, qtdEstoque, preco, negrito, italico, cor);

				produtos.add(produto);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return produtos;
	}

	/*
	 * Ordena os produtos da lista de produtos com base em um algoritmo de ordenação
	 * e um critério
	 */
	public static void ordena(List<Produto> produtos, SORT algoritmo, CRIT criterio, SortStrategy sortStrategy, int ini,
			int fim) {
		if (algoritmo.equals(SORT.insertion))
			sortStrategy = new InsertionSort();
		else if (algoritmo.equals(SORT.quick))
			sortStrategy = new QuickSort();
		else
			throw new RuntimeException("Algoritmo invalido!");

		sortStrategy.sort(produtos, criterio, ini, fim);
	}

	// Função auxiliar para pegar o índice de um campo no header de produtos.csv
	static int getIndex(String[] headers, String headerName) {
		for (int i = 0; i < headers.length; i++) {
			if (headers[i].trim().equalsIgnoreCase(headerName)) {
				return i;
			}
		}
		return -1;
	}

	public static FilterStrategy filtroEscolhido(FILTER filtro) {

		switch (filtro) {
		case todos:
			return new EveryFilter();
		case estoque_menor_igual:
			return new LessThanOrEqualStockFilter();
		case categoria_igual:
			return new SameCategoryFilter();
		case preco_no_intervalo:
			return new PriceBetweenFilter();
		case descricao_contem_substring:
			return new DescriptionContainsSubstringFilter();
		default:
			throw new RuntimeException("Filtro inválido!");
		}

	}

}
