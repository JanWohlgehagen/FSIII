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