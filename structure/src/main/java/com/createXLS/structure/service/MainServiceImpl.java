package com.createXLS.structure.service;

import java.util.*;

import com.createXLS.structure.dao.IStructureDao;
import com.createXLS.structure.util.ExportExcelUtils;
import com.createXLS.structure.util.MyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

@Service
public class MainServiceImpl implements IMainService{
	@Autowired
	IStructureDao structureDao;
	
	@Autowired
	private Environment env;
	
	@Override
	public boolean findStructure() {
		List<Map<String,Object>> list = structureDao.findStructure();
		Map<String,List<List<String>>> sheets = new LinkedHashMap<String,List<List<String>>>();
		String tbl = "";
		String comment = "";
		Map<String, String> tbl_comment = new LinkedHashMap<String, String>();
		// 根据每列数据进行循环
		List<List<String>> rows = null;
		List<String> cols = null;
		for(Map<String,Object> record:list) {
			if(!tbl.equals(MyUtils.toString(record.get("表名")))) {
				if(!"".equals(tbl)) {
					sheets.put(tbl, rows);
				}
				tbl = MyUtils.toString(record.get("表名"));
				comment = MyUtils.toString(record.get("表注释"));
				rows = new ArrayList<List<String>>();
			}
			cols = new ArrayList<String>();

			cols.add(MyUtils.toString(record.get("列名")));
			cols.add(MyUtils.toString(record.get("数据类型")));
			cols.add(MyUtils.toString(record.get("允许为空")));
			cols.add(MyUtils.toString(record.get("主键")));
			cols.add(MyUtils.toString(record.get("默认值")));
			cols.add(MyUtils.toString(record.get("备注")));
			rows.add(cols);
			tbl_comment.put(tbl, comment);
		}
		sheets.put(tbl, rows);
		//tbl_comment.put(tbl, comment);
		//下载地址为project的根目录下
		String fname = env.getProperty("database.name") + ".xlsx";
	    ExportExcelUtils.createExcel(sheets,tbl_comment,fname);
		return true;
	}

}
