package test.example.com.counselor.view.assessment;

import java.util.List;

/**
 * Created by Sli.D on 2018/1/2.
 */

public class AssessmentModel implements IAssessmentModel {


    List<AssessmentEntity> assessmentEntities;

    @Override
    public void setAssessmentEntity(List<AssessmentEntity> assessmentEntity) {
        this.assessmentEntities = assessmentEntity;
    }

    @Override
    public List<AssessmentEntity> getAssessmentEntity() {
        return this.assessmentEntities;
    }
}
