package com.preing.domain.product;

import java.util.List;


public class Verbalize {
 /**
  * @author caimingqin
  * @since 2013-09-09
  * @category 产品评论
  */
	private String code;
	private String content;
	private List<Score> scores;
	private String ownerCode;
	private String ownerName;
	public String getCode() {
		return code;
	}
	public String getContent() {
		return content;
	}
	public List<Score> getScores() {
		return scores;
	}
	public String getOwnerCode() {
		return ownerCode;
	}
	public String getOwnerName() {
		return ownerName;
	}
}
