package com.preing.domain.distributor;


public class Distributor {

	private String code;
	private String name;
	private String description;
	private DistributorAccount account;
	private DistributorLevel level=DistributorLevel.NORMAL;
	
	
	public String getCode() {
		return code;
	}
	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public DistributorAccount getAccount() {
		return account;
	}

	public DistributorLevel getLevel() {
		return level;
	}

	public enum DistributorLevel{
		NORMAL,ACC
	}
}
