package com.bigdata.bigdata.hbase;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Admin;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import org.apache.hadoop.hbase.client.Table;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

/**
 * Author:   zhiqiu
 * Date:     2019-09-17
 */
public class HbaseApp {

	Connection connection = null;
	Table table = null;
	Admin admin = null;

	String tableName = "pk_hbase_java_api";

	@Before
	public void setUp() {
		Configuration configuration = new Configuration();
		configuration.set("hbase.rootdir", "hdfs://mvptyz.cn:8020/hbase");
		configuration.set("hbase.zookeeper.quorum", "mvptyz.cn:2181");
		try {
			connection = ConnectionFactory.createConnection(configuration);
			admin = connection.getAdmin();

			Assert.assertNotNull(connection);
			Assert.assertNotNull(admin);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void getConnection() {

	}

	@Test
	public void createTable() throws IOException {
		TableName table = TableName.valueOf(tableName);
		if (admin.tableExists(table)) {
			System.out.println(tableName + " 已经存在...");
		} else {
			HTableDescriptor descriptor = new HTableDescriptor(table);
			descriptor.addFamily(new HColumnDescriptor("info"));
			descriptor.addFamily(new HColumnDescriptor("address"));
			admin.createTable(descriptor);
			System.out.println(tableName + " 创建成功...");
		}
	}

	@Test
	public void queryTableInfos() throws Exception {
		HTableDescriptor[] tables = admin.listTables();
		if (tables.length > 0) {
			for (HTableDescriptor table : tables) {
				System.out.println(table.getNameAsString());

				HColumnDescriptor[] columnDescriptors = table.getColumnFamilies();
				for (HColumnDescriptor hColumnDescriptor : columnDescriptors) {
					System.out.println("\t" + hColumnDescriptor.getNameAsString());
				}
			}
		}
	}

	@After
	public void tearDown() {
		try {
			connection.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
