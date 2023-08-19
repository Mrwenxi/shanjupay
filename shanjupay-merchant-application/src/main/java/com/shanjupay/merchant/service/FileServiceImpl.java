package com.shanjupay.merchant.service;

import com.shanjupay.merchant.common.domain.BusinessException;
import com.shanjupay.merchant.common.domain.CommonErrorCode;
import com.shanjupay.merchant.common.util.QiniuUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * @program: shanjupay
 * @ClassName FileServiceImpl
 * @description:
 * @author: wen
 * @create: 2023-08-19 10:24
 * @Version 1.0
 **/

@Service
public class FileServiceImpl implements FileService {

    @Value("${oss.qiniu.url}")
    private String qiniuUrl;
    @Value("${oss.qiniu.accessKey}")
    private String accessKey;
    @Value("${oss.qiniu.secretKey}")
    private String secretKey;
    @Value("${oss.qiniu.bucket}")
    private String bucket;


    @Override
    public String upload(byte[] bytes, String fileName) {
          try {
            QiniuUtils.upload2qiniu(accessKey,secretKey,bucket,bytes,fileName);
        } catch (RuntimeException e) {
            e.printStackTrace();
            throw new BusinessException(CommonErrorCode.E_100106);
        }
        //上传成功返回文件的访问地址（绝对路径）
        return qiniuUrl+fileName;
    }
}