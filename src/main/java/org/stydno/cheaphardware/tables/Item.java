package org.stydno.cheaphardware.tables;

import java.util.List;
import javax.persistence.*;

@Entity
@Table(name = "item")
public class Item {

    @Id
    @Column(name = "ITEM_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer itemId;
    @Column(name = "ITEM_MODEL")
    private String model;
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "item")
    private List<Advertisement> advertisements;

    public Item() {
    }

    public Item(String model) {
        this.model = model;
    }

    public void setId(Integer id) {
        this.itemId = id;
    }

    public void setModel(String model) {
        this.model = model;
    }


    public void setAdvertisement(List<Advertisement> advertisements) {
        this.advertisements = advertisements;
    }

    public Integer getId() {
        return itemId;
    }

    public String getModel() {
        return model;
    }

    public List<Advertisement> getAdvertisement() {
        return advertisements;
    }

}
