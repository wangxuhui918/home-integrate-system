

/*
 *
 *  * Copyright (c) 2022
 *  * http://license.coscl.org.cn/MulanPSL2
 *  * 汪旭辉
 *
 */

package com.guoshiyao.rely.minio.utils;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.FastByteArrayOutputStream;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.IoUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;
import com.guoshiyao.rely.base.BaseEv;
import com.guoshiyao.rely.exception.re.ex.ExceptionError;
import com.guoshiyao.rely.line.Line;
import io.minio.GetPresignedObjectUrlArgs;
import io.minio.PutObjectArgs;
import io.minio.RemoveBucketArgs;
import io.minio.RemoveObjectArgs;
import io.minio.http.Method;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Date;
import java.util.HashMap;

public class MinIoUtils {


    /**
     * @param jsonstr
     * @param dirpath
     * @param met
     * @param deletetemplocalfile null,true 删除 处理完成后删除本地文件
     * @param deleteserverfile    null,false 不删除 处理完成后删除服务端文件
     * @author 汪旭辉
     * @date 2022年2月22日
     * @readme
     */
    public static <T> T download(String jsonstr, String dirpath, MinIOCallBack<T> met, boolean deletetemplocalfile,
                                 boolean deleteserverfile) {
        MinIOGen.checkMinIOState();
        T t = null;
        HashMap<String, String> map = new HashMap<>();
        {
            Object o = JSONUtil.parseObj(jsonstr);
            BeanUtil.copyProperties(o, map);
        }
        String localpath = dirpath + BaseEv.FILE_SEPARATOR + map.get(MinIOGen.storagname);
        try {// 下载文件流
            HttpUtil.downloadFile(map.get(MinIOGen.downloadurl), localpath);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ExceptionError("文件下载失败: {}  {}", map.get(MinIOGen.namespace), map.get(MinIOGen.storagname));
        }
        if (FileUtil.exist(localpath)) {//处理执行器逻辑
            t = met.exc(localpath);
        }
        if (deletetemplocalfile) {
            boolean deletesuccess = FileUtil.del(localpath);
            if (!deletesuccess) {
                throw new ExceptionError("文件清空失败: {}  {}", map.get(MinIOGen.namespace),
                        map.get(MinIOGen.storagname));
            }
        }
        if (deleteserverfile) {
            MinIoUtils.delete(jsonstr);
        }
        return t;
    }

    public static boolean deleteBucket(String namespace) {
        MinIOGen.checkMinIOState();

        try {
            MinIOGen.client.removeBucket(RemoveBucketArgs.builder().bucket(namespace).build());
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean delete(String jsonstr) {
        MinIOGen.checkMinIOState();

        HashMap<String, String> map = new HashMap<>();
        {
            Object o = JSONUtil.parseObj(jsonstr);
            BeanUtil.copyProperties(o, map);
        }
        try {
            MinIOGen.client.removeObject(RemoveObjectArgs.builder().bucket(map.get(MinIOGen.namespace))
                    .object(map.get(MinIOGen.storagname)).build());
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * @param author          上传人
     * @param filename        文件名(demo.png)
     * @param filepath
     * @param inputstream
     * @param deletelocalpath
     * @return
     * @author 汪旭辉
     * @date 2022年2月22日
     * @readme
     */
    public static String uploadFile(String author, String filename, String filepath, InputStream inputstream, boolean deletelocalpath) {
        MinIOGen.checkMinIOState();

        if (StrUtil.hasBlank(author, filename)) {
            throw new ExceptionError("作者:{} 文件名:{} 必填", author, filename);
        }
        if (inputstream == null && StrUtil.isBlank(filepath)) {
            throw new ExceptionError("按[文件路径读取,读取文件流]必须选择一项,优先读取文件路径!");
        } else {
            if (inputstream == null) {
                inputstream = FileUtil.getInputStream(filepath);
            }
        }
        HashMap<String, String> gui = new HashMap<>();
        String storagname = StrUtil.format("{}/{}/{}/{}-{}-{}-{}-{}", DateUtil.format(new Date(), "yyyy"),
                DateUtil.format(new Date(), "MM"), DateUtil.format(new Date(), "dd"),
                DateUtil.format(new Date(), "yyyyMMdd"), MinIOGen.namespace_re, author, IdUtil.fastUUID(),
                filename);
        String namespace = MinIOGen.createNameSpace726();
        String md5 = "";
        try {
            {
                FastByteArrayOutputStream byteArrayOutputStream = IoUtil.read(inputstream);
                {
                    InputStream stream1 = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
                    md5 = SecureUtil.md5(stream1).toUpperCase();
                }
                {//文件流
                    InputStream inputstream2 = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
                    MinIOGen.client.putObject(PutObjectArgs.builder().bucket(namespace).object(storagname)
                            .stream(inputstream2, inputstream2.available(), -1).build());
                }
            }
            String url = MinIOGen.client.getPresignedObjectUrl(GetPresignedObjectUrlArgs.builder().method(Method.HEAD) //这里必须是PUT，如果是GET的话就是文件访问地址了。如果是POST上传会报错.
                    .bucket(namespace).object(storagname).build());//60 * 60 * 24 * 9 .expiry(60 * 60 * 24)
            url = StrUtil.subBefore(url, "?", true);
            int px = StrUtil.ordinalIndexOf(url, "/", 3);
            url = Line.setting.get("home.minio.endpoint") + url.substring(px);
            {
                gui.put(MinIOGen.storagname, storagname);
                gui.put(MinIOGen.namespace, namespace);
                gui.put(MinIOGen.filename, filename);
                gui.put(MinIOGen.author, author);
                gui.put(MinIOGen.downloadurl, url.trim());
                gui.put(MinIOGen.md5, md5);
            }
            inputstream.close();
        } catch (Exception e) {
            e.printStackTrace();
            throw new ExceptionError("文件流上传失败! author:{} filename {} ", author, filename);
        }
        if (deletelocalpath && StrUtil.isNotBlank(filepath) && FileUtil.exist(filepath)) {//上传完成文件后,删除本地文件
            FileUtil.del(filepath);
        }
        return JSONUtil.toJsonStr(gui);
    }

}
