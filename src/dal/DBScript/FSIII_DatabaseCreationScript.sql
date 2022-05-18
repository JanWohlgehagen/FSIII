USE MASTER
GO
ALTER DATABASE [CSe21A_FSIII_Simulator] SET SINGLE_USER WITH ROLLBACK IMMEDIATE;
DROP DATABASE [CSe21A_FSIII_Simulator]
GO

CREATE DATABASE CSe21A_FSIII_Simulator
GO
USE CSe21A_FSIII_Simulator
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
    [Student_ID]       INT           Null,

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

CREATE TABLE [Generelle_Oplysninger]
(
    [Borger_ID]           int            NOT NULL,
    [Mestring]            NVARCHAR(4000) NULL,
    [Motivation]          NVARCHAR(4000) NULL,
    [Ressourcer]          NVARCHAR(4000) NULL,
    [Roller]              NVARCHAR(4000) NULL,
    [Vaner]               NVARCHAR(4000) NULL,
    [Uddannelse]          NVARCHAR(4000) NULL,
    [Livshistorie]        NVARCHAR(4000) NULL,
    [Netvaerk]            NVARCHAR(4000) NULL,
    [Helbredsoplysninger] NVARCHAR(4000) NULL,
    [hjaelpemidler]       NVARCHAR(4000) NULL,
    [Boligens_Indretning] NVARCHAR(4000) NULL,

    CONSTRAINT PK_Customer_IDGO PRIMARY KEY ([Borger_ID]),
    CONSTRAINT FK_Borger_IDGO FOREIGN KEY ([Borger_ID]) REFERENCES Borger ([Borger_ID]) ON DELETE CASCADE

    )


CREATE TABLE [Helbredstilstand]
(
    [Helbredstislstand_ID]INT IDENTITY NOT NULL,
    [Borger_ID]           INT          NOT NULL,

     CONSTRAINT PK_HS_IDHS PRIMARY KEY (Helbredstislstand_ID, [Borger_ID]),
    CONSTRAINT FK_Borger_IDHS FOREIGN KEY ([Borger_ID]) REFERENCES Borger ([Borger_ID]) ON DELETE CASCADE
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
    [HS_Borger_ID]       INT            NOT NULL,
    [HS_UK_ID]           INT            NOT NULL,
    [Tilstand]           NVARCHAR(200)  NULL,
    [Vurdering]          NVARCHAR(4000) NULL,
    [Aarsag]             NVARCHAR(4000) NULL,
    [Faglig_Notat]       NVARCHAR(4000) NULL,
    [Forventet_Tilstand] NVARCHAR(200)  NULL,
    [Observation]        NVARCHAR(4000) NULL,
    [ObservationTime]    DATETIME      NULL,

    CONSTRAINT PK_H_Tilstands_ID PRIMARY KEY (HS_UK_ID, HS_Borger_ID),
    CONSTRAINT FK_HS_IDHSV FOREIGN KEY (HS_Borger_ID) REFERENCES Borger ([Borger_ID]) ON DELETE CASCADE,
    CONSTRAINT FK_HS_UK_IDHSV FOREIGN KEY (HS_UK_ID) REFERENCES HS_Underkategori ([HS_Underkategori_ID])
    )

CREATE TABLE [Funktionstilstand]
(
    [Funktionstilstand_ID] INT IDENTITY NOT NULL,
    [Borger_ID]            INT          NOT NULL,

     CONSTRAINT PK_FS_IDFS PRIMARY KEY (Funktionstilstand_ID, Borger_ID),
    CONSTRAINT FK_Borger_IDFS FOREIGN KEY (Borger_ID) REFERENCES Borger ([Borger_ID]) ON DELETE CASCADE
    )

CREATE TABLE [FS_Overkategori]
(
    [FS_Overkategori_ID]    INT IDENTITY  NOT NULL,
    [FS_Overkategori_Titel] NVARCHAR(200) NULL,

    CONSTRAINT PK_FSID PRIMARY KEY ([FS_Overkategori_ID]),
    )

CREATE TABLE [FS_Underkategori]
(
    [FS_Underkategori_ID]    INT IDENTITY  NOT NULL,
    [FS_Underkategori_Title] NVARCHAR(200) NULL,
    [FS_OK_ID]               INT           NOT NULL,

    CONSTRAINT PK_FS_UK_ID PRIMARY KEY ([FS_Underkategori_ID]),
    CONSTRAINT FK_FS_OK_ID FOREIGN KEY (FS_OK_ID) REFERENCES FS_Overkategori ([FS_Overkategori_ID])

    )


CREATE TABLE [F_Tilstandsvurdering]
(

    [FS_Borger_ID]       INT             NOT NULL,
    [FS_UK_ID]           INT             NOT NULL,
    [Udfoerelse]         NVARCHAR(4000)  NULL,
    [Betydning]          NVARCHAR(4000)  NULL,
    [Borger_Maal]        NVARCHAR(4000)  NULL,
    [Niveau]             INT             DEFAULT -1,
    [Vurdering]          NVARCHAR(4000)  NULL,
    [Aarsag]             NVARCHAR(4000)  NULL,
    [Faglig_Notat]       NVARCHAR(4000)  NULL,
    [Forventet_Tilstand] INT             NULL,
    [Observation]        NVARCHAR(4000)  NULL,
    [ObservationTime]    DATETIME       NULL,
    [Opfoelgning]        NVARCHAR(4000)  NULL,


    CONSTRAINT PK_F_Tilstands_ID PRIMARY KEY (FS_UK_ID, FS_Borger_ID),
    CONSTRAINT FK_HS_IDFSV FOREIGN KEY (FS_Borger_ID) REFERENCES Borger ([Borger_ID]) ON DELETE CASCADE,
    CONSTRAINT FK_UK_IDFSV FOREIGN KEY (FS_UK_ID) REFERENCES FS_Underkategori ([FS_Underkategori_ID])
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
    [Person_ID] INT            NOT NULL,
    [UserName]  NVARCHAR(100)  NOT NULL,
    [Password]  NVARCHAR(500)  NOT NULL,

    CONSTRAINT PK_ID PRIMARY KEY ([Person_ID]),
    CONSTRAINT FK_P_ID FOREIGN KEY ([Person_ID]) REFERENCES Person ([Person_ID]) ON DELETE CASCADE
    )