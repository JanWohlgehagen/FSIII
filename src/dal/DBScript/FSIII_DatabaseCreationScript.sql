USE MASTER
GO
ALTER DATABASE [CSe21A_FSIII_Simulator] SET SINGLE_USER WITH ROLLBACK IMMEDIATE;
DROP DATABASE [CSe21A_FSIII_Simulator]
GO

CREATE DATABASE CSe21A_FSIII_Simulator
GO
USE CSe21A_FSIII_Simulator
GO

CREATE TABLE [Borger]
(
    Borger_ID INT IDENTITY NOT NULL,
    [FirstName] NVARCHAR(50) NULL,
    [LastName] NVARCHAR(50) NULL,
    [Age] INT NULL,
    [Template] BIT NULL,
    [Helhedsvurdering] NVARCHAR (MAX) NULL,

    CONSTRAINT PK_Borger_ID PRIMARY KEY (Borger_ID)

)

CREATE TABLE [Case]
(
    [Borger_ID] INT NOT NULL,
    Case_ID INT IDENTITY NOT NULL,
    [OverkategoriTitle] NVARCHAR(50) NULL,
    [UnderkategoriTitle] NVARCHAR(50) NULL,
    [Henvisning] NVARCHAR(100) NULL,
    [Description] NVARCHAR(MAX) NULL,
    [Aasagsfritekst] NVARCHAR(MAX) NULL,
    [Aasagsdiagnose] NVARCHAR(250) NULL,
    [Aasagstilstand] NVARCHAR(250) NULL,
    [Borgerensonsker] NVARCHAR(250) NULL,
    [SagsAnsvarlig] NVARCHAR(100) NULL,
    [Bevilling] BIT NULL,
    [Bevillings_Tekst] NVARCHAR (350) NULL,
    [Plan] NVARCHAR(150) NULL,
    [Opfoelgnings_Tag] NVARCHAR(30) NULL,

    CONSTRAINT PK_Case_ID PRIMARY KEY (Case_ID,Borger_ID),
    CONSTRAINT FK_Borger_ID FOREIGN KEY (Borger_ID) REFERENCES Borger(Borger_ID) ON DELETE CASCADE ,
)

CREATE TABLE [Case_Dokumentation]
(
    [Case_ID] int NOT NULL,
    [Borger_ID] int NOT NULL,
    [Case_Dokumentation_ID] int IDENTITY NOT NULL,
    [Title] NVARCHAR(50) NOT NULL,
    [Timestamp]DATETIME2 NOT NULL,
    [Text] NVARCHAR(500) NULL,

    CONSTRAINT FK_Borger_IDCD FOREIGN KEY (Case_ID, Borger_ID) REFERENCES [Case](Case_ID,Borger_ID) ON DELETE CASCADE,

)

CREATE TABLE [Generelle_Oplysninger]
(
    [Borger_ID] int NOT NULL,
    [Mestring] NVARCHAR(200) NULL,
    [Motivation] NVARCHAR(200) NULL,
    [Ressourcer] NVARCHAR(200) NULL,
    [Roller] NVARCHAR(200) NULL,
    [Vaner] NVARCHAR(200) NULL,
    [Uddannelse] NVARCHAR(200) NULL,
    [Livshistorie] NVARCHAR(200) NULL,
    [Netvaerk] NVARCHAR(200) NULL,
    [Helbredsoplysninger] NVARCHAR(200) NULL,
    [Helhedsvurdering] NVARCHAR(250) NULL,
    [hjaelpemidler] NVARCHAR(200) NULL,
    [Boligens_Indretning] NVARCHAR(200) NULL,

    CONSTRAINT PK_Customer_IDGO PRIMARY KEY (Borger_ID),
    CONSTRAINT FK_Borger_IDGO FOREIGN KEY (Borger_ID) REFERENCES Borger(Borger_ID) ON DELETE CASCADE

)


CREATE TABLE [Helbredstilstand]
(
    [Helbredstislstand_ID]INT IDENTITY NOT NULL,
    [Borger_ID] INT NOT NULL,

    CONSTRAINT PK_HS_IDHS PRIMARY KEY (Helbredstislstand_ID, Borger_ID),
    CONSTRAINT FK_Borger_IDHS FOREIGN KEY (Borger_ID) REFERENCES Borger(Borger_ID) ON DELETE CASCADE
)

CREATE TABLE [HS_Overkategori]
(
    HS_Overkategori_ID INT IDENTITY NOT NULL,
    [Titel] NVARCHAR(100),


    CONSTRAINT PK_HSID PRIMARY KEY (HS_Overkategori_ID),

)

CREATE TABLE [HS_Underkategori]
(
    [HS_Underkategori_ID] INT IDENTITY NOT NULL ,
    [Titel] NVARCHAR(100) NOT NULL,
    [HS_OK_ID] INT NOT NULL,

    CONSTRAINT PK_HS_IDUK PRIMARY KEY ([HS_Underkategori_ID]),
    CONSTRAINT FK_HS_OK_ID FOREIGN KEY (HS_OK_ID) REFERENCES HS_Overkategori(HS_Overkategori_ID)
)



CREATE TABLE [H_Tilstandsvurdering]
(
    [HS_Borger_ID] INT NOT NULL,
    [HS_UK_ID] INT NOT NULL,

    [Tilstand] VARCHAR(200) NULL,
    [Vurdering] VARCHAR(200) NULL,
    [Aarsag] VARCHAR(200) NULL,
    [Faglig_Notat] VARCHAR(200) NULL,
    [Forventet_Tilstand] VARCHAR(200) NULL,

    CONSTRAINT PK_H_Tilstands_ID PRIMARY KEY (HS_UK_ID,HS_Borger_ID),
    CONSTRAINT FK_HS_IDHSV FOREIGN KEY (HS_Borger_ID) REFERENCES Borger(Borger_ID) ON DELETE CASCADE,
    CONSTRAINT FK_HS_UK_IDHSV FOREIGN KEY (HS_UK_ID) REFERENCES HS_Underkategori([HS_Underkategori_ID])
)

CREATE TABLE [Funktionstilstand]
(
    [Funktionstilstand_ID] INT IDENTITY NOT NULL,
    [Borger_ID] INT NOT NULL,

    CONSTRAINT PK_FS_IDFS PRIMARY KEY (Funktionstilstand_ID, Borger_ID),
    CONSTRAINT FK_Borger_IDFS FOREIGN KEY (Borger_ID) REFERENCES Borger(Borger_ID) ON DELETE CASCADE
)

CREATE TABLE [FS_Overkategori]
(
    [HS_Overkategori_ID] INT IDENTITY NOT NULL,
    [Titel] NVARCHAR(100),

    CONSTRAINT PK_FSID PRIMARY KEY ([HS_Overkategori_ID]),
)

CREATE TABLE [FS_Underkategori]
(
    [FS_Underkategori_ID] INT IDENTITY NOT NULL,
    [Titel] NVARCHAR(100) NOT NULL,
    [FS_OK_ID] INT NOT NULL,

    CONSTRAINT PK_FS_UK_ID PRIMARY KEY ([FS_Underkategori_ID]),
    CONSTRAINT FK_FS_OK_ID FOREIGN KEY (FS_OK_ID) REFERENCES FS_Overkategori([HS_Overkategori_ID])

)



CREATE TABLE [F_Tilstandsvurdering]
(
    [FS_Borger_ID] INT NOT NULL,
    [FS_UK_ID] INT NOT NULL,
    [Udfoerelse] VARCHAR(200) NULL,
    [Betydning] VARCHAR(200) NULL,
    [Borger_Maal] VARCHAR(250) NULL,
    [Niveau] INT NULL,
    [Vurdering] VARCHAR(200) NULL,
    [Aarsag] VARCHAR(200) NULL,
    [Faglig_Notat] VARCHAR(200) NULL,
    [Forventet_Tilstand] INT NULL,
    [Opfoelgning] NVARCHAR (200) NULL,

    CONSTRAINT PK_F_Tilstands_ID PRIMARY KEY (FS_UK_ID, FS_Borger_ID),
    CONSTRAINT FK_HS_IDFSV FOREIGN KEY (FS_Borger_ID) REFERENCES Borger(Borger_ID) ON DELETE CASCADE,
    CONSTRAINT FK_UK_IDFSV FOREIGN KEY (FS_UK_ID) REFERENCES FS_Underkategori([FS_Underkategori_ID])
)


CREATE TABLE [Class]
(
    [Class_ID] INT IDENTITY,
    [Name] NVARCHAR(200) NULL,

    CONSTRAINT PK_Class_ID PRIMARY KEY ([Class_ID])
)

CREATE TABLE[Person]
(
    [Person_ID] INT IDENTITY NOT NULL,
    [FirstName] NVARCHAR(80) NULL,
    [LastName] NVARCHAR(80) NULL,
    [Role] NVARCHAR(20) NOT NULL,

    CONSTRAINT PK_P_ID PRIMARY KEY ([Person_ID])
)

CREATE TABLE [Teacher]
(
    [Teacher_ID] INT NOT NULL,
    CONSTRAINT PK_Teacher_ID PRIMARY KEY ([Teacher_ID]),
    CONSTRAINT FK_T_ID FOREIGN KEY ([Teacher_ID]) REFERENCES Person([Person_ID])
)

CREATE TABLE [Classteachers]
(
    [Teacher_ID] INT NOT NULL,
    [Class_ID] INT NOT NULL,

    CONSTRAINT PK_Class_Teacher_ID PRIMARY KEY (Teacher_ID, Class_ID),
    CONSTRAINT FK_Teacher_ID FOREIGN KEY (Teacher_ID) REFERENCES Teacher([Teacher_ID]),
    CONSTRAINT FK_Class_ID FOREIGN KEY (Class_ID) REFERENCES Class([Class_ID])
)


CREATE TABLE [Student](
     [Student_ID] INT NOT NULL,
     [Class_ID] INT,

     CONSTRAINT Student_ID PRIMARY KEY ([Student_ID]),
     CONSTRAINT FK_S_ID FOREIGN KEY ([Student_ID]) REFERENCES Person([Person_ID]),
     CONSTRAINT FK_Student_Class_ID FOREIGN KEY (Class_ID) REFERENCES Class([Class_ID])

)

CREATE TABLE [Credentials]
(
    [Credentials_ID] INT NOT NULL ,
    [UserName] NVARCHAR(100) NOT NULL,
    [Password] NVARCHAR(100) NOT NULL,

    CONSTRAINT PK_ID PRIMARY KEY ([Credentials_ID]),
    CONSTRAINT FK_P_ID FOREIGN KEY ([Credentials_ID]) REFERENCES Person([Person_ID])
)

