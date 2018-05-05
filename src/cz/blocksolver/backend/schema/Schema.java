package cz.blocksolver.backend.schema;

import cz.blocksolver.backend.block.Block;
import cz.blocksolver.backend.block.BlockType;
import cz.blocksolver.backend.port.InputPort;
import cz.blocksolver.frontend.DragBlock;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Trida reprezentujici schema
 * @author David Endrych (xendry02)
 * @author Marek Kukučka (xkukuc04)
 */
public class Schema implements ISchema {

    private String name;

    private List<Block> blocks;

    /**
     * Konstruktor
     * @param name Nazev schematu
     */
    public Schema(String name){
        this.name = name;
        blocks = new ArrayList<>();
    }

    /**
     * Getter pro bloky ve schematu
     * @return seznam bloku ktere nalezi schematu
     */
    public List<Block> getBlocks() {
        return blocks;
    }

    /**
     * Getter pro nazev schematu
     * @return nazev schematu
     */
    public String getName() {
        return name;
    }

    /**
     * Setter pro nazev schematu
     * @param name novy nazev schematu
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Metoda prida blok do schematu
     * @param block blok ktery se ma pridat do schematu
     */
    @Override
    public void addBlock(Block block) {
        blocks.add(block);
    }

    /**
     * Metoda odstrani blok ze schematu
     * @param block blok ktery se ma odstranit ze schematu
     */
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
    public String toString() {
        return this.name;
    }
}
