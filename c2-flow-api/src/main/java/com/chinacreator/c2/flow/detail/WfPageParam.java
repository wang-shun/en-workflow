package com.chinacreator.c2.flow.detail;

import java.io.Serializable;

/**
 * 分页排序参数
 * 
 * @param order
 *            升序或降序(asc|desc)
 * @param paged
 *            是否分页
 * @param start
 *            分页参数-启始行
 * @param size
 *            分页参数-每页记录数
 * @param total
 *            分页参数- 总记录数
 * @author minghua.guo
 * 
 */
public class WfPageParam implements Serializable {
	private static final long serialVersionUID = 1L;
	public static final String SORT_ASC = "asc";
	public static final String SORT_DESC = "desc";
	private boolean paged = false;
	private String order = SORT_ASC;
	private long start = 0;
	private long size = 10;
	private long total = 0;

	public WfPageParam() {
		super();
	}

	public WfPageParam(boolean paged, String order, long start, long size,
			long total) {
		super();
		this.paged = paged;
		this.order = order;
		this.start = start;
		this.size = size;
		this.total = total;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	public long getStart() {
		return start;
	}

	public void setStart(long start) {
		this.start = start;
	}

	public long getSize() {
		return size;
	}

	public void setSize(long size) {
		this.size = size;
	}

	public long getTotal() {
		return total;
	}

	public void setTotal(long total) {
		this.total = total;
	}

	public boolean isPaged() {
		return paged;
	}

	public void setPaged(boolean paged) {
		this.paged = paged;
	}

	@Override
	public String toString() {
		return "WfPageQuery [paged=" + paged + ", order=" + order + ", start="
				+ start + ", size=" + size + ", total=" + total + "]";
	}
}
