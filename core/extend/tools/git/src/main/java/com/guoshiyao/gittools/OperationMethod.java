package com.guoshiyao.gittools;

/**
 * @author 汪旭辉
 * @date 2022/11/7
 * @readme
 */
public enum OperationMethod {
    无("-1"), //
    删除工作目录("0"), //
    拉取源代码默认分支A("1"), //
    切换至分支A("2"),//
    更新分支A("3"),//
    硬更新所有分支("11"), //
    以本地分支A重置本地B分支("4"), //
    强推A分支("5"), //
    解除保护分支A("6"), //
    增加保护分支A("7"),//
    以远端分支A创建本地分支B("9"), //
    删除本地分支A和远端分支A("10"), //
    删除15日前标记("12");//

    public static OperationMethod getOperationMethodByName(String name) {
        for (OperationMethod o : OperationMethod.values()) {
            if (o.name().equals(name)) {
                return o;
            }
        }
        return 无;
    }

    OperationMethod(String method) {
        this.setMethod(method);
    }

    private String method;

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }
}
