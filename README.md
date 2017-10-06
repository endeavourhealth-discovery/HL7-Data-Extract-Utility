# HL7-Data-Extract-Utility

A simple command line HL7 data extraction utility which creates a MessageId named text file for each message extracted containing the message payload. 

##### [install directory]
`Typically c:\backup\bin` 

##### [application arguments ]
`java -jar hl7-data-extract-utility.jar [host] [channel] [username] [password] [rootDir] [lastMessageId] [batch]`

`[host] - Host IP of the database server`

`[channel] - Channel (1 - Homerton or 2 - Barts)` 

`[username] - DB username`

`[password] - DB password`

`[rootDir] - Extract root directory. Files are saved into a sub directory based on channel, i.e. c:\backup\hl7\1`

`[lastMessageId] - MessageId to start the extraction after. Start with 0 then update this for each run. Each run returns the last message Id extracted for reference`

`[batch] - Optional batch limit, i.e. 1000 messages`
