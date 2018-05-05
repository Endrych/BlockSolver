package cz.blocksolver.backend.schema;

import cz.blocksolver.backend.block.Block;

public interface ISchema {
    void addBlock(Block block);
    void removeBlock(Block block);
    void checkForCycles();
//    void executeSchema();
}
