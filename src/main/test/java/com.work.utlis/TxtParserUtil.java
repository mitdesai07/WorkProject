package com.work.utils;

import java.io.*;
import java.util.*;

public class TxtParserUtil {

    public static List<Map<String, String>> parseTxtFile(String txtFilePath) {
        List<Map<String, String>> records = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(txtFilePath))) {
            String line;

            // Skip the first line (header)
            if ((line = reader.readLine()) != null) {
                System.out.println("Skipping header line: " + line);
            }

            while ((line = reader.readLine()) != null) {
                Map<String, String> record = new LinkedHashMap<>();

                // Extract fields based on the layout in the correct order
                record.put("AccountNumber", getField(line, 0, 20));
                record.put("AccountName", getField(line, 110, 142));
                record.put("AddressLine1", getField(line, 142, 174));
                record.put("AddressLine2", getField(line, 174, 206));
                record.put("AddressLine3", getField(line, 206, 238));
                record.put("AddressLine4", getField(line, 238, 270));
                record.put("AddressLine5", getField(line, 270, 302));
                record.put("AddressLine6", getField(line, 302, 334));
                record.put("AddressLine7", getField(line, 334, 366));

                records.add(record);
            }

        } catch (IOException e) {
            System.err.println("Error reading TXT file: " + e.getMessage());
        }

        return records;
    }

    private static String getField(String line, int start, int end) {
        try {
            // Trim and return the substring; handle cases where the line is too short
            return line.length() >= start ? line.substring(start, Math.min(line.length(), end)).trim() : null;
        } catch (Exception e) {
            // Return null if any unexpected error occurs
            return null;
        }
    }

    public static void printRecords(List<Map<String, String>> records) {
        for (Map<String, String> record : records) {
            System.out.println("Account Number: " + record.get("AccountNumber"));
            System.out.println("Account Name: " + record.get("AccountName"));
            System.out.println("Address Line 1: " + record.get("AddressLine1"));
            System.out.println("Address Line 2: " + record.get("AddressLine2"));
            System.out.println("Address Line 3: " + record.get("AddressLine3"));
            System.out.println("Address Line 4: " + record.get("AddressLine4"));
            System.out.println("Address Line 5: " + record.get("AddressLine5"));
            System.out.println("Address Line 6: " + record.get("AddressLine6"));
            System.out.println("Address Line 7: " + record.get("AddressLine7"));
            System.out.println("------------------------------------------------");
        }
    }
}



