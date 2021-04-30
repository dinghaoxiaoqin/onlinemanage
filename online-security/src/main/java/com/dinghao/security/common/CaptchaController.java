package com.dinghao.security.common;

import com.alibaba.fastjson.JSON;
import com.dinghao.common.constant.Constants;
import com.dinghao.common.exception.ResultBody;
import com.dinghao.common.utils.ToolUtil;
import com.dinghao.common.utils.sign.Base64;
import com.dinghao.common.utils.uuid.IdUtils;
import com.google.code.kaptcha.Producer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.util.FastByteArrayOutputStream;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

/**
 * 验证码操作处理
 * @author dinghao
 */
@RestController
@Slf4j
@RequestMapping(value = "/captcha")
@CrossOrigin
public class CaptchaController {

    private static final String DEFAULT_CAPTCHA= "math";
    private static final String CHAR_CAPTCHA= "char";


    @Resource(name = "captchaProducer")
    private Producer captchaProducer;

    @Resource(name = "captchaProducerMath")
    private Producer captchaProducerMath;

    @Autowired
    private StringRedisTemplate redisTemplate;

    /**
     * 验证码类型
     */
    @Value("${online.captchaType}")
    private String captchaType;

    /**
     * 生成验证码
     */
    @GetMapping(value = "/captchaImage")
    public ResultBody captchaImage(HttpServletResponse response){
        // 保存验证码信息
        String uuid = IdUtils.simpleUUID();
        String verifyKey = Constants.CAPTCHA_CODE_KEY + uuid;
        String capStr = null, code = null;
        BufferedImage image = null;

        // 生成验证码
        if (ToolUtil.equals(DEFAULT_CAPTCHA,captchaType))
        {
            String capText = captchaProducerMath.createText();
            capStr = capText.substring(0, capText.lastIndexOf("@"));
            code = capText.substring(capText.lastIndexOf("@") + 1);
            image = captchaProducerMath.createImage(capStr);
        }
        else if (ToolUtil.equals(CHAR_CAPTCHA,captchaType))
        {
            capStr = code = captchaProducer.createText();
            image = captchaProducer.createImage(capStr);
        }
        //保存到redis
        redisTemplate.opsForValue().set(verifyKey,code,Constants.CAPTCHA_EXPIRATION,TimeUnit.MINUTES);
       // redisCache.setCacheObject(verifyKey, code, Constants.CAPTCHA_EXPIRATION, TimeUnit.MINUTES);
        // 转换流信息写出
        FastByteArrayOutputStream os = new FastByteArrayOutputStream();
        try
        {
            ImageIO.write(image, "jpg", os);
        }
        catch (IOException e)
        {
            return ResultBody.error("507",e.getMessage());
        }
        HashMap<String,Object> ajax = new HashMap<>();
        ajax.put("uuid", uuid);
        ajax.put("img", Base64.encode(os.toByteArray()));
        return ResultBody.success(ajax);
    }

}
