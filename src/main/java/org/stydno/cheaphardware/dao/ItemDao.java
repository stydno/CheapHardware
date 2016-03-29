package org.stydno.cheaphardware.dao;

import java.sql.SQLException;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.stydno.cheaphardware.tables.Item;

@Repository
public class ItemDao {

    Item item;

    public Item createItem(String model) throws SQLException {
        item = new Item(model);
        return item;
    }

    public Item getItem(Criteria cr, Integer itemId) throws SQLException {
        int a = itemId;
        cr.add(Restrictions.like("itemId", a));
        return (Item) cr.list().get(0);
    }

    Integer getId(Criteria cr, String model) {
        cr.add(Restrictions.ilike("model", model));
        if (cr.list().isEmpty() == true) {
            return 0;
        }
        item = (Item) cr.list().get(0);
        return item.getId();
    }

}
