/*FOR THE USERS*/
INSERT INTO users (username,user_password,first_name,last_name,iscontributor,isadmin) 
VALUES ('Kostas1','Hello1234!','Konstantinos1','Kokonos1',true,true),
       ('Kostas2','Hello1234!','Konstantinos2','Kokonos2',true,false),
       ('Kostas3','Hello1234!','Konstantinos3','Kokonos3',false,false);

/*FOR THE GOAL*/
INSERT INTO goal (goal_name,complete_goal) 
VALUES ('Lose weight',false),
       ('Gain Muscle Mass',false),
       ('Get Shredded',false);

/*FOR THE PROFILE*/
INSERT INTO profile (goal_id,age,weight,height)
VALUES (2,24,78,160),
		(1,26,80,170),
		(1,28,82,167);

/*FOR THE PROGRAM*/
INSERT INTO program (program_name,category,complete_program) 
VALUES ('Mr/Ms Olympia','Upper-Lower Body',false),
       ('Up Up Up','Upper Body',false),
       ('Down Down Down','Lower Body',false),
       ('Run Run Run','Aerobic',false);

/*FOR THE WORKOUT*/
INSERT INTO workout (workout_name,type,complete_workout)
VALUES ('Popeye','Arms',false),
       ('Want to have better posture?','Back',false),
       ('These are chicken legs?','Legs',false);

/*FOR THE EXERCISE*/
INSERT INTO exercise (workout_id,exercise_name,description,target_muscle_group,repetitions,image,video,complete_exercise)
VALUES (1,'Barbell Bicep Curl','','Biceps',15,'','https://www.youtube.com/watch?v=kwG2ipFRgfo&ab_channel=Howcast',false),
       (1,'Dumbbell Preacher Curl','','Triceps',15,'','https://www.youtube.com/watch?v=WK5yZMlgMb4&ab_channel=Bodybuilding.com',false),
       (2,'Deadlift','','Entire Posterior Chain',20,'','https://www.youtube.com/watch?v=ytGaGIn3SjE&ab_channel=JeremyEthier',false),
       (2,'Bent-Over Row','','Total Back-Builder',20,'','https://www.youtube.com/watch?v=FWJR5Ve8bnQ&ab_channel=MaxEuceda',false),
       (3,'Barbell Bulgarian Split Squat','Stand facing away from the bench, holding a barbell across your upper back. Have one leg resting on the bench behind you, laces down.','hamstrings',10,'','',false),
       (3,'Seated Dumbbell Calf Raise','','soleus',10,'','',false),
       (3,'Goblet Squat','','core and quadriceps',10,'','',false);

/*FOR THE GOAL-PROGRAM*/
INSERT INTO goal_program (goal_id,program_id)
VALUES (1,2),(1,3),
       (2,2),(2,3),
       (3,1),(3,2),(3,3);

/*FOR THE PROGRAM-WORKOUT*/
INSERT INTO program_workout (program_id,workout_id)
VALUES (1,1),(1,2),(1,3),
       (2,1),(2,2),
       (3,2),(3,3);


