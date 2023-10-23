

/*
 *
 *  * Copyright (c) 2022
 *  * http://license.coscl.org.cn/MulanPSL2
 *  * 汪旭辉
 *
 */

package cn.bigcore.micro.mybatis.automethod;

import cn.bigcore.micro.FyyInitEnv;
import cn.bigcore.micro.bean.FyyBean;
import cn.bigcore.micro.mybatis.annotation.FyyMybatisRuleInjectionMehtod;
import cn.bigcore.micro.mybatis.annotation.bean.FyyMybatisNull;
import cn.bigcore.micro.base.exception.type.FyyExceptionError;
import cn.bigcore.micro.mybatis.annotation.bean.FyyMybatisMethod;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.lang.tree.Tree;
import cn.hutool.core.util.ClassUtil;
import cn.hutool.core.util.StrUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import cn.bigcore.micro.outgoing.FyyInputParamInterface;
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
public class FyyMybatisAutoMethod {

    public final static String name = "@annotation(cn.bigcore.micro.config.annotation.FyyRuleInjection)";


    @Pointcut(name)
    private void annotationPointCut() {
    }


    @Around("annotationPointCut()")
    public Object annotationAround(ProceedingJoinPoint jp) throws Throwable {
        FyyMybatisRuleInjectionMehtod myAnnotation = null;
        FyyInputParamInterface input = null;
        String methFullPath = null;
        try {
            MethodSignature signature = (MethodSignature) jp.getSignature();
            Method method = signature.getMethod();
            methFullPath = method.getDeclaringClass().getName() + "." + method.getName();
            myAnnotation = method.getAnnotation(FyyMybatisRuleInjectionMehtod.class);
            {
                Object[] args = jp.getArgs();
                boolean validate = false;
                for (Object arg : args) {
                    if (arg instanceof FyyInputParamInterface) {
                        input = (FyyInputParamInterface) arg;
                        break;
                    }
                }
            }
        } catch (Exception e) {
        }

        if (myAnnotation != null && input != null && FyyInitEnv.SettingInformation.methTree.get(methFullPath) == null) {
            if (myAnnotation.mapper().equals(FyyMybatisNull.class)) {
                throw new FyyExceptionError("RuleAnnotationAutoMethod注解mapper不允许为空");
            }
            if (myAnnotation.method() == FyyMybatisMethod.nomethod) {
                throw new FyyExceptionError("RuleAnnotationAutoMethod注解method不允许为空");
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
                throw new FyyExceptionError("RuleAnnotationAutoMethod注解mapper未" + Mapper.class);
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
            FyyInitEnv.SettingInformation.methTree.put(methFullPath, className);
        }


        if (myAnnotation != null && input != null && FyyInitEnv.SettingInformation.methTree.get(methFullPath) != null) {
            Mapper mapper = (Mapper) FyyBean.getBean(FyyInitEnv.SettingInformation.methTree.get(methFullPath).getId());
            if (myAnnotation.method() == FyyMybatisMethod.pageSelect) {
                Object inputvo = input.getData();
                Object domain = ClassUtil.loadClass(FyyInitEnv.SettingInformation.methTree.get(methFullPath).getName().toString(), false).newInstance();
//                Object vo = input.getData().getClass().newInstance();
                Page page = PageHelper.startPage(Integer.parseInt(input.getPageNum()), Integer.parseInt(input.getPageSize()));
                HashMap<String, Object> pageData = new HashMap<>();
                List returnlist = new ArrayList<>();
                {
                    BeanUtil.copyProperties(inputvo, domain);
                }
                {
                    returnlist = BeanUtil.copyToList(mapper.select(domain), input.getData().getClass());
                    pageData.put("total", page.getTotal());
                    pageData.put("data", returnlist);
                }
                return pageData;
            } else if (myAnnotation.method() == FyyMybatisMethod.andSelect) {
                Object domain = ClassUtil.loadClass(FyyInitEnv.SettingInformation.methTree.get(methFullPath).getName().toString(), false).newInstance();
                List returnlist = new ArrayList<>();
                {
                    returnlist = BeanUtil.copyToList(mapper.select(domain), input.getData().getClass());
                }
                return returnlist;
            } else if (myAnnotation.method() == FyyMybatisMethod.add) {
                Object domain = ClassUtil.loadClass(FyyInitEnv.SettingInformation.methTree.get(methFullPath).getName().toString(), false).newInstance();
                Object inputvo = input.getData();
                BeanUtil.copyProperties(inputvo, domain);
                return mapper.insert(domain);
            } else if (myAnnotation.method() == FyyMybatisMethod.queryByPrimaryKey) {
                Object domain = ClassUtil.loadClass(FyyInitEnv.SettingInformation.methTree.get(methFullPath).getName().toString(), false).newInstance();
                Object inputvo = input.getData();
                BeanUtil.copyProperties(inputvo, domain);
                return mapper.selectByPrimaryKey(domain);
            } else if (myAnnotation.method() == FyyMybatisMethod.deleteByPrimaryKey) {
                Object domain = ClassUtil.loadClass(FyyInitEnv.SettingInformation.methTree.get(methFullPath).getName().toString(), false).newInstance();
                Object inputvo = input.getData();
                BeanUtil.copyProperties(inputvo, domain);
                return mapper.deleteByPrimaryKey(domain);
            } else if (myAnnotation.method() == FyyMybatisMethod.updateByPrimaryKeySelective) {
                Object domain = ClassUtil.loadClass(FyyInitEnv.SettingInformation.methTree.get(methFullPath).getName().toString(), false).newInstance();
                Object inputvo = input.getData();
                BeanUtil.copyProperties(inputvo, domain);
                return mapper.updateByPrimaryKeySelective(domain);
            }
        }
        return jp.proceed();//返回值不为空以及非管理的放过,,,
    }


}
