package test.example.com.counselor.view.contract.rank;

import java.util.List;

/**
 * Created by Sli.D on 2018/1/2.
 */

public interface IContractModel {
    void setContractEntity(List<ContractEntity> contractEntities);
    List<ContractEntity> getContractEntity();
}
