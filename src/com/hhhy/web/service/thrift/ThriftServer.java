package com.hhhy.web.service.thrift;

import org.apache.log4j.Logger;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TThreadPoolServer;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TServerTransport;
import org.apache.thrift.transport.TTransportException;

import com.hhhy.web.service.thrift.gencode.HhhyService;


/**
 * Created by lenovo on 8/10/2014.
 */
public class ThriftServer {
    private static final Logger logger = Logger.getLogger(ThriftServer.class);
    private static HhhyService.Processor processor;

    public static void main(String[] args) {
        start();
    }

    // will start in Log4jServlet when web start
    public static void start() {
        ThriftRequireHandler handler = new ThriftRequireHandler();
        processor = new HhhyService.Processor(handler);

        Runnable runner = new Runnable() {
            @Override
            public void run() {
                runServer(processor);
            }
        };
        new Thread(runner).start();
    }

    private static void runServer(HhhyService.Processor processor){
        TServer server = null;

        try {
            TServerTransport serverTransport = null;
            serverTransport = new TServerSocket(12306);
            TThreadPoolServer.Args args = new TThreadPoolServer.Args(serverTransport);
            args.processor(processor);
            args.minWorkerThreads(1);
            args.maxWorkerThreads(256);
            server = new TThreadPoolServer(args);
            logger.info("Starting the TThreadPoolServer server...");
            server.serve();
            logger.info("TThreadPoolServer server started...");
        } catch (TTransportException e) {
            logger.error(e.getMessage());
        }
    }
}
