package org.stydno.cheaphardware.dao;

import org.stydno.cheaphardware.utils.HibernateUtil;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import java.sql.SQLException;
import java.util.List;
import org.stydno.cheaphardware.tables.Advertisement;
import org.stydno.cheaphardware.tables.Item;
import org.stydno.cheaphardware.tables.Seller;

@Repository
public class Dao {

    private Session session;
    Item item;
    Seller seller;
    Advertisement advertisement;
    List<Advertisement> ads;
    Integer id;
    @Autowired
    ItemDao itemDao;
    @Autowired
    AdvertisementDao advertisementDao;
    @Autowired
    SellerDao sellerDao;

    public void createHibernateSession() {
        session = HibernateUtil.getSessionFactory().openSession();
    }

    public void closeHibernateSession() {
        session.close();
    }

    public void createSeller(String sellerName, String sellerPhone) {
        try {
            session.getTransaction().begin();
            seller = sellerDao.createSeller(sellerName, sellerPhone);
            session.save(seller);
            session.getTransaction().commit();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public Seller getSeller(Integer id) {
        try {
            session.getTransaction().begin();
            Criteria cr = session.createCriteria(Seller.class);
            seller = sellerDao.getSeller(cr, id);
            session.getTransaction().commit();
            return seller;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }

    public Item getItem(Integer id) {
        try {
            session.getTransaction().begin();
            Criteria cr = session.createCriteria(Item.class);
            item = itemDao.getItem(cr, id);
            session.getTransaction().commit();
            return item;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }

    public Integer getItemId(String model) {
        session.getTransaction().begin();
        Criteria cr = session.createCriteria(Item.class);
        Integer id = itemDao.getId(cr, model);
        session.getTransaction().commit();
        return id;
    }

    public Integer getSellerId(String sellerPhone) {
        session.getTransaction().begin();
        Criteria cr = session.createCriteria(Seller.class);
        id = sellerDao.getId(cr, sellerPhone);
        session.getTransaction().commit();
        return id;
    }

    public List getAdsWithSeller(Integer sellerId) {
        session.getTransaction().begin();
        Criteria cr = session.createCriteria(Advertisement.class);
        ads = advertisementDao.getAdsWithSeller(cr, sellerId);
        session.getTransaction().commit();
        return ads;
    }

    public List getAdsWithItem(Integer itemId) {
        session.getTransaction().begin();
        Criteria cr = session.createCriteria(Advertisement.class);
        ads = advertisementDao.getAdsWithItem(cr, itemId);
        session.getTransaction().commit();
        return ads;
    }

    public void deleteAds(Integer AdsId) {
        session.getTransaction().begin();
        Criteria cr = session.createCriteria(Advertisement.class);
        Advertisement ad = (Advertisement) advertisementDao.deleteAds(cr, AdsId);
        
        session.delete(advertisementDao.deleteAds(cr, AdsId));
        session.getTransaction().commit();
    }

    public void changePrice(Integer adsId, Integer price) {
        session.getTransaction().begin();
        Criteria cr = session.createCriteria(Advertisement.class);
        advertisementDao.changePrice(cr, adsId, price);
        session.getTransaction().commit();
    }

    public void createAdvertisement(Integer price, Seller seller, Item item) {
        session.getTransaction().begin();
        advertisement = advertisementDao.createAdvertisement(price, seller, item);
        session.save(advertisement);
        session.getTransaction().commit();

    }

    public void createItem(String model) {
        try {
            session.getTransaction().begin();
            item = itemDao.createItem(model);
            session.save(item);
            session.getTransaction().commit();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public List getAds() {
        session.getTransaction().begin();
        Criteria cr = session.createCriteria(Advertisement.class);
        ads = advertisementDao.getAds(cr);
        session.getTransaction().commit();
        return ads;
    }

}
