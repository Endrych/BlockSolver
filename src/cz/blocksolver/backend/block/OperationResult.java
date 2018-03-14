package cz.blocksolver.backend.block;

import cz.blocksolver.backend.port.PortType;

public class OperationResult {
    private Double result;
    private Boolean status;
    private PortType type;
    public OperationResult(Double result, Boolean status){
        this.result = result;
        this.status = status;
        this.type = PortType.NUMBER;
    }
    public OperationResult(Double result){
        this.result = result;
        this.type = PortType.NUMBER;
        setStatus();
    }

    public OperationResult(Double result, PortType type){
        this.result = result;
        this.type = type;
        setStatus();
    }

    private void setStatus(){
        if(this.result == Double.POSITIVE_INFINITY || this.result == Double.NEGATIVE_INFINITY){
            this.status = false;
        }
        else{
            this.status = true;
        }
    }

    public PortType getPortType(){
        return type;
    }

    public Double getResult() {
        return result;
    }

    public Boolean getStatus() {
        return status;
    }
}
