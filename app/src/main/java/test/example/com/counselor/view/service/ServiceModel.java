package test.example.com.counselor.view.service;

import java.util.List;

import test.example.com.counselor.view.service.entity.AdviceEntity;
import test.example.com.counselor.view.service.entity.ClassicCaseEntity;
import test.example.com.counselor.view.service.entity.SummaryEntity;
import test.example.com.counselor.view.service.entity.WorkLogEntity;

/**
 * Created by Sli.D on 2018/1/2.
 */

public class ServiceModel implements IServiceModel{

    List<WorkLogEntity> workLogEntities;
    List<AdviceEntity> adviceEntities;
    List<ClassicCaseEntity> classicCaseEntities;
    List<SummaryEntity> summaryEntities;
    @Override
    public void setWorkLogEntities(List<WorkLogEntity> workLogEntities) {
        this.workLogEntities = workLogEntities;
    }

    @Override
    public List<WorkLogEntity> getWorkLogEntities() {
        return this.workLogEntities;
    }

    @Override
    public void setAdviceEntities(List<AdviceEntity> adviceEntities) {
        this.adviceEntities = adviceEntities;
    }

    @Override
    public List<AdviceEntity> getAdviceEntities() {
        return this.adviceEntities;
    }

    @Override
    public void setClassicCaseEntities(List<ClassicCaseEntity> classicCaseEntities) {
        this.classicCaseEntities = classicCaseEntities;
    }

    @Override
    public List<ClassicCaseEntity> getClassicCaseEntities() {
        return this.classicCaseEntities;
    }

    @Override
    public void setSummaryEntities(List<SummaryEntity> summaryEntities) {
        this.summaryEntities = summaryEntities;
    }

    @Override
    public List<SummaryEntity> getSummaryEntities() {
        return this.summaryEntities;
    }

}
