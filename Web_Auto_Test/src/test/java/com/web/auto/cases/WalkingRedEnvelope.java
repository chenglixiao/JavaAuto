package com.web.auto.cases;

import com.web.auto.util.BehaviorUtil;
import org.testng.annotations.BeforeGroups;
import org.testng.annotations.Test;

/**
 * @ Author：Pada_xiao
 * @ Date：17:48 2019/4/8
 * @ Description：
 * @ Modified By：
 * @Version:
 */
public class WalkingRedEnvelope extends LoginFirst {
    @BeforeGroups("creatActivity")
    public void interview(){
        BehaviorUtil.interview("walkingRedEnvelope.url");
    }
    @Test(dataProvider = "reserveData", groups={"creatActivity"})
    public void reserveCreatActivity(){
        BehaviorUtil.interview("walkingRedEnvelope.url");
    }
    @Test(dataProvider = "positiveData",groups = {"creatActivity"})
    public void PosicreatActivity(){
        BehaviorUtil.interview("walkingRedEnvelope.url");

    }
    @Test
    public void deleteActivity(){

    }
}
