package com.dinghao.system.service.impl;



import com.dinghao.system.domain.SysNotice;
import com.dinghao.system.mapper.SysNoticeMapper;
import com.dinghao.system.service.ISysNoticeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 通知公告Service业务层处理
 * 
 * @author dinghao
 * @date 2021-04-10
 */
@Service
@Slf4j
public class SysNoticeServiceImpl implements ISysNoticeService
{
    @Autowired
    private SysNoticeMapper sysNoticeMapper;

    /**
     * 获取公告通知列表
     * @param sysNotice
     * @return
     */
    @Override
    public List<SysNotice> getSysNotices(SysNotice sysNotice) {
        return sysNoticeMapper.selectNoticeList(sysNotice);
    }

    /**
     * 根据id获取通知公告
     * @param noticeId
     * @return
     */
    @Override
    public SysNotice selectNoticeById(Long noticeId) {
        return sysNoticeMapper.selectNoticeById(noticeId);
    }

    /**
     * 新增公告通知
     * @param notice
     * @return
     */
    @Override
    public Integer insertNotice(SysNotice notice) {
        return sysNoticeMapper.insertSysNotice(notice);
    }

    /**
     * 修改公告通知
     * @param notice
     * @return
     */
    @Override
    public Integer updateNotice(SysNotice notice) {
        return sysNoticeMapper.updateSysNotice(notice);
    }

    /**
     * 删除公告通知
     * @param noticeIds
     * @return
     */
    @Override
    public Integer deleteNoticeByIds(Long[] noticeIds) {
        return sysNoticeMapper.deleteNoticeByIds(noticeIds);
    }
}
