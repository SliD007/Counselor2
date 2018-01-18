package test.example.com.counselor.view.service.showgroupcase;

/**
 * Created by Sli.D on 2018/1/11.
 */

public class ShowGroupCaseModel implements IShowGroupCaseModel {

    public GroupCaseDetialEntity getGroupCaseDetialEntity() {
        return groupCaseDetialEntity;
    }

    public void setGroupCaseDetialEntity(GroupCaseDetialEntity groupCaseDetialEntity) {
        this.groupCaseDetialEntity = groupCaseDetialEntity;
    }

    private GroupCaseDetialEntity groupCaseDetialEntity;

}
