package com.chinacreator.c2.flow.detail;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 工作流自定义分页List
 * @author minghua.guo
 *
 * @param <E> 同List&lt;E>
 * @param <T> extends WfPageParam，用于WfPageList返回分页和查询参数
 */
public class WfPageList<E, T extends WfPageParam> implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private List<E> datas = new ArrayList<E>();
	
	public void add(E obj){
		datas.add(obj);
	}
	
	public void addAll(List<E> objs){
		datas.addAll(objs);
	}
	
	private T wfQuery;

	public T getWfQuery() {
		return wfQuery;
	}

	public void setWfQuery(T wfQuery) {
		this.wfQuery = wfQuery;
	}

	public List<E> getDatas() {
		return datas;
	}

	public void setDatas(List<E> datas) {
		this.datas = datas;
	}
}