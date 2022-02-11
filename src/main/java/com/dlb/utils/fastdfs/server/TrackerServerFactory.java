package com.dlb.utils.fastdfs.server;

import org.apache.commons.pool2.BasePooledObjectFactory;
import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.impl.DefaultPooledObject;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerServer;

/**
 * TrackerServer 工厂类，创建对象池需要 BasePooledObjectFactory 对象或子类.
 * 需要导包commons-pool2
 */
public class TrackerServerFactory extends BasePooledObjectFactory<TrackerServer> {

    @Override
    public TrackerServer create() throws Exception {
        // 创建管理端对象
        TrackerClient trackerClient = new TrackerClient();
        // 通过管理端对象获取连接 并返回连接对象
        return trackerClient.getTrackerServer();
    }

    @Override
    public PooledObject<TrackerServer> wrap(TrackerServer trackerServer) {
        return new DefaultPooledObject<TrackerServer>(trackerServer);
    }
}
