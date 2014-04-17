package org.nobel.highriseapi.entities;

import java.util.Date;
import java.util.List;

import org.nobel.highriseapi.entities.base.EntityWithName;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;

public class Deal extends EntityWithName {

    public enum PriceType {
        fixed, hour, month, year
    }

    public enum Status {
        lost, pending, won;
    }

    private static final long serialVersionUID = 4575876434128342385L;

    @Element(name = "account-id",required = false)
    private long accountId;
    
    @Element(name = "author-id",required = false)
    private long authorId;
    
    @Element(required = false)
    private String background;

	@Element(required = false)
    private Category category;

	@Element(required = false)
    private String currency;
	
	@Element(required = false)
    private String duration;
	
	@Element(name = "party-id", required = false)
    private long partyId;

    @ElementList(name = "parties")
    private List<Party> parties;

    @Element(required = false)
    private Party party;

    @Element(required = false)
    private Integer price;

    @Element(name = "price-type", required = false)
    private PriceType priceType;
    
    @Element(name = "responsible-party-id", required = false)
    private long responsiblePartyId;
    
    @Element(name = "status-changed-on", required = false)
    private String statusChangedOn;
    
    @Element(name = "created-at", required = false)
    private Date createdAt;
    
    @Element(name = "updated-at", required = false)
    private Date updatedAt;

    @Element(required = false)
    private Status status;

    public String getBackground() {
        return background;
    }

    public org.nobel.highriseapi.entities.Category getCategory() {
        return category;
    }

    public String getCurrency() {
        return currency;
    }

    public List<Party> getParties() {
        return parties;
    }

    public Party getParty() {
        return party;
    }

    public Integer getPrice() {
        return price;
    }

    public PriceType getPriceType() {
        return priceType;
    }

    public Status getStatus() {
        return status;
    }

    public void setBackground(String background) {
        this.background = background;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public void setParties(List<Party> parties) {
        this.parties = parties;
    }

    public void setParty(Party party) {
        this.party = party;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public void setPriceType(PriceType priceType) {
        this.priceType = priceType;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	public long getAccountId() {
		return accountId;
	}

	public void setAccountId(long accountId) {
		this.accountId = accountId;
	}

	public long getAuthorId() {
		return authorId;
	}

	public void setAuthorId(long authorId) {
		this.authorId = authorId;
	}

	public long getPartyId() {
		return partyId;
	}

	public void setPartyId(long partyId) {
		this.partyId = partyId;
	}

	public long getResponsiblePartyId() {
		return responsiblePartyId;
	}

	public void setResponsiblePartyId(long responsiblePartyId) {
		this.responsiblePartyId = responsiblePartyId;
	}

	public String getStatusChangedOn() {
		return statusChangedOn;
	}

	public void setStatusChangedOn(String statusChangedOn) {
		this.statusChangedOn = statusChangedOn;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

}
