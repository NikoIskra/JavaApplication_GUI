package com.example.iskra7.utils;

import hr.java.production.enumeration.GradoviEnum;
import hr.java.production.model.*;

import java.io.*;
import java.math.BigDecimal;
import java.util.*;

public class FileUtils {
    private static final String CATEGORY_FILE_NAME="dat/categories.txt";
    private static final int CATEGORY_FILE_NUMBER_OF_LINES=3;
    private static final String ITEMS_FILE_NAME="dat/items.txt";
    private static final int ITEM_FILE_NUMBER_OF_LINES=9;
    private static final String ADDRESS_FILE_NAME="dat/addresses.txt";
    private static final int ADDRESS_FILE_NUMBER_OF_LINES=3;
    private static final String FACTORY_FILE_NAME="dat/factories.txt";
    private static final int FACTORY_NUMBER_OF_LINES=4;
    private static final String STORE_FILE_NAME="dat/stores.txt";
    private static final int STORE_NUMBER_OF_LINES=4;

    public static List<Category> getCategories() throws IOException {
        FileReader fileReader=new FileReader(CATEGORY_FILE_NAME);
        List<Category> categoryList=new ArrayList<>();
        BufferedReader bufferedReader=new BufferedReader(fileReader);
        String readLine;

        while((readLine= bufferedReader.readLine())!=null) {
            String id=readLine;
            String name=bufferedReader.readLine();
            String description= bufferedReader.readLine();
            Long idLong=Long.parseLong(id);

            Category category=new Category(name,idLong,description);
            categoryList.add(category);
        }
        return categoryList;
    }

    public static void saveCategories(List<Category> categories) throws IOException {
        FileWriter fileWriter=new FileWriter(CATEGORY_FILE_NAME);
        PrintWriter printWriter=new PrintWriter(fileWriter);

        for (Category category:categories) {
            printWriter.println(category.getId().toString());
            printWriter.println(category.getName());
            printWriter.println(category.getDescription());
        }
        fileWriter.flush();
    }

    public static List<Item> getItems() throws IOException {
        FileReader fileReader=new FileReader(ITEMS_FILE_NAME);
        List<Item> itemList=new ArrayList<>();
        BufferedReader bufferedReader=new BufferedReader(fileReader);
        String readLine;

        while ((readLine= bufferedReader.readLine())!=null) {
            String id=readLine;
            String name= bufferedReader.readLine();
            String categoryString= bufferedReader.readLine();
            String widthString= bufferedReader.readLine();
            String heightString= bufferedReader.readLine();
            String lengthString= bufferedReader.readLine();
            String productionCostString=bufferedReader.readLine();
            String sellingPriceString= bufferedReader.readLine();
            String discountAmountString= bufferedReader.readLine();
            Long idLong=Long.parseLong(id);
            BigDecimal width= new BigDecimal(widthString);
            BigDecimal height=new BigDecimal(heightString);
            BigDecimal length=new BigDecimal(lengthString);
            BigDecimal productionCost=new BigDecimal(productionCostString);
            BigDecimal sellingPrice=new BigDecimal(sellingPriceString);
            BigDecimal discountAmount=new BigDecimal(discountAmountString);
            int category=Integer.parseInt(categoryString);
            Discount discount=new Discount(discountAmount);


            Item item=new Item(name, idLong, discount, getCategories().get(category - 1), width, height, length, productionCost, sellingPrice);
            itemList.add(item);
        }
        return itemList;
    }

    public static void saveItems(List<Item> items) throws IOException {
        FileWriter fileWriter=new FileWriter(ITEMS_FILE_NAME);
        PrintWriter printWriter=new PrintWriter(fileWriter);

        for (Item item : items) {
            printWriter.println(item.getId().toString());
            printWriter.println(item.getName());
            printWriter.println(item.getCategory().getId());
            printWriter.println(item.getWidth().toString());
            printWriter.println(item.getHeight().toString());
            printWriter.println(item.getLength().toString());
            printWriter.println(item.getProductionCost().toString());
            printWriter.println(item.getSellingPrice().toString());
            printWriter.println(item.getDiscountAmount().discountAmount().toString());
        }
        fileWriter.flush();
    }

    public static List<Factory> getFactories() throws IOException {
        FileReader fileReader=new FileReader(FACTORY_FILE_NAME);
        List<Factory> factoryList=new ArrayList<>();
        BufferedReader bufferedReader=new BufferedReader(fileReader);
        String readLine;

        while ((readLine= bufferedReader.readLine())!=null) {
            String idString=readLine;
            String name= bufferedReader.readLine();
            String street= bufferedReader.readLine();
            String houseNumber= bufferedReader.readLine();
            String city= bufferedReader.readLine();
            String item= bufferedReader.readLine();
            Long id=Long.parseLong(idString);

            Set<Item> factoryItems=new HashSet<>();
            factoryItems.add(getItems().get(Integer.parseInt(item)-1));

            GradoviEnum factoryCity=GradoviEnum.valueOf(city.toUpperCase(Locale.ROOT));

            Address address=new Address.Builder(street)
                    .atHouseNumber(houseNumber)
                    .inCity(factoryCity)
                    .build();

            Factory factory=new Factory(name, id, address, factoryItems);
            factoryList.add(factory);
        }
        return factoryList;
    }

    public static void saveFactories(List<Factory> factories) throws IOException {
        FileWriter fileWriter=new FileWriter(FACTORY_FILE_NAME);
        PrintWriter printWriter=new PrintWriter(fileWriter);

        for (Factory factory : factories) {
            printWriter.println(factory.getId().toString());
            printWriter.println(factory.getName());
            printWriter.println(factory.getAddress().getStreet());
            printWriter.println(factory.getAddress().getHouseNumber());
            printWriter.println(factory.getAddress().getCity().getName());
            printWriter.println(factory.getItems().iterator().next().getId());
        }
        fileWriter.flush();
    }

    public static List<Store> getStores() throws IOException {
        FileReader fileReader=new FileReader(STORE_FILE_NAME);
        List<Store> storeList=new ArrayList<>();

        BufferedReader bufferedReader=new BufferedReader(fileReader);
        String readLine;

        while ((readLine= bufferedReader.readLine())!=null) {
            String idString=readLine;
            String name= bufferedReader.readLine();
            String webAddress= bufferedReader.readLine();
            String item= bufferedReader.readLine();
            Long id=Long.parseLong(idString);

            Set<Item> storeItems=new HashSet<>();
            storeItems.add(getItems().get(Integer.parseInt(item) -1));

            Store store=new Store(name, id, webAddress, storeItems);
            storeList.add(store);
        }
        return storeList;
    }

    public static void saveStores(List<Store> stores) throws IOException {
        FileWriter fileWriter=new FileWriter(STORE_FILE_NAME);
        PrintWriter printWriter=new PrintWriter(fileWriter);

        for (Store store : stores) {
            printWriter.println(store.getId().toString());
            printWriter.println(store.getName());
            printWriter.println(store.getWebAddress());
            printWriter.println(store.getItems().iterator().next().getId());
        }
        fileWriter.flush();
    }
}
