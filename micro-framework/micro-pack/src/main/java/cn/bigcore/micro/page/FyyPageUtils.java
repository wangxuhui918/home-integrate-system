package cn.bigcore.micro.page;

import cn.bigcore.micro.base.exception.type.FyyExceptionError;
import cn.bigcore.micro.thread.FyyThreadReUtils;
import cn.bigcore.micro.base.thread.FyyKeyBase;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

/**
 * page线程工具
 */
public class FyyPageUtils {
    /**
     * 开始准备分页
     *
     * @return
     */
    public static Page starPage() {
        Integer pageSize = Integer.parseInt(FyyThreadReUtils.getStrParamByPath(FyyKeyBase.PAGE_SIZE.getKeyName()));
        Integer pageNum = Integer.parseInt(FyyThreadReUtils.getStrParamByPath(FyyKeyBase.PAGE_NUM.getKeyName()));
        if (pageSize <= 0 || pageNum <= 0) {
            throw new FyyExceptionError("分页参数{}&&{}必须>0", "pageSize", "pageNum");
        }
        Page page = PageHelper.startPage(pageNum, pageSize);
        FyyPageMethod.setLocalPage(page);
        return page;
    }
}
