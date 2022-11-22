

/*
 *
 *  * Copyright (c) 2022
 *  * http://license.coscl.org.cn/MulanPSL2
 *  * 汪旭辉
 *
 */

package com.guoshiyao.rely.mybatis.automethod;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.lang.tree.Tree;
import cn.hutool.core.util.ClassUtil;
import cn.hutool.core.util.StrUtil;
import com.guoshiyao.rely.BaseEv;
import com.guoshiyao.rely.bean.Bean;
import com.guoshiyao.rely.mybatis.annotation.RuleInjectionMehtod;
import com.guoshiyao.rely.mybatis.annotation.bean.HomeNull;
import com.guoshiyao.rely.plugin.exception.re.ex.ExceptionError;
import com.guoshiyao.rely.plugin.outgoing.InputParamAb;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import tk.mybatis.mapper.common.Mapper;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Aspect
@Order(Integer.MAX_VALUE)
public class MybatisAutoMethod {

    public final static String name = "@annotation(com.guoshiyao.rely.annotation.mapper.RuleInjection)";


    @Pointcut(name)
    private void annotationPointCut() {
    }


    @Around("annotationPointCut()")
    public Object annotationAround(ProceedingJoinPoint jp) throws Throwable {
        RuleInjectionMehtod myAnnotation = null;
        InputParamAb input = null;
        String methFullPath = null;
        try {
            MethodSignature signature = (MethodSignature) jp.getSignature();
            Method method = signature.getMethod();
            methFullPath = method.getDeclaringClass().getName() + "." + method.getName();
            myAnnotation = method.getAnnotation(RuleInjectionMehtod.class);
            {
                Object[] args = jp.getArgs();
                boolean validate = false;
                for (Object arg : args) {
                    if (arg instanceof InputParamAb) {
                        input = (InputParamAb) arg;
                        break;
                    }
                }
            }
        } catch (Exception e) {
        }

        if (myAnnotation != null && input != null && BaseEv.SettingInformation.methTree.get(methFullPath) == null) {
            if (myAnnotation.mapper().equals(HomeNull.class)) {
                throw new ExceptionError("RuleAnnotationAutoMethod注解mapper不允许为空");
            }
            if (myAnnotation.method() == com.guoshiyao.rely.mybatis.annotation.bean.Method.nomethod) {
                throw new ExceptionError("RuleAnnotationAutoMethod注解method不允许为空");
            }
            ////BaseBiz
            boolean t = false;
            Class[] cd = myAnnotation.mapper().getInterfaces();
            if (cd != null && cd.length > 0) {
                for (Class anInterface : cd) {
                    if (anInterface.equals(Mapper.class)) {
                        t = true;
                        break;
                    }
                }
            }
            if (!t) {
                throw new ExceptionError("RuleAnnotationAutoMethod注解mapper未" + Mapper.class);
            }
            //获取对应泛型,获取Domain对象
            String domainClass = "";
            try {
                domainClass = StrUtil.subBetween(myAnnotation.mapper().getGenericInterfaces()[0].getTypeName(), "<", ">").split(",")[0];
            } catch (Exception e) {
                e.printStackTrace();
            }
            Tree<Class> className = new Tree<>();
            className.setId(myAnnotation.mapper());//id为mapper
            className.setName(domainClass);//name 为 domain
            BaseEv.SettingInformation.methTree.put(methFullPath, className);
        }


        if (myAnnotation != null && input != null && BaseEv.SettingInformation.methTree.get(methFullPath) != null) {
            Mapper mapper = (Mapper) Bean.getBean(BaseEv.SettingInformation.methTree.get(methFullPath).getId());
            if (myAnnotation.method() == com.guoshiyao.rely.mybatis.annotation.bean.Method.pageSelect) {
                Object inputvo = input.getData();
                Object domain = ClassUtil.loadClass(BaseEv.SettingInformation.methTree.get(methFullPath).getName().toString(), false).newInstance();
                Object vo = input.getData().getClass().newInstance();

//                Page pageRequest = new Page(Integer.parseInt(input.getPageNum()), Integer.parseInt(input.getPageSize()));
                com.guoshiyao.rely.mybatis.page.Page pageRequest = new com.guoshiyao.rely.mybatis.page.Page(Integer.parseInt(input.getPageNum()), Integer.parseInt(input.getPageSize()));
                HashMap<String, Object> pageData = new HashMap<>();
                List returnlist = new ArrayList<>();
                {
                    BeanUtil.copyProperties(inputvo, domain);
                    pageData.put("pageSize", pageRequest.getPageSize());
                    pageData.put("pageNumber", pageRequest.getPageNo());
                    pageData.putAll(BeanUtil.beanToMap(domain));
                }
                {
//                    RowBounds rowbounds = new RowBounds(pageRequest.getPageSize(), pageRequest.getPageSize());
                    returnlist = BeanUtil.copyToList(mapper.selectByRowBounds(domain, pageRequest.getRowbounds()), input.getData().getClass());
                    pageData.put("total", mapper.selectCount(domain));
                    pageData.put("data", returnlist);
                }
                return pageData;
            } else if (myAnnotation.method() == com.guoshiyao.rely.mybatis.annotation.bean.Method.andSelect) {
                Object domain = ClassUtil.loadClass(BaseEv.SettingInformation.methTree.get(methFullPath).getName().toString(), false).newInstance();
                List returnlist = new ArrayList<>();
                {
                    returnlist = BeanUtil.copyToList(mapper.select(domain), input.getData().getClass());
                }
                return returnlist;
            } else if (myAnnotation.method() == com.guoshiyao.rely.mybatis.annotation.bean.Method.add) {
                Object domain = ClassUtil.loadClass(BaseEv.SettingInformation.methTree.get(methFullPath).getName().toString(), false).newInstance();
                Object inputvo = input.getData();
                BeanUtil.copyProperties(inputvo, domain);
                return mapper.insert(domain);
            } else if (myAnnotation.method() == com.guoshiyao.rely.mybatis.annotation.bean.Method.queryByPrimaryKey) {
                Object domain = ClassUtil.loadClass(BaseEv.SettingInformation.methTree.get(methFullPath).getName().toString(), false).newInstance();
                Object inputvo = input.getData();
                BeanUtil.copyProperties(inputvo, domain);
                return mapper.selectByPrimaryKey(domain);
            } else if (myAnnotation.method() == com.guoshiyao.rely.mybatis.annotation.bean.Method.deleteByPrimaryKey) {
                Object domain = ClassUtil.loadClass(BaseEv.SettingInformation.methTree.get(methFullPath).getName().toString(), false).newInstance();
                Object inputvo = input.getData();
                BeanUtil.copyProperties(inputvo, domain);
                return mapper.deleteByPrimaryKey(domain);
            } else if (myAnnotation.method() == com.guoshiyao.rely.mybatis.annotation.bean.Method.updateByPrimaryKeySelective) {
                Object domain = ClassUtil.loadClass(BaseEv.SettingInformation.methTree.get(methFullPath).getName().toString(), false).newInstance();
                Object inputvo = input.getData();
                BeanUtil.copyProperties(inputvo, domain);
                return mapper.updateByPrimaryKeySelective(domain);
            }
        }
        return jp.proceed();//返回值不为空以及非管理的放过,,,
    }


}
