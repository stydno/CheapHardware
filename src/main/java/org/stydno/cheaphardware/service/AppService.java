package org.stydno.cheaphardware.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.stydno.cheaphardware.dao.Dao;
import org.stydno.cheaphardware.tables.Advertisement;

@Service(value = "service")
public class AppService {

    Integer itemId;
    Integer sellerId;
    Integer tempId;
    Integer zero = new Integer(0);
    @Autowired
    private Dao dao;

    public void createSession() {
        dao.createHibernateSession();
    }

    public void closeSession() {
        dao.closeHibernateSession();
    }

    public void createAdvertisement(Integer price, String sellerName, String sellerPhone, String model) {
        dao.createHibernateSession();
        if ((tempId = dao.getItemId(model)) == 0) {
            dao.createItem(model);
            itemId = dao.getItemId(model);
        } else {
            itemId = tempId;
        }

        if ((tempId = dao.getSellerId(sellerPhone)) == 0) {
            dao.createSeller(sellerName, sellerPhone);
            sellerId = dao.getSellerId(sellerPhone);
        } else {
            sellerId = tempId;
        }
        System.out.println(sellerId);
        System.out.println(itemId);
        dao.createAdvertisement(price, dao.getSeller(sellerId), dao.getItem(itemId));
        dao.closeHibernateSession();
    }

    public List getAds() {
        dao.createHibernateSession();
        List<Advertisement> ads = dao.getAds();
        dao.closeHibernateSession();
        return ads;
    }

    public List getAdsWithSeller(Integer sellerId) {
        dao.createHibernateSession();
        List ads = dao.getAdsWithSeller(sellerId);
        dao.closeHibernateSession();
        return ads;
    }

    public List getAdsWithSeller(String sellerName) {
        dao.createHibernateSession();
        List ads = dao.getAdsWithSeller(dao.getSellerId(sellerName));
        dao.closeHibernateSession();
        return ads;
    }

    public List getAdsWithItem(Integer itemId) {
        dao.createHibernateSession();
        List ads = dao.getAdsWithItem(itemId);
        dao.closeHibernateSession();
        return ads;
    }

    public List getAdsWithItem(String model) {
        dao.createHibernateSession();
        System.out.println(dao.getItemId(model));
        List ads = dao.getAdsWithItem(dao.getItemId(model));
        dao.closeHibernateSession();
        return ads;
    }

    public void changePrice(Integer adsId, Integer price) {
        dao.createHibernateSession();
        dao.changePrice(adsId, price);
        dao.closeHibernateSession();
    }

    public void deleteAds(Integer AdsId) {
        dao.createHibernateSession();
        dao.deleteAds(AdsId);
        dao.closeHibernateSession();
    }
}
