USE MASTER
GO
ALTER DATABASE [CSe21A_FSIII_Simulator2] SET SINGLE_USER WITH ROLLBACK IMMEDIATE;
DROP DATABASE [CSe21A_FSIII_Simulator2]
GO

CREATE DATABASE CSe21A_FSIII_Simulator2
GO
USE CSe21A_FSIII_Simulator2
GO
CREATE TABLE [Person]
(
    [Person_ID] INT IDENTITY  NOT NULL,
    [FirstName] NVARCHAR(200) NULL,
    [LastName]  NVARCHAR(200) NULL,
    [Role]      NVARCHAR(50)  NOT NULL,

    CONSTRAINT PK_P_ID PRIMARY KEY ([Person_ID])
    )

CREATE TABLE [Borger]
(
    [Borger_ID]        INT IDENTITY  NOT NULL,
    [FirstName]        NVARCHAR(200) NULL,
    [LastName]         NVARCHAR(200) NULL,
    [Age]              INT           NULL,
    [Template]         BIT           NULL,
    [Student_ID]       INT           NULL,

    CONSTRAINT PK_Borger_ID PRIMARY KEY ([Borger_ID]),
    CONSTRAINT FK_Borger_Student_ID FOREIGN KEY (Student_ID) REFERENCES Person([Person_ID]) ON DELETE CASCADE


    )

CREATE TABLE [Case]
(
    [Borger_ID]          INT            NOT NULL,
    [Case_ID]            INT IDENTITY   NOT NULL,
    [OverkategoriTitle]  NVARCHAR(50)   NULL,
    [UnderkategoriTitle] NVARCHAR(50)   NULL,
    [Henvisning]         NVARCHAR(100)  NULL,
    [Description]        NVARCHAR(4000) NULL,
    [Aasagsfritekst]     NVARCHAR(4000) NULL,
    [Aasagsdiagnose]     NVARCHAR(4000) NULL,
    [Aasagstilstand]     NVARCHAR(4000) NULL,
    [Borgerensonsker]    NVARCHAR(4000) NULL,
    [SagsAnsvarlig]      NVARCHAR(200)  NULL,
    [Bevilling]          BIT            NULL,
    [Bevillings_Tekst]   NVARCHAR(4000) NULL,
    [Plan]               NVARCHAR(4000) NULL,
    [Opfoelgnings_Tag]   NVARCHAR(50)   NULL,

    CONSTRAINT PK_Case_ID PRIMARY KEY ([Case_ID], [Borger_ID]),
    CONSTRAINT FK_Borger_ID FOREIGN KEY (Borger_ID) REFERENCES Borger ([Borger_ID]) ON DELETE CASCADE,
    )

CREATE TABLE [Case_Dokumentation]
(
    [Case_ID]               int            NOT NULL,
    [Borger_ID]             int            NOT NULL,
    [Case_Dokumentation_ID] int IDENTITY   NOT NULL,
    [Title]                 NVARCHAR(200)  NOT NULL,
    [Timestamp]DATETIME2                   NOT NULL,
    [Text]                  NVARCHAR(4000) NULL,

    CONSTRAINT FK_Borger_IDCD FOREIGN KEY (Case_ID, [Borger_ID]) REFERENCES [Case] ([Case_ID], Borger_ID) ON DELETE CASCADE,
    )

CREATE TABLE [General_Information]
(
    [GI_ID] int IDENTITY,
    [Title] NVARCHAR(200),

    CONSTRAINT PK_GI_ID PRIMARY KEY ([GI_ID]),
    )

CREATE TABLE [GI_Assessment](
    [Citizen_ID] int NOT NULL,
    [FK_GI_ID] int NOT NULL,
    [Description] NVARCHAR (4000),

    CONSTRAINT PK_GI_Assessment PRIMARY KEY ([Citizen_ID], [FK_GI_ID]),
    CONSTRAINT FK_Citizen_ID FOREIGN KEY ([Citizen_ID]) REFERENCES [Borger] ([Borger_ID]) ON DELETE CASCADE,
    )


CREATE TABLE [HS_Overkategori]
(
    [HS_Overkategori_ID]    INT IDENTITY NOT NULL,
    [HS_Overkategori_Titel] NVARCHAR(200),


    CONSTRAINT PK_HSID PRIMARY KEY (HS_Overkategori_ID),

    )

CREATE TABLE [HS_Underkategori]
(
    [HS_Underkategori_ID]    INT IDENTITY  NOT NULL,
    [HS_Underkategori_Titel] NVARCHAR(200) NOT NULL,
    [HS_OK_ID]               INT           NOT NULL,

    CONSTRAINT PK_HS_IDUK PRIMARY KEY ([HS_Underkategori_ID]),
    CONSTRAINT FK_HS_OK_ID FOREIGN KEY (HS_OK_ID) REFERENCES HS_Overkategori (HS_Overkategori_ID)
    )


CREATE TABLE [H_Tilstandsvurdering]
(
    [HS_Borger_ID]       INT           NOT NULL,
    [HS_UK_ID]           INT           NOT NULL,
    [Tilstand]           VARCHAR(200)  NULL,
    [Vurdering]          VARCHAR(4000) NULL,
    [Aarsag]             VARCHAR(4000) NULL,
    [Faglig_Notat]       VARCHAR(4000) NULL,
    [Forventet_Tilstand] VARCHAR(200)  NULL,

    CONSTRAINT PK_H_Tilstands_ID PRIMARY KEY (HS_UK_ID, HS_Borger_ID),
    CONSTRAINT FK_HS_IDHSV FOREIGN KEY (HS_Borger_ID) REFERENCES Borger ([Borger_ID]) ON DELETE CASCADE,
    CONSTRAINT FK_HS_UK_IDHSV FOREIGN KEY (HS_UK_ID) REFERENCES HS_Underkategori ([HS_Underkategori_ID])
    )


CREATE TABLE [FC_Category]
(
    [FC_C_ID]    INT IDENTITY  NOT NULL,
    [FC_C_Title] NVARCHAR(200) NULL,

    CONSTRAINT PK_FCID PRIMARY KEY ([FC_C_ID]),
    )

CREATE TABLE [FC_Subcategory]
(
    [FC_SC_ID]    INT IDENTITY  NOT NULL,
    [FC_SC_Title] NVARCHAR(200) NULL,
    [FC_C_ID]               INT           NOT NULL,

    CONSTRAINT PK_FC_SC_ID PRIMARY KEY ([FC_SC_ID]),
    CONSTRAINT FK_FS_OK_ID FOREIGN KEY (FC_C_ID) REFERENCES FC_Category ([FC_C_ID])

    )

CREATE TABLE [FC_Assessments](
                                 FC_A_ID INT IDENTITY NOT NULL,
                                 FC_A_Title NVARCHAR (200),

    CONSTRAINT PK_FC_A_ID PRIMARY KEY ([FC_A_ID])
    )


CREATE TABLE [FC_Assessment]
(

    [FC_S_ID] INT NOT NULL,
    [FC_A_ID] INT NOT NULL,
    [Citizen_ID] INT NOT NULL,
    [Description] NVARCHAR (4000),

    CONSTRAINT [PK_FC_Assessment_ID] PRIMARY KEY (FC_S_ID, FC_A_ID, Citizen_ID),
    CONSTRAINT [FK_FC_S_ID] FOREIGN KEY ([FC_S_ID]) REFERENCES FC_Subcategory ([FC_SC_ID]),
    CONSTRAINT [FC_A_ID] FOREIGN KEY (FC_A_ID) REFERENCES FC_Assessments ([FC_A_ID]),
    CONSTRAINT [FK_A_Citizen_ID] FOREIGN KEY (Citizen_ID) REFERENCES Borger ([Borger_ID]) ON DELETE CASCADE
    )


CREATE TABLE [Class]
(
    [Class_ID] INT IDENTITY,
    [Name]     NVARCHAR(200) NULL,

    CONSTRAINT PK_Class_ID PRIMARY KEY ([Class_ID])
    )

CREATE TABLE [ClassTeachers]
(
    [Teacher_ID] INT NOT NULL,
    [Class_ID]   INT NOT NULL,

     CONSTRAINT PK_Class_Teacher_ID PRIMARY KEY (Teacher_ID, Class_ID),
    CONSTRAINT FK_Teacher_ID FOREIGN KEY (Teacher_ID) REFERENCES Person ([Person_ID]) ON DELETE CASCADE,
    CONSTRAINT FK_Class_Teacher_ID FOREIGN KEY (Class_ID) REFERENCES Class ([Class_ID]) ON DELETE CASCADE
    )


CREATE TABLE [ClassStudents]
(
    [Student_ID] INT NOT NULL,
    [Class_ID]   INT NOT NULL,

     CONSTRAINT PK_Class_Student_ID PRIMARY KEY (Student_ID, Class_ID),
    CONSTRAINT FK_Student_ID FOREIGN KEY (Student_ID) REFERENCES Person ([Person_ID]) ON DELETE CASCADE,
    CONSTRAINT FK_Class_Student_ID FOREIGN KEY (Class_ID) REFERENCES Class ([Class_ID]) ON DELETE CASCADE
    )

CREATE TABLE [Credentials]
(
    [Person_ID] INT           NOT NULL,
    [UserName]  NVARCHAR(100) NOT NULL,
    [Password]  NVARCHAR(500)  NOT NULL,

    CONSTRAINT PK_ID PRIMARY KEY ([Person_ID]),
    CONSTRAINT FK_P_ID FOREIGN KEY ([Person_ID]) REFERENCES Person ([Person_ID]) ON DELETE CASCADE
    )