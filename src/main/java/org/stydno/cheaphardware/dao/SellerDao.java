package org.stydno.cheaphardware.dao;

import java.sql.SQLException;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.stydno.cheaphardware.tables.Seller;

@Repository
public class SellerDao {

    List<Seller> sellerList;

    public Seller createSeller(String sellerName, String sellerPhone) throws SQLException {
        return new Seller(sellerName, sellerPhone);
    }

    public Seller getSeller(Criteria cr, Integer sellerId) throws SQLException {
        cr.add(Restrictions.like("sellerId", sellerId));
        return (Seller) cr.list().get(0);
    }

    public Integer getId(Criteria cr, String sellerPhone) {
        cr.add(Restrictions.ilike("sellerPhone", sellerPhone));
        sellerList = (List<Seller>) cr.list();
        if (sellerList.isEmpty() == true) {
            return 0;
        } else {
            return sellerList.get(0).getId();
        }
    }

}
