-- You can use this file to load seed data into the database using SQL statements

insert into Member (id, name, email, phone_number) values (0, 'John Smith', 'john.smith@mailinator.com', '2125551212')
insert into Archer (id, name, userName, gender, archerclass) values (3,'Hyma', 'hyma.muchandi@gmail.com','F', 'Recurve')

insert into Tournament(id, tournamentName, shotsPerRound, numberOfRounds) values (7, 'GA900', 6, 20 )


insert into ScoreCard (participant_id, id, tournament_id, totalScore, archerClass, division) values (3, 4, 7, 700,'Recurve', 'junior')

insert into ScoreLine (scoreCard_Id, id, round) values (4,1,1)
insert into Score (scoreLineId, scoreOrder, shotNumber, scoreValue, is10X) values (1,1,1,1,False)
insert into Score (scoreLineId, scoreOrder, shotNumber, scoreValue, is10X) values (1,2,2,10,False)
insert into Score (scoreLineId, scoreOrder, shotNumber, scoreValue, is10X) values (1,3,3,10,True)
insert into Score (scoreLineId, scoreOrder, shotNumber, scoreValue, is10X) values (1,4,4,5,False)
insert into Score (scoreLineId, scoreOrder, shotNumber, scoreValue, is10X) values (1,5,5,6,False)
insert into Score (scoreLineId, scoreOrder, shotNumber, scoreValue, is10X) values (1,6,6,9,False)

insert into ScoreLine (scoreCard_Id, id, round) values (4,2,2)
insert into Score (scoreLineId, scoreOrder, shotNumber, scoreValue, is10X) values (2,1,1,1,False)
insert into Score (scoreLineId, scoreOrder, shotNumber, scoreValue, is10X) values (2,2,2,10,False)
insert into Score (scoreLineId, scoreOrder, shotNumber, scoreValue, is10X) values (2,3,3,10,True)
insert into Score (scoreLineId, scoreOrder, shotNumber, scoreValue, is10X) values (2,4,4,5,False)
insert into Score (scoreLineId, scoreOrder, shotNumber, scoreValue, is10X) values (2,5,5,6,False)
insert into Score (scoreLineId, scoreOrder, shotNumber, scoreValue, is10X) values (2,6,6,9,False)

insert into Club (id, name, address1, city, state, country, zip, contactName, contactPhoneNumber, enabled) values (8, 'Forsyth 4H', 'Ducktown park', 'Cumming', 'GA', 'USA','30041','Rick', '999-999-9999',true)

insert into ClubMembership(id, club_id, member_id, membershipType, duration, expiresOn) values (9, 8, 3, 'Single', 'SemiAnnual', null)