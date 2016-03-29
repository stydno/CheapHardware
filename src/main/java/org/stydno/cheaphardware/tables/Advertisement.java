package org.stydno.cheaphardware.tables;

import java.util.Date;
import javax.persistence.*;

@Entity
@Table(name = "ads")
public class Advertisement {

    @Id
    @Column(name = "ADS_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer adsId;
    @Column(name = "ADS_PRICE")
    private Integer price;
    @Column(name = "ADS_DATE")
    @Temporal(TemporalType.DATE)
    private Date adDate;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "SELLER_ID")
    private Seller seller;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ITEM_ID")
    private Item item;

    public Advertisement() {

    }

    public Advertisement(Integer price, Date adDate, Seller seller, Item item) {
        this.price = price;
        this.seller = seller;
        this.adDate = adDate;
        this.item = item;
    }

    public void setId(Integer adsId) {
        this.adsId = adsId;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public void setDate(Date adDate) {
        this.adDate = adDate;
    }

    public void setSeller(Seller seller) {
        this.seller = seller;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public Integer getId() {
        return adsId;
    }

    public Integer getPrice() {
        return price;
    }

    public Date getDate() {
        return adDate;
    }

    public Seller getSeller() {
        return seller;
    }

    public Item getItem() {
        return item;
    }

}
