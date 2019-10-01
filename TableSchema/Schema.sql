 
 IF OBJECT_ID('FSE_Parent_Task', 'U') IS NOT NULL
 DROP TABLE FSE_Parent_Task;

  
 IF OBJECT_ID('FSE_Task', 'U') IS NOT NULL
 DROP TABLE FSE_Task;

   
 IF OBJECT_ID('FSE_Project', 'U') IS NOT NULL
 DROP TABLE FSE_Project;

    
 IF OBJECT_ID('FSE_User', 'U') IS NOT NULL
 DROP TABLE FSE_User;

 CREATE TABLE FSE_Parent_Task (
	[Parent_ID] [bigint] IDENTITY NOT NULL,
	[Parent_Task] [varchar](255) NOT NULL,
    CONSTRAINT PK_FSE_Parent_Task PRIMARY KEY (Parent_ID)
);


 CREATE TABLE FSE_Task (
	[Task_ID] [bigint] IDENTITY NOT NULL,
	[Parent_ID] [bigint] NULL,
	[Project_ID] [bigint] NOT NULL,
	[Task] [varchar](255) NOT NULL,
	[Start_Date] [datetime] NOT NULL,
	[End_Date] [datetime] NOT NULL,
	[Priority] [int] NOT NULL,
	[Status] [varchar](20) NOT NULL,
    CONSTRAINT PK_FSE_Task PRIMARY KEY (Task_ID)
);


 CREATE TABLE FSE_Project (
	[Project_ID] [bigint] IDENTITY NOT NULL,
	[Project] [varchar](255) NOT NULL,
	[Start_Date] [datetime] NOT NULL,
	[End_Date] [datetime] NOT NULL,
	[Priority] [int] NOT NULL,
    CONSTRAINT PK_FSE_Project PRIMARY KEY (Project_ID)
);




CREATE TABLE FSE_User (
	[User_ID] [bigint] IDENTITY NOT NULL,
	[First_Name] [varchar](255) NOT NULL,
	[Last_Name] [varchar](255) NOT NULL,
	[Employee_ID] [varchar](50) NOT NULL,
	[Project_ID] [bigint] NULL,
	[Task_ID] [bigint] NULL,
    CONSTRAINT PK_FSE_User PRIMARY KEY ([User_ID])
);



