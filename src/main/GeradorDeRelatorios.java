package main;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import decorator.BoldProduct;
import decorator.ItalicBoldProduct;
import decorator.ItalicProduct;
import decorator.ProdutoFormatado;
import decorator.StandardProduct;
import enums.CRIT;
import enums.FILTER;
import enums.SORT;
import strategies.FilterStrategy;
import strategies.SortStrategy;

public class GeradorDeRelatorios {
	// operador bit a bit "ou" pode ser usado para combinar mais de
	// um estilo de formatacao simultaneamente (veja como no main)

	private List<Produto> produtos;
	private SORT algoritmo;
	private CRIT criterio;
	private FILTER filtro;
	private String argFiltro;
	
	private SortStrategy sortStrategy;
	private FilterStrategy filterStrategy;
	private Produto formatDecorator;

	public GeradorDeRelatorios(List<Produto> produtos, SORT algoritmo, CRIT criterio, FILTER filtro,
		String argFiltro) {
		
		this.produtos = new ArrayList<>(produtos);
		this.algoritmo = algoritmo;
		this.criterio = criterio;
		this.filtro = filtro;
		this.argFiltro = argFiltro;
			
	}
		
	public void geraRelatorio(String arquivoSaida) throws IOException {
		MetodosAuxiliaresGerador.debug(produtos, argFiltro);

		MetodosAuxiliaresGerador.ordena(produtos, algoritmo, criterio, sortStrategy, 0, produtos.size() - 1);

		PrintWriter out = new PrintWriter(arquivoSaida);

		MetodosAuxiliaresGerador.inicioHtml(out);
		
		int count = 0;

		// Verifica se um Produto p foi selecionado de acordo com o filtro 
		for (Produto p : produtos) {
		    boolean selecionado = false;
		    
		    filterStrategy = MetodosAuxiliaresGerador.filtroEscolhido(this.filtro);
		    
			selecionado = filterStrategy.selecionarProduto(p, argFiltro);

			if (selecionado) {
			    // System.out.println("Produto " + count + ": " + p.formataParaImpressao() + " selecionado!");
			    out.print("<li>");
			    
			    // Formata todos os produtos com base no arquivo produtos.csv
			    if(((ProdutoFormatado) p).isNegrito() && ((ProdutoFormatado) p).isItalico()) formatDecorator = new ItalicBoldProduct(p);
			    else if(((ProdutoFormatado) p).isNegrito()) formatDecorator = new BoldProduct(p);
			    else if(((ProdutoFormatado) p).isItalico()) formatDecorator = new ItalicProduct(p);
			    else formatDecorator = new StandardProduct(p);
			    
			    out.print(formatDecorator.formataParaImpressao());

			    out.println("</li>");
			    
			    count++;
			}

		}

		MetodosAuxiliaresGerador.fimHtml(produtos, out, count);
	}

}
