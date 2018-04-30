package cz.blocksolver.backend.schema;

import cz.blocksolver.backend.block.Block;
import cz.blocksolver.backend.block.BlockType;
import cz.blocksolver.backend.port.InputPort;
import cz.blocksolver.frontend.DragBlock;

import java.lang.reflect.Array;
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

//
//    private Double getPortValue(InputPort port){
//        if(port.getConnectedToOutputPort()){
//            return port.getOutputPort().getValue();
//        }else if(port.getValueSet()){
//            return port.getValue();
//        }else{
//            return 0.0;
//        }
//    }
//
//    private Boolean hasSetValue(InputPort port){
//        if(port.getConnectedToOutputPort()){
//            return true;
//        }else if(port.getValueSet()){
//            return true;
//        }else{
//            return false;
//        }
//    }
//
//    @Override
//    public void executeSchema() {
//        Boolean executedSome = false;
//        System.out.println("SCHEMA EXXECUTING");
//        for(int i = 0; i < blocks.size(); i++) {
//            blocks.get(i).setExecuted(false);
//        }
//
//        do{
//            for(int i = 0; i < blocks.size(); i++){
//                executedSome = false;
//                System.out.println(blocks.get(i));
//                if(blocks.get(i).getType() == BlockType.ARITHMETIC){
//                    System.out.println("Je aritmeticky");
//                    if(hasSetValue(blocks.get(i).getInputPort(1)) && hasSetValue(blocks.get(i).getInputPort(2))){
//                        System.out.println("Hodnoty nastaveny");
//                        if(!blocks.get(i).getExecuted()){
//                            if(blocks.get(i).executeBlock(getPortValue(blocks.get(i).getInputPort(1)), getPortValue(blocks.get(i).getInputPort(2)))){
//                                System.out.println("Value in executed block " + blocks.get(i).getOutputPort().getValue());
//                                executedSome = true;
//                                blocks.get(i).setExecuted(true);
//                                break;
//                            }
//                        }
//                    }
//                }
//
//            }
//        }while(executedSome);
//
//        blocks.size();
//    }
}
