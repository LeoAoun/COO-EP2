package strategies.concrete.filter;
import main.Produto;
import strategies.FilterStrategy;

public class EveryFilter implements FilterStrategy{
    @Override
    public boolean selecionarProduto(Produto p, String argFiltro) {
        return true;
    }

}
