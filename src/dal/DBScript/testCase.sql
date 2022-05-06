CREATE TABLE [Case]
(
    [Borger_ID] INT NOT NULL,
    [ID] INT IDENTITY NOT NULL,
    [OverkategoriTitle] NVARCHAR(50) NULL,
    [UnderkategoriTitle] NVARCHAR(50) NULL,
    [Henvisning] NVARCHAR(100) NULL,
    [Description] NVARCHAR(MAX) NULL,
    [Aasagsfritekst] NVARCHAR(MAX) NULL,
    [Aasagsdiagnose] NVARCHAR(250) NULL,
    [Aasagstilstand] NVARCHAR(250) NULL,
    [Borgerensonsker] NVARCHAR(250) NULL,
    [Bevilling] BIT NULL,
    [Bevillings_Tekst] NVARCHAR (350) NULL,
    [Plan] NVARCHAR(150) NULL,
    [Opfoelgnings_Tag] NVARCHAR(30) NULL,

    CONSTRAINT PK_Case_ID PRIMARY KEY (ID,Borger_ID),
    CONSTRAINT FK_Borger_ID FOREIGN KEY (Borger_ID) REFERENCES Borger(ID) ON DELETE CASCADE ,
)