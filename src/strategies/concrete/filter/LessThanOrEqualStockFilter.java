package strategies.concrete.filter;
import main.Produto;
import strategies.FilterStrategy;

public class LessThanOrEqualStockFilter implements FilterStrategy{
    @Override
    public boolean selecionarProduto(Produto p, String argFiltro) {
        return p.getQtdEstoque() <= Integer.parseInt(argFiltro);
    }
}
