USE MASTER
GO
DROP DATABASE CSe21A_FSIII_Simulator
GO
CREATE DATABASE CSe21A_FSIII_Simulator
GO
USE CSe21A_FSIII_Simulator
GO

CREATE TABLE [Borger]
(
    [ID] int IDENTITY,
    [FistName] NVARCHAR(50) NULL,
    [LastName] NVARCHAR(50) NULL,

    CONSTRAINT PK_Borger_ID PRIMARY KEY (ID)

)

CREATE TABLE [Case]
(
    [Borger_ID] int,
    [ID] int IDENTITY,
    [Title] NVARCHAR(50) NULL,
    [Description] NVARCHAR(250) NULL,
    [Bevilling] BIT NULL,
    [Plan] NVARCHAR(150) NULL,
    [Opfoelgnings_Tag] NVARCHAR(30) NULL,

    CONSTRAINT Case_ID PRIMARY KEY (ID, Borger_ID),
    CONSTRAINT FK_Borger_ID FOREIGN KEY (Borger_ID) REFERENCES Borger(ID),
)

CREATE TABLE [Case_Dokumentation]
(
    [Case_ID] int,
    [Borger_ID] int,
    [ID] int IDENTITY,
    [Timestamp]DATETIME2 NOT NULL,
    [Text] NVARCHAR(500) NULL,

    CONSTRAINT CaseID PRIMARY KEY (Case_ID, ID,Borger_ID),
    CONSTRAINT Borger_ID FOREIGN KEY (Borger_ID) REFERENCES [Case](Borger_ID),
    CONSTRAINT Case_ID FOREIGN KEY (Case_ID) REFERENCES [Case](ID)
)

CREATE TABLE [Generelle_Oplysninger]
(
    [Borger_ID] int,
    [Mestring] NVARCHAR(200) NULL,
    [Motivation] NVARCHAR(200) NULL,
    [Ressourcer] NVARCHAR(200) NULL,
    [Roller] NVARCHAR(200) NULL,
    [Vaner] NVARCHAR(200) NULL,
    [Uddannelse] NVARCHAR(200) NULL,
    [Livshistorie] NVARCHAR(200) NULL,
    [Netvaerk] NVARCHAR(200) NULL,
    [Helbredsoplysninger] NVARCHAR(200) NULL,
    [hjaelpemidler] NVARCHAR(200) NULL,
    [Boligens_Indretning] NVARCHAR(200) NULL,

    CONSTRAINT PK_Customer_ID PRIMARY KEY (Borger_ID),
    CONSTRAINT FK_Borger_ID FOREIGN KEY (Borger_ID) REFERENCES Borger(ID)

)


CREATE TABLE [Helbredstilstand]
(
    [ID] INT IDENTITY,
    [Borger_ID] NVARCHAR(30) NULL,
    
    CONSTRAINT PK_HS_ID PRIMARY KEY (ID, Borger_ID),
    CONSTRAINT FK_Borger_ID FOREIGN KEY (Borger_ID) REFERENCES Borger(ID)
)

CREATE TABLE [HS_Underkategori]
(
    [ID] INT IDENTITY ,
    [Titel] NVARCHAR(100) NOT NULL,
    [HS_OK_ID] INT,

    CONSTRAINT PK_HS_ID PRIMARY KEY (ID, HS_OK_ID),
    CONSTRAINT FK_HS_OK_ID FOREIGN KEY (HS_OK_ID) REFERENCES HS_Overkategori(ID)
)

CREATE TABLE [HS_Overkategori]
(
    [ID] INT IDENTITY ,
    [Titel] NVARCHAR(100),


    CONSTRAINT PK_ID PRIMARY KEY (ID),

)

CREATE TABLE [H_Tilstandsvurdering]
(
    [HS_ID] INT,
    [HS_UK_ID] INT,
    [Udfald] NVARCHAR(50) NULL,
    [Tilstand] VARCHAR(200) NULL,
    [Vurdering] VARCHAR(200) NULL,
    [Aarsag] VARCHAR(200) NULL,
    [Faglig_Notat] VARCHAR(200) NULL,

    CONSTRAINT PK_H_Tilstands_ID PRIMARY KEY (HS_ID,HS_UK_ID),
    CONSTRAINT FK_HS_ID FOREIGN KEY (HS_ID) REFERENCES Helbredstilstand(Borger_ID),
    CONSTRAINT FK_HS_UK_ID FOREIGN KEY (HS_UK_ID) REFERENCES HS_Underkategori(ID)
)

CREATE TABLE [Funktionstilstand]
(
    [ID] INT IDENTITY,
    [Borger_ID] NVARCHAR(30) NULL,

    CONSTRAINT PK_FS_ID PRIMARY KEY (ID, Borger_ID),
    CONSTRAINT FK_Borger_ID FOREIGN KEY (Borger_ID) REFERENCES Borger(ID)
)

CREATE TABLE [FS_Underkategori]
(
    [ID] INT IDENTITY ,
    [Titel] NVARCHAR(100) NOT NULL,
    [FS_OK_ID] INT,

    CONSTRAINT PK_FS_UK_ID PRIMARY KEY (ID, FS_OK_ID),
    CONSTRAINT FK_FS_OK_ID FOREIGN KEY (FS_OK_ID) REFERENCES FS_Overkategori(ID)

)

CREATE TABLE [FS_Overkategori]
(
    [ID] INT IDENTITY ,
    [Titel] NVARCHAR(100),

    CONSTRAINT PK_ID PRIMARY KEY (ID),
)

CREATE TABLE [F_Tilstandsvurdering]
(
    [FS_ID] INT,
    [FS_UK_ID] INT,
    [Udfoerelse] VARCHAR(200) NULL,
    [Betydning] VARCHAR(200) NULL,
    [Borger_Maal] VARCHAR(250) NULL,
    [Niveau] INT NULL,
    [Vurdering] VARCHAR(200) NULL,
    [Aarsag] VARCHAR(200) NULL,
    [Faglig_Notat] VARCHAR(200) NULL,

    CONSTRAINT PK_H_Tilstands_ID PRIMARY KEY (FS_ID,FS_UK_ID),
    CONSTRAINT FK_HS_ID FOREIGN KEY (FS_ID) REFERENCES Funktionstilstand(Borger_ID),
    CONSTRAINT FK_UK_ID FOREIGN KEY (FS_UK_ID) REFERENCES FS_Underkategori(ID)
)

INSERT INTO FS_Overkategori (Titel) VALUES ('Egenomsorg')
INSERT INTO FS_Overkategori (Titel) VALUES ('Praktiske opgaver')
INSERT INTO FS_Overkategori (Titel) VALUES ('Mobilitet')
INSERT INTO FS_Overkategori (Titel) VALUES ('Mentale funktioner')
INSERT INTO FS_Overkategori (Titel) VALUES ('Samfundsliv')

INSERT INTO FS_Underkategori (Titel, FS_OK_ID) VALUES ('Vaske sig',1)
INSERT INTO FS_Underkategori (Titel, FS_OK_ID) VALUES (N'Gå på toilet', 1)
INSERT INTO FS_Underkategori (Titel, FS_OK_ID) VALUES ('Kropspleje',1)
INSERT INTO FS_Underkategori (Titel, FS_OK_ID) VALUES (N'Af- og påklædning', 1)
INSERT INTO FS_Underkategori (Titel, FS_OK_ID) VALUES ('Spise',1)
INSERT INTO FS_Underkategori (Titel, FS_OK_ID) VALUES ('Drikke',1)
INSERT INTO FS_Underkategori (Titel, FS_OK_ID) VALUES ('Varetage egen sundhed',1)
INSERT INTO FS_Underkategori (Titel, FS_OK_ID) VALUES (N'Fødeindtagelse', 1)

INSERT INTO FS_Underkategori (Titel, FS_OK_ID) VALUES (N'Udføre daglige rutiner', 2)
INSERT INTO FS_Underkategori (Titel, FS_OK_ID) VALUES ('Skaffe sig varer og tjenesteydelser', 2)
INSERT INTO FS_Underkategori (Titel, FS_OK_ID) VALUES ('Lave mad', 2)
INSERT INTO FS_Underkategori (Titel, FS_OK_ID) VALUES ('Lave husligt arbejde', 2)

INSERT INTO FS_Underkategori (Titel, FS_OK_ID) VALUES (N'Ændre kropsstilling', 3)
INSERT INTO FS_Underkategori (Titel, FS_OK_ID) VALUES ('Forflytte sig', 3)
INSERT INTO FS_Underkategori (Titel, FS_OK_ID) VALUES (N'Løfte og bære', 3)
INSERT INTO FS_Underkategori (Titel, FS_OK_ID) VALUES (N'Gå', 3)
INSERT INTO FS_Underkategori (Titel, FS_OK_ID) VALUES (N'Bevæge sig omkring', 3)
INSERT INTO FS_Underkategori (Titel, FS_OK_ID) VALUES (N'Færden i forskellige omgivelser', 3)
INSERT INTO FS_Underkategori (Titel, FS_OK_ID) VALUES ('Bruge transportmidler', 3)
INSERT INTO FS_Underkategori (Titel, FS_OK_ID) VALUES ('Udholdenhed', 3)
INSERT INTO FS_Underkategori (Titel, FS_OK_ID) VALUES ('Muskelstyrke', 3)

INSERT INTO FS_Underkategori (Titel, FS_OK_ID) VALUES (N'Tilegne sig færdigheder', 4)
INSERT INTO FS_Underkategori (Titel, FS_OK_ID) VALUES (N'Problemløsning', 4)
INSERT INTO FS_Underkategori (Titel, FS_OK_ID) VALUES ('Anvende kommunikationsudstyr og teknikker', 4)
INSERT INTO FS_Underkategori (Titel, FS_OK_ID) VALUES ('Orienteringsevne', 4)
INSERT INTO FS_Underkategori (Titel, FS_OK_ID) VALUES ('Energi og handlekraft', 4)
INSERT INTO FS_Underkategori (Titel, FS_OK_ID) VALUES ('Hukommelse', 4)
INSERT INTO FS_Underkategori (Titel, FS_OK_ID) VALUES (N'Følelsesfunktioner', 4)
INSERT INTO FS_Underkategori (Titel, FS_OK_ID) VALUES ('Overordnede kognitive funktioner', 4)

INSERT INTO FS_Underkategori (Titel, FS_OK_ID) VALUES (N'Have lønnet beskæftigelse', 5)


INSERT INTO HS_Overkategori (Titel) VALUES ('Funktionsniveau')
INSERT INTO HS_Overkategori (Titel) VALUES (N'Bevægeapparat')
INSERT INTO HS_Overkategori (Titel) VALUES (N'Ernæring')
INSERT INTO HS_Overkategori (Titel) VALUES ('Hud og slimhinder')
INSERT INTO HS_Overkategori (Titel) VALUES ('Kommunikation')
INSERT INTO HS_Overkategori (Titel) VALUES ('Psykosociale forhold')
INSERT INTO HS_Overkategori (Titel) VALUES ('Respiration og cirkulation')
INSERT INTO HS_Overkategori (Titel) VALUES ('Seksualitet')
INSERT INTO HS_Overkategori (Titel) VALUES ('Smerter og sanseindtryk')
INSERT INTO HS_Overkategori (Titel) VALUES (N'Søvn og hvile')
INSERT INTO HS_Overkategori (Titel) VALUES ('Viden og udvikling')
INSERT INTO HS_Overkategori (Titel) VALUES ('Udskillelse af affaldsstoffer')


INSERT INTO HS_Underkategori (Titel,HS_OK_ID) VALUES ('Problemer med personlig pleje', 1)
INSERT INTO HS_Underkategori (Titel,HS_OK_ID) VALUES ('Problemer med daglige aktiviteter', 1)

INSERT INTO HS_Underkategori (Titel,HS_OK_ID) VALUES (N'Problemer med mobilitet og bevægelse', 2)

INSERT INTO HS_Underkategori (Titel,HS_OK_ID) VALUES (N'Problemer med væskeindtag', 3)
INSERT INTO HS_Underkategori (Titel,HS_OK_ID) VALUES (N'Problemer med fødeindtag', 3)
INSERT INTO HS_Underkategori (Titel,HS_OK_ID) VALUES (N'Uhensigtsmæssig vægtændring', 3)
INSERT INTO HS_Underkategori (Titel,HS_OK_ID) VALUES (N'Problemer med overvægt', 3)
INSERT INTO HS_Underkategori (Titel,HS_OK_ID) VALUES (N'Problemer med undervægt', 3)

INSERT INTO HS_Underkategori (Titel,HS_OK_ID) VALUES (N'Problemer med kirugisk sår', 4)
INSERT INTO HS_Underkategori (Titel,HS_OK_ID) VALUES (N'Problemer med diabetisk sår', 4)
INSERT INTO HS_Underkategori (Titel,HS_OK_ID) VALUES (N'Problemer med cancersår', 4)
INSERT INTO HS_Underkategori (Titel,HS_OK_ID) VALUES (N'Problemer med tryksår', 4)
INSERT INTO HS_Underkategori (Titel,HS_OK_ID) VALUES (N'Problemer med arterielt sår', 4)
INSERT INTO HS_Underkategori (Titel,HS_OK_ID) VALUES (N'Problemer med venøst sår', 4)
INSERT INTO HS_Underkategori (Titel,HS_OK_ID) VALUES (N'Problemer med blandingssår', 4)
INSERT INTO HS_Underkategori (Titel,HS_OK_ID) VALUES (N'Problemer med traumesår', 4)
INSERT INTO HS_Underkategori (Titel,HS_OK_ID) VALUES ('Andre problemer med hud og slimhinder', 4)

INSERT INTO HS_Underkategori (Titel,HS_OK_ID) VALUES ('Problemer med kommunikation', 5)

INSERT INTO HS_Underkategori (Titel,HS_OK_ID) VALUES (N'Problemer med socialt samvær', 6)
INSERT INTO HS_Underkategori (Titel,HS_OK_ID) VALUES ('Emotionelle problemer', 6)
INSERT INTO HS_Underkategori (Titel,HS_OK_ID) VALUES ('Problemer med misbrug', 6)
INSERT INTO HS_Underkategori (Titel,HS_OK_ID) VALUES ('Mentale problemer', 6)

INSERT INTO HS_Underkategori (Titel,HS_OK_ID) VALUES ('Respirationsproblemer', 7)
INSERT INTO HS_Underkategori (Titel,HS_OK_ID) VALUES ('cirkulationsproblemer', 7)

INSERT INTO HS_Underkategori (Titel,HS_OK_ID) VALUES ('Problemer med seksualitet', 8)

INSERT INTO HS_Underkategori (Titel,HS_OK_ID) VALUES ('Akutte smerter', 9)
INSERT INTO HS_Underkategori (Titel,HS_OK_ID) VALUES ('Periodevise smerter', 9)
INSERT INTO HS_Underkategori (Titel,HS_OK_ID) VALUES ('Kroniske smerter', 9)
INSERT INTO HS_Underkategori (Titel,HS_OK_ID) VALUES ('Problemer med synssans', 9)
INSERT INTO HS_Underkategori (Titel,HS_OK_ID) VALUES ('Problemer med lugtesans', 9)
INSERT INTO HS_Underkategori (Titel,HS_OK_ID) VALUES (N'Problemer med hørelse', 9)
INSERT INTO HS_Underkategori (Titel,HS_OK_ID) VALUES ('Problemer med smagssans', 9)
INSERT INTO HS_Underkategori (Titel,HS_OK_ID) VALUES (N'Problemer med følesans', 9)

INSERT INTO HS_Underkategori (Titel,HS_OK_ID) VALUES (N'Døgnrytmeproblemer', 10)
INSERT INTO HS_Underkategori (Titel,HS_OK_ID) VALUES (N'Søvnproblemer', 10)

INSERT INTO HS_Underkategori (Titel,HS_OK_ID) VALUES ('Problemer med hukommelse', 11)
INSERT INTO HS_Underkategori (Titel,HS_OK_ID) VALUES (N'Problemer med indsigt i behandlingsformål', 11)
INSERT INTO HS_Underkategori (Titel,HS_OK_ID) VALUES ('Problemer med sygdomsindsigt', 11)
INSERT INTO HS_Underkategori (Titel,HS_OK_ID) VALUES ('Kognitiveproblemer', 11)

INSERT INTO HS_Underkategori (Titel,HS_OK_ID) VALUES ('Problemer med vandladning', 12)
INSERT INTO HS_Underkategori (Titel,HS_OK_ID) VALUES ('Problemer med urininkontinens', 12)
INSERT INTO HS_Underkategori (Titel,HS_OK_ID) VALUES (N'Problemer med afføringskontinens', 12)
INSERT INTO HS_Underkategori (Titel,HS_OK_ID) VALUES ('Problemer med mave og tarm', 12)
