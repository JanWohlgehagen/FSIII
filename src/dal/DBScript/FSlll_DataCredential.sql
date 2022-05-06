USE CSe21A_FSIII_Simulator
GO

INSERT INTO [Person] (FirstName, LastName, Role) VALUES ('Tobias', 'Rasmussen', 'STUDENT')
INSERT INTO [Person] (FirstName, LastName, Role) VALUES ('Jan', 'Wohlgehagen', 'STUDENT')
INSERT INTO [Person] (FirstName, LastName, Role) VALUES ('Mikkel', 'Theut', 'STUDENT')
INSERT INTO [Person] (FirstName, LastName, Role) VALUES ('Simon', 'Tved', 'STUDENT')


INSERT INTO [Credentials] (ID,UserName,Password) VALUES (1,'tobias', '$2a$10$YvmNIkjXaNy9CJFDCEi09uAGVnNgMs353F9tCGTkq9NHwtPCGjsAy')
INSERT INTO [Credentials] (ID,UserName,Password) VALUES (2,'jan', '$2a$10$YvmNIkjXaNy9CJFDCEi09uAGVnNgMs353F9tCGTkq9NHwtPCGjsAy')
INSERT INTO [Credentials] (ID,UserName,Password) VALUES (3,'mikkel', '$2a$10$YvmNIkjXaNy9CJFDCEi09uAGVnNgMs353F9tCGTkq9NHwtPCGjsAy')
INSERT INTO [Credentials] (ID,UserName,Password) VALUES (4,'simon', '$2a$10$fDGJS/UbXJ3QmJ6A5YYx9OGgHX0WZF6MIqSghr1/hInhyhHsRCIRq')

INSERT INTO Borger (FirstName, LastName, Age, Template) VALUES ('Rosa', 'Jensen', 63, false)
INSERT INTO Borger (FirstName, LastName, Age, Template) VALUES ('Jan', 'Olsen', 56, false)
INSERT INTO Borger (FirstName, LastName, Age, Template) VALUES ('Simon', 'Jensen', 40, false)
INSERT INTO Borger (FirstName, LastName, Age, Template) VALUES ('Jo', 'Hansen', 90, false)
INSERT INTO Borger (FirstName, LastName, Age, Template) VALUES ('Bo', 'Rasmussen', 80, false)
INSERT INTO Borger (FirstName, LastName, Age, Template) VALUES ('Bent', 'Z', 83, false)

INSERT INTO [Case] (Borger_ID, Title, Description, Bevilling, Bevillings_Tekst, [Plan], Opfoelgnings_Tag)
VALUES (1, 'braket Arm', 'Det ved ikke', 0,'test', 'Der er ikke en plan', 'Fortsættes')

INSERT INTO [Case] (Borger_ID, Title, Description, Bevilling, Bevillings_Tekst, [Plan], Opfoelgnings_Tag)
VALUES (1, 'braket ben', 'Det ved ikke', 0,'test', 'Der er ikke en plan', 'Fortsættes')

INSERT INTO [Case] (Borger_ID, Title, Description, Bevilling, Bevillings_Tekst, [Plan], Opfoelgnings_Tag)
VALUES (2, 'braket Arm', 'Det ved ikke', 0,'test', 'Der er ikke en plan', 'Fortsættes')

INSERT INTO [Case] (Borger_ID, Title, Description, Bevilling, Bevillings_Tekst, [Plan], Opfoelgnings_Tag)
VALUES (2, 'braket ben', 'Det ved ikke', 0,'test', 'Der er ikke en plan', 'Fortsættes')

INSERT INTO [Case] (Borger_ID, Title, Description, Bevilling, Bevillings_Tekst, [Plan], Opfoelgnings_Tag)
VALUES (3, 'braket Arm', 'Det ved ikke', 0,'test', 'Der er ikke en plan', 'Fortsættes')

INSERT INTO [Case] (Borger_ID, Title, Description, Bevilling, Bevillings_Tekst, [Plan], Opfoelgnings_Tag)
VALUES (3, 'braket ben', 'Det ved ikke', 0,'test', 'Der er ikke en plan', 'Fortsættes')

INSERT INTO [Case] (Borger_ID, Title, Description, Bevilling, Bevillings_Tekst, [Plan], Opfoelgnings_Tag)
VALUES (4, 'braket Arm', 'Det ved ikke', 0,'test', 'Der er ikke en plan', 'Fortsættes')

INSERT INTO [Case] (Borger_ID, Title, Description, Bevilling, Bevillings_Tekst, [Plan], Opfoelgnings_Tag)
VALUES (4, 'braket ben', 'Det ved ikke', 0,'test', 'Der er ikke en plan', 'Fortsættes')

INSERT INTO [Case] (Borger_ID, Title, Description, Bevilling, Bevillings_Tekst, [Plan], Opfoelgnings_Tag)
VALUES (5, 'braket Arm', 'Det ved ikke', 0,'test', 'Der er ikke en plan', 'Fortsættes')

INSERT INTO [Case] (Borger_ID, Title, Description, Bevilling, Bevillings_Tekst, [Plan], Opfoelgnings_Tag)
VALUES (5, 'braket ben', 'Det ved ikke', 0,'test', 'Der er ikke en plan', 'Fortsættes')

INSERT INTO [Case] (Borger_ID, Title, Description, Bevilling, Bevillings_Tekst, [Plan], Opfoelgnings_Tag)
VALUES (6, 'braket Arm1', 'Det ved ikke', 0,'test', 'Der er ikke en plan', 'Fortsættes')

INSERT INTO [Case] (Borger_ID, Title, Description, Bevilling, Bevillings_Tekst, [Plan], Opfoelgnings_Tag)
VALUES (6, 'braket ben', 'Det ved ikke', 0,'test', 'Der er ikke en plan', 'Fortsættes')