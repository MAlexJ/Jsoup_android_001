package service;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;


import java.io.IOException;

import constants.Constants;
import entity.Catalog;
import entity.ProductCatalog;


public class JsoupCatalogService implements Constants {

    private String catalogHTML;
    private Document docCatalogHTML;
    private ProductCatalog productCatalogList = new ProductCatalog();


    public void init(int timeout) {
        try {
            docCatalogHTML = Jsoup.connect(Constants.productCatalog).timeout(timeout).get();
            catalogHTML = docCatalogHTML.select(Constants.productCatalogSelectorCSS).html();
            parceContext(catalogHTML);
        } catch (IOException e) {
            if (timeout<5000) init(timeout * 2);
            else throw new RuntimeException("My Exception -> Ошибка соединения с интернетом");
        }
    }

    public ProductCatalog getProductCatalogList() {
        return productCatalogList;
    }

    private void parceContext(String str) {
        if (str == null) {
            throw new ArithmeticException();
        }
        char[] chars = str.toCharArray();
        int targetEnd = str.indexOf("</li>");
        if (targetEnd >= 0) {
            targetEnd += 5;

            char[] charsCatalog = new char[targetEnd];
            System.arraycopy(chars, 0, charsCatalog, 0, targetEnd);

            String strCatalog = new String(charsCatalog);
            Catalog catalog = new Catalog(strCatalog);
            String tittle = parseCatalogTitle(strCatalog);
            String imageSrc = parseCatalogImgSRC(strCatalog);

            if (imageSrc != null) {
                catalog.setImage(imageSrc);
            }

            String imageLongdesc = parseCatalogImgLongdesc(strCatalog);
            if (imageLongdesc != null) {
                catalog.setImage(imageLongdesc);
            }
            catalog.setName(tittle);
            catalog.generationID();
            productCatalogList.addCatalog(catalog);


            char[] resultArray = new char[chars.length - targetEnd];
            System.arraycopy(chars, targetEnd, resultArray, 0, chars.length - targetEnd);
            String resultString = new String(resultArray);
            parceContext(resultString);
        } else {
            return;
        }
    }


    private static String parseCatalogTitle(String str) {

        if (str == null) throw new ArithmeticException();

        int indexAlt = str.indexOf("alt=\"");
        char[] chars = str.toCharArray();

        //Max number symbol to result array
        int numberSymbolArray = 300;
        char[] resultTitle = new char[numberSymbolArray];
        indexAlt += 5;
        System.arraycopy(chars, indexAlt, resultTitle, 0, numberSymbolArray);

        StringBuilder sb = new StringBuilder();
        for (Character iter : resultTitle) {
            if (!(iter == '"'))
                sb.append(iter);
            else break;
        }

        return sb.toString();
    }

    //переделать на рекурсию в один метод
    private static String parseCatalogImgSRC(String str) {
        if (str == null) throw new ArithmeticException();
        int indexAlt = str.indexOf("src=");
        if (indexAlt < 0) {
            return null;
        }
        char[] chars = str.toCharArray();
        //lendth -> "src="
        indexAlt += 5;
        //Max number symbol to result array
        int numberSymbolArray = 100;
        char[] resultTitle = new char[numberSymbolArray];
        System.arraycopy(chars, indexAlt, resultTitle, 0, numberSymbolArray);
        String strImgSrc = new String(resultTitle);
        int numJPG = strImgSrc.indexOf(".jpg");
        int numJPGcount = numJPG + 3;
        int numJPEG = strImgSrc.indexOf(".jpeg");
        int numJPEGcount = numJPEG + 4;
        StringBuilder sb = new StringBuilder();
        if (numJPG >= 0) {
            for (int i = 0; i <= numJPGcount; i++) {
                sb.append(resultTitle[i]);
            }
        }
        if (numJPEG > 0) {
            for (int i = 0; i <= numJPEGcount; i++) {
                sb.append(resultTitle[i]);
            }
        }
        return sb.toString();
    }

    //переделать на рекурсию в один метод
    private static String parseCatalogImgLongdesc(String str) {
        if (str == null) throw new ArithmeticException();
        int indexAlt = str.indexOf("longdesc=");
        if (indexAlt < 0) {
            return null;
        }
        char[] chars = str.toCharArray();
        //lendth -> "longdesc="
        indexAlt += 10;
        //Max number symbol to result array
        int numberSymbolArray = 100;
        char[] resultTitle = new char[numberSymbolArray];
        System.arraycopy(chars, indexAlt, resultTitle, 0, numberSymbolArray);
        String strImgSrc = new String(resultTitle);
        int numJPG = strImgSrc.indexOf(".jpg");
        int numJPGcount = numJPG + 3;
        int numJPEG = strImgSrc.indexOf(".jpeg");
        int numJPEGcount = numJPEG + 4;
        StringBuilder sb = new StringBuilder();
        if (numJPG >= 0) {
            for (int i = 0; i <= numJPGcount; i++) {
                sb.append(resultTitle[i]);
            }
        }
        if (numJPEG > 0) {
            for (int i = 0; i <= numJPEGcount; i++) {
                sb.append(resultTitle[i]);
            }
        }
        return sb.toString();
    }

}
