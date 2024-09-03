CREATE TABLE [dbo].[TodoItems]
(
	[Id] INT NOT NULL PRIMARY KEY IDENTITY(1,1), 
    [Title] NVARCHAR(128) NOT NULL, 
    [Description] NVARCHAR(MAX) NULL, 
    [Deadline] DATETIMEOFFSET NULL, 
    [Completed] BIT NOT NULL DEFAULT 0, 
    [Active] BIT NOT NULL DEFAULT 1, 
    [DurationSeconds] INT NOT NULL,
    [HateLevel] TINYINT NOT NULL,
    [Importance] TINYINT NOT NULL,
    [RecurrenceId] INT NULL, 
    CONSTRAINT [CK_HateLevel_Range] CHECK ([HateLevel] >= 1 AND [HateLevel] <= 5),
    CONSTRAINT [CK_Importance_Range] CHECK ([Importance] >= 1 AND [Importance] <= 5)
)
