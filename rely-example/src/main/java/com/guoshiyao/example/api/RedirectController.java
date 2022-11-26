/*
 *
 *  * Copyright (c) 2022
 *  * http://license.coscl.org.cn/MulanPSL2
 *  * 汪旭辉
 *
 */

package com.guoshiyao.example.api;

import cn.hutool.core.io.resource.ResourceUtil;
import cn.hutool.core.util.StrUtil;
import com.guoshiyao.example.api.vo.DemoVo;
import com.guoshiyao.rely.annotation.RuleController;
import com.guoshiyao.rely.config.exception.CodeRe;
import com.guoshiyao.rely.outgoing.InputParamRe;
import com.guoshiyao.rely.outgoing.OutputParamRe;
import com.guoshiyao.rely.outgoing.utils.CodeUtils;
import com.guoshiyao.rely.plugin.exception.re.ex.ExceptionError;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RuleController
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
    public Object example(@RequestBody InputParamRe<DemoVo> input) {
        return input.getInputData().getDemo_id();
    }


    @ApiOperation(value = "样例", notes = " ")
    @RequestMapping(value = "/example/output2", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public OutputParamRe output2(@RequestBody InputParamRe<DemoVo> input) {
        return CodeUtils.go(new CodeRe("A001", "你的"));
    }

    @ApiOperation(value = "样例", notes = " ")
    @RequestMapping(value = "/example/output3", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public void output3(@RequestBody InputParamRe<DemoVo> input) {
        if (StrUtil.equals(input.getInputData().getName(), "阿莫西林")) {//正常的业务异常
            throw new ExceptionError("药品{},库存不足", "阿莫西林");
        } else {//继续处理流程
            //1. 进行库存扣减正常业务,此时会返回给前端内置true的消息体
            //2. 如果此时出现空指针等异常也会封装为false消息码体并返回
        }
    }
}
