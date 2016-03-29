package org.stydno.cheaphardware.tables;

import java.util.List;
import javax.persistence.*;

@Entity
@Table(name = "seller")
public class Seller {

    @Id
    @Column(name = "SELLER_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer sellerId;
    @Column(name = "SELLER_NAME")
    private String sellerName;
    @Column(name = "SELLER_PHONE")
    private String sellerPhone;
    
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "seller")
    private List<Advertisement> advertisements;

    public Seller() {
    }

    public Seller(String sellerName, String sellerPhone) {
        this.sellerPhone = sellerPhone;
        this.sellerName = sellerName;
    }

    public void setId(Integer sellerId) {
        this.sellerId = sellerId;
    }

    public void setName(String sellerName) {
        this.sellerName = sellerName;
    }

    public void setPhone(String sellerPhone) {
        this.sellerPhone = sellerPhone;
    }

    public void setAdvertisement(List<Advertisement> advertisements) {
        this.advertisements = advertisements;
    }

    public Integer  getId() {
        return sellerId;
    }

    public String getName() {
        return sellerName;
    }

    public String getPhone() {
        return sellerPhone;
    }

    public List<Advertisement> getAdvertisement() {
        return advertisements;
    }

}
