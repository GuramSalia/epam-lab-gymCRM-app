CREATE TABLE IF NOT EXISTS Users (
    UserId SERIAL PRIMARY KEY,
    FirstName VARCHAR(255) NOT NULL,
    LastName VARCHAR(255) NOT NULL,
    Username VARCHAR(255) NOT NULL,
    Password VARCHAR(255) NOT NULL,
    IsActive BOOLEAN NOT NULL
);

CREATE TABLE IF NOT EXISTS Customers (
    CustomerId SERIAL PRIMARY KEY,
    DateOfBirth DATE,
    Address VARCHAR(255),
    UserId INT NOT NULL UNIQUE,
    FOREIGN KEY (UserId) REFERENCES Users(UserId) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS TrainingTypes (
    TrainingTypeId SERIAL PRIMARY KEY,
    TrainingTypeName VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS Trainers (
    TrainerId SERIAL PRIMARY KEY,
    TrainingTypeId INT,
    UserId INT NOT NULL UNIQUE,
    FOREIGN KEY (TrainingTypeId) REFERENCES TrainingTypes(TrainingTypeId),
    FOREIGN KEY (UserId) REFERENCES Users(UserId) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS Trainings (
    TrainingId SERIAL PRIMARY KEY,
    CustomerId INT,
    TrainerId INT,
    TrainingName VARCHAR(255) NOT NULL,
    TrainingTypeId INT,
    TrainingDate DATE NOT NULL,
    TrainingDuration INT NOT NULL,
    FOREIGN KEY (TrainingTypeId) REFERENCES TrainingTypes(TrainingTypeId),
    FOREIGN KEY (TrainerId) REFERENCES Trainers(TrainerId),
    FOREIGN KEY (CustomerId) REFERENCES Customers(CustomerId) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS Customers_Trainers (
    CustomerId INT,
    TrainerId INT,
    PRIMARY KEY (CustomerId, TrainerId),
    FOREIGN KEY (CustomerId) REFERENCES Customers(CustomerId) ON DELETE CASCADE,
    FOREIGN KEY (TrainerId) REFERENCES Trainers(TrainerId) ON DELETE CASCADE
);


--CREATE TABLE IF NOT EXISTS Trainings_Customers (
--    TrainingId INT,
--    CustomerId INT,
--    PRIMARY KEY (TrainingId, CustomerId),
--    FOREIGN KEY (TrainingId) REFERENCES Trainings(TrainingId),
--    FOREIGN KEY (CustomerId) REFERENCES Customers(CustomerId),
--    UNIQUE (CustomerId)  -- Ensure that each Customer is associated with a specific Training only once
--);
--
--CREATE TABLE IF NOT EXISTS Trainings_Trainers (
--    TrainingId INT,
--    TrainerId INT,
--    PRIMARY KEY (TrainingId, TrainerId),
--    FOREIGN KEY (TrainingId) REFERENCES Trainings(TrainingId),
--    FOREIGN KEY (TrainerId) REFERENCES Trainers(TrainerId),
--    UNIQUE (TrainerId)  -- Ensure that each Trainer is associated with a specific Training only once
--);
--
--
--
--CREATE TABLE IF NOT EXISTS Trainings_TrainingTypes (
--    TrainingId INT,
--    TrainingTypeId INT,
--    PRIMARY KEY (TrainingId, TrainingTypeId),
--    FOREIGN KEY (TrainingId) REFERENCES Trainings(TrainingId),
--    FOREIGN KEY (TrainingTypeId) REFERENCES TrainingTypes(TrainingTypeId),
--    UNIQUE (TrainingTypeId)  -- Ensure that each TrainingType is associated with a specific Training only once
--);

--CREATE TABLE IF NOT EXISTS Trainers_TrainingTypes (
--    TrainerId INT,
--    TrainingTypeId INT,
--    PRIMARY KEY (TrainerId, TrainingTypeId),
--    FOREIGN KEY (TrainerId) REFERENCES Trainers(TrainerId),
--    FOREIGN KEY (TrainingTypeId) REFERENCES TrainingTypes(TrainingTypeId)
--);
