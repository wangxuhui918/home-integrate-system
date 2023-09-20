package cn.bigcore.micro.page;

import cn.bigcore.micro.config.annotation.FyyRuleInjection;
import cn.bigcore.micro.exception.re.ex.FyyExceptionError;
import cn.bigcore.micro.outgoing.FyyPageTotalInterface;
import cn.bigcore.micro.thread.FyyThreadReUtils;
import cn.bigcore.micro.thread.bean.FyyKeyBase;
import com.github.pagehelper.PageHelper;

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
        return PageHelper.getLocalPage().getTotal();
    }
}
