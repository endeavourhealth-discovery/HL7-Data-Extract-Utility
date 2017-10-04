# HL7-Data-Extract-Utility

##### [install directory]
`Typically c:\backup\bin` 

##### [application arguments ]
`java -jar hl7-data-extract-utility.jar [host] [channel] [username] [password] [rootDir] [lastMessageId] [batch]`

`[host] - Host IP of the database server`

`[channel] - Channel (1 - Homerton or 2 - Barts)` 

`[username] - DB username`

`[password] - DB password`

`[rootDir] - Extract root directory. Files are saved into a sub directory based on channel`

`[lastMessageId] - MessageId to start extraction after. Update this for each run`

`[batch] - Optional batch limit, i.e. 1000 messages`
