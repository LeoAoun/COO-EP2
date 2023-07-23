package strategies;

import java.util.List;

import enums.*;
import main.Produto;

public interface SortStrategy {
    void sort(List<Produto> produtos, CRIT criterio, int ini, int fim);
}
