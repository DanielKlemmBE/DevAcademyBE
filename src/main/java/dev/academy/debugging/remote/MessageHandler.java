package dev.academy.debugging.remote;

public class MessageHandler {
    public static String handleMessage(String message, int messageNrOffset){
        message = message.replace("{", "").replace("}", "");
        String[] messageParts = message.split(",");
        for(String keySet : messageParts) {
            String[] keyAndEntry = keySet.split(":");
            String key = keyAndEntry[0];
            if(key.equals("requestNo")){
                // increase the value by the offset
                String entry = keyAndEntry[1];
                entry += messageNrOffset;
                return entry;
            }
        }
        return String.valueOf(messageNrOffset);
    }
}
