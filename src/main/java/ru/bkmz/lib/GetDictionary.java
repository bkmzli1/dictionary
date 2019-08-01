package ru.bkmz.lib;

import javafx.scene.text.Text;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;


public class GetDictionary {
    public String[] urls = {"https://ravesli.com/slovar-programmista-sleng-kotoryj-dolzhen-znat-kazhdyj-koder/",
            "http://jtdigest.narod.ru/kollection/ut88/slovar.htm",
            "http://slavan53.ru/slovar-programmista/"},
            ABC = abc.split("");
    public ArrayList<String> dirtyNames = new ArrayList<String>(),
            allNams = new ArrayList<String>(),
            name = new ArrayList<String>(),
            values = new ArrayList<String>(),
            url = new ArrayList<String>(),
            urlOut;
    private String abc = "А Б В Г Д Ж З И К Л М Н О П Р С Т У Ф Х Ц Ч Ш Э Ю".replace(" ", "");
    private Text text;
    private int allOtstyp = 160;

    public static void main(String[] args) throws IOException {
        GetDictionary getDictionary = new GetDictionary();

        Document document;
        document = Jsoup.connect(getDictionary.urls[3]).get();


    }

    private String getSlovar(Document document, int j) {
        Elements elements = document.select("a");
        String name = String.valueOf(elements.get(j));
        //System.out.println(name);
        char[] c = name.toCharArray();
        int urli = 0;
        String urls = "";
        for (int i = 0; i < c.length; i++) {
            String cS = String.valueOf(c[i]);
            if (cS.equals("\"")) {
                urli++;
            }
            if (urli == 1) {
                urls += cS;
            }
        }
        urls = "https://gufo.me" + urls.replace("\"", "");

        return urls;
    }


    public void start(Text text) {
        urlOut = new ArrayList<>();
        this.text = text;
        for (int i = 0; i < urls.length; i++) {

            try {
                this.text.setText("connect:" + urls[i]);
                Document document = Jsoup.connect(urls[i]).get();

                if (i == 0) {
                    text.setText("link processing started:" + urls[i]);
                    urlOne(document);
                } else if (i == 1) {
                    dirtyNames = new ArrayList<String>();
                    allNams = new ArrayList<String>();
                    urlTwo(document);
                } else if (i == 2) {
                    dirtyNames = new ArrayList<String>();
                    allNams = new ArrayList<String>();
                    urlTree(document);
                }
                urlOut.add(urls[i]);
            } catch (Exception e) {
                text.setText("failed to establish a connection with:" + urls[i]);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                }
            }

        }

    }


    private void urlTree(Document document) throws UnsupportedEncodingException {

    }

    private void clier() {
        for (int i = 0; i < dirtyNames.size() - 2; i++) {
            dirtyNames.set(i, dirtyNames.get(i).replace("   ", ""));
            dirtyNames.set(i, dirtyNames.get(i).replace("  ", ""));
            for (int j = 0; j < ABC.length; j++) {
                if (dirtyNames.get(i).equals(ABC[j])) {
                    dirtyNames.set(i, dirtyNames.get(i).replace(ABC[j], ""));

                }

            }
            if (!dirtyNames.get(i).equals("")) {
                dirtyNames.set(i, dirtyNames.get(i));
                allNams.add(dirtyNames.get(i));
            }

        }
    }

    private void urlTwo(Document document) {
        int step = 2;
        int stepI = 0;
        this.text.setText("link processing step " + stepI++ + "/" + step);
        Elements elements = document.select("p");
        String[] names = elements.text().replace("...", "&&&")
                .replace(".      ", ".\n")
                .replace("     ", "")
                .replace("&&&", "...")
                .replace(" - ", "&")
                .replace("Основные языки персональных компьютеров - ", "\nОсновные языки персональных компьютеров - ")
                .split("\n");
        for (String name :
                names) {
            allNams.add(name);
        }
        this.text.setText("link processing step " + stepI++ + "/" + step);
        for (String name :
                allNams) {
            char[] c = name.toCharArray();
            String n = "", d = "";
            int otstyp = 0;
            boolean notVoid = true;
            for (int i = 0; i < c.length; i++) {
                String cS = String.valueOf(c[i]);
                if (cS.equals("&")) {
                    notVoid = false;
                }

                if (notVoid) {
                    n += cS;
                } else {
                    d += cS;
                    otstyp++;
                    if (otstyp == allOtstyp) {
                        d += "\n";
                        otstyp = 0;
                    }
                }

            }
            url.add(urls[1]);
            this.name.add(n);
            values.add(d.replace("&", "- "));
        }

    }


    protected void getAll(int i, Elements elements) throws Exception {


        elements.forEach(element -> {
            Element pElements = element.child(i);
            String name = pElements.text();
            addList(name);


        });


    }

    protected void addList(String s) {
        dirtyNames.add(s);
    }

    protected void urlOne(Document document) {
        int step = 3;
        int stepI = 0;
        try {
            this.text.setText("link processing step " + stepI++ + "/" + step);
            Elements elements = document.getElementsByAttributeValue("class", "content");

            for (int i = 4; i < Integer.MAX_VALUE; i++) {

                getAll(i, elements);
            }
        } catch (Exception e) {
        }
        this.text.setText("link processing step " + stepI++ + "/" + step);
        clier();
        this.text.setText("link processing step " + stepI++ + "/" + step);
        for (String s :
                allNams) {
            char[] c = s.toCharArray();


            String n = "", d = "";
            int otstyp = 0;
            boolean notVoid = true;
            for (int i = 0; i < c.length; i++) {
                String cS = String.valueOf(c[i]);

                for (char j = 0; j <= 500; j++) {
                    if (j <= 32 || j >= 127) {
                        String ps = String.valueOf(j);
                        if (ps.equals(cS)) {

                            notVoid = false;
                        }
                    }
                }

                if (notVoid) {
                    n += cS + "";
                } else {
                    d += cS + "";
                    otstyp++;
                    if (otstyp == allOtstyp) {
                        d += "\n";
                        otstyp = 0;
                    }

                }
            }
            url.add(urls[0]);
            name.add(n);
            values.add(d);
        }
    }

}


