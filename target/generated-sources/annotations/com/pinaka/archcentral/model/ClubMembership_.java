package com.pinaka.archcentral.model;

import java.util.Calendar;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(ClubMembership.class)
public abstract class ClubMembership_ {

	public static volatile SingularAttribute<ClubMembership, Archer> member;
	public static volatile SingularAttribute<ClubMembership, Long> id;
	public static volatile SingularAttribute<ClubMembership, MembershipType> membershipType;
	public static volatile SingularAttribute<ClubMembership, MembershipDuration> duration;
	public static volatile SingularAttribute<ClubMembership, Calendar> expiresOn;
	public static volatile SingularAttribute<ClubMembership, Club> club;

}

