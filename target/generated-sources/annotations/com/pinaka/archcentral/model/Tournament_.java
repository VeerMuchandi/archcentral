package com.pinaka.archcentral.model;

import java.util.Calendar;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Tournament.class)
public abstract class Tournament_ {

	public static volatile SingularAttribute<Tournament, Integer> numberOfRounds;
	public static volatile SingularAttribute<Tournament, Long> id;
	public static volatile SingularAttribute<Tournament, Integer> shotsPerRound;
	public static volatile SingularAttribute<Tournament, String> tournamentName;
	public static volatile SingularAttribute<Tournament, Calendar> date;
	public static volatile SingularAttribute<Tournament, String> tournamentDescription;
	public static volatile SetAttribute<Tournament, ScoreCard> scorecards;

}

