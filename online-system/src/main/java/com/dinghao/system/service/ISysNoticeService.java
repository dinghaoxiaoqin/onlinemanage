package com.dinghao.system.service;


import com.dinghao.system.domain.SysNotice;

import java.util.List;

/**
 * 通知公告Service接口
 * 
 * @author dinghao
 * @date 2021-04-10
 */
public interface ISysNoticeService 
{
  

    /**
     * 获取公告通知列表
     * @param sysNotice
     * @return
     */
    List<SysNotice> getSysNotices(SysNotice sysNotice);

    /**
     * 根据id获取公告通知
     * @param noticeId
     * @return
     */
    SysNotice selectNoticeById(Long noticeId);

    Integer insertNotice(SysNotice notice);

    Integer updateNotice(SysNotice notice);

    Integer deleteNoticeByIds(Long[] noticeIds);
}
