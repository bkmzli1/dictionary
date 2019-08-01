package ru.bkmz.lib.table;


import javafx.scene.text.Text;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static ru.bkmz.lib.MainDictionary.appdata;
import static ru.bkmz.lib.controllers.Controller.getDictionary;

public class BD {
    public static String[] nameColumn;
    private static Map<Integer, Integer> languageMapId;
    private static Map<Integer, String> languageMapWord;
    private static Map<Integer, String> languageMapDefinition;
    private static Map<Integer, String> languageMapUrl;
    private static Map<Integer, String> languageMapUsers;
    private static Connection con;
    private static String url = "jdbc:sqlite:" + appdata + "res/BD/BD";
    public int idAll;

    static void close() {
        try {
            con.close();
        } catch (Exception e) {

        }
    }

    private void conection() {
        try {
            Class.forName("org.sqlite.JDBC");
            con = DriverManager.getConnection(url);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public void sqlite() {
        languageMapId = new HashMap<Integer, Integer>();
        languageMapWord = new HashMap<Integer, String>();
        languageMapDefinition = new HashMap<Integer, String>();
        languageMapUrl = new HashMap<Integer, String>();
        languageMapUsers = new HashMap<Integer, String>();

        try {
            conection();
            Statement statement = con.createStatement();
            query(statement);

        } catch (Exception e) {
            e.printStackTrace();
        }
        close();
    }

    public String getName(int i) {
        return languageMapWord.get(i);
    }

    public String getComint(int i) {
        return languageMapDefinition.get(i);
    }

    public String getUrl(int i) {
        return languageMapUrl.get(i);
    }

    public String getLanguageMapUsers(int i) {
        return languageMapUsers.get(i);
    }

    public int getId(int i) {
        return languageMapId.get(i);
    }

    public void sqliteAdd(String word, String definition, String url, boolean users) {

        try {
            conection();
            Statement statement = con.createStatement();


            word = word.replace("'", "\"");
            definition = definition.replace("'", "\"");
            url = url.replace("'", "\"");
            int user;
            if (users) {
                user = 1;
            } else {
                user = 0;
            }
            statement.execute("INSERT INTO \"Date\" (\"id\",\"word\", \"definition\", \"url\",\"users\") VALUES ('" + idAll + "', '" + word + "', '" + definition + "', '" + url + "', '" + user + "')");

            query(statement);

        } catch (Exception e) {
            e.printStackTrace();
        }
        close();
    }

    private void query(Statement statement) throws SQLException {

        ResultSet rs = statement.executeQuery("SELECT * FROM " + "Date");

        idAll = 0;
        while (rs.next()) {
            int id = rs.getInt("id");
            String word = rs.getString("word");
            String definition = rs.getString("definition");
            String url = rs.getString("url");
            String user = rs.getString("users");
            if (id != idAll) {
                System.out.println(id + "->" + idAll);
                sqliteUp(id, "", "", "", idAll + "", statement);
                close();


                sqlite();


            }
            languageMapUsers.put(idAll, user);
            languageMapId.put(idAll, id + 1);
            languageMapWord.put(idAll, word);
            languageMapDefinition.put(idAll, definition);
            languageMapUrl.put(idAll, url);

            idAll++;
        }
    }


    public void sqliteInfo() {
        try {
            conection();
            Statement statement = con.createStatement();
            String[] word;
            ResultSet rs = statement.executeQuery("pragma table_info(Date)");
            String s = "";
            while (rs.next()) {
                s += rs.getString("name") + "!";
            }
            word = s.split("!");
            nameColumn = word;
        } catch (Exception e) {
            e.printStackTrace();
        }
        close();
    }

    public void sqliteDelete(int id) {

        try {
            conection();
            Statement statement = con.createStatement();
            System.out.println(id);
            statement.execute("DELETE FROM \"Date\" WHERE \"id\" = " + (id - 1));

        } catch (Exception e) {
            e.printStackTrace();
        }
        close();
    }

    public void sqliteDelete(int id, String s) {

        try {
            conection();
            Statement statement = con.createStatement();
            statement.execute("DELETE FROM \"Date\" WHERE \"id\" = " + (id));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void sqliteUp(int editid, String word, String definition, String url, String id) {
        String execute = "";
        int col = 0;
        try {
            conection();
            Statement statement = con.createStatement();
            edit(editid, word, definition, url, id, statement, execute, col, 1);

        } catch (Exception e) {
            e.printStackTrace();
        }
        close();
    }

    private void sqliteUp(int editid, String word, String definition, String url, String id, Statement statement) {
        String execute = "";
        int col = 0;
        try {
            Class.forName("org.sqlite.JDBC");
            edit(editid, word, definition, url, id, statement, execute, col, 0);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void edit(int editid, String word, String definition, String url, String id, Statement statement, String execute, int col, int users) throws SQLException {
        if (!id.equals("")) {
            int idi = Integer.parseInt(id);
            execute += " \"id\" = " + idi;
            col++;
        }
        if (!word.equals("")) {
            if (col > 0) {
                execute += ",";
            }
            execute += " \"word\" = '" + word + "'";
            col++;
        }
        if (!definition.equals("")) {
            if (col > 0) {
                execute += ",";
            }
            execute += " \"definition\" = '" + definition + "'";
            col++;
        }
        if (!url.equals("")) {
            if (col > 0) {
                execute += ",";
            }
            execute += "\"url\" = '" + url + "'";
            col++;
        }
        if (col > 0) {
            execute += ",";
        }
        execute += "\"users\" = '" + users + "'";
        if (col != 0) {
            statement.execute("UPDATE\"Date\" SET " + execute + " WHERE \"id\" = " + editid);
        }
        query(statement);
    }


    public void clier(Text text) {
        text.setText("started clearing old information");
        ArrayList<Integer> arrayList = new ArrayList();
        try {
            conection();
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM " + "Date");
            text.setText("identifying changes and adding information");
            while (rs.next()) {

                int id = rs.getInt("id");
                String url = rs.getString("url");

                int user = rs.getInt("users");
                for (String u :
                        getDictionary.urlOut) {
                    if (u.equals(url)) {
                        if (user != 1) {
                            arrayList.add(id);
                        }
                    }
                }

            }


        } catch (Exception e) {
            e.printStackTrace();
        }
        close();
        int inf = 0;
        for (int i :
                arrayList) {
            text.setText("clearing old information:" + inf++ + "/" + arrayList.size());
            sqliteDelete(i, "");
        }
    }
}


