package test.example.com.counselor.view.rank;

import java.util.List;

/**
 * Created by Sli.D on 2018/1/2.
 */

public interface IRankModel {
    void setRankEntities(List<RankEntity> rankEntities);
    List<RankEntity> getRankEntities();
}
