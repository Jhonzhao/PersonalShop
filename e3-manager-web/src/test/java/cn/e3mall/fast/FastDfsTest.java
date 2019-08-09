package cn.e3mall.fast;

import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.StorageClient;
import org.csource.fastdfs.StorageServer;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerServer;
import org.junit.Test;

import cn.e3mall.common.utils.FastDFSClient;

public class FastDfsTest {

	@Test
	public void testUpload() throws Exception{
		//创建一个配置文件。文件名任意，内容就是tracker服务器的地址
		//使用一个全局对象加载配置文件
		ClientGlobal.init("F:/maven/e3-manager-web/src/main/resources/conf/client.conf");
		//创建一个TrackerClient对象
		TrackerClient trackerClient = new TrackerClient();
		//通过TrackClient获得一个TrackerServer对象
		TrackerServer trackerServer = trackerClient.getConnection();
		//创建一个StrorageServer的引用，可以是null
		StorageServer storageServer = null;
		//创建一个StorageClient，参数需要TrackerServer和StorageServer
		StorageClient storageClient = new StorageClient(trackerServer, storageServer);
		//使用StorageClient上传文件
		String[] strings = storageClient.upload_file("G:/google piceture/img-65a4d20ed9907070cc8b0a4bd9419a10.jpg","jpg", null);
		for (String string : strings) {
			System.out.println(string);
		}
	}
	
	@Test
	public void testFastDfsClient() throws Exception{
		FastDFSClient fastDfsClient = new FastDFSClient("F:/maven/e3-manager-web/src/main/resources/conf/client.conf");
		String string = fastDfsClient.uploadFile("G:/google piceture/a5s4l21523527460855.jpg");
		System.out.println(string);
		
	}
}
