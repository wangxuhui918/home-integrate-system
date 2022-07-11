/*
 *
 *  * Copyright (c) 2022
 *  * http://license.coscl.org.cn/MulanPSL2
 *  * 汪旭辉 wangxuhui918@163.com
 *
 */

package com.guoshiyao.example.api;

import cn.hutool.core.io.resource.ResourceUtil;
import com.guoshiyao.example.api.vo.DemoVo;
import com.guoshiyao.rely.coreannotation.rule.RuleAnnotationApi;
import com.guoshiyao.rely.exception.code.CodeAb;
import com.guoshiyao.rely.exception.code.CodeRe;
import com.guoshiyao.rely.exception.re.ex.ExceptionError;
import com.guoshiyao.rely.outgoing.InputParamRe;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RuleAnnotationApi
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

}
