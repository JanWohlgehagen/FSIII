USE MASTER
GO
USE CSe21A_FSIII_Simulator2
GO

INSERT INTO General_Information (Title) VALUES ('Mestring')
INSERT INTO General_Information (Title) VALUES ('Motivation')
INSERT INTO General_Information (Title) VALUES ('Ressourcer')
INSERT INTO General_Information (Title) VALUES ('Roller')
INSERT INTO General_Information (Title) VALUES ('Vaner')
INSERT INTO General_Information (Title) VALUES ('Uddannelse')
INSERT INTO General_Information (Title) VALUES ('Livshistorie')
INSERT INTO General_Information (Title) VALUES ('Netvaerk')
INSERT INTO General_Information (Title) VALUES ('hjaelpemidler')
INSERT INTO General_Information (Title) VALUES ('Helbredsoplysninger')
INSERT INTO General_Information (Title) VALUES ('Boligens_Indretning')


INSERT INTO FS_Overkategori (FS_Overkategori_Titel) VALUES ('Egenomsorg')
INSERT INTO FS_Overkategori (FS_Overkategori_Titel) VALUES ('Praktiske opgaver')
INSERT INTO FS_Overkategori (FS_Overkategori_Titel) VALUES ('Mobilitet')
INSERT INTO FS_Overkategori (FS_Overkategori_Titel) VALUES ('Mentale funktioner')
INSERT INTO FS_Overkategori (FS_Overkategori_Titel) VALUES ('Samfundsliv')

INSERT INTO FS_Underkategori (FS_Underkategori_Title, FS_OK_ID) VALUES ('Vaske sig',1)
INSERT INTO FS_Underkategori (FS_Underkategori_Title, FS_OK_ID) VALUES (N'Gå på toilet', 1)
INSERT INTO FS_Underkategori (FS_Underkategori_Title, FS_OK_ID) VALUES ('Kropspleje',1)
INSERT INTO FS_Underkategori (FS_Underkategori_Title, FS_OK_ID) VALUES (N'Af- og påklædning', 1)
INSERT INTO FS_Underkategori (FS_Underkategori_Title, FS_OK_ID) VALUES ('Spise',1)
INSERT INTO FS_Underkategori (FS_Underkategori_Title, FS_OK_ID) VALUES ('Drikke',1)
INSERT INTO FS_Underkategori (FS_Underkategori_Title, FS_OK_ID) VALUES ('Varetage egen sundhed',1)
INSERT INTO FS_Underkategori (FS_Underkategori_Title, FS_OK_ID) VALUES (N'Fødeindtagelse', 1)
INSERT INTO FS_Underkategori (FS_Underkategori_Title, FS_OK_ID) VALUES (N'Udføre daglige rutiner', 2)
INSERT INTO FS_Underkategori (FS_Underkategori_Title, FS_OK_ID) VALUES ('Skaffe sig varer og tjenesteydelser', 2)
INSERT INTO FS_Underkategori (FS_Underkategori_Title, FS_OK_ID) VALUES ('Lave mad', 2)
INSERT INTO FS_Underkategori (FS_Underkategori_Title, FS_OK_ID) VALUES ('Lave husligt arbejde', 2)

INSERT INTO FS_Underkategori (FS_Underkategori_Title, FS_OK_ID) VALUES (N'Ændre kropsstilling', 3)
INSERT INTO FS_Underkategori (FS_Underkategori_Title, FS_OK_ID) VALUES ('Forflytte sig', 3)
INSERT INTO FS_Underkategori (FS_Underkategori_Title, FS_OK_ID) VALUES (N'Løfte og bære', 3)
INSERT INTO FS_Underkategori (FS_Underkategori_Title, FS_OK_ID) VALUES (N'Gå', 3)
INSERT INTO FS_Underkategori (FS_Underkategori_Title, FS_OK_ID) VALUES (N'Bevæge sig omkring', 3)
INSERT INTO FS_Underkategori (FS_Underkategori_Title, FS_OK_ID) VALUES (N'Færden i forskellige omgivelser', 3)
INSERT INTO FS_Underkategori (FS_Underkategori_Title, FS_OK_ID) VALUES ('Bruge transportmidler', 3)
INSERT INTO FS_Underkategori (FS_Underkategori_Title, FS_OK_ID) VALUES ('Udholdenhed', 3)
INSERT INTO FS_Underkategori (FS_Underkategori_Title, FS_OK_ID) VALUES ('Muskelstyrke', 3)

INSERT INTO FS_Underkategori (FS_Underkategori_Title, FS_OK_ID) VALUES (N'Tilegne sig færdigheder', 4)
INSERT INTO FS_Underkategori (FS_Underkategori_Title, FS_OK_ID) VALUES (N'Problemløsning', 4)
INSERT INTO FS_Underkategori (FS_Underkategori_Title, FS_OK_ID) VALUES ('Anvende kommunikationsudstyr og teknikker', 4)
INSERT INTO FS_Underkategori (FS_Underkategori_Title, FS_OK_ID) VALUES ('Orienteringsevne', 4)
INSERT INTO FS_Underkategori (FS_Underkategori_Title, FS_OK_ID) VALUES ('Energi og handlekraft', 4)
INSERT INTO FS_Underkategori (FS_Underkategori_Title, FS_OK_ID) VALUES ('Hukommelse', 4)
INSERT INTO FS_Underkategori (FS_Underkategori_Title, FS_OK_ID) VALUES (N'Følelsesfunktioner', 4)
INSERT INTO FS_Underkategori (FS_Underkategori_Title, FS_OK_ID) VALUES ('Overordnede kognitive funktioner', 4)

INSERT INTO FS_Underkategori (FS_Underkategori_Title, FS_OK_ID) VALUES (N'Have lønnet beskæftigelse', 5)


INSERT INTO HS_Overkategori (HS_Overkategori_Titel) VALUES ('Funktionsniveau')
INSERT INTO HS_Overkategori (HS_Overkategori_Titel) VALUES (N'Bevægeapparat')
INSERT INTO HS_Overkategori (HS_Overkategori_Titel) VALUES (N'Ernæring')
INSERT INTO HS_Overkategori (HS_Overkategori_Titel) VALUES ('Hud og slimhinder')
INSERT INTO HS_Overkategori (HS_Overkategori_Titel) VALUES ('Kommunikation')
INSERT INTO HS_Overkategori (HS_Overkategori_Titel) VALUES ('Psykosociale forhold')
INSERT INTO HS_Overkategori (HS_Overkategori_Titel) VALUES ('Respiration og cirkulation')
INSERT INTO HS_Overkategori (HS_Overkategori_Titel) VALUES ('Seksualitet')
INSERT INTO HS_Overkategori (HS_Overkategori_Titel) VALUES ('Smerter og sanseindtryk')
INSERT INTO HS_Overkategori (HS_Overkategori_Titel) VALUES (N'Søvn og hvile')
INSERT INTO HS_Overkategori (HS_Overkategori_Titel) VALUES ('Viden og udvikling')
INSERT INTO HS_Overkategori (HS_Overkategori_Titel) VALUES ('Udskillelse af affaldsstoffer')


INSERT INTO HS_Underkategori (HS_Underkategori_Titel,HS_OK_ID) VALUES ('Problemer med personlig pleje', 1)
INSERT INTO HS_Underkategori (HS_Underkategori_Titel,HS_OK_ID) VALUES ('Problemer med daglige aktiviteter', 1)

INSERT INTO HS_Underkategori (HS_Underkategori_Titel,HS_OK_ID) VALUES (N'Problemer med mobilitet og bevægelse', 2)

INSERT INTO HS_Underkategori (HS_Underkategori_Titel,HS_OK_ID) VALUES (N'Problemer med væskeindtag', 3)
INSERT INTO HS_Underkategori (HS_Underkategori_Titel,HS_OK_ID) VALUES (N'Problemer med fødeindtag', 3)
INSERT INTO HS_Underkategori (HS_Underkategori_Titel,HS_OK_ID) VALUES (N'Uhensigtsmæssig vægtændring', 3)
INSERT INTO HS_Underkategori (HS_Underkategori_Titel,HS_OK_ID) VALUES (N'Problemer med overvægt', 3)
INSERT INTO HS_Underkategori (HS_Underkategori_Titel,HS_OK_ID) VALUES (N'Problemer med undervægt', 3)

INSERT INTO HS_Underkategori (HS_Underkategori_Titel,HS_OK_ID) VALUES (N'Problemer med kirugisk sår', 4)
INSERT INTO HS_Underkategori (HS_Underkategori_Titel,HS_OK_ID) VALUES (N'Problemer med diabetisk sår', 4)
INSERT INTO HS_Underkategori (HS_Underkategori_Titel,HS_OK_ID) VALUES (N'Problemer med cancersår', 4)
INSERT INTO HS_Underkategori (HS_Underkategori_Titel,HS_OK_ID) VALUES (N'Problemer med tryksår', 4)
INSERT INTO HS_Underkategori (HS_Underkategori_Titel,HS_OK_ID) VALUES (N'Problemer med arterielt sår', 4)
INSERT INTO HS_Underkategori (HS_Underkategori_Titel,HS_OK_ID) VALUES (N'Problemer med venøst sår', 4)
INSERT INTO HS_Underkategori (HS_Underkategori_Titel,HS_OK_ID) VALUES (N'Problemer med blandingssår', 4)
INSERT INTO HS_Underkategori (HS_Underkategori_Titel,HS_OK_ID) VALUES (N'Problemer med traumesår', 4)
INSERT INTO HS_Underkategori (HS_Underkategori_Titel,HS_OK_ID) VALUES ('Andre problemer med hud og slimhinder', 4)

INSERT INTO HS_Underkategori (HS_Underkategori_Titel,HS_OK_ID) VALUES ('Problemer med kommunikation', 5)

INSERT INTO HS_Underkategori (HS_Underkategori_Titel,HS_OK_ID) VALUES (N'Problemer med socialt samvær', 6)
INSERT INTO HS_Underkategori (HS_Underkategori_Titel,HS_OK_ID) VALUES ('Emotionelle problemer', 6)
INSERT INTO HS_Underkategori (HS_Underkategori_Titel,HS_OK_ID) VALUES ('Problemer med misbrug', 6)
INSERT INTO HS_Underkategori (HS_Underkategori_Titel,HS_OK_ID) VALUES ('Mentale problemer', 6)

INSERT INTO HS_Underkategori (HS_Underkategori_Titel,HS_OK_ID) VALUES ('Respirationsproblemer', 7)
INSERT INTO HS_Underkategori (HS_Underkategori_Titel,HS_OK_ID) VALUES ('cirkulationsproblemer', 7)

INSERT INTO HS_Underkategori (HS_Underkategori_Titel,HS_OK_ID) VALUES ('Problemer med seksualitet', 8)

INSERT INTO HS_Underkategori (HS_Underkategori_Titel,HS_OK_ID) VALUES ('Akutte smerter', 9)
INSERT INTO HS_Underkategori (HS_Underkategori_Titel,HS_OK_ID) VALUES ('Periodevise smerter', 9)
INSERT INTO HS_Underkategori (HS_Underkategori_Titel,HS_OK_ID) VALUES ('Kroniske smerter', 9)
INSERT INTO HS_Underkategori (HS_Underkategori_Titel,HS_OK_ID) VALUES ('Problemer med synssans', 9)
INSERT INTO HS_Underkategori (HS_Underkategori_Titel,HS_OK_ID) VALUES ('Problemer med lugtesans', 9)
INSERT INTO HS_Underkategori (HS_Underkategori_Titel,HS_OK_ID) VALUES (N'Problemer med hørelse', 9)
INSERT INTO HS_Underkategori (HS_Underkategori_Titel,HS_OK_ID) VALUES ('Problemer med smagssans', 9)
INSERT INTO HS_Underkategori (HS_Underkategori_Titel,HS_OK_ID) VALUES (N'Problemer med følesans', 9)

INSERT INTO HS_Underkategori (HS_Underkategori_Titel,HS_OK_ID) VALUES (N'Døgnrytmeproblemer', 10)
INSERT INTO HS_Underkategori (HS_Underkategori_Titel,HS_OK_ID) VALUES (N'Søvnproblemer', 10)

INSERT INTO HS_Underkategori (HS_Underkategori_Titel,HS_OK_ID) VALUES ('Problemer med hukommelse', 11)
INSERT INTO HS_Underkategori (HS_Underkategori_Titel,HS_OK_ID) VALUES (N'Problemer med indsigt i behandlingsformål', 11)
INSERT INTO HS_Underkategori (HS_Underkategori_Titel,HS_OK_ID) VALUES ('Problemer med sygdomsindsigt', 11)
INSERT INTO HS_Underkategori (HS_Underkategori_Titel,HS_OK_ID) VALUES ('Kognitiveproblemer', 11)

INSERT INTO HS_Underkategori (HS_Underkategori_Titel,HS_OK_ID) VALUES ('Problemer med vandladning', 12)
INSERT INTO HS_Underkategori (HS_Underkategori_Titel,HS_OK_ID) VALUES ('Problemer med urininkontinens', 12)
INSERT INTO HS_Underkategori (HS_Underkategori_Titel,HS_OK_ID) VALUES (N'Problemer med afføringskontinens', 12)
INSERT INTO HS_Underkategori (HS_Underkategori_Titel,HS_OK_ID) VALUES ('Problemer med mave og tarm', 12)