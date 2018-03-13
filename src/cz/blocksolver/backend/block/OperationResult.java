package cz.blocksolver.backend.block;

public class OperationResult {
    private Double result;
    private Boolean status;

    public OperationResult(Double result, Boolean status){
        this.result = result;
        this.status = status;
    }
    public OperationResult(Double result){
        this.result = result;
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
}
