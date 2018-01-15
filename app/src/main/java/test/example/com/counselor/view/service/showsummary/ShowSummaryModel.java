package test.example.com.counselor.view.service.showsummary;

/**
 * Created by Sli.D on 2018/1/11.
 */

public class ShowSummaryModel implements IShowSummaryModel {

    public SummaryEntity getSummaryDetialEntity() {
        return summaryEntity;
    }

    public void setSummaryDetialEntity(SummaryEntity summaryEntity) {
        this.summaryEntity = summaryEntity;
    }

    private SummaryEntity summaryEntity;

}
