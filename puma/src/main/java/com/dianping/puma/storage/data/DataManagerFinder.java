package com.dianping.puma.storage.data;

import com.dianping.puma.common.LifeCycle;
import com.dianping.puma.storage.Sequence;
import com.dianping.puma.storage.data.bucket.ReadDataBucket;
import com.dianping.puma.storage.data.impl.DataKeyImpl;
import com.dianping.puma.storage.data.impl.DataValueImpl;

import java.io.File;
import java.io.IOException;

public interface DataManagerFinder extends LifeCycle {

	public File rootDir();

	public ReadDataBucket findReadDataBucket(Sequence sequence) throws IOException;

	public ReadDataBucket findNextReadDataBucket(Sequence sequence) throws IOException;

	public ReadDataManager<DataKeyImpl, DataValueImpl> findReadDataBucket(DataKeyImpl dataKey) throws IOException;

	public ReadDataManager<DataKeyImpl, DataValueImpl> findNextReadDataBucket(DataKeyImpl dataKey) throws IOException;

	public WriteDataManager<DataKeyImpl, DataValueImpl> genNextWriteDataBucket() throws IOException;
}