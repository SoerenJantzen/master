package de.sjantzen.master.dummyInit;

import de.sjantzen.master.model.*;
import de.sjantzen.master.repositories.*;
import jdk.nashorn.internal.runtime.OptimisticReturnFilters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

/**
 * Created by sJantzen on 05.01.2018.
 */
@Controller
public class DummyInitializer {

    private static final Logger LOG = LoggerFactory.getLogger(DummyInitializer.class);

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private OpeningHoursRepository openingHoursRepository;

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OrdersRepository ordersRepository;

    @Autowired
    private ProductRepository productRepository;

    /**
     * Initializes dummy datas to test backend basics.
     * @return
     */
    @RequestMapping("/init/dummyData")
    public String initDbData() {
        LOG.info("Initialize dummy datas.");

        /*
         * COMPANY 1
         */
        // address
        final Address address1 = new Address("Jenny-Lind-Str. 7", "13189", "Berlin", null);
        addressRepository.save(address1);

        // opening hours
        final OpeningHours openingHours = new OpeningHours("", "", "12:00", "22:00", "12:00", "22:00", "12:00", "22:00",
                "12:00", "22:00","12:00", "22:00","12:00", "22:00", null);
        openingHoursRepository.save(openingHours);

        final Company comp1 = new Company("Athena-Roma", address1, null, new HashSet<Category>(), openingHours, null);
        companyRepository.save(comp1);

        address1.setCompany(comp1);
        addressRepository.save(address1);

        openingHours.setCompany(comp1);
        openingHoursRepository.save(openingHours);

        final Category cat1 = new Category(0, "Softdrinks", "Kalte Getränke. Wasser, Limonaden, Säfte und mehr.", false, new HashSet<Product>(), comp1);
        final Category cat2 = new Category(1, "Vorspeisen / Salate", "Kalte und warme Vorspeisen und Salate.", false, new HashSet<Product>(), comp1);
        final Category cat3 = new Category(2, "Hauptgerichte", "Verschiedene Hauptgerichte der italienischen Küche.", false, new HashSet<Product>(), comp1);
        final Category cat4 = new Category(3, "Desserts", "Süße Nachspeisen.", false, new HashSet<Product>(), comp1);
        categoryRepository.save(cat1);
        categoryRepository.save(cat2);
        categoryRepository.save(cat3);
        categoryRepository.save(cat4);

        comp1.getCategories().add(cat1);
        comp1.getCategories().add(cat2);
        comp1.getCategories().add(cat3);
        comp1.getCategories().add(cat4);
        companyRepository.save(comp1);

        // products
        // softdrinks
        final Product prod0 = new Product(0, "Wasser (still)", "Einfaches stilles Wasser.", BigDecimal.valueOf(2), cat1, null);
        final Product prod1 = new Product(1, "Cola", "Coca Cola", BigDecimal.valueOf(3), cat1, null);
        final Product prod2 = new Product(2, "Fanta", "Fanta", BigDecimal.valueOf(3), cat1, null);
        final Product prod3 = new Product(3, "Sprite", "Sprite", BigDecimal.valueOf(3), cat1, null);
        final Product prod4 = new Product(4, "Apfelsaft", "Bio Apfelsaft", BigDecimal.valueOf(3), cat1, null);
        final Product prod5 = new Product(5, "Orangensaft", "Frisch gepresst", BigDecimal.valueOf(3), cat1, null);
        productRepository.save(prod0);
        productRepository.save(prod1);
        productRepository.save(prod2);
        productRepository.save(prod3);
        productRepository.save(prod4);
        productRepository.save(prod5);

        cat1.getProducts().add(prod0);
        cat1.getProducts().add(prod1);
        cat1.getProducts().add(prod2);
        cat1.getProducts().add(prod3);
        cat1.getProducts().add(prod4);
        cat1.getProducts().add(prod5);
        categoryRepository.save(cat1);

        // starters / salads
        final Product prod6 = new Product(0,"Ceasar Salat", "Salat mit Hühnerbruststreifen", BigDecimal.valueOf(6), cat2, null);
        final Product prod7 = new Product(1,"Gurkensalat", "Klassisch mit Dill und Essig", BigDecimal.valueOf(4), cat2, null);
        final Product prod8 = new Product(2,"Bruschetta", "Typisch italienische Vorspeise", BigDecimal.valueOf(4), cat2, null);
        productRepository.save(prod6);
        productRepository.save(prod7);
        productRepository.save(prod8);

        cat2.getProducts().add(prod6);
        cat2.getProducts().add(prod7);
        cat2.getProducts().add(prod8);
        categoryRepository.save(cat2);

        // meals
        final Product prod9 = new Product(0,"Pizza Diavolo", "Pizza belegt mit Salamie und Jalapenios (scharf).", BigDecimal.valueOf(10), cat3, null);
        final Product prod10 = new Product(1,"Pasta Carbonara", "Nudeln mit Schinken und Sahnesoße", BigDecimal.valueOf(9), cat3, null);
        final Product prod11 = new Product(2,"Saltimbocca", "Schweinemedaillions mit Salbei, Schinken, Kroketten und Weißweinsoße", BigDecimal.valueOf(15), cat3, null);
        productRepository.save(prod9);
        productRepository.save(prod10);
        productRepository.save(prod11);

        cat3.getProducts().add(prod9);
        cat3.getProducts().add(prod10);
        cat3.getProducts().add(prod11);
        categoryRepository.save(cat3);

        // desserts
        final Product prod12 = new Product(0,"Mousse ou Chocolate", "Schokoladenmousse mit Vanilleeis und frischen Früchten", BigDecimal.valueOf(4), cat4, null);
        final Product prod13 = new Product(1,"Three times chocolate", "Schokoladendreierlei mit Schokoladenmousse und Schokoparfait, bestückt mit Schokosplittern", BigDecimal.valueOf(6), cat4, null);
        final Product prod14 = new Product(2,"Crème Brulé", "Französische Süßspeise", BigDecimal.valueOf(4), cat4, null);
        productRepository.save(prod12);
        productRepository.save(prod13);
        productRepository.save(prod14);

        cat4.getProducts().add(prod12);
        cat4.getProducts().add(prod13);
        cat4.getProducts().add(prod14);
        categoryRepository.save(cat4);

        /*
         * COMPANY 2
         */
        // address
        final Address address2 = new Address("Maximilianstraße 1", "13187", "Berlin", null);
        addressRepository.save(address2);

        // opening hours
        final OpeningHours openingHours2 = new OpeningHours("10:00", "18:00", "10:00", "18:00", "10:00", "18:00", "10:00", "18:00",
                "10:00", "23:00", "10:00", "23:00","10:00", "18:00", null);
        openingHoursRepository.save(openingHours2);

        final Company comp2 = new Company("Vilja Café", address2, null, new HashSet<Category>(), openingHours2, null);
        companyRepository.save(comp2);

        address2.setCompany(comp2);
        addressRepository.save(address2);

        openingHours2.setCompany(comp2);
        openingHoursRepository.save(openingHours2);

        final Category cat11 = new Category(0, "Kalte Getränke", "Kalte Getränke. Wasser, Limonaden, Säfte und mehr.", false, new HashSet<Product>(), comp2);
        final Category cat22 = new Category(1, "Heisse Getränke", "Heiße Getränke. Kaffee und mehr.", false, new HashSet<Product>(), comp2);
        final Category cat33 = new Category(2, "Alkoholisch", "Alkoholische Getränke.", false, new HashSet<Product>(), comp2);
        final Category cat44 = new Category(3, "Speisen", "Frühstück und Backwaren.", false, new HashSet<Product>(), comp2);
        categoryRepository.save(cat11);
        categoryRepository.save(cat22);
        categoryRepository.save(cat33);
        categoryRepository.save(cat44);

        comp2.getCategories().add(cat11);
        comp2.getCategories().add(cat22);
        comp2.getCategories().add(cat33);
        comp2.getCategories().add(cat44);
        companyRepository.save(comp1);

        // products
        // cold drinks
        final Product prod15 = new Product(0, "Wasser (still)", "Einfaches stilles Wasser.", BigDecimal.valueOf(2), cat11, null);
        final Product prod16 = new Product(1, "Cola", "Coca Cola", BigDecimal.valueOf(3), cat11, null);
        final Product prod17 = new Product(2, "Bio Zisch", "Brause", BigDecimal.valueOf(3), cat11, null);
        final Product prod18 = new Product(3, "Club Mate", "Erweckendes Erfrischungsgetränk", BigDecimal.valueOf(3), cat11, null);
        final Product prod19 = new Product(4, "Malzbier", "Malziges Kaltgetränk", BigDecimal.valueOf(3), cat11, null);
        final Product prod20 = new Product(5, "Orangensaft", "Frisch gepresst", BigDecimal.valueOf(3), cat11, null);
        productRepository.save(prod15);
        productRepository.save(prod16);
        productRepository.save(prod17);
        productRepository.save(prod18);
        productRepository.save(prod19);
        productRepository.save(prod20);

        cat11.getProducts().add(prod15);
        cat11.getProducts().add(prod16);
        cat11.getProducts().add(prod17);
        cat11.getProducts().add(prod18);
        cat11.getProducts().add(prod19);
        cat11.getProducts().add(prod20);
        categoryRepository.save(cat11);

        // hot drinks
        final Product prod21 = new Product(0,"Kaffee", "Auch entkoffeiniert.", BigDecimal.valueOf(2), cat22, null);
        final Product prod22 = new Product(1,"Espresso", "Aus Italien.", BigDecimal.valueOf(2), cat22, null);
        final Product prod23 = new Product(2,"Frischer Ingwertee", "Heiß und würzig.", BigDecimal.valueOf(3), cat22, null);
        productRepository.save(prod21);
        productRepository.save(prod22);
        productRepository.save(prod23);

        cat22.getProducts().add(prod21);
        cat22.getProducts().add(prod22);
        cat22.getProducts().add(prod23);
        categoryRepository.save(cat2);

        // alcoholic drinks
        final Product prod24 = new Product(0,"Astra Urtyp", "Hamburger Kiezbier.", BigDecimal.valueOf(2), cat33, null);
        final Product prod25 = new Product(1,"Catarratto 2008", "Weisswein", BigDecimal.valueOf(3), cat33, null);
        final Product prod26 = new Product(2,"Vilja", "Rum Cocktail mit Havanna, Soda, Rosmarin, Salbei, Lime", BigDecimal.valueOf(6), cat33, null);
        productRepository.save(prod24);
        productRepository.save(prod25);
        productRepository.save(prod26);

        cat33.getProducts().add(prod24);
        cat33.getProducts().add(prod25);
        cat33.getProducts().add(prod26);
        categoryRepository.save(cat33);

        // meals
        final Product prod27 = new Product(0,"Kuchen", "Täglich wechselnde Angebote", BigDecimal.valueOf(2), cat44, null);
        final Product prod28 = new Product(1,"Panino Caprese", "Baguette mit Tomate, Mozzarella, Basilikum", BigDecimal.valueOf(3), cat44, null);
        final Product prod29 = new Product(2,"Großes Frühstück", "2 Brötchen, Butter, Wurst, Käse, 1 Bio-Ei, Obst", BigDecimal.valueOf(8), cat44, null);
        productRepository.save(prod27);
        productRepository.save(prod28);
        productRepository.save(prod29);

        cat44.getProducts().add(prod24);
        cat44.getProducts().add(prod25);
        cat44.getProducts().add(prod26);
        categoryRepository.save(cat44);

        /*
         * USER
         */
        User user1 = new User("testUser1", "password1", "", "soerenjantzen@gmx.net", 1, new HashSet<Orders>());
        userRepository.save(user1);

        /*
         * Orders
         */
        LocalDateTime ldtNow = LocalDateTime.now();
        Date createdDatetime = Date.from(ldtNow.atZone(ZoneId.systemDefault()).toInstant());
        Date dueDatetime = Date.from(ldtNow.atZone(ZoneId.systemDefault()).plusHours(1).toInstant());

        List<Product> orderedProducts1 = new ArrayList<>();
        orderedProducts1.add(prod1);
        orderedProducts1.add(prod11);
        orderedProducts1.add(prod13);

        List<Product> orderedProducts2 = new ArrayList<>();
        orderedProducts2.add(prod3);
        orderedProducts2.add(prod10);

        List<Product> orderedProducts3 = new ArrayList<>();
        orderedProducts3.add(prod23);
        orderedProducts3.add(prod29);

        Orders order1 = new Orders(user1, comp1, createdDatetime, dueDatetime, orderedProducts1, "0001");
        Orders order2 = new Orders(user1, comp1, createdDatetime, dueDatetime, orderedProducts2, "0002");
        Orders order3 = new Orders(user1, comp2, createdDatetime, dueDatetime, orderedProducts3, "0003");
        ordersRepository.save(order1);
        ordersRepository.save(order2);
        ordersRepository.save(order3);

        user1.getOrders().add(order1);
        user1.getOrders().add(order2);
        user1.getOrders().add(order3);
        userRepository.save(user1);

        return "Dummy data were initialized.";
    }
}
