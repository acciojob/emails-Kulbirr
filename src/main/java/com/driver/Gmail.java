package com.driver;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Gmail extends Email {

    class Mail{
       private Date date;
       private String sender;
       private String message;


       public Mail(Date date, String sender, String message){
            this.date = date;
            this.sender = sender;
            this.message = message;
        }

        public Date getDate() {
            return date;
        }

        public String getSender() {
            return sender;
        }

        public String getMessage() {
            return message;
        }
    }



    int inboxCapacity; //maximum number of mails inbox can store
    private List<Mail> inbox = new ArrayList<>();
    private List<Mail> trash = new ArrayList<>();
    //Inbox: Stores mails. Each mail has date (Date), sender (String), message (String). It is guaranteed that message is distinct for all mails.
    //Trash: Stores mails. Each mail has date (Date), sender (String), message (String)
    public Gmail(String emailId, int inboxCapacity) {
        super(emailId);
        this.inboxCapacity = inboxCapacity;
    }

    public void receiveMail(Date date, String sender, String message){
        Mail mail = new Mail(date, sender, message);
        if(inbox.size() > this.inboxCapacity){
            Mail lastMessage = inbox.get(inbox.size()-1);
            trash.add(lastMessage);
            inbox.remove(lastMessage);
        }
        inbox.add(mail);
        return;
        // If the inbox is full, move the oldest mail in the inbox to trash and add the new mail to inbox.
        // It is guaranteed that:
        // 1. Each mail in the inbox is distinct.
        // 2. The mails are received in non-decreasing order. This means that the date of a new mail is greater than equal to the dates of mails received already.
    }

    public void deleteMail(String message){
        // Each message is distinct
        // If the given message is found in any mail in the inbox, move the mail to trash, else do nothing
        for(Mail mail : inbox){
            if(mail.getMessage().equals(message)){
                trash.add(mail);
                inbox.remove(mail);
                return;
            }
        }
    }

    public String findLatestMessage(){
        // If the inbox is empty, return null
        // Else, return the message of the latest mail present in the inbox
        if(inbox.isEmpty()) return null;
        Mail mail = inbox.get(0);
        String latestMessage = mail.getMessage();
        return latestMessage;
    }

    public String findOldestMessage(){
        // If the inbox is empty, return null
        // Else, return the message of the oldest mail present in the inbox
        if(inbox.isEmpty())return null;
        Mail mail = inbox.get(inbox.size()-1);
        String oldestMessage  = mail.getMessage();
        return oldestMessage;
    }

    public int findMailsBetweenDates(Date start, Date end){
        //find number of mails in the inbox which are received between given dates
        //It is guaranteed that start date <= end date
        int mailsBetweenSpecificDate = 0;
        for(Mail mail : inbox){
            if(mail.getDate().after(start) && mail.getDate().before(end)){
                mailsBetweenSpecificDate++;
            }
        }
        return mailsBetweenSpecificDate;
    }

    public int getInboxSize(){
        // Return number of mails in inbox
        return inbox.size();
    }

    public int getTrashSize(){
        // Return number of mails in Trash
        return trash.size();
    }

    public void emptyTrash(){
        // clear all mails in the trash
        trash.clear();
    }

    public int getInboxCapacity() {
        // Return the maximum number of mails that can be stored in the inbox
        return this.inboxCapacity;
    }
}
