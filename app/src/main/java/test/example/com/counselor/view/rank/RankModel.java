package test.example.com.counselor.view.rank;

import java.util.List;

/**
 * Created by Sli.D on 2018/1/2.
 */

public class RankModel implements IRankModel {


    List<RankEntity> rankEntities;

    @Override
    public void setRankEntities(List<RankEntity> rankEntities) {
        this.rankEntities = rankEntities;
    }

    @Override
    public List<RankEntity> getRankEntities() {
        return this.rankEntities;
    }
}
