package com.company;

import java.io.BufferedReader;
import java.util.Set;
import java.util.Map.Entry;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.*;

class Content {
    Content(){}
    Content(String iName, String iReleaseDate, int iNumberOfCopies,int iFilmLength, int iScore)
    {
        Name = iName;
        ReleaseDate = iReleaseDate;
        NumberOfCopies = iNumberOfCopies;
        FilmLength = iFilmLength;
        Score = iScore;
    }
    String Name = "";
    String ReleaseDate = "";
    int NumberOfCopies = 0;
    int FilmLength = 0; // in mins
    int Score = 0; //Out of 10
}

public class Database {

    private String FileName = "MyDB.txt";

    public Map<String,Content> DB = new TreeMap<String,Content>();

    public Database()
    {
        try
        {
            BufferedReader reader = new BufferedReader(new FileReader(FileName));
            String line;
            while ((line = reader.readLine()) != null)
            {
                int BreakCounter = 0;
                int lastBreak = -1;
                Content tempCont = new Content();
                String Key= "";
                for(int i = 0; i<line.length();i++)
                {
                    if(line.charAt(i)=='#')
                    {
                        lastBreak++;
                        switch(BreakCounter)
                        {
                            case 0:
                                Key = line.substring(lastBreak, i);
                            case 1:
                                tempCont.Name = line.substring(lastBreak, i);
                                break;
                            case 2:
                                tempCont.ReleaseDate = line.substring(lastBreak, i);
                                break;
                            case 3:
                                tempCont.NumberOfCopies = Integer.parseInt(line.substring(lastBreak, i));
                                break;
                            case 4:
                                tempCont.FilmLength = Integer.parseInt(line.substring(lastBreak, i));
                                break;
                            case 5:
                                tempCont.Score = Integer.parseInt(line.substring(lastBreak, i));
                                break;
                        }
                        lastBreak = i;
                        BreakCounter++;
                    }

                }
                DB.put(Key, tempCont);
            }
            reader.close();
        }
        catch (Exception e)
        {
            System.err.format("Exception occurred trying to read '%s'.", FileName);
            e.printStackTrace();
        }
    }

    void WriteFile()
    {
        try
        {
            BufferedWriter writer = new BufferedWriter(new FileWriter(FileName));

            for (Map.Entry<String, Content> it : DB.entrySet())
            {
                writer.write(
                        it.getKey()+""+
                                it.getValue().Name +"#"+
                                it.getValue().ReleaseDate +"#"+
                                it.getValue().NumberOfCopies +"#"+
                                it.getValue().FilmLength +"#"+
                                it.getValue().Score +"#"
                );
                writer.newLine();
            }
            writer.close();
        }
        catch (Exception e)
        {
            System.err.format("Exception occurred trying to write '%s'.", FileName);
            e.printStackTrace();
        }


    }
    public List<Content> Sort(Boolean dsc)
    {
        list<String> ToSort;
        Iterator it = DB.entrySet().iterator();
        while (it.hasNext()) 
        {
            Map.Entry ob = (Map.Entry)it.next();
            ToSort.add(ob.getValue().Name);
            it.remove();
        }
        java.util.Collections.sort(ToSort)
        return ToSort;
    }

    public void Add(String k,String Name,String ReleaseDate,int NumberOfCopies, int FilmLength, int Score)
    {
        DB.put(k,new Content(Name, ReleaseDate, NumberOfCopies, FilmLength, Score));
    }
    public void ViewList(List<Content> Films){
        for (Content i:Films){
            System.out.println(i.Name+" " + i.ReleaseDate+" " + i.FilmLength+" " + i.NumberOfCopies+" " +i.Score);
        }
    }
}


