package com.company;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;


public  class LibraryManagement {
    static class Books{
        String bookName;
        String bookAuthor;
        String issueTo;
        String issuedOn;

        Books(String bookName,String bookAuthor){
            this.bookName=bookName;
            this.bookAuthor=bookAuthor;
        }

        Books(String Sname){
            this.issueTo=Sname;
        }

    }
    static class Student{
        String Sname;
        int Sid;
        Student(String Sname,int Sid){
            this.Sname=Sname;
            this.Sid=Sid;
        }
    }



   static class Library {
        ArrayList<Books> books = new ArrayList<>(10);
        ArrayList<Student> students=new ArrayList<>(10);
        HashMap<String,Student> studentsTakenTheBook=new HashMap<>();

       ArrayList<Books> removedBooks = new ArrayList<>(10);


        public void addBooks(String bookName, String bookAuthor) {
            Books b = new Books(bookName, bookAuthor);

            books.add(b);
        }

        public void displayBooks(){
            int i=1;
            if(books.isEmpty()){
                System.out.println("No books available");
            }
            else{
                for(Books book:books){
                System.out.println(i++ +"-" +"Book Name:"+book.bookName+"   " +"Author Name:"+book.bookAuthor);
            }

            }
        }

        public void issueBook(String Sname,int Sid,String name){
         Student s=new Student(Sname,Sid);
         students.add(s);

         for(int i=0;i<books.size();i++){
             Books book= books.get(i);
             if(book.bookName.equalsIgnoreCase(name)){
                 book.issueTo=Sname;

                 LocalDateTime dt= LocalDateTime.now();
                 DateTimeFormatter df=DateTimeFormatter.ofPattern("dd-MM-yyyy a.");
                 String mydate=dt.format(df);

                 book.issuedOn= mydate;
                 System.out.println("--Issuing the Book--");
                 System.out.println("Book Name:"+book.bookName+"\nIssued to: "+book.issueTo+" \nIssued on:"+book.issuedOn);
                 System.out.println();
                 Books removed= books.remove(i);
                 removedBooks.add(removed);
                 studentsTakenTheBook.put(book.bookName,s);
                 return;
             }

         }
            System.out.println("No book Found");
         }



       public void returnBook(String bookToBeReturn) {
            if(!studentsTakenTheBook.containsKey(bookToBeReturn)){
                System.out.println("No book found");
                return;
            }

           LocalDateTime dt= LocalDateTime.now();
           DateTimeFormatter df=DateTimeFormatter.ofPattern("dd-MM-yyyy a.");
           String mydate=dt.format(df);
           Student name=studentsTakenTheBook.get(bookToBeReturn);

           for(Books removed:removedBooks){

               System.out.println("--Book successfully returned--\n" +"Book Name:"+bookToBeReturn+ "\nReturned by Student: "+name.Sname+"\nStudent ID:"+name.Sid +"\nReturned On: "+mydate);
               System.out.println();
               books.add(removed);
               break;
           }
           studentsTakenTheBook.remove(bookToBeReturn);
       }


   }
    public static void main(String[] args) {
        Library l= new Library();
        l.addBooks("Oliver Twist","William");
        l.addBooks("THE ROOM ON THE ROOF","Ruskin Bond");
        l.addBooks("COMBAT OF SHADOWS","MANOHAR MALGONKAR");
        l.addBooks("THE GOD OF SMALL THINGS","ARUNDHATI ROY");
        l.addBooks("THE WHITE TIGER"," ARAVIND ADIGA");
       // l.displayBooks();

        l.issueBook("Rahul",123,"oliver twist");
        l.issueBook("Rohit",2334,"THE ROOM ON THE ROOF");
        l.issueBook("thsf",123,"fdsfasf");

        l.returnBook("Oliver Twist");
        l.returnBook("THE ROOM ON THE ROOF");




    }
}
