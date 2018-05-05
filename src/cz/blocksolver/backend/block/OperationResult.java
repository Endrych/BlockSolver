package cz.blocksolver.backend.block;

import cz.blocksolver.backend.port.PortType;

/**
 * Trida reprezentujici vysledek operace
 * @author David Endrych (xendry02)
 * @author Marek Kukučka (xkukuc04)
 */
public class OperationResult {
    private Double result;
    private Boolean status;
    private PortType type;

    /**
     * Konstruktor
     * @param result vysledek operace
     * @param status Stav vysledku
     */
    public OperationResult(Double result, Boolean status){
        this.result = result;
        this.status = status;
        this.type = PortType.NUMBER;
    }

    /**
     * Metoda nastavi vysledek, typ, status
     * @param result vysldek
     */
    public OperationResult(Double result){
        this.result = result;
        this.type = PortType.NUMBER;
        setStatus();
    }

    /**
     * Metoda nastavi vysledek, typ, status
     * @param result vysledek
     * @param type typ
     */
    public OperationResult(Double result, PortType type){
        this.result = result;
        this.type = type;
        setStatus();
    }

    /**
     * Metoda nastavi status v zavislosti na resultu
     */
    private void setStatus(){
        if(this.result == Double.POSITIVE_INFINITY || this.result == Double.NEGATIVE_INFINITY){
            this.status = false;
        }
        else{
            this.status = true;
        }
    }

    /**
     * Getter pro typ
     * @return typ
     */
    public PortType getPortType(){
        return type;
    }

    /**
     * Getter pro vysledek
     * @return vysledek
     */
    public Double getResult() {
        return result;
    }

    /**
     * Getter pro status
     * @return status
     */
    public Boolean getStatus() {
        return status;
    }
}
