package com.createXLS.structure.dao;

import java.util.List;
import java.util.Map;

public interface IStructureDao {
	List<Map<String,Object>> findStructure();
}
