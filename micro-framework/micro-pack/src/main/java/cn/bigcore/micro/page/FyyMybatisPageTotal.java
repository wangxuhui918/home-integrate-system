package cn.bigcore.micro.page;

import cn.bigcore.micro.config.annotation.FyyRuleInjection;
import cn.bigcore.micro.outgoing.FyyPageTotalInterface;

@FyyRuleInjection
public class FyyMybatisPageTotal implements FyyPageTotalInterface {
    /**
     * 直接返回PageHelper线程计数
     *
     * @param pageNum
     * @param pageSize
     * @return
     */
    @Override
    public long getTotal(Integer pageNum, Integer pageSize) {
        return FyyPageMethod.getLocalPage().getTotal();
    }

    @Override
    public void remove() {
        FyyPageMethod.clearPage();
    }

}
