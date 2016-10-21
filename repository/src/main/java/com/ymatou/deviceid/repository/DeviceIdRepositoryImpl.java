package com.ymatou.deviceid.repository;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.ymatou.deviceid.facade.model.vo.DeviceInfo;


/**
 * Created by liangzhonghua on 2016/9/22.
 */

@Repository("deviceIdRepository")
public class DeviceIdRepositoryImpl implements DeviceIdRepository {

    private final String CollectionName = "deviceId";

    // 取出最新的DeviceId
    private final int BY_ACTIVETIME_DESC = 1;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public void insert(HashMap<String, Object> deviceIdInfo) {
        mongoTemplate.insert(deviceIdInfo, CollectionName);
    }



    @Override
    public void save(DeviceInfo deviceInfo) {
        mongoTemplate.save(deviceInfo, CollectionName);
    }

    @Override
    public DeviceInfo getDeviceInfo(String deviceId, int type) {

        Criteria criteria = new Criteria();
        criteria.andOperator(Criteria.where("deviceid").is(deviceId));

        Query query;
        if (type == BY_ACTIVETIME_DESC) {
            query = new Query(criteria).with(new Sort(Sort.Direction.DESC, "activeTime"));
        } else {
            query = new Query(criteria).with(new Sort(Sort.Direction.ASC, "activeTime"));
        }
        return mongoTemplate.findOne(query, DeviceInfo.class, CollectionName);
    }

    @Override
    public DeviceInfo getFirstDeviceInfo(String deviceId, String did) {

        Criteria criteria = new Criteria();
        criteria.orOperator(Criteria.where("deviceid").is(deviceId), Criteria.where("did").is(did));

        Query query;

        query = new Query(criteria).with(new Sort(Sort.Direction.ASC, "activeTime"));

        return mongoTemplate.findOne(query, DeviceInfo.class, CollectionName);
    }
}
