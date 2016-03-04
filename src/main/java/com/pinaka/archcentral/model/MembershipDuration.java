package com.pinaka.archcentral.model;

import java.util.Calendar;

public enum MembershipDuration {
	Weekly (7, Calendar.DATE), Monthly(1, Calendar.MONTH), Quarterly(3, Calendar.MONTH), SemiAnnual(6, Calendar.MONTH), Annual(1, Calendar.YEAR), BiAnnual(2, Calendar.YEAR), LifeTime(100, Calendar.YEAR);
	
	private int duration;
	private int scale;

	MembershipDuration(int duration, int scale ) {
		this.duration = duration;
		this.scale = scale;
	}
	
	public int value() {
		return duration;
	}
	
	public int scale() {
		return scale;
	}

}
