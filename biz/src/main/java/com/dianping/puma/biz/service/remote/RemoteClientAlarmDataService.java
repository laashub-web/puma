package com.dianping.puma.biz.service.remote;

import com.dianping.puma.alarm.model.data.PullTimeDelayAlarmData;
import com.dianping.puma.alarm.model.data.PushTimeDelayAlarmData;
import com.dianping.puma.alarm.service.PumaClientAlarmDataService;
import com.dianping.puma.biz.convert.Converter;
import com.dianping.puma.biz.dao.ClientAlarmDataDao;
import com.dianping.puma.biz.entity.ClientAlarmDataEntity;

import java.util.Map;

/**
 * Created by xiaotian.li on 16/3/22.
 * Email: lixiaotian07@gmail.com
 */
public class RemoteClientAlarmDataService implements PumaClientAlarmDataService {

    private Converter converter;

    private ClientAlarmDataDao clientAlarmDataDao;

    @Override
    public PullTimeDelayAlarmData findPullTimeDelay(String clientName) {
        ClientAlarmDataEntity entity = clientAlarmDataDao.find(clientName);
        return converter.convert(entity, PullTimeDelayAlarmData.class);
    }

    @Override
    public PushTimeDelayAlarmData findPushTimeDelay(String clientName) {
        ClientAlarmDataEntity entity = clientAlarmDataDao.find(clientName);
        return converter.convert(entity, PushTimeDelayAlarmData.class);
    }

    @Override
    public Map<String, PullTimeDelayAlarmData> findPullTimeDelayAll() {
        return null;
    }

    @Override
    public Map<String, PushTimeDelayAlarmData> findPushTimeDelayAll() {
        return null;
    }

    @Override
    public void replacePullTimeDelay(String clientName, PullTimeDelayAlarmData data) {
        ClientAlarmDataEntity entity = converter.convert(data, ClientAlarmDataEntity.class);
        entity.setClientName(clientName);

        int result = clientAlarmDataDao.updatePullTimeDelay(entity);
        if (result == 0) {
            try {
                clientAlarmDataDao.insert(entity);
            } catch (Throwable ignore) {
                clientAlarmDataDao.updatePullTimeDelay(entity);
            }
        }
    }

    @Override
    public void replacePushTimeDelay(String clientName, PushTimeDelayAlarmData data) {
        ClientAlarmDataEntity entity = converter.convert(data, ClientAlarmDataEntity.class);
        entity.setClientName(clientName);

        int result = clientAlarmDataDao.updatePushTimeDelay(entity);
        if (result == 0) {
            try {
                clientAlarmDataDao.insert(entity);
            } catch (Throwable ignore) {
                clientAlarmDataDao.updatePushTimeDelay(entity);
            }
        }
    }

    public void setConverter(Converter converter) {
        this.converter = converter;
    }

    public void setClientAlarmDataDao(ClientAlarmDataDao clientAlarmDataDao) {
        this.clientAlarmDataDao = clientAlarmDataDao;
    }
}