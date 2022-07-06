

/*
 *
 *  * Copyright (c) 2022
 *  * http://license.coscl.org.cn/MulanPSL2
 *  * 汪旭辉 wangxuhui918@163.com
 *
 */

package com.guoshiyao.rely.mybatis.automethod;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.lang.tree.Tree;
import cn.hutool.core.util.ClassUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.db.Page;
import com.guoshiyao.rely.bean.Bean;
import com.guoshiyao.rely.coreannotation.rule.HomeNull;
import com.guoshiyao.rely.coreannotation.rule.RuleAnnotationAutoMethod;
import com.guoshiyao.rely.exception.re.ex.ExceptionError;
import com.guoshiyao.rely.mybatis.starter.mybatis.other.base.BaseBiz;
import com.guoshiyao.rely.outgoing.InputParamAb;
import org.apache.ibatis.session.RowBounds;
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
@Order(2)
public class MybatisAutoMethod {


    private static final HashMap<String, Tree<Class>> methTree = new HashMap<>();

    @Pointcut("@annotation(com.guoshiyao.rely.coreannotation.rule.RuleAnnotationAutoMethod)")
    private void annotationPointCut() {
    }


    @Around("annotationPointCut()")
    public Object annotationAround(ProceedingJoinPoint jp) throws Throwable {
        RuleAnnotationAutoMethod myAnnotation = null;
        InputParamAb input = null;
        String methFullPath = null;
        try {
            MethodSignature signature = (MethodSignature) jp.getSignature();
            Method method = signature.getMethod();
            methFullPath = method.getDeclaringClass().getName() + "." + method.getName();
            myAnnotation = method.getAnnotation(RuleAnnotationAutoMethod.class);
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
        if (myAnnotation != null && input != null && methTree.get(methFullPath) != null) {
            Mapper mapper = (Mapper) Bean.getBean(methTree.get(methFullPath).getId());
            if (myAnnotation.method() == com.guoshiyao.rely.coreannotation.base.Method.pageSelect) {
                Object inputvo = input.getData();
                Object domain = ClassUtil.loadClass(methTree.get(methFullPath).getName().toString(), false).newInstance();
                Object vo = input.getData().getClass().newInstance();

                Page pageRequest = new Page(Integer.parseInt(input.getPageNum()), Integer.parseInt(input.getPageSize()));
                HashMap<String, Object> pageData = new HashMap<>();
                List returnlist = new ArrayList<>();
                {
                    BeanUtil.copyProperties(inputvo, domain);
                    pageData.put("pageSize", pageRequest.getPageSize());
                    pageData.put("pageNumber", pageRequest.getPageNumber() - 1);
                    pageData.putAll(BeanUtil.beanToMap(domain));
                }
                {
                    RowBounds rowbounds = new RowBounds(pageRequest.getPageSize(), pageRequest.getPageSize());
                    returnlist = BeanUtil.copyToList(mapper.selectByRowBounds(domain, rowbounds), input.getData().getClass());
                    pageData.put("total", mapper.selectCount(domain));
                    pageData.put("data", returnlist);
                }
                return pageData;
            } else if (myAnnotation.method() == com.guoshiyao.rely.coreannotation.base.Method.andSelect) {
                Object domain = ClassUtil.loadClass(methTree.get(methFullPath).getName().toString(), false).newInstance();
                List returnlist = new ArrayList<>();
                {
                    returnlist = BeanUtil.copyToList(mapper.select(domain), input.getData().getClass());
                }
                return returnlist;
            } else if (myAnnotation.method() == com.guoshiyao.rely.coreannotation.base.Method.add) {
                Object domain = ClassUtil.loadClass(methTree.get(methFullPath).getName().toString(), false).newInstance();
                Object inputvo = input.getData();
                BeanUtil.copyProperties(inputvo, domain);
                return mapper.insert(domain);
            } else if (myAnnotation.method() == com.guoshiyao.rely.coreannotation.base.Method.deleteByPrimaryKey) {
                Object domain = ClassUtil.loadClass(methTree.get(methFullPath).getName().toString(), false).newInstance();
                Object inputvo = input.getData();
                BeanUtil.copyProperties(inputvo, domain);
                return mapper.deleteByPrimaryKey(domain);
            } else if (myAnnotation.method() == com.guoshiyao.rely.coreannotation.base.Method.updateByPrimaryKeySelective) {
                Object domain = ClassUtil.loadClass(methTree.get(methFullPath).getName().toString(), false).newInstance();
                Object inputvo = input.getData();
                BeanUtil.copyProperties(inputvo, domain);
                return mapper.updateByPrimaryKeySelective(domain);
            }
        }
        if (myAnnotation != null && input != null && methTree.get(methFullPath) == null) {
            if (myAnnotation.mapper().equals(HomeNull.class)) {
                throw new ExceptionError("RuleAnnotationAutoMethod注解mapper不允许为空");
            }
            if (myAnnotation.method() == com.guoshiyao.rely.coreannotation.base.Method.nomethod) {
                throw new ExceptionError("RuleAnnotationAutoMethod注解method不允许为空");
            }
            ////BaseBiz
            if (!(myAnnotation.mapper().getSuperclass().equals(Mapper.class))) {
                throw new ExceptionError("RuleAnnotationAutoMethod注解mapper未继承" + BaseBiz.class);
            }
            //获取对应泛型,获取Domain对象
            String domainClass = "";
            try {
                domainClass = StrUtil.subBetween(myAnnotation.mapper().getGenericSuperclass().getTypeName(), "<", ">").split(",")[0];
            } catch (Exception e) {
                e.printStackTrace();
            }
            Tree<Class> className = new Tree<>();
            className.setId(myAnnotation.mapper());//id为mapper
            className.setName(domainClass);//name 为 domain
            methTree.put(methFullPath, className);
        }
        return jp.proceed();//返回值不为空以及非管理的放过,,,
    }


}
