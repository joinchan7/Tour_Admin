package com.chan.ssm.service;

import com.chan.ssm.domain.SysLog;

import java.util.List;

public interface SysLogService {

    void save(SysLog sysLog);

    List<SysLog> findAll();

    List<SysLog> findAll(Integer page, Integer size);
}
