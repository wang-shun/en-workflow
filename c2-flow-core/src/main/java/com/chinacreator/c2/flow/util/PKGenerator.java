package com.chinacreator.c2.flow.util;

import java.util.Locale;
import java.util.UUID;

import org.activiti.engine.impl.cfg.IdGenerator;

/**
 * 数据库主键生成工具类 <br>
 * 
 * @author minghua.guo
 */
public class PKGenerator implements IdGenerator{

	@Override
	public String getNextId() {
        String s = UUID.randomUUID().toString().toUpperCase(Locale.US);
        return s.substring(0, 8) + s.substring(9, 13) + s.substring(14, 18)
                + s.substring(19, 23) + s.substring(24);
	}
}
