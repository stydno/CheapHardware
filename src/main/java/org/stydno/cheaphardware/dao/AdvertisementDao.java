package org.stydno.cheaphardware.dao;

import java.util.Date;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.stydno.cheaphardware.tables.Advertisement;
import org.stydno.cheaphardware.tables.Item;
import org.stydno.cheaphardware.tables.Seller;

@Repository
public class AdvertisementDao {

    Advertisement ads;

    public Advertisement createAdvertisement(Integer price, Seller seller, Item item) {
        Date date = new Date();
        return new Advertisement(price, date, seller, item);
    }

    public void changePrice(Criteria cr, Integer adsId, Integer price) {
        cr.add(Restrictions.like("id", adsId));
        ads = (Advertisement) cr.list().get(0);
        ads.setPrice(price);
    }

    public List getAdsWithSeller(Criteria cr, Integer sellerId) {
        cr.add(Restrictions.like("seller.sellerId", sellerId));
        return cr.list();

    }

    public List getAdsWithItem(Criteria cr, Integer itemId) {
        cr.add(Restrictions.like("item.itemId", itemId));
        return cr.list();
    }

    public Object deleteAds(Criteria cr, Integer adsId) {
        cr.add(Restrictions.like("adsId", adsId));
        return cr.list().get(0);
    }

    public List getAds(Criteria cr) {
        cr.add(Restrictions.isNotNull("adsId"));
        return cr.list();
    }

}
