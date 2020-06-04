package com.chan.ssm.service.impl;

import com.chan.ssm.dao.SysLogDao;
import com.chan.ssm.domain.SysLog;
import com.chan.ssm.service.SysLogService;
import com.chan.ssm.utils.IdUtils;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class SysLogServiceImpl implements SysLogService {

    @Autowired
    private SysLogDao sysLogDao;

    @Override
    public List<SysLog> findAll() {
        return sysLogDao.findAll();
    }

    @Override
    public List<SysLog> findAll(Integer page, Integer size) {
        PageHelper.startPage(page, size);
        return sysLogDao.findAll();
    }

    @Override
    public void save(SysLog sysLog) {
        sysLog.setId(IdUtils.randomId());
        sysLogDao.save(sysLog);
    }

}
