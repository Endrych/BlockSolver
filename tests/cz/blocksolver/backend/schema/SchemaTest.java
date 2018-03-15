package cz.blocksolver.backend.schema;

import cz.blocksolver.backend.block.ArithmeticBlock;
import cz.blocksolver.backend.block.Block;
import cz.blocksolver.backend.block.BlockType;
import cz.blocksolver.backend.block.arithmetic.AddOperation;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class SchemaTest {
    private Schema schema;

    @Before
    public void init(){
        schema = new Schema();
    }

    @Test
    public void addBlocks(){
        ArithmeticBlock block = new ArithmeticBlock("Test", 10,20, 20, 20, AddOperation.getInstance());
        schema.addBlock(block);
        assertEquals(schema.getBlocks().size(),1);
    }

    @Test
    public void removeBlocks(){
        ArithmeticBlock block = new ArithmeticBlock("Test", 10,20, 20, 20, AddOperation.getInstance());
        schema.addBlock(block);
        assertEquals(schema.getBlocks().size(),1);
        schema.removeBlock(block);
        assertEquals(schema.getBlocks().size(),0);
    }

}