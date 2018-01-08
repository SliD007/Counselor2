package test.example.com.counselor.view.assessment;

import java.util.List;

/**
 * Created by Sli.D on 2018/1/2.
 */

public interface IAssessmentModel {
    void setAssessmentEntity(List<AssessmentEntity> assessmentEntity);
    List<AssessmentEntity> getAssessmentEntity();
}
