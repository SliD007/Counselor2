package test.example.com.counselor.view.service;

import java.util.List;

import test.example.com.counselor.view.service.entity.AdviceEntity;
import test.example.com.counselor.view.service.entity.ClassicCaseEntity;
import test.example.com.counselor.view.service.entity.SummaryEntity;
import test.example.com.counselor.view.service.entity.WorkLogEntity;

/**
 * Created by Sli.D on 2018/1/2.
 */

public interface IServiceModel {
    void setWorkLogEntities(List<WorkLogEntity> workLogEntities);
    List<WorkLogEntity> getWorkLogEntities();

    void setAdviceEntities(List<AdviceEntity> adviceEntities);
    List<AdviceEntity> getAdviceEntities();

    void setClassicCaseEntities(List<ClassicCaseEntity> classicCaseEntities);
    List<ClassicCaseEntity> getClassicCaseEntities();

    void setSummaryEntities(List<SummaryEntity> summaryEntities);
    List<SummaryEntity> getSummaryEntities();

}
