package com.company;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.Arrays;
import java.util.Scanner;

abstract class MyProgram {
    /*
     *  Class serves for writing and reading documents
     */
    public String number;
    public String name;

    public MyProgram(){
    }
}

class Act extends MyProgram {

    public String number = "first";
    public String name = "act";

    public Act(String number, String name) {
        this.name = name;
        this.number = number;
    }

    public void write(String f) {
        try(DataOutputStream dos = new DataOutputStream(new FileOutputStream(f))) {
            dos.writeUTF(this.number);
            dos.writeUTF(this.name);
            System.out.println("Act has been written!");
        }
        catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void read(String f) {
        try(DataInputStream dis = new DataInputStream(new FileInputStream(f))) {
            this.number = dis.readUTF();
            this.name = dis.readUTF();
            System.out.println("Act has been read!");
        }
        catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void print()
    {
        System.out.printf(this.number, this.name);
    }
}

class Agreement extends MyProgram {

    public String number = "second";
    public String name = "agreement";

    public Agreement(String number, String name) {
        this.name = name;
        this.number = number;
    }

    public void write(String f) {
        try(DataOutputStream dos = new DataOutputStream(new FileOutputStream(f))) {
            dos.writeUTF(this.number);
            dos.writeUTF(this.name);
            System.out.println("Agreement has been written!");
        }
        catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void read(String f) {
        try(DataInputStream dis = new DataInputStream(new FileInputStream(f))) {
            this.number = dis.readUTF();
            this.name = dis.readUTF();
            System.out.println("Agreement has been read!");
        }
        catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void print()
    {
        System.out.printf(this.number, this.name);
    }
}

public class Main {

    public static void main(String[] args) {

        Act a = new Act("first", "act");
        a.write("Act.txt");
        a.read("Act.txt");
        Agreement ag  = new Agreement("second", "agreement");
        ag.write("Agreement.txt");
        ag.read("Agreement.txt");


        try(PrintStream ps = new PrintStream ("Act.txt")) {
            ps.print("first" + " " + "act");
            File f = new File("Act.txt");
            Scanner s = new Scanner(f);
            String line = s.nextLine();
            String[] docs = line.split(" ");
            System.out.println(Arrays.toString(docs));
            System.out.println("Act is printed!");
            File nf = new File("Act_02.txt");
            f.renameTo(nf);
            Files.copy(f.toPath(), nf.toPath(), StandardCopyOption.REPLACE_EXISTING);
            System.out.println("Act has been renamed to: " + nf);
            boolean deleted = nf.delete();
            if(deleted)
                System.out.println("Act has been deleted");
        }
        catch (IOException e) {
            System.out.println(e.getMessage());
        }

        try(PrintStream ps2 = new PrintStream ("Agreement.txt")) {
            ps2.print("second" + " " + "agreement");
            File f2 = new File("Agreement.txt");
            Scanner s2 = new Scanner(f2);
            String line = s2.nextLine();
            String[] docs2 = line.split(" ");
            System.out.println(Arrays.toString(docs2));
            System.out.println("Agreement is printed!");
            File nf2 = new File("Agreement_02.txt");
            f2.renameTo(nf2);
            Files.copy(f2.toPath(), nf2.toPath(), StandardCopyOption.REPLACE_EXISTING);
            System.out.println("Agreement has been renamed to: " + nf2);
            boolean deleted = nf2.delete();
            if(deleted)
                System.out.println("Agreement has been deleted");
        }
        catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}