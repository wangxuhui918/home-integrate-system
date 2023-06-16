
/*
 *
 *  * Copyright (c) 2022
 *  * http://license.coscl.org.cn/MulanPSL2
 *  * 汪旭辉
 *
 */

package cn.bigcore.micro.minio.utils;

import cn.bigcore.micro.FyyInitEnv;
import cn.bigcore.micro.config.config.impl.bean.FyyConfigEntryDetailsValues;
import cn.bigcore.micro.exception.re.ex.FyyExceptionError;
import io.minio.BucketExistsArgs;
import io.minio.MakeBucketArgs;
import io.minio.MinioClient;
import io.minio.SetBucketPolicyArgs;

public class FyyLineMinIOGen {
    protected final static String storagname = "storagname";
    protected final static String downloadurl = "downloadurl";
    protected final static String namespace = "namespace";
    protected final static String filename = "filename";
    protected final static String author = "author";
    protected final static String md5 = "md5";
    protected static String namespace_re = "";
    protected static MinioClient client = null;

    public static void intiConect() {
        if (client == null) {
            namespace_re = FyyInitEnv.SettingInformation.setting.get(FyyConfigEntryDetailsValues.HOME_MINIO_NAMESPACE_RE.getKey());
            client = MinioClient.builder().endpoint(FyyInitEnv.SettingInformation.setting.get(FyyConfigEntryDetailsValues.HOME_MINIO_ENDPOINT.getKey())).credentials(FyyInitEnv.SettingInformation.setting.get(FyyConfigEntryDetailsValues.HOME_MINIO_ACCESSKEY.getKey()), FyyInitEnv.SettingInformation.setting.get(FyyConfigEntryDetailsValues.HOME_MINIO_SECRETKEY.getKey())).build();
        }
    }

    public static void checkMinIOState() {
        if (client == null) {
            throw new FyyExceptionError("MinIO连接失败,请检查相关参数是否正常,{},{},{},{}", FyyInitEnv.SettingInformation.setting.get(FyyConfigEntryDetailsValues.HOME_MINIO_NAMESPACE_RE.getKey()), FyyInitEnv.SettingInformation.setting.get(FyyConfigEntryDetailsValues.HOME_MINIO_ENDPOINT.getKey()), FyyInitEnv.SettingInformation.setting.get(FyyConfigEntryDetailsValues.HOME_MINIO_ACCESSKEY.getKey()), FyyInitEnv.SettingInformation.setting.get(FyyConfigEntryDetailsValues.HOME_MINIO_SECRETKEY.getKey()));
        }
    }

    public static String createNameSpace726() {
        String bucketName = namespace_re;
        String bp1 = "{\"Version\":\"2012-10-17\",\"Statement\":[{\"Effect\":\"Allow\",\"Principal\":{\"AWS\":[\"*\"]},\"Action\":[\"s3:GetBucketLocation\",\"s3:ListBucket\",\"s3:ListBucketMultipartUploads\"],\"Resource\":[\"arn:aws:s3:::"
                + bucketName
                + "\"]},{\"Effect\":\"Allow\",\"Principal\":{\"AWS\":[\"*\"]},\"Action\":[\"s3:DeleteObject\",\"s3:GetObject\",\"s3:ListMultipartUploadParts\",\"s3:PutObject\",\"s3:AbortMultipartUpload\"],\"Resource\":[\"arn:aws:s3:::"
                + bucketName + "/*\"]}]}";
        try {
            boolean isExist = client.bucketExists(BucketExistsArgs.builder().bucket(bucketName).build());
            if (!isExist) {
                client.makeBucket(MakeBucketArgs.builder().bucket(bucketName).build());
                client.setBucketPolicy(SetBucketPolicyArgs.builder().bucket(bucketName).config(bp1).build());
            }
        } catch (Exception e) {
            try {
                if (!client.bucketExists(BucketExistsArgs.builder().bucket(bucketName).build())) {
                    e.printStackTrace();
                    throw new FyyExceptionError("创建命名空间失败");
                }
            } catch (Exception e1) {
            }
        }
        return bucketName;
    }
}
