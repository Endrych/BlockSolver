package cz.blocksolver.backend.schema;

import cz.blocksolver.backend.block.Block;

/**
 * Interface pro Schema
 * @author David Endrych (xendry02)
 * @author Marek Kukučka (xkukuc04)
 */
public interface ISchema {
    /**
     * Metoda pro pridani bloku do schematu
     * @param block blok ktery se ma pridat do schematu
     */
    void addBlock(Block block);

    /**
     * Metoda pro odstraneni bloku ze schematu
     * @param block blok ktery se ma odstranit ze schematu
     */
    void removeBlock(Block block);
}
