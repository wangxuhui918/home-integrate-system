/*
 *
 *  * Copyright (c) 2022
 *  * http://license.coscl.org.cn/MulanPSL2
 *  * 汪旭辉
 *
 */

package cn.bigcore.example.api;

import cn.bigcore.example.api.vo.DemoVo;
import cn.bigcore.micro.annotation.FyyRuleController;
import cn.bigcore.micro.exception.FyyCodeRe;
import cn.bigcore.micro.base.exception.type.FyyExceptionError;
import cn.bigcore.micro.outgoing.impl.FyyInputParamRe;
import cn.bigcore.micro.outgoing.impl.FyyOutputParamRe;
import cn.bigcore.micro.outgoing.utils.FyyCodeUtils;
import cn.hutool.core.io.resource.ResourceUtil;
import cn.hutool.core.util.StrUtil;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@FyyRuleController
@Controller
public class RedirectController extends BaseController {

    @ApiOperation(value = "读取文件", notes = " ")
    @RequestMapping(value = "/example/readCodeFile", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public String readCodeFile(String fileResourcePath) {
        return ResourceUtil.readUtf8Str(fileResourcePath);
    }


    @ApiOperation(value = "安装流程", notes = " ")
    @RequestMapping(value = "index", method = {RequestMethod.POST, RequestMethod.GET})
    public String mainindex() {
        return "example/index.html";
    }

    @ApiOperation(value = "安装流程", notes = " ")
    @RequestMapping(value = "/example/{index}", method = {RequestMethod.POST, RequestMethod.GET})
    public String index(@PathVariable String index) {
        return "example/" + index + ".html";
    }


    @ApiOperation(value = "样例", notes = " ")
    @RequestMapping(value = "/example/example", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public Object example(@RequestBody FyyInputParamRe<DemoVo> input) {
        return input.getInputData().getDemo_id();
    }


    @ApiOperation(value = "样例", notes = " ")
    @RequestMapping(value = "/example/output2", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public FyyOutputParamRe output2(@RequestBody FyyInputParamRe<DemoVo> input) {
        return FyyCodeUtils.go(new FyyCodeRe("A001", "你的"));
    }

    @ApiOperation(value = "样例", notes = " ")
    @RequestMapping(value = "/example/output3", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public void output3(@RequestBody FyyInputParamRe<DemoVo> input) {
        if (StrUtil.equals(input.getInputData().getName(), "阿莫西林")) {//正常的业务异常
            throw new FyyExceptionError("药品{},库存不足", "阿莫西林");
        } else {//继续处理流程
            //1. 进行库存扣减正常业务,此时会返回给前端内置true的消息体
            //2. 如果此时出现空指针等异常也会封装为false消息码体并返回
        }
    }
}
