package com.dinghao.security.controller.system;

import com.dinghao.common.core.domain.controller.BaseController;
import com.dinghao.common.exception.ResultBody;
import com.dinghao.system.service.ISysConfigService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 参数配置 信息操作处理
 * @author dinghao
 */
@RestController
@RequestMapping(value = "/system/config")
@Slf4j
@CrossOrigin
public class SysConfigController extends BaseController {

    @Autowired
    private ISysConfigService configService;

    /**
     * 根据参数键名查询参数值
     */
    @GetMapping(value = "/configKey/{configKey}")
    public ResultBody getConfigKey(@PathVariable String configKey)
    {
        return ResultBody.success(configService.selectConfigByKey(configKey));
    }
}
