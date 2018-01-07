package test.example.com.counselor.view.contract;

import java.util.List;

/**
 * Created by Sli.D on 2018/1/2.
 */

public class ContractModel implements IContractModel {


    List<ContractEntity> contractEntities;

    @Override
    public void setContractEntity(List<ContractEntity> contractEntities) {
        this.contractEntities = contractEntities;
    }

    @Override
    public List<ContractEntity> getContractEntity() {
        return this.contractEntities;
    }
}
