package entity;

import java.util.ArrayList;
import java.util.List;


public class ProductCatalog {

    private List<Catalog> catalogList = new ArrayList<Catalog>();


    public ProductCatalog() {
    }


    public boolean addCatalog(Catalog catalog) {
        return catalogList.add(catalog);
    }

    public List<Catalog> getCatalogList() {
        return catalogList;
    }

    public void printCatalog() {
        for (Catalog iter : catalogList) {
            System.out.println();
            System.out.println("***************************");
            System.out.println(iter);
            System.out.println("***************************");
            System.out.println();
        }
    }

    @Override
    public String toString() {
        return "ProductCatalog{" +
                "catalogList=" + catalogList +
                '}';
    }
}
