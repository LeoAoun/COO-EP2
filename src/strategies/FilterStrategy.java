package strategies;
import main.Produto;

public interface FilterStrategy {
    boolean selecionarProduto(Produto p, String argFiltro);
}
