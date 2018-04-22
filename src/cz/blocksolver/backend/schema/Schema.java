package cz.blocksolver.backend.schema;

import cz.blocksolver.backend.block.Block;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Schema implements ISchema {

    private String name;

    private List<Block> blocks;

    public Schema(String name){
        this.name = name;
        blocks = new ArrayList<>();
    }

    public List<Block> getBlocks() {
        return blocks;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void addBlock(Block block) {
        blocks.add(block);
    }

    @Override
    public void removeBlock(Block block) {
        if(blocks.contains(block)){
            blocks.remove(block);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Schema schema = (Schema) o;
        return Objects.equals(name, schema.name) &&
                Objects.equals(blocks, schema.blocks);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, blocks);
    }

    @Override
    public void checkForCycles() {
        throw new java.lang.UnsupportedOperationException();
    }

    @Override
    public String toString() {
        return this.name;
    }
}
