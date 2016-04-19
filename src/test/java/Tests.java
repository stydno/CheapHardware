
import java.sql.SQLException;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.junit.After;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import org.stydno.cheaphardware.dao.AdvertisementDao;
import org.stydno.cheaphardware.dao.Dao;
import org.stydno.cheaphardware.dao.ItemDao;
import org.stydno.cheaphardware.dao.SellerDao;
import org.stydno.cheaphardware.service.AppService;
import org.stydno.cheaphardware.tables.Advertisement;
import org.stydno.cheaphardware.tables.Item;
import org.stydno.cheaphardware.tables.Seller;
import org.stydno.cheaphardware.utils.TestHibernateUtil;

public class Tests {

    Session session;
    AppService service = new AppService();
    Dao dao = new Dao();
    ItemDao itemDao = new ItemDao();
    SellerDao sellerDao = new SellerDao();
    AdvertisementDao advertisementDao = new AdvertisementDao();
    Item item;
    Item gainedItem;
    Seller seller;
    Seller gainedSeller;
    Advertisement advertisement;
    Advertisement gainedAdvertisement;
    String model = "test model";
    String phone = "test phone";
    String name = "test name";
    Integer price = 1;
    Integer newPrice = 2;
    Integer gainedPrice;
    Integer sellerId;
    Integer itemId;
    Integer adsId;
    List<Advertisement> ads;

    public void createHibernateSession() {
        session = TestHibernateUtil.getSessionFactory().openSession();
    }

    public void closeHibernateSession() {
        session.close();
    }

    @Before
    public void beforeTest() {
        createHibernateSession();
        session.getTransaction().begin();
    }

    @After
    public void afterTest() {
        session.getTransaction().rollback();
        closeHibernateSession();
    }

    @Test
    public void createGetAds() throws SQLException {
        item = itemDao.createItem(model);
        session.save(item);
        seller = sellerDao.createSeller(name, phone);
        session.save(seller);
        price = 1;
        Advertisement testAdvertisement1 = advertisementDao.createAdvertisement(price, seller, item);
        session.save(testAdvertisement1);
        Criteria cr = session.createCriteria(Advertisement.class);
        List<Advertisement> testAds = (List<Advertisement>) advertisementDao.getAds(cr);
        assertEquals(testAds.size(), 1);
        gainedPrice = testAds.get(0).getPrice();
        assertEquals(price, gainedPrice);

    }

    @Test
    public void createDeleteAds() throws SQLException {
        item = itemDao.createItem(model);
        session.save(item);
        seller = sellerDao.createSeller(name, phone);
        session.save(seller);
        Advertisement testAdvertisement = advertisementDao.createAdvertisement(price, seller, item);
        session.save(testAdvertisement);
        Criteria cr = session.createCriteria(Advertisement.class);
        advertisement = (Advertisement) advertisementDao.getAds(cr).get(0);
        adsId = advertisement.getId();
        session.delete(advertisementDao.deleteAds(cr, adsId));
        assertTrue(advertisementDao.getAds(cr).isEmpty());
    }

    @Test
    public void createGetAdsWithItem() throws SQLException {
        item = itemDao.createItem(model);
        session.save(item);
        Criteria crItem = session.createCriteria(Item.class);
        itemId = itemDao.getId(crItem, model);
        seller = sellerDao.createSeller(name, phone);
        session.save(seller);
        Advertisement testAdvertisement = advertisementDao.createAdvertisement(price, seller, item);
        session.save(testAdvertisement);
        Criteria crAds = session.createCriteria(Advertisement.class);
        gainedAdvertisement = (Advertisement) advertisementDao.getAdsWithItem(crAds, itemId).get(0);
        assertEquals(price, gainedAdvertisement.getPrice());
        assertEquals(item, gainedAdvertisement.getItem());
        assertEquals(seller, gainedAdvertisement.getSeller());
    }

    @Test
    public void createGetAdsWithSeller() throws SQLException {
        item = itemDao.createItem(model);
        session.save(item);
        seller = sellerDao.createSeller(name, phone);
        session.save(seller);
        Criteria crSeller = session.createCriteria(Seller.class);
        sellerId = sellerDao.getId(crSeller, phone);
        advertisement = advertisementDao.createAdvertisement(price, seller, item);
        session.save(advertisement);
        Criteria crAds = session.createCriteria(Advertisement.class);
        gainedAdvertisement = (Advertisement) advertisementDao.getAdsWithSeller(crAds, sellerId).get(0);
        assertEquals(price, gainedAdvertisement.getPrice());
        assertEquals(item, gainedAdvertisement.getItem());
        assertEquals(seller, gainedAdvertisement.getSeller());
    }

    @Test
    public void createChangePrice() throws SQLException {
        item = itemDao.createItem(model);
        session.save(item);
        seller = sellerDao.createSeller(name, phone);
        session.save(seller);
        Criteria crSeller = session.createCriteria(Seller.class);
        sellerId = sellerDao.getId(crSeller, phone);
        advertisement = advertisementDao.createAdvertisement(price, seller, item);
        session.save(advertisement);
        Criteria crAds = session.createCriteria(Advertisement.class);
        gainedAdvertisement = (Advertisement) advertisementDao.getAdsWithSeller(crAds, sellerId).get(0);
        adsId = gainedAdvertisement.getId();
        advertisementDao.changePrice(crAds, adsId, newPrice);
        session.update(gainedAdvertisement);
        gainedAdvertisement = (Advertisement) advertisementDao.getAdsWithSeller(crAds, sellerId).get(0);
        assertEquals(gainedAdvertisement.getPrice(), newPrice);
    }

    @Test
    public void createGetItem() throws SQLException {
        Criteria crItem = session.createCriteria(Item.class);
        item = itemDao.createItem(model);
        session.save(item);
        itemId = itemDao.getId(crItem, model);
        gainedItem = itemDao.getItem(crItem, itemId);
        assertEquals(model, gainedItem.getModel());
    }

    @Test
    public void createGetSeller() throws SQLException {
        Criteria crSeller = session.createCriteria(Seller.class);
        seller = sellerDao.createSeller(name, phone);
        session.save(seller);
        sellerId = sellerDao.getId(crSeller, phone);
        gainedSeller = sellerDao.getSeller(crSeller, sellerId);
        assertEquals(phone, gainedSeller.getPhone());
        assertEquals(name, gainedSeller.getName());
    }

}
