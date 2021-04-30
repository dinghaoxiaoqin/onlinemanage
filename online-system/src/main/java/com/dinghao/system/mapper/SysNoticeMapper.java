package com.dinghao.system.mapper;


import com.dinghao.system.domain.SysNotice;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 通知公告Mapper接口
 * 
 * @author dinghao
 * @date 2021-04-10
 */
public interface SysNoticeMapper 
{

    List<SysNotice> selectNoticeList(SysNotice sysNotice);

    SysNotice selectNoticeById(@Param("noticeId") Long noticeId);

    Integer insertSysNotice(SysNotice notice);

    Integer updateSysNotice(SysNotice notice);

    Integer deleteNoticeByIds(@Param("noticeIds") Long[] noticeIds);
}
