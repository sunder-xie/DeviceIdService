package com.ymatou.deviceid.facade.rest;

import com.alibaba.dubbo.config.annotation.Service;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;

/**
 * Created by liangzhonghua on 2016/9/26.
 */
@Component("warmUp")
@Service(protocol = "rest")
@Produces({"application/json"})
@Path("/")
public class WarmUpImpl implements WarmUp {

    @Override
    @GET
    @Path("/version")
    public String version() {
        return "{"
                + "\"1.0.0\":\"2016-09-30.1 created\","
                + "\"1.0.1\":\"2016-10-09.1 get method add param type\","
                + "\"1.0.2\":\"2016-10-09.3 add update method\","
                + "\"1.0.2\":\"2016-10-21.1 add getFirst method\""
                + "}";
    }

    @Override
    @GET
    @Path("/warmup")
    public String warmup() {
        return "ok";
    }
}
