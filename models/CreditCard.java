package models;
import transactions.*;
import utils.*;
import java.io.Serializable;
public class CreditCard implements Serializable {
    private long cardNo;
    private String holderName;
    private String expDate;
    private boolean active;
    public CreditCard(long cardNo, String holderName, String expDate) {
        this.cardNo = cardNo; this.holderName = holderName; this.expDate = expDate; this.active = true;
    }
    public String getCardHolderName() { return holderName; }
    public void setActivate_deactivate(boolean a) { this.active = a; }
}
