CREATE TABLE IF NOT EXISTS `User` (
	`UserId`	INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
	`Username`	TEXT NOT NULL,
	`Password`	TEXT NOT NULL,
	`IsAdmin`	INTEGER,
	`IsStdMan`	INTEGER,
	`IsExamMan`	INTEGER,
	`FacId`	TEXT,
	FOREIGN KEY(`FacId`) REFERENCES `Faculty`(`FacId`) on delete restrict on update cascade
);
INSERT INTO `User` (UserId,Username,Password,IsAdmin,IsStdMan,IsExamMan,FacId) VALUES (1,'admin','81dc9bdb52d04dc20036dbd8313ed055',1,0,0,'001');
INSERT INTO `User` (UserId,Username,Password,IsAdmin,IsStdMan,IsExamMan,FacId) VALUES (2,'admin2','81dc9bdb52d04dc20036dbd8313ed055',1,0,0,'001');
INSERT INTO `User` (UserId,Username,Password,IsAdmin,IsStdMan,IsExamMan,FacId) VALUES (3,'admin2','81dc9bdb52d04dc20036dbd8313ed055',1,0,0,'001');
CREATE TABLE IF NOT EXISTS `Subject` (
	`SubId`	TEXT NOT NULL,
	`SubName`	TEXT,
	`IsPracticle`	INTEGER,
	`IsTheory`	INTEGER,
	`Credits`	INTEGER,
	`Price`	INTEGER,
	`CourseId`	TEXT,
	`FacId`	TEXT,
	FOREIGN KEY(`FacId`) REFERENCES `Faculty`(`FacId`) on delete restrict on update cascade,
	FOREIGN KEY(`CourseId`) REFERENCES `Course`(`CourseId`) on delete restrict on update cascade,
	PRIMARY KEY(`SubId`)
);
CREATE TABLE IF NOT EXISTS `Student` (
	`StdId`	TEXT NOT NULL,
	`Name`	TEXT NOT NULL,
	`Address`	TEXT,
	`DOB`	TEXT,
	`FacId`	TEXT,
	`CourseId`	TEXT,
	`FinalGrade`	TEXT,
	FOREIGN KEY(`CourseId`) REFERENCES `Course`(`CourseId`) on delete restrict on update cascade,
	PRIMARY KEY(`StdId`),
	FOREIGN KEY(`FacId`) REFERENCES `Faculty`(`FacId`) on delete restrict on update cascade
);
CREATE TABLE IF NOT EXISTS `Std_undergrad` (
	`StuId`	TEXT NOT NULL,
	`AL_index`	TEXT,
	`ResultSub1`	TEXT,
	`ResultSub2`	TEXT,
	`ResultSub3`	TEXT,
	`ResultSub4`	TEXT,
	`IslandRank`	INTEGER,
	PRIMARY KEY(`StuId`),
	FOREIGN KEY(`StuId`) REFERENCES `Student`(`StdId`) on delete cascade on update cascade
);
CREATE TABLE IF NOT EXISTS `Std_postgrad` (
	`StdId`	TEXT NOT NULL,
	`QualificationType`	TEXT,
	`Institute`	TEXT,
	`Year`	INTEGER,
	PRIMARY KEY(`StdId`),
	FOREIGN KEY(`StdId`) REFERENCES `Student`(`StdId`) on delete cascade on update cascade
);
CREATE TABLE IF NOT EXISTS `Std_Sub` (
	`StdId`	TEXT NOT NULL,
	`SubId`	TEXT NOT NULL,
	`Year`	INTEGER,
	`Semester`	INTEGER,
	FOREIGN KEY(`SubId`) REFERENCES `Subject`(`SubId`) on delete restrict on update cascade,
	FOREIGN KEY(`StdId`) REFERENCES `Student`(`StdId`) on delete cascade on update cascade,
	PRIMARY KEY(`StdId`,`SubId`)
);
CREATE TABLE IF NOT EXISTS `Std_Evaluation` (
	`StdId`	TEXT NOT NULL,
	`EvalId`	TEXT NOT NULL,
	`Score`	TEXT,
	`Grade`	TEXT,
	FOREIGN KEY(`EvalId`) REFERENCES `Evaluation`(`EvalId`) on delete cascade on update cascade,
	PRIMARY KEY(`StdId`,`EvalId`),
	FOREIGN KEY(`StdId`) REFERENCES `Student`(`StdId`) on delete cascade on update cascade
);
CREATE TABLE IF NOT EXISTS `Payment` (
	`PaymentId`	TEXT NOT NULL,
	`StdId`	TEXT,
	`SubID`	TEXT,
	`IsPaid`	INTEGER,
	`Amount`	INTEGER,
	`Semester`	INTEGER,
	`FacId`	TEXT,
	PRIMARY KEY(`PaymentId`,`SubID`),
	FOREIGN KEY(`StdId`) REFERENCES `Student`(`StdId`) on delete cascade on update cascade,
	FOREIGN KEY(`SubID`) REFERENCES `Subject`(`SubId`) on delete cascade on update cascade,
	FOREIGN KEY(`FacId`) REFERENCES `Faculty`(`FacId`) on delete cascade on update cascade
);
CREATE TABLE IF NOT EXISTS `Lecturer_Subject_Hall` (
	`LecturerId`	TEXT NOT NULL,
	`SubId`	TEXT NOT NULL,
	`HallId`	TEXT NOT NULL,
	`Day`	TEXT NOT NULL,
	`STime`	TEXT,
	`ETime`	TEXT,
	PRIMARY KEY(`LecturerId`,`SubId`,`HallId`,`Day`),
	FOREIGN KEY(`HallId`) REFERENCES `Hall`(`HallId`) on delete restrict on update cascade,
	FOREIGN KEY(`LecturerId`) REFERENCES `Lecturer`(`LecturerId`) on delete restrict on update cascade,
	FOREIGN KEY(`SubId`) REFERENCES `Subject`(`SubId`) on delete cascade on update cascade
);
CREATE TABLE IF NOT EXISTS `Lecturer` (
	`LecturerId`	TEXT NOT NULL,
	`Name`	TEXT,
	`Qualification`	BLOB,
	`FacId`	TEXT,
	FOREIGN KEY(`FacId`) REFERENCES `Faculty`(`FacId`) on delete restrict on update cascade,
	PRIMARY KEY(`LecturerId`)
);
CREATE TABLE IF NOT EXISTS `Lab` (
	`LabId`	TEXT NOT NULL,
	`LabName`	TEXT,
	`FacId`	TEXT,
	FOREIGN KEY(`FacId`) REFERENCES `Faculty`(`FacId`) on delete restrict on update cascade,
	PRIMARY KEY(`LabId`)
);
CREATE TABLE IF NOT EXISTS `Instructor_Subject_Lab` (
	`InstructorId`	TEXT NOT NULL,
	`SubId`	TEXT NOT NULL,
	`LabId`	TEXT NOT NULL,
	`Day`	TEXT NOT NULL,
	`STime`	TEXT NOT NULL,
	`ETime`	TEXT,
	FOREIGN KEY(`LabId`) REFERENCES `Lab`(`LabId`) on delete restrict on update cascade,
	PRIMARY KEY(`STime`,`Day`,`LabId`,`SubId`,`InstructorId`),
	FOREIGN KEY(`InstructorId`) REFERENCES `Instructor`(`InstructorID`) on delete restrict on update cascade,
	FOREIGN KEY(`SubId`) REFERENCES `Subject`(`SubId`) on delete cascade on update cascade
);
CREATE TABLE IF NOT EXISTS `Instructor_Subject_Hall` (
	`InstructorId`	TEXT NOT NULL,
	`SubId`	TEXT NOT NULL,
	`HallId`	TEXT NOT NULL,
	`Day`	TEXT NOT NULL,
	`STime`	TEXT,
	`ETime`	TEXT,
	FOREIGN KEY(`SubId`) REFERENCES `Subject`(`SubId`) on delete cascade on update cascade,
	PRIMARY KEY(`InstructorId`,`SubId`,`HallId`,`Day`),
	FOREIGN KEY(`InstructorId`) REFERENCES `Instructor`(`InstructorID`) on delete restrict on update cascade,
	FOREIGN KEY(`HallId`) REFERENCES `Hall`(`HallId`) on delete restrict on update cascade
);
CREATE TABLE IF NOT EXISTS `Instructor` (
	`InstructorID`	TEXT NOT NULL,
	`Name`	TEXT,
	`Qualification`	BLOB,
	`FacId`	TEXT,
	FOREIGN KEY(`FacId`) REFERENCES `Faculty`(`FacId`) on delete restrict on update cascade,
	PRIMARY KEY(`InstructorID`)
);
CREATE TABLE IF NOT EXISTS `Hall` (
	`HallId`	TEXT NOT NULL,
	`Capacity`	INTEGER,
	`Location`	TEXT,
	`FacId`	TEXT,
	FOREIGN KEY(`FacId`) REFERENCES `Faculty`(`FacId`) on delete restrict on update cascade,
	PRIMARY KEY(`HallId`)
);
CREATE TABLE IF NOT EXISTS `Faculty` (
	`FacId`	TEXT NOT NULL,
	`FacName`	TEXT,
	`MaxStdLimit`	INTEGER,
	`PrgNo`	INTEGER,
	PRIMARY KEY(`FacId`)
);
INSERT INTO `Faculty` (FacId,FacName,MaxStdLimit,PrgNo) VALUES ('001','School Of Computing',NULL,1);
CREATE TABLE IF NOT EXISTS `Evaluation` (
	`EvalId`	TEXT NOT NULL,
	`Type`	TEXT,
	`LabId`	TEXT,
	`HallId`	TEXT,
	`SubId`	TEXT,
	`FacId`	TEXT,
	FOREIGN KEY(`HallId`) REFERENCES `Hall`(`HallId`) on delete restrict on update cascade,
	FOREIGN KEY(`SubId`) REFERENCES `Subject`(`SubId`) on delete cascade on update cascade,
	FOREIGN KEY(`LabId`) REFERENCES `Lab`(`LabId`) on delete restrict on update cascade,
	FOREIGN KEY(`FacId`) REFERENCES `Faculty`(`FacId`) on delete cascade on update cascade,
	PRIMARY KEY(`EvalId`)
);
CREATE TABLE IF NOT EXISTS `Course` (
	`CourseId`	TEXT NOT NULL,
	`CourseName`	TEXT,
	`Years`	INTEGER,
	`Type`	TEXT,
	`TotalCredits`	INTEGER,
	`FacId`	TEXT,
	FOREIGN KEY(`FacId`) REFERENCES `Faculty`(`FacId`) on delete restrict on update cascade,
	PRIMARY KEY(`CourseId`)
);
COMMIT;
