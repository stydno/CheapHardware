package org.stydno.cheaphardware.dao;

import java.sql.SQLException;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.stydno.cheaphardware.tables.Seller;

@Repository
public class SellerDao {

    Seller seller;

    public Seller createSeller(String sellerName, String sellerPhone) throws SQLException {
        seller = new Seller(sellerName, sellerPhone);
        return seller;
    }

    public Seller getSeller(Criteria cr, Integer sellerId) throws SQLException {
        cr.add(Restrictions.like("sellerId", sellerId));
        return (Seller) cr.list().get(0);
    }

    Integer getId(Criteria cr, String sellerPhone) {
        cr.add(Restrictions.ilike("sellerPhone", sellerPhone));
        if (cr.list().isEmpty() == true) {
            return 0;
        } else {
            seller = (Seller) cr.list().get(0);
            return seller.getId();
        }
    }

}
