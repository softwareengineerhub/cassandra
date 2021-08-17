package com.app;

import com.datastax.oss.driver.api.core.CqlSession;
import org.apache.thrift.transport.TTransportException;
import org.cassandraunit.utils.EmbeddedCassandraServerHelper;

import java.io.IOException;
import java.net.InetSocketAddress;

public class StartEmbeddedCassandra {

    public static void main(String[] args) throws IOException, TTransportException {
        EmbeddedCassandraServerHelper.startEmbeddedCassandra(45000);
        String host = EmbeddedCassandraServerHelper.getHost();
        int port1 = EmbeddedCassandraServerHelper.getNativeTransportPort();
        int port2 = EmbeddedCassandraServerHelper.getRpcPort();
        System.out.println("host=" + host);
        System.out.println("port1=" + port1);
        System.out.println("port2=" + port2);

        CqlSession cqlSession = CqlSession.builder().addContactPoint(new InetSocketAddress("localhost", 9142)).withLocalDatacenter("datacenter1").build();
        System.exit(0);
    }

}
