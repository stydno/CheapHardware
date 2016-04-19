package org.stydno.cheaphardware.dao;

import java.sql.SQLException;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.stydno.cheaphardware.tables.Item;

@Repository
public class ItemDao {

    List<Item> itemList;

    public Item createItem(String model) throws SQLException {
        return new Item(model);
    }

    public Item getItem(Criteria cr, Integer itemId) throws SQLException {
        int a = itemId;
        cr.add(Restrictions.like("itemId", a));
        return (Item) cr.list().get(0);
    }

    public Integer getId(Criteria cr, String model) {
        cr.add(Restrictions.ilike("model", model));
        itemList = (List<Item>) cr.list();
        if (itemList.isEmpty() == true) {
            return 0;
        }
        return itemList.get(0).getId();
    }

}
