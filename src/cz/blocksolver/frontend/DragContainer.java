package cz.blocksolver.frontend;


import javafx.scene.input.DataFormat;
import javafx.util.Pair;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Slouzi k ulozeni hodnot pri pretahovani bloku
 * @author David Endrych (xendry02)
 * @author Marek Kukučka (xkukuc04)
 */
public class DragContainer implements Serializable{

    private static final long serialVersionUID = -1514551545454564L;

    private final List<Pair<String, Object>> dataPairs = new ArrayList<>();

    public static final DataFormat Binding = new DataFormat("cz.blocksolver.frontend.view.FileSystemBinding");

    public static final DataFormat Node = new DataFormat("cz.blocksolver.frontend.view.FileSystemNode");

    public DragContainer(){}

    /**
     * Ulozi hodnotu s klicem do listu
     * @param key
     * @param value
     */
    public void addData(String key, Object value){
        dataPairs.add(new Pair<>(key, value));
    }

    /**
     * Ziska hodnotu z listu podle klice
     * @param key
     * @param <T>
     * @return
     */
    public <T> T getValue(String key){
        for (Pair<String, Object> data: dataPairs){
            if(data.getKey().equals(key)){
                return (T) data.getValue();
            }
        }

        return null;
    }

    /**
     * Vrati list paru klicu s hodnotami
     * @return
     */
    public List<Pair<String, Object> > getData () {return dataPairs; }

    public static final DataFormat AddNode = new DataFormat("cz.blocksolver.frontend.Block.add");
}
