package activemq.onequickstart.way1.embed;
//
//import org.apache.activemq.ActiveMQConnection;
//import org.apache.activemq.broker.BrokerService;
//import org.apache.activemq.broker.jmx.ManagementContext;
//import org.apache.activemq.store.PListStore;
//import org.apache.activemq.store.kahadb.KahaDBPersistenceAdapter;
//import org.apache.activemq.store.kahadb.plist.PListStoreImpl;
//import org.apache.activemq.usage.MemoryUsage;
//import org.apache.activemq.usage.StoreUsage;
//import org.apache.activemq.usage.SystemUsage;
//import org.apache.activemq.usage.TempUsage;

import java.io.File;

public class EmbedMQ {

//    private static final String USERNAME = ActiveMQConnection.DEFAULT_USER;
//    private static final String PASSWORD = ActiveMQConnection.DEFAULT_PASSWORD;
//    private static final String BROKER_URL = "tcp://0.0.0.0:62001";
//
//
//    public static void main(String[] args) {
//
//       try {
//
//           /**
//            * 内置的ActiveMQ
//            */
//           BrokerService brokerService = new BrokerService();
//           brokerService.setBrokerName("EmbedMQ");
//           brokerService.addConnector(BROKER_URL);
//
//           // ============== 配置持久化存储 ==============
//           // 使用 KahaDB 作为持久化适配器
//           KahaDBPersistenceAdapter persistenceAdapter = new KahaDBPersistenceAdapter();
//           persistenceAdapter.setDirectory(new File("D:\\mq\\tempKhDB"));
//           brokerService.setPersistenceAdapter(persistenceAdapter);
//
//
//           // ============== 配置存储空间限制 ==============
//           // 创建系统资源使用配置
//           SystemUsage systemUsage = new SystemUsage();
//
//           // 1. 配置持久化存储限制 (1GB)
//           StoreUsage storeUsage = systemUsage.getStoreUsage();
//           storeUsage.setLimit(1024 * 1024 * 512); //
//           // 可选：设置使用百分比阈值触发警告
//           //storeUsage.setPercentUsage(90); // 90%使用率时触发警告
//
//           // 2. 配置临时存储限制 (1GB)
//           TempUsage tempUsage = systemUsage.getTempUsage();
//           tempUsage.setLimit(1024 * 1024 * 512);
//
//           // 3. 配置内存使用限制 (1GB)
//           MemoryUsage memoryUsage = systemUsage.getMemoryUsage();
//           memoryUsage.setLimit(1024 * 1024 * 32);
//
//           brokerService.setSystemUsage(systemUsage);
//
//
//           ManagementContext managementContext = new ManagementContext();
//           brokerService.setManagementContext(managementContext);
//           brokerService.start();
//           //主线程关闭，mq也关闭了。主线程休眠等待，保证内置的mq启动
//           Thread.sleep(10000000);
//
//       }catch (Exception e){
//           e.printStackTrace();
//       }
//
//    }

}
