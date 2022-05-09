USE CSe21A_FSIII_Simulator
GO

INSERT INTO [Person] (FirstName, LastName, Role) VALUES ('Tobias', 'Rasmussen', 'STUDENT')
INSERT INTO [Person] (FirstName, LastName, Role) VALUES ('Jan', 'Wohlgehagen', 'STUDENT')
INSERT INTO [Person] (FirstName, LastName, Role) VALUES ('Mikkel', 'Theut', 'STUDENT')
INSERT INTO [Person] (FirstName, LastName, Role) VALUES ('Simon', 'Tved', 'STUDENT')


INSERT INTO [Credentials] (Person_ID,UserName,Password) VALUES (1,'tobias', '$2a$10$YvmNIkjXaNy9CJFDCEi09uAGVnNgMs353F9tCGTkq9NHwtPCGjsAy')
INSERT INTO [Credentials] (Person_ID,UserName,Password) VALUES (2,'jan', '$2a$10$YvmNIkjXaNy9CJFDCEi09uAGVnNgMs353F9tCGTkq9NHwtPCGjsAy')
INSERT INTO [Credentials] (Person_ID,UserName,Password) VALUES (3,'mikkel', '$2a$10$YvmNIkjXaNy9CJFDCEi09uAGVnNgMs353F9tCGTkq9NHwtPCGjsAy')
INSERT INTO [Credentials] (Person_ID,UserName,Password) VALUES (4,'simon', '$2a$10$fDGJS/UbXJ3QmJ6A5YYx9OGgHX0WZF6MIqSghr1/hInhyhHsRCIRq')

INSERT INTO Borger (FirstName, LastName, Age, Template) VALUES ('Rosa', 'Jensen', 63, 0)
INSERT INTO Borger (FirstName, LastName, Age, Template) VALUES ('Jan', 'Olsen', 56, 0)
INSERT INTO Borger (FirstName, LastName, Age, Template) VALUES ('Simon', 'Jensen', 40, 0)
INSERT INTO Borger (FirstName, LastName, Age, Template) VALUES ('Jo', 'Hansen', 90, 0)
INSERT INTO Borger (FirstName, LastName, Age, Template) VALUES ('Bo', 'Rasmussen', 80, 0)
INSERT INTO Borger (FirstName, LastName, Age, Template) VALUES ('Bent', 'Z', 83, 0)

INSERT INTO [Case] (Borger_ID, OverkategoriTitle, UnderkategoriTitle, Henvisning, Description, Aasagsfritekst, Aasagsdiagnose, Aasagstilstand, Borgerensonsker, Bevilling, Bevillings_Tekst, [Plan], Opfoelgnings_Tag, SagsAnsvarlig)
VALUES (1, 'Egenomsorg', 'Spise', 'Der er ikke en plan', 'Der er ikke en plan', 'Der er ikke en plan', 'Der er ikke en plan','Der er ikke en plan','Det ved ikke', 0,'test', 'Der er ikke en plan', 'Fortsættes', 'Tobias')

INSERT INTO [Case] (Borger_ID, OverkategoriTitle, UnderkategoriTitle, Henvisning, Description, Aasagsfritekst, Aasagsdiagnose, Aasagstilstand, Borgerensonsker, Bevilling, Bevillings_Tekst, [Plan], Opfoelgnings_Tag, SagsAnsvarlig)
VALUES (1, 'Smerter og sanseindtryk', 'Problemer med sygdomsindsigt', 'Der er ikke en plan', 'Der er ikke en plan', 'Der er ikke en plan', 'Der er ikke en plan','Der er ikke en plan','Det ved ikke', 0,'test', 'Der er ikke en plan','Fortsættes', 'Tobias')

INSERT INTO [Case] (Borger_ID, OverkategoriTitle, UnderkategoriTitle, Henvisning, Description, Aasagsfritekst, Aasagsdiagnose, Aasagstilstand, Borgerensonsker, Bevilling, Bevillings_Tekst, [Plan], Opfoelgnings_Tag, SagsAnsvarlig)
VALUES (2, 'Egenomsorg', 'Spise', 'Der er ikke en plan', 'Der er ikke en plan', 'Der er ikke en plan', 'Der er ikke en plan','Der er ikke en plan','Det ved ikke', 0,'test', 'Der er ikke en plan','Fortsættes', 'Tobias')

INSERT INTO [Case] (Borger_ID, OverkategoriTitle, UnderkategoriTitle, Henvisning, Description, Aasagsfritekst, Aasagsdiagnose, Aasagstilstand, Borgerensonsker, Bevilling, Bevillings_Tekst, [Plan], Opfoelgnings_Tag, SagsAnsvarlig)
VALUES (2, 'Smerter og sanseindtryk', 'Problemer med sygdomsindsigt', 'Der er ikke en plan', 'Der er ikke en plan', 'Der er ikke en plan', 'Der er ikke en plan','Der er ikke en plan','Det ved ikke', 0,'test', 'Der er ikke en plan','Fortsættes', 'Tobias')

INSERT INTO [Case] (Borger_ID, OverkategoriTitle, UnderkategoriTitle, Henvisning, Description, Aasagsfritekst, Aasagsdiagnose, Aasagstilstand, Borgerensonsker, Bevilling, Bevillings_Tekst, [Plan], Opfoelgnings_Tag, SagsAnsvarlig)
VALUES (3, 'Egenomsorg', 'Spise', 'Der er ikke en plan', 'Der er ikke en plan', 'Der er ikke en plan', 'Der er ikke en plan','Der er ikke en plan','Det ved ikke', 0,'test', 'Der er ikke en plan','Fortsættes', 'Tobias')

INSERT INTO [Case] (Borger_ID, OverkategoriTitle, UnderkategoriTitle, Henvisning, Description, Aasagsfritekst, Aasagsdiagnose, Aasagstilstand, Borgerensonsker, Bevilling, Bevillings_Tekst, [Plan], Opfoelgnings_Tag, SagsAnsvarlig)
VALUES (3, 'Smerter og sanseindtryk', 'Problemer med sygdomsindsigt', 'Der er ikke en plan', 'Der er ikke en plan', 'Der er ikke en plan', 'Der er ikke en plan','Der er ikke en plan','Det ved ikke', 0,'test', 'Der er ikke en plan', 'Fortsættes', 'Tobias')

INSERT INTO [Case] (Borger_ID, OverkategoriTitle, UnderkategoriTitle, Henvisning, Description, Aasagsfritekst, Aasagsdiagnose, Aasagstilstand, Borgerensonsker, Bevilling, Bevillings_Tekst, [Plan], Opfoelgnings_Tag, SagsAnsvarlig)
VALUES (4, 'Egenomsorg', 'Spise', 'Der er ikke en plan', 'Der er ikke en plan', 'Der er ikke en plan', 'Der er ikke en plan','Der er ikke en plan','Det ved ikke', 0,'test', 'Der er ikke en plan', 'Fortsættes', 'Tobias')

INSERT INTO [Case] (Borger_ID, OverkategoriTitle, UnderkategoriTitle, Henvisning, Description, Aasagsfritekst, Aasagsdiagnose, Aasagstilstand, Borgerensonsker, Bevilling, Bevillings_Tekst, [Plan], Opfoelgnings_Tag, SagsAnsvarlig)
VALUES (4, 'Smerter og sanseindtryk', 'Problemer med sygdomsindsigt', 'Der er ikke en plan', 'Der er ikke en plan', 'Der er ikke en plan', 'Der er ikke en plan','Der er ikke en plan','Det ved ikke', 0,'test', 'Der er ikke en plan','Fortsættes', 'Tobias')

INSERT INTO [Case] (Borger_ID, OverkategoriTitle, UnderkategoriTitle, Henvisning, Description, Aasagsfritekst, Aasagsdiagnose, Aasagstilstand, Borgerensonsker, Bevilling, Bevillings_Tekst, [Plan], Opfoelgnings_Tag, SagsAnsvarlig)
VALUES (5, 'Egenomsorg', 'Spise', 'Der er ikke en plan', 'Der er ikke en plan', 'Der er ikke en plan', 'Der er ikke en plan','Der er ikke en plan','Det ved ikke', 0,'test', 'Der er ikke en plan', 'Fortsættes', 'Tobias')

INSERT INTO [Case] (Borger_ID, OverkategoriTitle, UnderkategoriTitle, Henvisning, Description, Aasagsfritekst, Aasagsdiagnose, Aasagstilstand, Borgerensonsker, Bevilling, Bevillings_Tekst, [Plan], Opfoelgnings_Tag, SagsAnsvarlig)
VALUES (5, 'Smerter og sanseindtryk', 'Problemer med sygdomsindsigt', 'Der er ikke en plan',  'Der er ikke en plan', 'Der er ikke en plan', 'Der er ikke en plan','Der er ikke en plan','Det ved ikke', 0,'test', 'Der er ikke en plan', 'Fortsættes', 'Tobias')

INSERT INTO [Case] (Borger_ID, OverkategoriTitle, UnderkategoriTitle, Henvisning, Description, Aasagsfritekst, Aasagsdiagnose, Aasagstilstand, Borgerensonsker, Bevilling, Bevillings_Tekst, [Plan], Opfoelgnings_Tag, SagsAnsvarlig)
VALUES (6, 'Egenomsorg', 'Spise',  'Der er ikke en plan', 'Der er ikke en plan', 'Der er ikke en plan', 'Der er ikke en plan','Der er ikke en plan','Det ved ikke', 0,'test', 'Der er ikke en plan', 'Fortsættes', 'Tobias')

INSERT INTO [Case] (Borger_ID, OverkategoriTitle, UnderkategoriTitle, Henvisning, Description, Aasagsfritekst, Aasagsdiagnose, Aasagstilstand, Borgerensonsker, Bevilling, Bevillings_Tekst, [Plan], Opfoelgnings_Tag, SagsAnsvarlig)
VALUES (6, 'Smerter og sanseindtryk', 'Problemer med sygdomsindsigt', 'Der er ikke en plan', 'Der er ikke en plan', 'Der er ikke en plan', 'Der er ikke en plan','Der er ikke en plan','Det ved ikke', 0,'test', 'Der er ikke en plan', 'Fortsættes', 'Tobias')