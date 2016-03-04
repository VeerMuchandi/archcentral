package com.pinaka.archcentral.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(ScoreCard.class)
public abstract class ScoreCard_ {

	public static volatile SingularAttribute<ScoreCard, Long> id;
	public static volatile SingularAttribute<ScoreCard, Tournament> tournament;
	public static volatile SingularAttribute<ScoreCard, Integer> totalScore;
	public static volatile SingularAttribute<ScoreCard, String> division;
	public static volatile SingularAttribute<ScoreCard, Archer> participant;
	public static volatile SetAttribute<ScoreCard, ScoreLine> scoreLines;
	public static volatile SingularAttribute<ScoreCard, ArcherClass> archerClass;

}

