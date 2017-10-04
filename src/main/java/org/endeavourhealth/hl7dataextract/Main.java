package org.endeavourhealth.hl7dataextract;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.nio.charset.Charset;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Main {
    public static void main( String args[] ) {
        String host = ""; String channel = ""; String username = ""; String password = "";
        String rootDir = ""; String lastMessageId = "0"; String batch = "";
        if (args.length > 0) host = args[0];
        if (args.length > 1) channel = args[1];
        if (args.length > 2) username = args[2];
        if (args.length > 3) password = args[3];
        if (args.length > 4) rootDir = args[4];
        if (args.length > 5) lastMessageId = args[5];
        if (args.length > 6) batch = args[6];

        File dir = new File(rootDir+channel);
        if (!dir.exists())
            dir.mkdir();

        try {
            Class.forName("org.postgresql.Driver");
            Connection c = DriverManager.getConnection("jdbc:postgresql://"+host+":5432/hl7receiver", username, password);
            c.setAutoCommit(false);
            System.out.println("Connected to jdbc:postgresql://"+host+":5432/hl7receiver successfully\n");

            Statement stmt = c.createStatement();
            String query = "select message_id, inbound_payload " +
                    "from log.message" +
                    " where channel_id = " + channel +
                    " and message_id > " + lastMessageId +
                    " order by message_id asc";
            if (batch != "")
                query = query.concat(" limit "+batch);

            ResultSet rs = stmt.executeQuery(query);
            int count = 0; String padMessageId = String.format("%08d", Integer.parseInt(lastMessageId));
            while ( rs.next() ) {
                int messageId = rs.getInt("message_id");
                String payload = rs.getString("inbound_payload");

                padMessageId = String.format("%08d", messageId);
                String fileName = dir.getPath()+"\\"+padMessageId+".txt";
                File file = new File (fileName);
                FileUtils.writeStringToFile(file, payload, Charset.defaultCharset());
                count++;
            }
            System.out.println(count + " files successfully written to "+dir.getPath()+"\\");
            System.out.println("Last messageId = "+padMessageId);

            rs.close();
            stmt.close();
            c.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName()+": "+ e.getMessage() );
            System.exit(0);
        }
    }
}
